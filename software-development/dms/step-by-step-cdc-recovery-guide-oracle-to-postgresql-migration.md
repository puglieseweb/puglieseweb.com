# Step-by-Step CDC Recovery Guide: Oracle to PostgreSQL Migration

This guide explains how to set up, maintain, and recover CDC (Change Data Capture) during an Oracle to PostgreSQL migration using AWS DMS.

### Part 1: Prerequisites and Configuration

#### Step 1: Configure Oracle Source for Optimal CDC Recovery

1.  **Enable ARCHIVELOG Mode** (if not already enabled):

    ```sql
    -- Connect as SYSDBA
    SHUTDOWN IMMEDIATE;
    STARTUP MOUNT;
    ALTER DATABASE ARCHIVELOG;
    ALTER DATABASE OPEN;

    -- Verify ARCHIVELOG mode is enabled
    SELECT log_mode FROM v$database;
    ```
2.  **Configure Supplemental Logging**:

    ```sql
    -- Enable database-level supplemental logging
    ALTER DATABASE ADD SUPPLEMENTAL LOG DATA;

    -- Enable primary key supplemental logging for all tables
    ALTER DATABASE ADD SUPPLEMENTAL LOG DATA (PRIMARY KEY) COLUMNS;

    -- For tables without primary keys, add all columns
    ALTER TABLE schema.table_without_pk ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;
    ```
3.  **Configure Adequate Redo Log Retention**:

    ```sql
    -- Check current retention policy
    SELECT name, value FROM v$parameter WHERE name = 'db_recovery_file_dest_size';

    -- Increase the retention space (adjust size as needed)
    ALTER SYSTEM SET db_recovery_file_dest_size = 100G SCOPE=BOTH;

    -- Set archive log retention hours (e.g., 168 hours = 7 days)
    ALTER SYSTEM SET db_flashback_retention_target = 168 SCOPE=BOTH;
    ```
4.  **Create a Regular SCN Checkpoint Process**:

    ```sql
    -- Create table to store SCN checkpoints
    CREATE TABLE admin.dms_scn_checkpoints (
      checkpoint_id NUMBER GENERATED ALWAYS AS IDENTITY,
      checkpoint_time TIMESTAMP DEFAULT SYSTIMESTAMP,
      checkpoint_name VARCHAR2(100),
      scn NUMBER,
      notes VARCHAR2(4000),
      PRIMARY KEY (checkpoint_id)
    );

    -- Procedure to capture current SCN
    CREATE OR REPLACE PROCEDURE admin.capture_scn_checkpoint(
      p_checkpoint_name IN VARCHAR2,
      p_notes IN VARCHAR2 DEFAULT NULL
    )
    AS
      v_current_scn NUMBER;
    BEGIN
      SELECT CURRENT_SCN INTO v_current_scn FROM V$DATABASE;
      
      INSERT INTO admin.dms_scn_checkpoints (
        checkpoint_name, scn, notes
      ) VALUES (
        p_checkpoint_name, v_current_scn, p_notes
      );
      
      COMMIT;
    END;
    /

    -- Schedule to run every 6 hours
    BEGIN
      DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'SCN_CHECKPOINT_JOB',
        job_type        => 'STORED_PROCEDURE',
        job_action      => 'admin.capture_scn_checkpoint',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=HOURLY;INTERVAL=6',
        enabled         => TRUE,
        comments        => 'Job to capture SCN checkpoints every 6 hours',
        auto_drop       => FALSE,
        job_class       => 'DEFAULT_JOB_CLASS'
      );
      
      -- Pass parameters for scheduled job
      DBMS_SCHEDULER.SET_JOB_ARGUMENT_VALUE (
        job_name          => 'SCN_CHECKPOINT_JOB',
        argument_position => 1,
        argument_value    => 'SCHEDULED_CHECKPOINT'
      );
    END;
    /
    ```
5.  **Monitor Oracle Archive Log Generation**:

    ```sql
    -- Create monitoring view
    CREATE OR REPLACE VIEW admin.archive_log_stats AS
    SELECT
      TRUNC(FIRST_TIME) AS log_date,
      COUNT(*) AS logs_generated,
      ROUND(SUM(BLOCKS * BLOCK_SIZE) / 1024 / 1024 / 1024, 2) AS size_gb
    FROM
      V$ARCHIVED_LOG
    WHERE
      FIRST_TIME > SYSDATE - 7
    GROUP BY
      TRUNC(FIRST_TIME)
    ORDER BY
      log_date;
    ```

#### Step 2: Configure PostgreSQL Target for CDC

1.  **Set Up Target PostgreSQL with Appropriate Settings**:

    ```sql
    -- Connect to PostgreSQL and create a CDC status tracking table
    CREATE SCHEMA IF NOT EXISTS admin;

    CREATE TABLE admin.dms_cdc_checkpoints (
      checkpoint_id SERIAL PRIMARY KEY,
      checkpoint_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      checkpoint_name VARCHAR(100),
      task_arn TEXT,
      oracle_scn NUMERIC,
      postgres_lsn TEXT,
      notes TEXT
    );
    ```
2.  **Configure PostgreSQL for Performance During CDC**:

    ```sql
    -- These settings may need to be adjusted in postgresql.conf

    -- Increase WAL level for logical replication compatibility
    ALTER SYSTEM SET wal_level = logical;

    -- Other helpful settings for DMS performance
    ALTER SYSTEM SET max_wal_senders = 10;
    ALTER SYSTEM SET max_replication_slots = 10;

    -- Apply changes
    SELECT pg_reload_conf();
    ```

#### Step 3: Configure AWS DMS for Recoverable CDC

1.  **Create Initial DMS Task with CDC in Mind**:

    ```bash
    # Using AWS CLI to create a task with both full load and CDC
    aws dms create-replication-task \
      --replication-task-identifier "oracle-to-pg-migration" \
      --source-endpoint-arn "arn:aws:dms:region:account:endpoint:source-oracle-endpoint" \
      --target-endpoint-arn "arn:aws:dms:region:account:endpoint:target-pg-endpoint" \
      --replication-instance-arn "arn:aws:dms:region:account:rep:instance-name" \
      --migration-type "full-load-and-cdc" \
      --table-mappings file://table-mappings.json \
      --replication-task-settings file://task-settings.json
    ```
