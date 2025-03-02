# Multi-Stage Migration Implementation Plan

This plan details how to implement a multi-stage migration from an on-premises Oracle database to AWS PostgreSQL, with cross-database joins and final target tables.

### Architecture Overview

```
┌───────────────┐         ┌─────────────────────────────────────────────────────┐
│               │         │                      AWS                             │
│  On-Premises  │         │                                                      │
│     Oracle    │─────┐   │  ┌──────────────┐        ┌──────────────┐           │
│   Database    │     │   │  │  PostgreSQL  │        │  PostgreSQL  │           │
│ (Source Table)│     │   │  │  Database 1  │        │  Database 2  │           │
└───────────────┘     │   │  │              │        │              │           │
                      │   │  │ ┌──────────┐ │        │ ┌──────────┐ │           │
                      └───┼──►│  Staging  │◄├────────┼─┤ Existing │ │           │
                          │  │ │  Table   │ │        │ │  Table   │ │           │
                 DMS Task 1  │ └────┬─────┘ │        │ └──────────┘ │           │
                          │  │      │       │        │              │           │
                          │  │      │       │        └──────────────┘           │
                          │  │      │       │            ▲                      │
                          │  │      │       │            │                      │
                          │  │      ▼       │            │                      │
                          │  │ ┌──────────┐ │            │                      │
                          │  │ │  Cross-  │ │            │                      │
                          │  │ │ Database │─┼────────────┘                      │
                          │  │ │   View   │ │       FDW Connection              │
                          │  │ └────┬─────┘ │                                   │
                          │  │      │       │                                   │
                          │  │      │       │                                   │
                          │  │      ▼       │                                   │
                          │  │ ┌──────────┐ │                                   │
                          │  │ │  Target  │ │                                   │
                          │  │ │  Table   │◄┼───────────────────────────────────┘
                          │  │ └──────────┘ │        DMS Task 2
                          │  │              │
                          │  └──────────────┘
                          │
                          └────────────────────────────────────────────┘
                                           AWS DMS
```

### Step 1: Initial Oracle to PostgreSQL Migration

#### 1.1. Set Up Source Oracle Database for Migration

```sql
-- Connect to Oracle as a privileged user
-- Create a dedicated user for DMS
CREATE USER dms_user IDENTIFIED BY "complex_password";

-- Grant necessary privileges
GRANT CREATE SESSION TO dms_user;
GRANT SELECT ANY TABLE TO dms_user;
GRANT SELECT ANY TRANSACTION TO dms_user;
GRANT SELECT ON V_$ARCHIVED_LOG TO dms_user;
GRANT SELECT ON V_$LOG TO dms_user;
GRANT SELECT ON V_$LOGFILE TO dms_user;
GRANT SELECT ON V_$DATABASE TO dms_user;
GRANT SELECT ON V_$THREAD TO dms_user;
GRANT SELECT ON V_$PARAMETER TO dms_user;
GRANT SELECT ON V_$NLS_PARAMETERS TO dms_user;
GRANT SELECT ON V_$TIMEZONE_NAMES TO dms_user;
GRANT SELECT ON V_$TRANSACTION TO dms_user;
GRANT SELECT ON ALL_INDEXES TO dms_user;
GRANT SELECT ON ALL_OBJECTS TO dms_user;
GRANT SELECT ON ALL_TABLES TO dms_user;
GRANT SELECT ON ALL_USERS TO dms_user;
GRANT SELECT ON ALL_CATALOG TO dms_user;
GRANT SELECT ON ALL_CONSTRAINTS TO dms_user;
GRANT SELECT ON ALL_CONS_COLUMNS TO dms_user;
GRANT SELECT ON ALL_TAB_COLS TO dms_user;
GRANT SELECT ON ALL_IND_COLUMNS TO dms_user;
GRANT SELECT ON ALL_ENCRYPTED_COLUMNS TO dms_user;
GRANT SELECT ON ALL_LOG_GROUPS TO dms_user;
GRANT SELECT ON ALL_TAB_PARTITIONS TO dms_user;
GRANT SELECT ON SYS.DBA_REGISTRY TO dms_user;
GRANT SELECT ON SYS.OBJ$ TO dms_user;
GRANT SELECT ON DBA_TABLESPACES TO dms_user;
GRANT SELECT ON DBA_OBJECTS TO dms_user;
GRANT SELECT ON SYS.ENC$ TO dms_user;
GRANT SELECT ON SOURCE_TABLE TO dms_user; -- Replace SOURCE_TABLE with your actual table name

-- Enable supplemental logging if you plan to use CDC
ALTER DATABASE ADD SUPPLEMENTAL LOG DATA;
ALTER DATABASE ADD SUPPLEMENTAL LOG DATA (PRIMARY KEY) COLUMNS;
ALTER TABLE SOURCE_TABLE ADD SUPPLEMENTAL LOG DATA (ALL) COLUMNS;
```

