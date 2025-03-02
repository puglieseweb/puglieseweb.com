# Oracle to PostgreSQL Time-Window Data Reload Implementation Guide

### Overview

This guide walks through implementing a solution for reloading data from a specific time window from Oracle to PostgreSQL while maintaining ongoing CDC (Change Data Capture) replication.

### Prerequisites

* AWS DMS (Database Migration Service) set up with:
  * Source Oracle endpoint configured
  * Target PostgreSQL endpoint configured
  * Replication instance with adequate capacity
* Access to both Oracle and PostgreSQL databases with proper permissions
* AWS CLI installed and configured on your management workstation
* Basic knowledge of SQL, PostgreSQL, and Oracle database concepts

### Step 1: Prepare Target PostgreSQL Environment

1.  **Create Staging Schema and Tables**:

    ```sql
    -- Connect to your PostgreSQL database
    psql -h <postgres-host> -p <postgres-port> -U <username> -d <database>

    -- Create staging schema
    CREATE SCHEMA IF NOT EXISTS staging;

    -- Create staging tables mirroring your production tables
    CREATE TABLE staging.customers (
        customer_id INTEGER PRIMARY KEY,
        name VARCHAR(100),
        email VARCHAR(100),
        status VARCHAR(20),
        created_at TIMESTAMP,
        updated_at TIMESTAMP,
        _dms_processing_status VARCHAR(20) DEFAULT 'NEW'
    );

    CREATE TABLE staging.orders (
        order_id INTEGER PRIMARY KEY,
        customer_id INTEGER,
        amount NUMERIC(10,2),
        order_date TIMESTAMP,
        updated_at TIMESTAMP,
        _dms_processing_status VARCHAR(20) DEFAULT 'NEW'
    );

    -- Create indexes for optimization
    CREATE INDEX idx_customers_updated_at ON staging.customers(updated_at);
    CREATE INDEX idx_orders_updated_at ON staging.orders(updated_at);

    -- Create admin schema for logging and monitoring
    CREATE SCHEMA IF NOT EXISTS admin;

    -- Create log tables
    CREATE TABLE admin.merge_logs (
        id SERIAL PRIMARY KEY,
        table_name VARCHAR(100),
        merge_time TIMESTAMP,
        records_updated INT,
        records_inserted INT
    );

    CREATE TABLE admin.merge_summary (
        id SERIAL PRIMARY KEY,
        merge_start TIMESTAMP,
        merge_end TIMESTAMP,
        status VARCHAR(20),
        notes TEXT
    );
    ```