2.  **Configure task-settings.json with Logging and Oracle Settings**:

    ```json
    {
      "TargetMetadata": {
        "TargetSchema": "",
        "SupportLobs": true,
        "FullLobMode": false,
        "LimitedSizeLobMode": true,
        "LobChunkSize": 64,
        "LobMaxSize": 32,
        "BatchApplyEnabled": true
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
            "Id": "TRANSFORMATION",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          },
          {
            "Id": "SOURCE_UNLOAD",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          },
          {
            "Id": "IO",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          },
          {
            "Id": "TARGET_LOAD",
            "Severity": "LOGGER_SEVERITY_DEFAULT"
          },
          {
            "Id": "PERFORMANCE",
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
        "AddSupplementalLogging": true
      },
      "ChangeProcessingTuning": {
        "BatchApplyPreserveTransaction": true,
        "BatchApplyTimeoutMin": 1,
        "BatchApplyTimeoutMax": 30,
        "BatchApplyMemoryLimit": 500,
        "BatchSplitSize": 0,
        "MinTransactionSize": 1000,
        "CommitTimeout": 1,
        "MemoryLimitTotal": 1024,
        "MemoryKeepTime": 60,
        "StatementCacheSize": 50
      }
    }
    ```
3.  **Create a Checkpoint Recording Script**:

    ```bash
    #!/bin/bash
    # dms-record-checkpoints.sh

    # Configuration
    TASK_ARN="arn:aws:dms:region:account:task:your-task-arn"
    PG_HOST="your-postgres-host"
    PG_PORT="5432"
    PG_DB="your-postgres-db"
    PG_USER="your-postgres-user"
    PG_PASSWORD="your-postgres-password"

    # Get DMS task status
    TASK_INFO=$(aws dms describe-replication-tasks \
      --filters Name=replication-task-arn,Values=$TASK_ARN \
      --query 'ReplicationTasks[0]')

    TASK_STATUS=$(echo $TASK_INFO | jq -r '.Status')

    # Only record if task is running
    if [ "$TASK_STATUS" != "running" ]; then
      echo "Task is not running (status: $TASK_STATUS). Skipping checkpoint."
      exit 0
    fi

    # Create checkpoint name based on date/time
    CHECKPOINT_NAME="AUTO_$(date +%Y%m%d_%H%M%S)"
    NOTES="Automatic checkpoint created by monitoring script"

    # Query Oracle SCN (requires connection to Oracle)
    ORACLE_SCN=$(sqlplus -s $ORACLE_USER/$ORACLE_PASSWORD@$ORACLE_CONNECTION << EOF
    SET HEADING OFF FEEDBACK OFF VERIFY OFF
    SELECT CURRENT_SCN FROM V\$DATABASE;
    EXIT;
    EOF
    )
    ORACLE_SCN=$(echo $ORACLE_SCN | tr -d ' ')

    # Determine last processed SCN from DMS if available
    CACHED_CHANGES=$(echo $TASK_INFO | jq -r '.ReplicationTaskStats.CdcLatencySource')

    # Record checkpoint in PostgreSQL
    PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -p $PG_PORT -d $PG_DB -U $PG_USER << EOF
    INSERT INTO admin.dms_cdc_checkpoints 
    (checkpoint_name, task_arn, oracle_scn, notes)
    VALUES 
    ('$CHECKPOINT_NAME', '$TASK_ARN', $ORACLE_SCN, '$NOTES - CDC Latency: $CACHED_CHANGES seconds');
    EOF

    echo "Recorded checkpoint $CHECKPOINT_NAME with SCN $ORACLE_SCN"
    ```
4.  **Schedule Checkpoint Script**:

    ```bash
    # Add to crontab to run every hour
    crontab -e

    # Add this line to run every hour
    0 * * * * /path/to/dms-record-checkpoints.sh >> /var/log/dms-checkpoints.log 2>&1
    ```

### Part 2: CDC Recovery Procedures

#### Step 1: Identifying Available Recovery Points

1.  **Check Available Oracle SCN Checkpoints**:

    ```sql
    -- Connect to Oracle and run:
    SELECT
      checkpoint_id,
      checkpoint_time,
      checkpoint_name,
      scn,
      notes
    FROM admin.dms_scn_checkpoints
    ORDER BY checkpoint_time DESC;
    ```
2.  **Check Available Archive Logs**:

    ```sql
    -- Connect to Oracle and run:
    SELECT
      sequence#,
      first_change#,
      first_time,
      next_change#,
      next_time,
      archived,
      status
    FROM v$archived_log
    WHERE first_time > SYSDATE - 7
    ORDER BY sequence# DESC;
    ```
3.  **Check Minimum Available SCN**:

    ```sql
    -- Connect to Oracle and run:
    SELECT
      MIN(first_change#) AS min_available_scn,
      TO_CHAR(MIN(first_time), 'YYYY-MM-DD HH24:MI:SS') AS min_available_time
    FROM v$archived_log
    WHERE status = 'A';
    ```
4.  **Check Recorded DMS Checkpoints in PostgreSQL**:

    ```sql
    -- Connect to PostgreSQL and run:
    SELECT
      checkpoint_id,
      checkpoint_time,
      checkpoint_name,
      task_arn,
      oracle_scn,
      notes
    FROM admin.dms_cdc_checkpoints
    ORDER BY checkpoint_time DESC;
    ```

#### Step 2: Recover CDC from a Specific SCN

1.  **Stop the Current DMS Task**:

    ```bash
    aws dms stop-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn"
    ```
2.  **Modify Task to Start from Specific SCN**:

    ```bash
    aws dms modify-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn" \
      --cdc-start-position "Oracle SCN:6916533"
    ```
3.  **Start Task with New SCN Position**:

    ```bash
    aws dms start-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn" \
      --start-replication-task-type start-replication
    ```
4.  **Monitor CDC Recovery Progress**:

    ```bash
    # Check task status
    aws dms describe-replication-tasks \
      --filters Name=replication-task-arn,Values="arn:aws:dms:region:account:task:your-task-arn" \
      --query 'ReplicationTasks[0].{Status:Status,CDCLatencySource:ReplicationTaskStats.CdcLatencySource,CDCLatencyTarget:ReplicationTaskStats.CdcLatencyTarget}'
    ```