#### 1.2. Set Up Target PostgreSQL Database on AWS

```sql
-- Connect to PostgreSQL Database 1 as admin user
-- Create a staging schema to hold the migrated data
CREATE SCHEMA staging;

-- Create a schema for the final target table
CREATE SCHEMA target;

-- Create a user for DMS to connect with
CREATE USER dms_user WITH PASSWORD 'complex_password';

-- Grant necessary permissions
GRANT USAGE ON SCHEMA staging TO dms_user;
GRANT USAGE ON SCHEMA target TO dms_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA staging TO dms_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA target TO dms_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA staging TO dms_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA target TO dms_user;

-- Create extension for Foreign Data Wrapper
CREATE EXTENSION postgres_fdw;
```

#### 1.3. Set Up AWS DMS for Initial Migration

1. **Create Oracle Source Endpoint**:

```bash
aws dms create-endpoint \
  --endpoint-identifier oracle-source-endpoint \
  --endpoint-type source \
  --engine-name oracle \
  --username dms_user \
  --password complex_password \
  --server-name oracle-server.your-domain.com \
  --port 1521 \
  --database-name YOUR_SID \
  --extra-connection-attributes "useLogminerReader=N;useBfile=Y" \
  --region your-aws-region
```

2. **Create PostgreSQL Target Endpoint**:

```bash
aws dms create-endpoint \
  --endpoint-identifier postgresql-target-endpoint \
  --endpoint-type target \
  --engine-name postgres \
  --username dms_user \
  --password complex_password \
  --server-name postgres-db1.abcdef123456.region.rds.amazonaws.com \
  --port 5432 \
  --database-name postgres_db1 \
  --region your-aws-region
```

3. **Create a Replication Instance**:

```bash
aws dms create-replication-instance \
  --replication-instance-identifier dms-replication-instance \
  --replication-instance-class dms.c5.large \
  --allocated-storage 50 \
  --vpc-security-group-ids sg-abcdef123456 \
  --replication-subnet-group-id default-vpc-subnet-group \
  --region your-aws-region
```

4. **Create Task Settings JSON File** (task-settings.json):

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
    "TargetTablePrepMode": "DROP_AND_CREATE",
    "CreatePkAfterFullLoad": true,
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
        "Id": "TASK_MANAGER",
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
    "UseDirectPathFullLoad": true,
    "UseParallelReadThreads": true,
    "NumberOfThreads": 4,
    "ParallelASMReadThreads": 2,
    "ReadAheadBlocks": 10000,
    "AccessAlternateDirectly": false,
    "UseAlternateFolderForOnline": false,
    "OraclePathPrefix": "",
    "UsePathPrefix": false,
    "ReplacePathPrefix": false,
    "EnableHomogenousPartitionOps": false
  }
}
```

5. **Create Table Mappings JSON File** (table-mappings.json):

```json
{
  "rules": [
    {
      "rule-type": "selection",
      "rule-id": "1",
      "rule-name": "1",
      "object-locator": {
        "schema-name": "SCHEMA_NAME",
        "table-name": "SOURCE_TABLE"
      },
      "rule-action": "include"
    },
    {
      "rule-type": "transformation",
      "rule-id": "2",
      "rule-name": "2",
      "rule-action": "rename",
      "rule-target": "schema",
      "object-locator": {
        "schema-name": "SCHEMA_NAME"
      },
      "value": "staging"
    },
    {
      "rule-type": "transformation",
      "rule-id": "3",
      "rule-name": "3",
      "rule-action": "rename",
      "rule-target": "table",
      "object-locator": {
        "schema-name": "SCHEMA_NAME",
        "table-name": "SOURCE_TABLE"
      },
      "value": "staging_table"
    }
  ]
}
```

6. **Create the DMS Task**:

```bash
aws dms create-replication-task \
  --replication-task-identifier oracle-to-postgres-task \
  --source-endpoint-arn arn:aws:dms:region:account:endpoint:oracle-source-endpoint \
  --target-endpoint-arn arn:aws:dms:region:account:endpoint:postgresql-target-endpoint \
  --replication-instance-arn arn:aws:dms:region:account:rep:dms-replication-instance \
  --migration-type full-load-and-cdc \
  --table-mappings file://table-mappings.json \
  --replication-task-settings file://task-settings.json \
  --region your-aws-region