2.  **Create Merge Procedure**:

    ```sql
    CREATE OR REPLACE PROCEDURE merge_time_window_data()
    LANGUAGE plpgsql
    AS $$
    DECLARE
        merge_start_time TIMESTAMP;
        merge_end_time TIMESTAMP;
        records_updated INT;
        records_inserted INT;
    BEGIN
        -- Record start time for logging
        merge_start_time := CURRENT_TIMESTAMP;
        
        -- Begin transaction
        BEGIN
            -- Merge customers table
            WITH updated_rows AS (
                UPDATE production.customers p
                SET 
                    name = s.name,
                    email = s.email,
                    status = s.status,
                    updated_at = s.updated_at
                FROM staging.customers s
                WHERE p.customer_id = s.customer_id
                AND s.updated_at > p.updated_at
                RETURNING p.customer_id
            )
            SELECT COUNT(*) INTO records_updated FROM updated_rows;
            
            -- Insert new records
            WITH inserted_rows AS (
                INSERT INTO production.customers
                SELECT 
                    s.customer_id, s.name, s.email, s.status, s.created_at, s.updated_at
                FROM staging.customers s
                LEFT JOIN production.customers p ON s.customer_id = p.customer_id
                WHERE p.customer_id IS NULL
                RETURNING 1
            )
            SELECT COUNT(*) INTO records_inserted FROM inserted_rows;
            
            -- Log merge results
            INSERT INTO admin.merge_logs
            (table_name, merge_time, records_updated, records_inserted)
            VALUES ('customers', CURRENT_TIMESTAMP, records_updated, records_inserted);
            
            -- Repeat for orders table
            WITH updated_orders AS (
                UPDATE production.orders p
                SET 
                    customer_id = s.customer_id,
                    amount = s.amount,
                    order_date = s.order_date,
                    updated_at = s.updated_at
                FROM staging.orders s
                WHERE p.order_id = s.order_id
                AND s.updated_at > p.updated_at
                RETURNING p.order_id
            )
            SELECT COUNT(*) INTO records_updated FROM updated_orders;
            
            WITH inserted_orders AS (
                INSERT INTO production.orders
                SELECT 
                    s.order_id, s.customer_id, s.amount, s.order_date, s.updated_at
                FROM staging.orders s
                LEFT JOIN production.orders p ON s.order_id = p.order_id
                WHERE p.order_id IS NULL
                RETURNING 1
            )
            SELECT COUNT(*) INTO records_inserted FROM inserted_orders;
            
            INSERT INTO admin.merge_logs
            (table_name, merge_time, records_updated, records_inserted)
            VALUES ('orders', CURRENT_TIMESTAMP, records_updated, records_inserted);
            
            -- Mark all staging records as processed
            UPDATE staging.customers 
            SET _dms_processing_status = 'PROCESSED';
            
            UPDATE staging.orders 
            SET _dms_processing_status = 'PROCESSED';
            
            -- Commit transaction
            COMMIT;
            
            merge_end_time := CURRENT_TIMESTAMP;
            
            -- Log overall merge statistics
            INSERT INTO admin.merge_summary
            (merge_start, merge_end, status, notes)
            VALUES (merge_start_time, merge_end_time, 'SUCCESS', 
                    'Merged Oracle data from time window');
                    
        EXCEPTION WHEN OTHERS THEN
            -- Roll back transaction on error
            ROLLBACK;
            
            -- Log error
            INSERT INTO admin.merge_summary
            (merge_start, merge_end, status, notes)
            VALUES (merge_start_time, CURRENT_TIMESTAMP, 'FAILED', 
                    'Error: ' || SQLERRM);
                    
            -- Re-raise exception
            RAISE;
        END;
    END;
    $$;
    ```
3.  **Create Monitoring Views**:

    ```sql
    CREATE OR REPLACE VIEW admin.oracle_pg_migration_status AS
    SELECT 
        'customers' AS table_name,
        COUNT(*) AS total_records,
        SUM(CASE WHEN _dms_processing_status = 'NEW' THEN 1 ELSE 0 END) AS pending_records,
        SUM(CASE WHEN _dms_processing_status = 'PROCESSED' THEN 1 ELSE 0 END) AS processed_records,
        SUM(CASE WHEN _dms_processing_status = 'ERROR' THEN 1 ELSE 0 END) AS error_records
    FROM staging.customers
    UNION ALL
    SELECT 
        'orders' AS table_name,
        COUNT(*) AS total_records,
        SUM(CASE WHEN _dms_processing_status = 'NEW' THEN 1 ELSE 0 END) AS pending_records,
        SUM(CASE WHEN _dms_processing_status = 'PROCESSED' THEN 1 ELSE 0 END) AS processed_records,
        SUM(CASE WHEN _dms_processing_status = 'ERROR' THEN 1 ELSE 0 END) AS error_records
    FROM staging.orders;
    ```

### Step 2: Configure DMS Time-Window Migration