#### Step 3: Recover CDC from a Specific Timestamp

1.  **Stop the Current DMS Task**:

    ```bash
    aws dms stop-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn"
    ```
2.  **Find the Nearest SCN for a Timestamp**:

    ```sql
    -- Connect to Oracle and run (replace timestamp):
    SELECT
      scn
    FROM admin.dms_scn_checkpoints
    WHERE checkpoint_time <= TO_TIMESTAMP('2025-02-15 14:30:00', 'YYYY-MM-DD HH24:MI:SS')
    ORDER BY checkpoint_time DESC
    FETCH FIRST 1 ROW ONLY;

    -- Alternative using Oracle timestamp:
    SELECT timestamp_to_scn(TO_TIMESTAMP('2025-02-15 14:30:00', 'YYYY-MM-DD HH24:MI:SS')) FROM dual;
    ```
3.  **Modify Task to Start from a Specific Time**:

    ```bash
    # Using timestamp directly
    aws dms modify-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn" \
      --cdc-start-time "2025-02-15T14:30:00Z"

    # Alternative using SCN from previous step
    aws dms modify-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn" \
      --cdc-start-position "Oracle SCN:6945122"
    ```
4.  **Start Task with New Time Position**:

    ```bash
    aws dms start-replication-task \
      --replication-task-arn "arn:aws:dms:region:account:task:your-task-arn" \
      --start-replication-task-type start-replication
    ```
5.  **Verify Recovery Point Success**:

    ```bash
    # Monitor initial logs for successful connection to source at recovery point
    aws logs get-log-events \
      --log-group-name /aws/dms/replication-task/<task-id> \
      --log-stream-name <task-log-stream> \
      --limit 100
    ```

#### Step 4: Handling Recovery Failures

1.  **Check for Oracle Archive Log Availability Issues**:

    ```sql
    -- Connect to Oracle and run:
    SELECT
      sequence#,
      first_change#,
      next_change#,
      archived,
      status
    FROM v$archived_log
    WHERE first_change# <= 6916533 AND next_change# > 6916533;
    ```
2.  **Validate SCN Visibility to LogMiner**:

    ```sql
    -- Connect to Oracle and run:
    BEGIN
      DBMS_LOGMNR.START_LOGMNR(
        STARTSCN => 6916533,
        OPTIONS => DBMS_LOGMNR.DICT_FROM_ONLINE_CATALOG +
                  DBMS_LOGMNR.CONTINUOUS_MINE
      );
      
      -- Check if we can see transaction data at this SCN
      FOR rec IN (
        SELECT operation, sql_redo 
        FROM v$logmnr_contents 
        WHERE scn >= 6916533 AND ROWNUM <= 10
      ) LOOP
        DBMS_OUTPUT.PUT_LINE(rec.operation || ': ' || rec.sql_redo);
      END LOOP;
      
      DBMS_LOGMNR.END_LOGMNR;
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        DBMS_LOGMNR.END_LOGMNR;
    END;
    /
    ```
3.  **If Recovery Point Unavailable, Find Nearest Available SCN**:

    ```sql
    -- Connect to Oracle and run:
    SELECT
      sequence#,
      first_change# AS start_scn,
      first_time AS start_time,
      next_change# AS end_scn,
      next_time AS end_time
    FROM v$archived_log
    WHERE status = 'A' AND archived = 'YES'
    ORDER BY sequence#;
    ```
4.  **Create a New Recovery Task if Original Can't Be Recovered**:

    ```bash
    # If existing task can't be recovered, create a new one
    aws dms create-replication-task \
      --replication-task-identifier "oracle-to-pg-recovery-$(date +%Y%m%d)" \
      --source-endpoint-arn "arn:aws:dms:region:account:endpoint:source-oracle-endpoint" \
      --target-endpoint-arn "arn:aws:dms:region:account:endpoint:target-pg-endpoint" \
      --replication-instance-arn "arn:aws:dms:region:account:rep:instance-name" \
      --migration-type "cdc" \
      --table-mappings file://table-mappings.json \
      --replication-task-settings file://task-settings.json \
      --cdc-start-position "Oracle SCN:7001234"
    ```

### Part 3: Post-Recovery Validation

1.  **Verify Data Consistency After Recovery**:

    ```sql
    -- Create a PostgreSQL function to check record counts
    CREATE OR REPLACE FUNCTION admin.verify_table_counts(
      start_time TIMESTAMP DEFAULT NULL
    ) RETURNS TABLE (
      table_name TEXT,
      record_count BIGINT,
      updated_since_recovery BIGINT
    ) AS $$
    DECLARE
      tables RECORD;
      query TEXT;
      count_result BIGINT;
      updated_result BIGINT;
    BEGIN
      FOR tables IN 
        SELECT table_schema, table_name 
        FROM information_schema.tables 
        WHERE table_schema = 'public' 
        AND table_type = 'BASE TABLE'
      LOOP
        table_name := tables.table_schema || '.' || tables.table_name;
        
        -- Count all records
        query := 'SELECT COUNT(*) FROM ' || table_name;
        EXECUTE query INTO count_result;
        record_count := count_result;
        
        -- Count recently updated records
        IF start_time IS NOT NULL THEN
          BEGIN
            query := 'SELECT COUNT(*) FROM ' || table_name || 
                     ' WHERE updated_at >= $1';
            EXECUTE query USING start_time INTO updated_result;
            updated_since_recovery := updated_result;
          EXCEPTION WHEN OTHERS THEN
            updated_since_recovery := 0; -- Updated_at column might not exist
          END;
        ELSE
          updated_since_recovery := 0;
        END IF;
        
        RETURN NEXT;
      END LOOP;
      RETURN;
    END;
    $$ LANGUAGE plpgsql;

    -- Run the validation
    SELECT * FROM admin.verify_table_counts('2025-02-15 14:30:00');
    ```