```

7. **Start the DMS Task**:

```bash
aws dms start-replication-task \
  --replication-task-arn arn:aws:dms:region:account:task:oracle-to-postgres-task \
  --start-replication-task-type start-replication \
  --region your-aws-region
```

### Step 2: Set Up Cross-Database FDW Connection

#### 2.1. Set Up Foreign Data Wrapper in PostgreSQL Database 1

```sql
-- Connect to PostgreSQL Database 1
-- Create server connection to PostgreSQL Database 2
CREATE SERVER postgres_db2_server
FOREIGN DATA WRAPPER postgres_fdw
OPTIONS (host 'postgres-db2.abcdef123456.region.rds.amazonaws.com', port '5432', dbname 'postgres_db2');

-- Create user mapping
CREATE USER MAPPING FOR dms_user
SERVER postgres_db2_server
OPTIONS (user 'fdw_user', password 'complex_password');

-- Create foreign table referring to the table in PostgreSQL Database 2
CREATE FOREIGN TABLE staging.foreign_table (
    id integer,
    name varchar(255),
    value numeric,
    created_at timestamp,
    -- Add all columns from the remote table
    -- Make sure data types match exactly
    PRIMARY KEY (id)
)
SERVER postgres_db2_server
OPTIONS (schema_name 'public', table_name 'existing_table');

-- Grant permissions
GRANT SELECT ON staging.foreign_table TO dms_user;
```

#### 2.2. Create the Cross-Database View

```sql
-- Connect to PostgreSQL Database 1
-- Create a view that joins the staging table with the foreign table
CREATE OR REPLACE VIEW staging.combined_view AS
SELECT 
    s.id,
    s.column1,
    s.column2,
    f.name,
    f.value,
    -- Add all columns needed from both tables
    s.created_at AS source_created_at,
    f.created_at AS related_created_at
FROM 
    staging.staging_table s
JOIN 
    staging.foreign_table f ON s.id = f.id;  -- Adjust join condition

-- Grant permissions
GRANT SELECT ON staging.combined_view TO dms_user;
```

### Step 3: Migrate View Data to Target Table

#### 3.1. Create the Target Table

```sql
-- Connect to PostgreSQL Database 1
-- Create the target table with the desired schema
CREATE TABLE target.final_table (
    id integer PRIMARY KEY,
    column1 varchar(255),
    column2 varchar(255),
    name varchar(255),
    value numeric,
    source_created_at timestamp,
    related_created_at timestamp,
    -- Add any additional columns
    migration_timestamp timestamp DEFAULT CURRENT_TIMESTAMP
);

-- Grant permissions
GRANT ALL PRIVILEGES ON target.final_table TO dms_user;
```

#### 3.2. Set Up Second DMS Task for View Migration

1. **Create Table Mappings for View Migration** (view-mappings.json):

```json
{
  "rules": [
    {
      "rule-type": "selection",
      "rule-id": "1",
      "rule-name": "1",
      "object-locator": {
        "schema-name": "staging",
        "table-name": "combined_view"
      },
      "rule-action": "include"
    },
    {
      "rule-type": "transformation",
      "rule-id": "2",
      "rule-name": "2",
      "rule-action": "rename",
      "rule-target": "schema",
      "object-locator": {
        "schema-name": "staging"
      },
      "value": "target"
    },
    {
      "rule-type": "transformation",
      "rule-id": "3",
      "rule-name": "3",
      "rule-action": "rename",
      "rule-target": "table",
      "object-locator": {
        "schema-name": "staging",
        "table-name": "combined_view"
      },
      "value": "final_table"
    }
  ]
}
```

2. **Create DMS Task Settings for View Migration** (view-task-settings.json):

```json
{
  "TargetMetadata": {
    "TargetSchema": "target",
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
    "EnableLogging": true
  }
}
```

3. **Create a New DMS Task for View Migration**:

```bash
aws dms create-replication-task \
  --replication-task-identifier view-to-target-task \
  --source-endpoint-arn arn:aws:dms:region:account:endpoint:postgresql-target-endpoint \
  --target-endpoint-arn arn:aws:dms:region:account:endpoint:postgresql-target-endpoint \
  --replication-instance-arn arn:aws:dms:region:account:rep:dms-replication-instance \
  --migration-type full-load \
  --table-mappings file://view-mappings.json \
  --replication-task-settings file://view-task-settings.json \
  --region your-aws-region
