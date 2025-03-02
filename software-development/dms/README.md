# DMS

## Reload data from a specific time windows in the past

DMS isn't designed for time-windowed selective reloads while maintaining ongoing CDC. The challenge is that CDC works sequentially from transaction logs, and introducing historical data without creating duplicates or inconsistencies requires careful coordination.

The `ReloadTables` API in AWS DMS doesn't natively support reloading data for a specific time window in the past while CDC is ongoing. It's designed to perform a complete reload of the specified tables, not a targeted time-based refresh.

If this is a critical requirement, we need to implement a custom solution using a combination of DMS tasks and application-specific data reconciliation logic. To achieve this we have a few alternative approaches:

1. **Custom Solution with Staging Tables**:
   * Create staging tables in your target database
   * Use a separate DMS task to load historical data (from the time window) into these staging tables
   * Write custom merge logic to incorporate this historical data maintaining the current CDC stream
   * This keeps your ongoing CDC undisturbed
2. **Adjust CDC Start Position**:
   * If we want to "replay" changes from a specific point in time, we could:
     * Create a new task that starts CDC from your desired historical point
     * Direct this to a separate target schema/database
     * Merge the results after completion
3. **Backup/Snapshot Restoration**:
   * Restore a backup/snapshot from your desired time point to a separate environment
   * Migrate just the data you need from this point-in-time copy
   * Merge with your current state
4. **AWS Database Migration Assessment**:
   * Run data validation to identify specific records that differ between source and target
   * Selectively reload just those records rather than entire tables



## **Custom Solution with Staging Tables**

#### Key Requirements:

* No interruption to ongoing CDC replication
* Transactional consistency during merges
* Selective time-based data refresh
* Full audit trail of changes



For migrating from Oracle to PostgreSQL with a time-window data reload:

### Oracle to PostgreSQL Migration with Time-Window Data Reload

Oracle to PostgreSQL Migration with Time-Window Data ReloadEdited:

```sql
-- Step 1: Create staging tables in PostgreSQL to mirror your Oracle tables
-- Postgres syntax for staging tables
CREATE SCHEMA IF NOT EXISTS staging;

CREATE TABLE staging.customers (
    -- Schema adjusted for PostgreSQL from Oracle
    customer_id INTEGER PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    status VARCHAR(20),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    
    -- Processing metadata column
    _dms_processing_status VARCHAR(20) DEFAULT 'NEW'
);

CREATE TABLE staging.orders (
    order_id INTEGER PRIMARY KEY,
    customer_id INTEGER,
    amount NUMERIC(10,2),  -- NUMERIC instead of Oracle's NUMBER
    order_date TIMESTAMP,
    updated_at TIMESTAMP,
    
    _dms_processing_status VARCHAR(20) DEFAULT 'NEW'
);

-- Create indexes for merge performance
CREATE INDEX idx_customers_updated_at ON staging.customers(updated_at);
CREATE INDEX idx_orders_updated_at ON staging.orders(updated_at);

-- Step 2: DMS Task Configuration for Oracle to PostgreSQL with time window filter
-- Save as oracle-pg-time-window-mapping.json
/*
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
*/

-- Step 3: Create merge procedure in PostgreSQL
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
    
    -- Create log tables if they don't exist
    CREATE TABLE IF NOT EXISTS admin.merge_logs (
        id SERIAL PRIMARY KEY,
        table_name VARCHAR(100),
        merge_time TIMESTAMP,
        records_updated INT,
        records_inserted INT
    );
    
    CREATE TABLE IF NOT EXISTS admin.merge_summary (
        id SERIAL PRIMARY KEY,
        merge_start TIMESTAMP,
        merge_end TIMESTAMP,
        status VARCHAR(20),
        notes TEXT
    );
    
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
                'Merged Oracle data from time window: 2025-01-01 to 2025-01-31');
                
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

-- Step 4: Oracle to PostgreSQL DMS task orchestration script
-- Save as oracle-pg-time-window-reload.sh
/*
#!/bin/bash

# Configuration
SOURCE_ENDPOINT_ARN="arn:aws:dms:region:account:endpoint:oracle-source-endpoint"
TARGET_ENDPOINT_ARN="arn:aws:dms:region:account:endpoint:postgres-target-endpoint"
REPLICATION_INSTANCE_ARN="arn:aws:dms:region:account:rep:replication-instance-id"
TABLE_MAPPINGS_FILE="oracle-pg-time-window-mapping.json"
START_DATE="2025-01-01T00:00:00"
END_DATE="2025-01-31T23:59:59"
PG_HOST="your-postgres-host"
PG_PORT="5432"
PG_NAME="your-postgres-db"
PG_USER="your-postgres-user"
PG_PASSWORD="your-postgres-password"

echo "Starting Oracle to PostgreSQL time-window data reload from $START_DATE to $END_DATE"

# Step 1: Create task settings with Oracle-specific options
cat > oracle-pg-task-settings.json << EOF
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
    "AdditionalArchivedLogDestId": null,
    "AddSupplementalLogging": true,
    "RedoLogsFolderPath": null,
    "ReadAheadBlocks": 1000,
    "AccessAlternateDirectly": false,
    "UseAlternateFolderForOnline": false,
    "OraclePathPrefix": "",
    "UsePathPrefixForArchived": false,
    "EnableHomogenousPartitionOps": false,
    "ReplicateView": false,
    "SpatialConfig": null
  }
}
EOF

# Step 2: Create the migration task
TASK_ARN=$(aws dms create-replication-task \
  --replication-task-identifier "oracle-pg-historical-reload-$(date +%Y%m%d%H%M%S)" \
  --source-endpoint-arn $SOURCE_ENDPOINT_ARN \
  --target-endpoint-arn $TARGET_ENDPOINT_ARN \
  --replication-instance-arn $REPLICATION_INSTANCE_ARN \
  --migration-type "full-load" \
  --table-mappings file://$TABLE_MAPPINGS_FILE \
  --replication-task-settings file://oracle-pg-task-settings.json \
  --tags Key=Purpose,Value=OraclePgHistoricalReload Key=TimeWindow,Value="${START_DATE}-${END_DATE}" \
  --query 'ReplicationTask.ReplicationTaskArn' \
  --output text)

echo "Created Oracle to PostgreSQL replication task: $TASK_ARN"

# Step 3: Start the task and wait for completion
aws dms start-replication-task \
  --replication-task-arn $TASK_ARN \
  --start-replication-task-type start-replication

echo "Task started. Waiting for completion..."

# Poll for task completion (Oracle migrations may take longer)
while true; do
  STATUS=$(aws dms describe-replication-tasks \
    --filters Name=replication-task-arn,Values=$TASK_ARN \
    --query 'ReplicationTasks[0].Status' \
    --output text)
  
  PERCENT=$(aws dms describe-replication-tasks \
    --filters Name=replication-task-arn,Values=$TASK_ARN \
    --query 'ReplicationTasks[0].ReplicationTaskStats.FullLoadProgressPercent' \
    --output text)
  
  echo "Current status: $STATUS - $PERCENT% completed"
  
  if [ "$STATUS" == "stopped" ] || [ "$STATUS" == "failed" ]; then
    break
  fi
  
  sleep 120
done

# Step 4: Check if task completed successfully
if [ "$STATUS" == "stopped" ]; then
  echo "Oracle to PostgreSQL historical data migration completed successfully"
  
  # Step 5: Run the merge procedure
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

# Step 6: Clean up staging data (optional)
read -p "Do you want to clean up staging tables? (y/n) " CLEANUP
if [ "$CLEANUP" == "y" ]; then
  echo "Cleaning up staging tables..."
  PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_NAME -c "TRUNCATE TABLE staging.customers; TRUNCATE TABLE staging.orders;"
fi

echo "Oracle to PostgreSQL time-window data reload process completed"
*/

-- Step 5: Oracle to PostgreSQL data type conversion verification query
-- Use this to check for data type mismatches after migration
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

-- Create monitoring view for Oracle to PostgreSQL migration status
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

### Oracle to PostgreSQL Migration Considerations

#### 1. Data Type Handling

* Oracle NUMBER types map to PostgreSQL NUMERIC
* Oracle DATE/TIMESTAMP handling requires careful conversion
* Added transformation rules in DMS task to handle Oracle-specific data types
* Included a verification view to identify data type mismatches

#### 2. Oracle-Specific DMS Settings

* Added Oracle-specific settings in the task configuration:
  * LogMiner reader for CDC (more efficient with Oracle)
  * Character length semantics settings
  * Timestamp with timezone conversion options
  * Supplemental logging options

#### 3. PostgreSQL Syntax Adjustments

* Used PostgreSQL's CTE (WITH clause) syntax for efficient updates and inserts
* Adjusted the merge procedure to use PostgreSQL's transaction handling
* Created PostgreSQL-compatible monitoring views

#### 4. Performance Optimizations

* Added additional polling time in the orchestration script (Oracle migrations often take longer)
* Added progress percentage reporting to monitor large table migrations
* Improved transaction handling for PostgreSQL's MVCC architecture

#### 5. Oracle-PostgreSQL Schema Compatibility

* Added data type verification view to identify potential data format issues
* Use of PostgreSQL-specific syntax for stored procedures
* Handling of Oracle's case-sensitivity vs PostgreSQL's case-insensitivity

This approach allows you to selectively migrate and reload data from a specific time window in your Oracle database to PostgreSQL while maintaining your ongoing CDC replication. The solution is specifically optimized for the Oracle to PostgreSQL migration path.