2.  **Monitor CDC Performance After Recovery**:

    ```bash
    # Create a monitoring script
    cat << 'EOF' > monitor-cdc-latency.sh
    #!/bin/bash

    TASK_ARN="arn:aws:dms:region:account:task:your-task-arn"

    while true; do
      NOW=$(date +"%Y-%m-%d %H:%M:%S")
      TASK_STATS=$(aws dms describe-replication-tasks \
        --filters Name=replication-task-arn,Values=$TASK_ARN \
        --query 'ReplicationTasks[0].ReplicationTaskStats')
      
      SOURCE_LATENCY=$(echo $TASK_STATS | jq -r '.CdcLatencySource')
      TARGET_LATENCY=$(echo $TASK_STATS | jq -r '.CdcLatencyTarget')
      
      echo "$NOW - Source Latency: $SOURCE_LATENCY seconds, Target Latency: $TARGET_LATENCY seconds"
      
      sleep 60
    done
    EOF

    chmod +x monitor-cdc-latency.sh
    ./monitor-cdc-latency.sh
    ```
3.  **Create Regular Data Validation Checks**:

    ```sql
    -- Example check to compare row counts in critical tables
    -- Must be adapted to your specific validation needs
    CREATE OR REPLACE PROCEDURE admin.validate_critical_tables()
    LANGUAGE plpgsql
    AS $$
    DECLARE
      validation_passed BOOLEAN := TRUE;
      total_customers INTEGER;
      total_orders INTEGER;
      total_order_items INTEGER;
      validation_msg TEXT := '';
    BEGIN
      -- Get counts from critical tables
      SELECT COUNT(*) INTO total_customers FROM public.customers;
      SELECT COUNT(*) INTO total_orders FROM public.orders;
      SELECT COUNT(*) INTO total_order_items FROM public.order_items;
      
      -- Basic validation rules
      IF total_orders > 0 AND total_order_items = 0 THEN
        validation_passed := FALSE;
        validation_msg := validation_msg || 'Orders exist but no order items. ';
      END IF;
      
      IF total_customers = 0 THEN
        validation_passed := FALSE;
        validation_msg := validation_msg || 'No customers found. ';
      END IF;
      
      -- Log validation results
      INSERT INTO admin.data_validations (
        validation_time, passed, details
      ) VALUES (
        CURRENT_TIMESTAMP, validation_passed, validation_msg
      );
      
      -- Raise notice with results
      RAISE NOTICE 'Validation completed. Passed: %, Message: %', 
        validation_passed, COALESCE(validation_msg, 'No issues found.');
    END;
    $$;

    -- Create table for validation results
    CREATE TABLE IF NOT EXISTS admin.data_validations (
      validation_id SERIAL PRIMARY KEY,
      validation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      passed BOOLEAN,
      details TEXT
    );

    -- Run validation
    CALL admin.validate_critical_tables();
    ```

### Part 4: Automating Recovery for High Availability

1.  **Create a CDCMonitor Function in AWS Lambda**:

    ```python
    import boto3
    import os
    import json
    import psycopg2
    from datetime import datetime, timedelta

    def lambda_handler(event, context):
        # Configuration
        task_arn = os.environ['DMS_TASK_ARN']
        pg_host = os.environ['PG_HOST']
        pg_port = os.environ['PG_PORT']
        pg_db = os.environ['PG_DB']
        pg_user = os.environ['PG_USER']
        pg_password = os.environ['PG_PASSWORD']
        
        # Initialize AWS clients
        dms = boto3.client('dms')
        
        # Get task status
        try:
            response = dms.describe_replication_tasks(
                Filters=[
                    {
                        'Name': 'replication-task-arn',
                        'Values': [task_arn]
                    }
                ]
            )
            
            task = response['ReplicationTasks'][0]
            status = task['Status']
            task_stats = task.get('ReplicationTaskStats', {})
            
            # Check for CDC issues
            source_latency = task_stats.get('CdcLatencySource', 0)
            target_latency = task_stats.get('CdcLatencyTarget', 0)
            
            # Connect to PostgreSQL to log status
            conn = psycopg2.connect(
                host=pg_host,
                port=pg_port,
                dbname=pg_db,
                user=pg_user,
                password=pg_password
            )
            
            cur = conn.cursor()
            
            # Log status to monitoring table
            cur.execute(
                """
                INSERT INTO admin.dms_monitoring
                (task_arn, status, source_latency, target_latency)
                VALUES (%s, %s, %s, %s)
                """,
                (task_arn, status, source_latency, target_latency)
            )
            
            # Check for recovery needs
            if status == 'failed' or source_latency > 1800:  # 30 minutes latency threshold
                # Get last good checkpoint
                cur.execute(
                    """
                    SELECT checkpoint_name, oracle_scn
                    FROM admin.dms_cdc_checkpoints
                    WHERE task_arn = %s AND oracle_scn IS NOT NULL
                    ORDER BY checkpoint_time DESC
                    LIMIT 1
                    """,
                    (task_arn,)
                )
                
                result = cur.fetchone()
                
                if result:
                    checkpoint_name, oracle_scn = result
                    
                    # Log recovery attempt
                    cur.execute(
                        """
                        INSERT INTO admin.dms_recovery_events
                        (task_arn, event_type, checkpoint_name, oracle_scn, notes)
                        VALUES (%s, %s, %s, %s, %s)
                        """,
                        (task_arn, 'AUTO_RECOVERY', checkpoint_name, oracle_scn, 
                         f"Automatic recovery triggered. Status: {status}, Source latency: {source_latency}")
                    )
                    
                    # Stop the task if it's not already stopped
                    if status != 'stopped':
                        dms.stop_replication_task(
                            ReplicationTaskArn=task_arn
                        )
                    
                    # Modify the task to restart from last good SCN
                    dms.modify_replication_task(
                        ReplicationTaskArn=task_arn,
                        CdcStartPosition=f"Oracle SCN:{oracle_scn}"
                    )
                    
                    # Start the task again
                    dms.start_replication_task(
                        ReplicationTaskArn=task_arn,
                        StartReplicationTaskType='start-replication'
                    )
                    
                    message = f"Automatic recovery initiated from SCN: {oracle_scn}"
                else:
                    message = "No valid checkpoint found for recovery"
            else:
                message = "CDC functioning normally"
            
            conn.commit()
            cur.close()
            conn.close()
            
            return {
                'statusCode': 200,
                'body': json.dumps({
                    'task_arn': task_arn,
                    'status': status,
                    'source_latency': source_latency,
                    'target_latency': target_latency,
                    'message': message
                })
            }
        
        except Exception as e:
            return {
                'statusCode': 500,
                'body': json.dumps({
                    'error': str(e)
                })
            }
    ```