1.  **Create DMS Task Settings File**: Create a file named `oracle-pg-task-settings.json` with the following content:

    ```json
    {
      "TargetMetadata": {
        "TargetSchema": "staging",
        "SupportLobs": true,
        "FullLobMode": false,
        "LobChunkSize": 64,
        "LimitedSizeLobMode": true,
        "LobMaxSize": 32
      },
      "FullLoadSettings": {
        "TargetTablePrepMode": "DO_NOTHING",
        "CreatePkAfterFullLoad": false,
        "StopTaskCachedChangesApplied": false,
        "StopTaskCachedChangesNotApplied": false,
        "MaxFullLoadSubTasks": 8,
        "TransactionConsistencyTimeout": 600,
        "CommitRate": 10000
      },
      "Logging": {
        "EnableLogging": true,
        "LogComponents": [
          {
            "Id": "SOURCE_UNLOAD",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          },
          {
            "Id": "TARGET_LOAD",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          },
          {
            "Id": "TRANSFORMATION",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          }
        ]
      },
      "OracleSettings": {
        "ReadTableSpaceName": false,
        "EnableHomogenousTablespace": false,
        "StandbyDelayTime": 0,
        "ArchivedLogsOnly": false,
        "ArchivedLogDestId": 0,
        "UsePathPrefix": "",
        "UseLogminerReader": true,
        "SecurityDbEncryption": false,
        "SecurityDbEncryptionName": "",
        "DirectPathNoLog": false,
        "AllowSelectNestedTables": true,
        "ConvertTimestampWithZoneToUTC": true,
        "NumberDataTypeScale": 38,
        "AsmServer": "",
        "CharLengthSemantics": "CHAR",
        "AddSupplementalLogging": true,
        "ReadAheadBlocks": 1000
      }
    }
    ```
2.  **Create Table Mappings File**: Create a file named `oracle-pg-time-window-mapping.json` with the following content (adjust the date range as needed):

    ```json
    {
      "rules": [
        {
          "rule-type": "selection",
          "rule-id": "1",
          "rule-name": "customers-time-window",
          "object-locator": {
            "schema-name": "SOURCE_SCHEMA",
            "table-name": "CUSTOMERS"
          },
          "rule-action": "include",
          "filters": [
            {
              "filter-type": "source",
              "column-name": "UPDATED_AT",
              "filter-conditions": [
                {
                  "filter-operator": "gte",
                  "value": "2025-01-01 00:00:00"
                },
                {
                  "filter-operator": "lte",
                  "value": "2025-01-31 23:59:59"
                }
              ]
            }
          ]
        },
        {
          "rule-type": "selection",
          "rule-id": "2",
          "rule-name": "orders-time-window",
          "object-locator": {
            "schema-name": "SOURCE_SCHEMA",
            "table-name": "ORDERS"
          },
          "rule-action": "include",
          "filters": [
            {
              "filter-type": "source",
              "column-name": "UPDATED_AT",
              "filter-conditions": [
                {
                  "filter-operator": "gte",
                  "value": "2025-01-01 00:00:00"
                },
                {
                  "filter-operator": "lte",
                  "value": "2025-01-31 23:59:59"
                }
              ]
            }
          ]
        },
        {
          "rule-type": "transformation",
          "rule-id": "3",
          "rule-name": "convert-oracle-timestamp",
          "rule-action": "convert-column-type",
          "rule-target": "column",
          "object-locator": {
            "schema-name": "SOURCE_SCHEMA",
            "table-name": "%",
            "column-name": "%_AT"
          },
          "data-type": {
            "type": "datetime",
            "precision": 0,
            "scale": 0
          }
        },
        {
          "rule-type": "transformation",
          "rule-id": "4",
          "rule-name": "convert-oracle-number",
          "rule-action": "convert-column-type",
          "rule-target": "column",
          "object-locator": {
            "schema-name": "SOURCE_SCHEMA",
            "table-name": "ORDERS",
            "column-name": "AMOUNT"
          },
          "data-type": {
            "type": "numeric",
            "precision": 10,
            "scale": 2
          }
        }
      ]
    }
    ```

### Step 3: Create and Run the Orchestration Script