```

4. **Start the View Migration Task**:

```bash
aws dms start-replication-task \
  --replication-task-arn arn:aws:dms:region:account:task:view-to-target-task \
  --start-replication-task-type start-replication \
  --region your-aws-region
```

#### 3.3. Alternative: Direct SQL-based Migration

If DMS has trouble with the view, you can use SQL for the final step:

```sql
-- Connect to PostgreSQL Database 1
-- Migrate data from view to target table
INSERT INTO target.final_table (
    id, column1, column2, name, value, source_created_at, related_created_at
)
SELECT 
    id, column1, column2, name, value, source_created_at, related_created_at
FROM 
    staging.combined_view;
```

### Step 4: Set Up Ongoing Synchronization (Optional)

If you need ongoing data synchronization from Oracle to the final target table:

#### 4.1. Create a Trigger-Based Approach

```sql
-- Connect to PostgreSQL Database 1
-- Create a function to handle updates
CREATE OR REPLACE FUNCTION staging.update_final_table()
RETURNS TRIGGER AS $$
BEGIN
    -- For inserts/updates to the staging table
    UPSERT INTO target.final_table (
        id, column1, column2, name, value, source_created_at, related_created_at
    )
    SELECT 
        s.id, s.column1, s.column2, f.name, f.value, s.created_at, f.created_at
    FROM 
        staging.staging_table s
    JOIN 
        staging.foreign_table f ON s.id = f.id
    WHERE 
        s.id = NEW.id;
        
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create trigger on staging table
CREATE TRIGGER sync_to_final_table
AFTER INSERT OR UPDATE ON staging.staging_table
FOR EACH ROW
EXECUTE FUNCTION staging.update_final_table();
```

### Step 5: Validation and Monitoring

#### 5.1. Validate Data Migration

```sql
-- Check row counts
SELECT COUNT(*) FROM staging.staging_table;
SELECT COUNT(*) FROM staging.foreign_table;
SELECT COUNT(*) FROM staging.combined_view;
SELECT COUNT(*) FROM target.final_table;

-- Validate data integrity
SELECT 
    (SELECT COUNT(*) FROM staging.combined_view) AS view_count,
    (SELECT COUNT(*) FROM target.final_table) AS target_count,
    CASE 
        WHEN (SELECT COUNT(*) FROM staging.combined_view) = (SELECT COUNT(*) FROM target.final_table)
        THEN 'MATCH'
        ELSE 'MISMATCH'
    END AS status;

-- Check for missing records
SELECT v.id
FROM staging.combined_view v
LEFT JOIN target.final_table t ON v.id = t.id
WHERE t.id IS NULL;
```

#### 5.2. Monitor DMS Tasks

```bash
# Check task status
aws dms describe-replication-tasks \
  --filters Name=replication-task-arn,Values=arn:aws:dms:region:account:task:oracle-to-postgres-task \
  --region your-aws-region

aws dms describe-replication-tasks \
  --filters Name=replication-task-arn,Values=arn:aws:dms:region:account:task:view-to-target-task \
  --region your-aws-region

# Monitor CloudWatch logs
aws logs get-log-events \
  --log-group-name /aws/dms/replication-task/oracle-to-postgres-task \
  --log-stream-name dms-task-oracle-to-postgres-task \
  --limit 10
```

### Troubleshooting

#### Foreign Data Wrapper Issues

```sql
-- Test FDW connection
SELECT * FROM staging.foreign_table LIMIT 10;

-- Check PostgreSQL logs for FDW errors
-- In AWS RDS, check the PostgreSQL error logs in CloudWatch
```

#### DMS Task Failures

```bash
# Get detailed task status
aws dms describe-replication-task-assessment-results \
  --replication-task-arn arn:aws:dms:region:account:task:oracle-to-postgres-task

# Check table statistics
aws dms describe-table-statistics \
  --replication-task-arn arn:aws:dms:region:account:task:oracle-to-postgres-task
```

#### View Migration Issues

```sql
-- Verify view access
SELECT * FROM staging.combined_view LIMIT 10;

-- Check for any errors in target table constraints
SELECT * FROM information_schema.constraint_column_usage
WHERE table_schema = 'target' AND table_name = 'final_table';
```

This implementation plan provides all the necessary steps to migrate data from an on-premises Oracle database to AWS PostgreSQL, create a cross-database view, and load the final data into a target table within the same PostgreSQL database.