2.  **Create Required PostgreSQL Tables for Lambda Function**:

    ```sql
    -- Monitoring table
    CREATE TABLE IF NOT EXISTS admin.dms_monitoring (
      monitoring_id SERIAL PRIMARY KEY,
      monitoring_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      task_arn TEXT,
      status TEXT,
      source_latency NUMERIC,
      target_latency NUMERIC
    );

    -- Recovery events table
    CREATE TABLE IF NOT EXISTS admin.dms_recovery_events (
      event_id SERIAL PRIMARY KEY,
      event_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      task_arn TEXT,
      event_type TEXT,
      checkpoint_name TEXT,
      oracle_scn NUMERIC,
      notes TEXT
    );
    ```
3.  **Set up CloudWatch Events to Trigger Lambda Function**:

    ```bash
    # Create CloudWatch Event Rule
    aws events put-rule \
      --name "DMSCDCMonitoringRule" \
      --schedule-expression "rate(5 minutes)" \
      --state ENABLED

    # Configure Lambda as target
    aws events put-targets \
      --rule "DMSCDCMonitoringRule" \
      --targets "Id"="1","Arn"="arn:aws:lambda:region:account:function:CDCMonitor"

    # Give CloudWatch Events permission to invoke Lambda
    aws lambda add-permission \
      --function-name CDCMonitor \
      --statement-id CloudWatchEvents \
      --action 'lambda:InvokeFunction' \
      --principal events.amazonaws.com \
      --source-arn arn:aws:events:region:account:rule/DMSCDCMonitoringRule
    ```

This comprehensive guide covers the setup, maintenance, and recovery of CDC during an Oracle to PostgreSQL migration using AWS DMS. The procedures are specifically designed for Oracle on-premises to PostgreSQL on AWS migrations, with an emphasis on establishing reliable recovery points and automating the recovery process.

### Part 5: Long-Term CDC Maintenance

#### Step 1: Database-Side Maintenance

1.  **Oracle Archive Log Management**:

    ```sql
    -- Create an Oracle procedure to monitor and alert on archive log space
    CREATE OR REPLACE PROCEDURE admin.monitor_archive_logs
    AS
      v_total_space NUMBER;
      v_used_space NUMBER;
      v_percent_used NUMBER;
      v_oldest_log_date DATE;
      v_alert_threshold NUMBER := 80; -- Alert at 80% usage
    BEGIN
      -- Get recovery file destination space information
      SELECT
        a.space_limit / 1024 / 1024 / 1024,
        a.space_used / 1024 / 1024 / 1024,
        ROUND(a.space_used / a.space_limit * 100, 2)
      INTO
        v_total_space,
        v_used_space,
        v_percent_used
      FROM
        v$recovery_file_dest a;
        
      -- Get oldest archive log date
      SELECT
        MIN(first_time)
      INTO
        v_oldest_log_date
      FROM
        v$archived_log
      WHERE
        archived = 'YES'
        AND deleted = 'NO';
        
      -- Log the information
      INSERT INTO admin.archive_log_monitor
      (total_space_gb, used_space_gb, percent_used, oldest_log_date)
      VALUES
      (v_total_space, v_used_space, v_percent_used, v_oldest_log_date);
      
      -- Alert if approaching capacity
      IF v_percent_used > v_alert_threshold THEN
        -- Send alert (implement your preferred alerting method)
        dbms_output.put_line('WARNING: Archive log space at ' || v_percent_used || '% capacity!');
        
        -- Log the alert
        INSERT INTO admin.dms_alerts
        (alert_type, severity, message)
        VALUES
        ('ARCHIVE_LOG_SPACE', 'WARNING', 
         'Archive log space at ' || v_percent_used || '% capacity. Oldest log: ' || v_oldest_log_date);
      END IF;
      
      COMMIT;
    EXCEPTION
      WHEN OTHERS THEN
        -- Log the error
        INSERT INTO admin.dms_alerts
        (alert_type, severity, message)
        VALUES
        ('ARCHIVE_LOG_SPACE', 'ERROR', 'Error monitoring archive logs: ' || SQLERRM);
        COMMIT;
    END;
    /

    -- Create supporting tables
    CREATE TABLE admin.archive_log_monitor (
      monitor_id NUMBER GENERATED ALWAYS AS IDENTITY,
      monitor_time TIMESTAMP DEFAULT SYSTIMESTAMP,
      total_space_gb NUMBER,
      used_space_gb NUMBER,
      percent_used NUMBER,
      oldest_log_date DATE,
      PRIMARY KEY (monitor_id)
    );

    CREATE TABLE admin.dms_alerts (
      alert_id NUMBER GENERATED ALWAYS AS IDENTITY,
      alert_time TIMESTAMP DEFAULT SYSTIMESTAMP,
      alert_type VARCHAR2(50),
      severity VARCHAR2(20),
      message VARCHAR2(4000),
      acknowledged CHAR(1) DEFAULT 'N',
      PRIMARY KEY (alert_id)
    );

    -- Schedule to run hourly
    BEGIN
      DBMS_SCHEDULER.CREATE_JOB (
        job_name        => 'ARCHIVE_LOG_MONITOR_JOB',
        job_type        => 'STORED_PROCEDURE',
        job_action      => 'admin.monitor_archive_logs',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=HOURLY;INTERVAL=1',
        enabled         => TRUE,
        comments        => 'Job to monitor archive log space hourly',
        auto_drop       => FALSE
      );
    END;
    /
    ```