1.  **Create the Orchestration Script**: Create a file named `oracle-pg-time-window-reload.sh` with the following content:

    ```bash
    #!/bin/bash

    # Configuration - Update these values
    SOURCE_ENDPOINT_ARN="arn:aws:dms:region:account:endpoint:oracle-source-endpoint"
    TARGET_ENDPOINT_ARN="arn:aws:dms:region:account:endpoint:postgres-target-endpoint"
    REPLICATION_INSTANCE_ARN="arn:aws:dms:region:account:rep:replication-instance-id"
    TABLE_MAPPINGS_FILE="oracle-pg-time-window-mapping.json"
    TASK_SETTINGS_FILE="oracle-pg-task-settings.json"
    START_DATE="2025-01-01T00:00:00"
    END_DATE="2025-01-31T23:59:59"
    PG_HOST="your-postgres-host"
    PG_PORT="5432"
    PG_NAME="your-postgres-db"
    PG_USER="your-postgres-user"
    PG_PASSWORD="your-postgres-password"

    echo "Starting Oracle to PostgreSQL time-window data reload from $START_DATE to $END_DATE"

    # Step 1: Create the migration task
    TASK_ARN=$(aws dms create-replication-task \
      --replication-task-identifier "oracle-pg-historical-reload-$(date +%Y%m%d%H%M%S)" \
      --source-endpoint-arn $SOURCE_ENDPOINT_ARN \
      --target-endpoint-arn $TARGET_ENDPOINT_ARN \
      --replication-instance-arn $REPLICATION_INSTANCE_ARN \
      --migration-type "full-load" \
      --table-mappings file://$TABLE_MAPPINGS_FILE \
      --replication-task-settings file://$TASK_SETTINGS_FILE \
      --tags Key=Purpose,Value=OraclePgHistoricalReload Key=TimeWindow,Value="${START_DATE}-${END_DATE}" \
      --query 'ReplicationTask.ReplicationTaskArn' \
      --output text)

    echo "Created Oracle to PostgreSQL replication task: $TASK_ARN"

    # Step 2: Start the task and wait for completion
    aws dms start-replication-task \
      --replication-task-arn $TASK_ARN \
      --start-replication-task-type start-replication

    echo "Task started. Waiting for completion..."

    # Poll for task completion
    while true; do
      STATUS=$(aws dms describe-replication-tasks \
        --filters Name=replication-task-arn,Values=$TASK_ARN \
        --query 'ReplicationTasks[0].Status' \
        --output text)
      
      PERCENT=$(aws dms describe-replication-tasks \
        --filters Name=replication-task-arn,Values=$TASK_ARN \
        --query 'ReplicationTasks[0].ReplicationTaskStats.FullLoadProgressPercent' \
        --output text || echo "N/A")
      
      echo "Current status: $STATUS - $PERCENT% completed"
      
      if [ "$STATUS" == "stopped" ] || [ "$STATUS" == "failed" ]; then
        break
      fi
      
      sleep 120
    done

    # Step 3: Check if task completed successfully
    if [ "$STATUS" == "stopped" ]; then
      echo "Oracle to PostgreSQL historical data migration completed successfully"
      
      # Step 4: Run the merge procedure
      echo "Starting data merge process..."
      PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_NAME -c "CALL merge_time_window_data();"
      
      if [ $? -eq 0 ]; then
        echo "Data merge completed successfully"
      else
        echo "Data merge failed"
        exit 1
      fi
    else
      echo "Historical data migration failed"
      exit 1
    fi

    # Step 5: Clean up staging data (optional)
    read -p "Do you want to clean up staging tables? (y/n) " CLEANUP
    if [ "$CLEANUP" == "y" ]; then
      echo "Cleaning up staging tables..."
      PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_NAME -c "TRUNCATE TABLE staging.customers; TRUNCATE TABLE staging.orders;"
    fi

    echo "Oracle to PostgreSQL time-window data reload process completed"
    ```
2.  **Make the Script Executable**:

    ```bash
    chmod +x oracle-pg-time-window-reload.sh
    ```
3.  **Run the Orchestration Script**:

    ```bash
    ./oracle-pg-time-window-reload.sh
    ```

### Step 4: Monitor and Validate the Process

1.  **Check Migration Status**:

    ```sql
    -- Connect to PostgreSQL
    psql -h <postgres-host> -p <postgres-port> -U <username> -d <database>

    -- Check migration status
    SELECT * FROM admin.oracle_pg_migration_status;
    ```
2.  **View Merge Logs**:

    ```sql
    -- Check detailed merge logs
    SELECT * FROM admin.merge_logs ORDER BY merge_time DESC;

    -- Check merge summary
    SELECT * FROM admin.merge_summary ORDER BY merge_start DESC;
    ```