2.  **PostgreSQL Maintenance Procedures**:

    ```sql
    -- Create a maintenance procedure for the target PostgreSQL
    CREATE OR REPLACE PROCEDURE admin.perform_dms_maintenance()
    LANGUAGE plpgsql
    AS $
    DECLARE
      v_current_month TEXT;
      v_old_month TEXT;
      v_table_exists BOOLEAN;
    BEGIN
      -- Create current month partition for monitoring data if needed
      v_current_month := to_char(current_date, 'YYYY_MM');
      
      -- Check if partition exists
      SELECT EXISTS (
        SELECT FROM information_schema.tables 
        WHERE table_schema = 'admin' 
        AND table_name = 'dms_monitoring_' || v_current_month
      ) INTO v_table_exists;
      
      -- Create new partition if needed
      IF NOT v_table_exists THEN
        EXECUTE 'CREATE TABLE admin.dms_monitoring_' || v_current_month || ' (
          LIKE admin.dms_monitoring INCLUDING ALL
        )';
        
        EXECUTE 'ALTER TABLE admin.dms_monitoring_' || v_current_month || 
                ' ADD CONSTRAINT dms_monitoring_' || v_current_month || '_month_check
                 CHECK (monitoring_time >= DATE ''' || 
                 to_char(date_trunc('month', current_date), 'YYYY-MM-DD') || 
                 ''' AND monitoring_time < DATE ''' || 
                 to_char(date_trunc('month', current_date + interval '1 month'), 'YYYY-MM-DD') || ''')';
                 
        EXECUTE 'CREATE INDEX idx_dms_monitoring_' || v_current_month || '_time
                 ON admin.dms_monitoring_' || v_current_month || ' (monitoring_time)';
      END IF;
      
      -- Purge old data (keeping last 90 days)
      DELETE FROM admin.dms_cdc_checkpoints
      WHERE checkpoint_time < current_date - interval '90 days';
      
      -- Move data from main table to partitions
      INSERT INTO admin.dms_monitoring_2025_03
      SELECT * FROM admin.dms_monitoring
      WHERE date_trunc('month', monitoring_time) = date_trunc('month', DATE '2025-03-01')
      ON CONFLICT DO NOTHING;
      
      -- Delete moved data from main table
      DELETE FROM admin.dms_monitoring
      WHERE date_trunc('month', monitoring_time) = date_trunc('month', DATE '2025-03-01');
      
      -- Vacuum tables to reclaim space
      VACUUM ANALYZE admin.dms_cdc_checkpoints;
      VACUUM ANALYZE admin.dms_monitoring;
      VACUUM ANALYZE admin.dms_recovery_events;
      
      -- Log maintenance completion
      INSERT INTO admin.maintenance_log
      (maintenance_type, details)
      VALUES
      ('DMS_TABLES', 'Routine maintenance completed: partition creation and data cleanup');
      
    EXCEPTION WHEN OTHERS THEN
      -- Log error
      INSERT INTO admin.maintenance_log
      (maintenance_type, details, error)
      VALUES
      ('DMS_TABLES', 'Maintenance failed', SQLERRM);
      RAISE;
    END;
    $;

    -- Create logging table
    CREATE TABLE IF NOT EXISTS admin.maintenance_log (
      log_id SERIAL PRIMARY KEY,
      log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      maintenance_type TEXT,
      details TEXT,
      error TEXT
    );
    ```

#### Step 2: Regular Health Checks

1.  **Oracle-side Health Check Script**:

    ```bash
    #!/bin/bash
    # oracle_cdc_health_check.sh

    ORACLE_SID="your_oracle_sid"
    ORACLE_HOME="/path/to/oracle/home"
    ORACLE_USER="system"
    ORACLE_PASSWORD="your_password"

    export ORACLE_SID ORACLE_HOME

    echo "======== Oracle CDC Health Check: $(date) ========"

    # Check archive log mode
    echo "Checking archive log mode:"
    $ORACLE_HOME/bin/sqlplus -s $ORACLE_USER/$ORACLE_PASSWORD << EOF
    SET HEADING OFF FEEDBACK OFF VERIFY OFF LINESIZE 1000
    SELECT 'Archive Log Mode: ' || log_mode FROM v\$database;
    EOF

    # Check supplemental logging
    echo -e "\nChecking supplemental logging:"
    $ORACLE_HOME/bin/sqlplus -s $ORACLE_USER/$ORACLE_PASSWORD << EOF
    SET HEADING OFF FEEDBACK OFF VERIFY OFF LINESIZE 1000
    SELECT 'Supplemental Logging: ' || supplemental_log_data_min FROM v\$database;
    SELECT 'PK Supplemental Logging: ' || supplemental_log_data_pk FROM v\$database;
    EOF

    # Check archive log destination usage
    echo -e "\nChecking archive log destination usage:"
    $ORACLE_HOME/bin/sqlplus -s $ORACLE_USER/$ORACLE_PASSWORD << EOF
    SET LINESIZE 200 PAGESIZE 100 FEEDBACK OFF VERIFY OFF
    COLUMN name FORMAT A30
    COLUMN space_limit FORMAT A15
    COLUMN space_used FORMAT A15
    COLUMN space_reclaimable FORMAT A15
    COLUMN percent_used FORMAT A15

    SELECT name, 
           ROUND(space_limit / 1024 / 1024 / 1024, 2) || ' GB' AS space_limit,
           ROUND(space_used / 1024 / 1024 / 1024, 2) || ' GB' AS space_used,
           ROUND(space_reclaimable / 1024 / 1024 / 1024, 2) || ' GB' AS space_reclaimable,
           ROUND(space_used / space_limit * 100, 2) || '%' AS percent_used
    FROM v\$recovery_file_dest;
    EOF

    # Check archive log generation in last 24 hours
    echo -e "\nArchive log generation in last 24 hours:"
    $ORACLE_HOME/bin/sqlplus -s $ORACLE_USER/$ORACLE_PASSWORD << EOF
    SET LINESIZE 200 PAGESIZE 100 FEEDBACK OFF VERIFY OFF

    SELECT TRUNC(completion_time, 'HH') AS hour,
           COUNT(*) AS logs_generated,
           ROUND(SUM(blocks * block_size) / 1024 / 1024, 2) || ' MB' AS size_generated
    FROM v\$archived_log
    WHERE completion_time > SYSDATE - 1
    GROUP BY TRUNC(completion_time, 'HH')
    ORDER BY hour;
    EOF

    # Check SCN advancement
    echo -e "\nChecking SCN advancement:"
    $ORACLE_HOME/bin/sqlplus -s $ORACLE_USER/$ORACLE_PASSWORD << EOF
    SET LINESIZE 200 PAGESIZE 100 FEEDBACK OFF VERIFY OFF

    SELECT checkpoint_name, 
           TO_CHAR(checkpoint_time, 'YYYY-MM-DD HH24:MI:SS') AS time, 
           scn
    FROM admin.dms_scn_checkpoints
    WHERE checkpoint_time > SYSDATE - 1
    ORDER BY checkpoint_time;
    EOF

    echo -e "\n======== Health Check Complete ========"
    ```
2.  **PostgreSQL-side Health Check Script**:

    ```bash
    #!/bin/bash
    # postgres_cdc_health_check.sh

    PG_HOST="your-postgres-host"
    PG_PORT="5432"
    PG_DB="your-postgres-db"
    PG_USER="your-postgres-user"
    PG_PASSWORD="your-postgres-password"

    export PGPASSWORD=$PG_PASSWORD

    echo "======== PostgreSQL CDC Health Check: $(date) ========"

    # Check DMS task status
    echo "DMS Task Status:"
    aws dms describe-replication-tasks \
      --query 'ReplicationTasks[*].{TaskId:ReplicationTaskIdentifier,Status:Status,Migration:MigrationType,Stats:ReplicationTaskStats.{CDCSourceLatency:CdcLatencySource,CDCTargetLatency:CdcLatencyTarget}}' \
      --output table

    # Check recent CDC checkpoints
    echo -e "\nRecent CDC Checkpoints:"
    psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_DB << EOF
    SELECT 
      checkpoint_id,
      checkpoint_time,
      checkpoint_name,
      task_arn,
      oracle_scn,
      notes
    FROM admin.dms_cdc_checkpoints
    ORDER BY checkpoint_time DESC
    LIMIT 5;
    EOF

    # Check data consistency across key tables
    echo -e "\nTable Record Counts:"
    psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_DB << EOF
    WITH prod_counts AS (
      SELECT 'customers' AS table_name, COUNT(*) AS count FROM public.customers
      UNION ALL
      SELECT 'orders' AS table_name, COUNT(*) AS count FROM public.orders
      UNION ALL
      SELECT 'products' AS table_name, COUNT(*) AS count FROM public.products
    )
    SELECT * FROM prod_counts;
    EOF

    # Check recent CDC recovery events
    echo -e "\nRecent Recovery Events:"
    psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_DB << EOF
    SELECT 
      event_id,
      event_time,
      event_type,
      checkpoint_name,
      oracle_scn,
      notes
    FROM admin.dms_recovery_events
    ORDER BY event_time DESC
    LIMIT 5;
    EOF

    # Check CDC latency trend
    echo -e "\nCDC Latency Trend (last 24 hours):"
    psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_DB << EOF
    SELECT 
      date_trunc('hour', monitoring_time) AS hour,
      AVG(source_latency) AS avg_source_latency,
      MAX(source_latency) AS max_source_latency,
      AVG(target_latency) AS avg_target_latency,
      MAX(target_latency) AS max_target_latency
    FROM admin.dms_monitoring
    WHERE monitoring_time > CURRENT_TIMESTAMP - INTERVAL '24 hours'
    GROUP BY date_trunc('hour', monitoring_time)
    ORDER BY hour;
    EOF

    echo -e "\n======== Health Check Complete ========"
    ```

#### Step 3: Backup and Disaster Recovery for CDC

1.  **Oracle Archive Log Backup Strategy**:

    ```bash
    #!/bin/bash
    # oracle_archivelog_backup.sh

    ORACLE_SID="your_oracle_sid"
    ORACLE_HOME="/path/to/oracle/home"
    BACKUP_DIR="/backup/archivelogs"
    DAYS_TO_KEEP=7

    export ORACLE_SID ORACLE_HOME

    # Get current date for backup folder
    BACKUP_DATE=$(date +%Y%m%d)
    BACKUP_FOLDER="$BACKUP_DIR/$BACKUP_DATE"

    # Create backup directory
    mkdir -p $BACKUP_FOLDER

    # Back up archive logs
    $ORACLE_HOME/bin/rman << EOF
    CONNECT TARGET /

    # Back up all archive logs not yet backed up
    BACKUP ARCHIVELOG ALL NOT BACKED UP FORMAT '$BACKUP_FOLDER/%d_%T_%s_%p.arc';

    # Delete archive logs older than specified days that have been backed up twice
    DELETE ARCHIVELOG ALL COMPLETED BEFORE 'SYSDATE-$DAYS_TO_KEEP' BACKED UP 2 TIMES TO DEVICE TYPE DISK;

    # List archive logs
    LIST ARCHIVELOG ALL;

    EXIT;
    EOF

    # Clean up old backup folders
    find $BACKUP_DIR -type d -mtime +$DAYS_TO_KEEP -exec rm -rf {} \; 2>/dev/null

    echo "Archive log backup completed: $(date)"
    ```