3.  **Check for Data Type Issues**:

    ```sql
    -- Create a verification view (if not already created)
    CREATE OR REPLACE VIEW admin.oracle_pg_type_verification AS
    WITH target_columns AS (
        SELECT 
            t.table_schema,
            t.table_name,
            c.column_name,
            c.data_type,
            c.character_maximum_length,
            c.numeric_precision,
            c.numeric_scale
        FROM 
            information_schema.tables t
        JOIN 
            information_schema.columns c ON t.table_schema = c.table_schema AND t.table_name = c.table_name
        WHERE 
            t.table_schema = 'production'
            AND t.table_type = 'BASE TABLE'
    ),
    staging_columns AS (
        SELECT 
            t.table_schema,
            t.table_name,
            c.column_name,
            c.data_type,
            c.character_maximum_length,
            c.numeric_precision,
            c.numeric_scale
        FROM 
            information_schema.tables t
        JOIN 
            information_schema.columns c ON t.table_schema = c.table_schema AND t.table_name = c.table_name
        WHERE 
            t.table_schema = 'staging'
            AND t.table_type = 'BASE TABLE'
    )
    SELECT
        t.table_name,
        t.column_name,
        t.data_type AS target_data_type,
        s.data_type AS staging_data_type,
        CASE
            WHEN t.data_type <> s.data_type THEN 'DATA TYPE MISMATCH'
            WHEN t.character_maximum_length <> s.character_maximum_length AND t.character_maximum_length IS NOT NULL THEN 'LENGTH MISMATCH'
            WHEN t.numeric_precision <> s.numeric_precision AND t.numeric_precision IS NOT NULL THEN 'PRECISION MISMATCH'
            WHEN t.numeric_scale <> s.numeric_scale AND t.numeric_scale IS NOT NULL THEN 'SCALE MISMATCH'
            ELSE 'OK'
        END AS status
    FROM
        target_columns t
    JOIN
        staging_columns s ON t.table_name = s.table_name AND t.column_name = s.column_name
    WHERE
        t.data_type <> s.data_type
        OR (t.character_maximum_length <> s.character_maximum_length AND t.character_maximum_length IS NOT NULL)
        OR (t.numeric_precision <> s.numeric_precision AND t.numeric_precision IS NOT NULL)
        OR (t.numeric_scale <> s.numeric_scale AND t.numeric_scale IS NOT NULL);

    -- Check for type issues
    SELECT * FROM admin.oracle_pg_type_verification;
    ```

### Step 5: Troubleshooting Common Issues

1. **DMS Task Failures**:
   * Check CloudWatch logs for detailed error messages
   * Verify Oracle supplemental logging is enabled for all source tables
   * Ensure PostgreSQL target has sufficient disk space
   * Validate that all Oracle data types are properly mapped to PostgreSQL equivalents
2. **Data Type Conversion Issues**:
   * Oracle NUMBER to PostgreSQL NUMERIC may require explicit precision/scale definitions
   * Oracle DATE/TIMESTAMP handling might need special conversion rules
   * Character encoding differences between databases might cause string conversion issues
3. **Merge Procedure Errors**:
   * Check admin.merge\_summary for specific error messages
   * Verify that primary keys are defined correctly in both staging and production tables
   * Ensure there are no constraint violations during the merge process

### Step 6: Finalizing the Process

1. **Cleanup Tasks**:
   * After successful verification, truncate staging tables if needed
   * Consider deleting the one-time DMS task to save resources
   * Archive logs for audit purposes
2. **Documentation**:
   * Document the entire process, including any issues encountered and resolutions
   * Record the time window that was reloaded for future reference
   * Note any data type conversion or mapping decisions made

### Additional Considerations

1. **Performance Optimization**:
   * Increase DMS `MaxFullLoadSubTasks` for larger tables
   * Adjust PostgreSQL configuration for better bulk load performance (increase `work_mem`, `maintenance_work_mem`, etc.)
   * Consider temporarily disabling indexes during the load process
2. **Parallel Ongoing CDC**:
   * Ensure your continuous CDC task doesn't conflict with the time-window reload
   * Monitor both processes carefully for any signs of interference
3. **Recovery Options**:
   * Create a backup of the target tables before running the merge process
   * Have a rollback plan in case of issues during the merge

This implementation guide provides a comprehensive approach to selectively reload time-window data from Oracle to PostgreSQL while maintaining ongoing CDC replication.