2.  **PostgreSQL CDC Configuration Backup**:

    ```bash
    #!/bin/bash
    # backup_dms_config.sh

    BACKUP_DIR="/backup/dms_config"
    AWS_REGION="your-region"
    PROFILE="your-aws-profile"

    # Create timestamp for this backup
    BACKUP_TIME=$(date +%Y%m%d_%H%M%S)
    BACKUP_FOLDER="$BACKUP_DIR/$BACKUP_TIME"

    mkdir -p $BACKUP_FOLDER

    # Export DMS endpoints
    aws dms describe-endpoints --region $AWS_REGION --profile $PROFILE > "$BACKUP_FOLDER/dms_endpoints.json"

    # Export DMS replication instances
    aws dms describe-replication-instances --region $AWS_REGION --profile $PROFILE > "$BACKUP_FOLDER/dms_replication_instances.json"

    # Export DMS tasks
    aws dms describe-replication-tasks --region $AWS_REGION --profile $PROFILE > "$BACKUP_FOLDER/dms_replication_tasks.json"

    # Export detailed task settings for each task
    for TASK_ARN in $(aws dms describe-replication-tasks --region $AWS_REGION --profile $PROFILE --query 'ReplicationTasks[*].ReplicationTaskArn' --output text); do
      TASK_ID=$(echo $TASK_ARN | awk -F':' '{print $NF}')
      aws dms describe-replication-tasks --region $AWS_REGION --profile $PROFILE --filters Name=replication-task-arn,Values=$TASK_ARN > "$BACKUP_FOLDER/task_${TASK_ID}_config.json"
    done

    # Backup SCN checkpoints from PostgreSQL
    PG_HOST="your-postgres-host"
    PG_PORT="5432"
    PG_DB="your-postgres-db"
    PG_USER="your-postgres-user"
    PG_PASSWORD="your-postgres-password"

    PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -p $PG_PORT -U $PG_USER -d $PG_DB -c "\COPY (SELECT * FROM admin.dms_cdc_checkpoints ORDER BY checkpoint_time) TO '$BACKUP_FOLDER/dms_cdc_checkpoints.csv' WITH CSV HEADER"

    # Create README with timestamp
    echo "DMS Configuration Backup" > "$BACKUP_FOLDER/README.txt"
    echo "Created: $(date)" >> "$BACKUP_FOLDER/README.txt"
    echo "AWS Region: $AWS_REGION" >> "$BACKUP_FOLDER/README.txt"
    echo "Contents:" >> "$BACKUP_FOLDER/README.txt"
    ls -la $BACKUP_FOLDER | grep -v README >> "$BACKUP_FOLDER/README.txt"

    # Compress the backup
    tar -czf "$BACKUP_DIR/dms_backup_$BACKUP_TIME.tar.gz" -C "$BACKUP_DIR" "$BACKUP_TIME"

    # Remove the uncompressed directory
    rm -rf "$BACKUP_FOLDER"

    echo "DMS configuration backup completed: $(date)"
    echo "Backup file: $BACKUP_DIR/dms_backup_$BACKUP_TIME.tar.gz"

    # Clean up old backups (keep last 30 days)
    find $BACKUP_DIR -name "dms_backup_*.tar.gz" -type f -mtime +30 -delete
    ```

### Part 6: Troubleshooting CDC Issues

#### Common CDC Issues and Solutions

1.  **Issue: CDC Task Fails with "ORA-01291: missing logfile"**

    **Solution:**

    ```sql
    -- Check for missing archive logs
    SELECT 
      sequence#, 
      name, 
      status 
    FROM v$archived_log 
    WHERE sequence# = <sequence_number_from_error>;

    -- If logs are missing, you may need to restore them from backup
    -- Then restart DMS task with appropriate SCN position
    ```
2.  **Issue: CDC Latency Increasing**

    **Solution:**

    ```bash
    # Check if Oracle is generating logs faster than DMS can process

    # First, check log generation rate
    sqlplus -s system/password << EOF
    SELECT 
      TRUNC(completion_time, 'HH') AS hour,
      COUNT(*) AS logs_generated,
      ROUND(SUM(blocks * block_size) / 1024 / 1024, 2) AS size_mb
    FROM v\$archived_log
    WHERE completion_time > SYSDATE - 1
    GROUP BY TRUNC(completion_time, 'HH')
    ORDER BY hour;
    EOF

    # Check DMS processing capacity in CloudWatch metrics
    aws cloudwatch get-metric-statistics \
      --namespace AWS/DMS \
      --metric-name CDCLatencySource \
      --dimensions Name=ReplicationInstanceIdentifier,Value=your-instance-id Name=ReplicationTaskIdentifier,Value=your-task-id \
      --start-time $(date -u -d '24 hours ago' +%Y-%m-%dT%H:%M:%SZ) \
      --end-time $(date -u +%Y-%m-%dT%H:%M:%SZ) \
      --period 3600 \
      --statistics Average \
      --region your-region

    # If DMS can't keep up, consider scaling up replication instance
    aws dms modify-replication-instance \
      --replication-instance-arn your-instance-arn \
      --replication-instance-class dms.c5.xlarge
    ```
3.  **Issue: Oracle SCN Jumps Unexpectedly**

    **Solution:**

    ```sql
    -- Check for SCN jumps in checkpoint history
    SELECT 
      a.checkpoint_id,
      a.checkpoint_time,
      a.scn,
      a.scn - LAG(a.scn) OVER (ORDER BY a.checkpoint_time) AS scn_jump,
      (a.checkpoint_time - LAG(a.checkpoint_time) OVER (ORDER BY a.checkpoint_time)) DAY TO SECOND AS time_diff
    FROM admin.dms_scn_checkpoints a
    WHERE a.checkpoint_time > SYSDATE - 7
    ORDER BY a.checkpoint_time;

    -- If large jumps detected, check for database activities during that time
    SELECT 
      operation, 
      start_time, 
      end_time 
    FROM dba_scheduler_job_run_details
    WHERE start_time > TO_DATE('YYYY-MM-DD HH24:MI:SS', 'time_of_jump');
    ```
4.  **Issue: Inconsistent Data After CDC Recovery**

    **Solution:**

    ```sql
    -- In PostgreSQL, create a validation query to find inconsistencies
    -- Example for order totals validation:

    WITH order_totals AS (
      SELECT 
        o.order_id,
        o.total_amount,
        SUM(oi.price * oi.quantity) AS calculated_total
      FROM 
        orders o
      JOIN 
        order_items oi ON o.order_id = oi.order_id
      GROUP BY 
        o.order_id, o.total_amount
    )
    SELECT 
      order_id,
      total_amount,
      calculated_total,
      total_amount - calculated_total AS difference
    FROM 
      order_totals
    WHERE 
      ABS(total_amount - calculated_total) > 0.01
      AND order_id IN (
        SELECT 
          o.order_id 
        FROM 
          orders o
        WHERE 
          updated_at > 'recovery_timestamp'
      );
    ```

This comprehensive guide covers the setup, maintenance, and recovery of CDC during an Oracle to PostgreSQL migration using AWS DMS. The procedures are specifically designed for Oracle on-premises to PostgreSQL on AWS migrations, with an emphasis on establishing reliable recovery points and automating the recovery process.
