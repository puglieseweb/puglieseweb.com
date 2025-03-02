# Join tables separate PostgreSQL databases

When we have two different PostgreSQL databases in AWS, you can join tables from these separate databases using PostgreSQL's Foreign Data Wrapper (FDW) functionality to create views or materialized views. This is an excellent approach for your scenario.

Here's how to set it up:

#### 1. Enable the PostgreSQL Foreign Data Wrapper

First, you need to enable the `postgres_fdw` extension in your primary PostgreSQL database:

```sql
CREATE EXTENSION postgres_fdw;
```

#### 2. Create a Foreign Server Connection

Set up a connection to your second PostgreSQL database:

```sql
CREATE SERVER foreign_server
FOREIGN DATA WRAPPER postgres_fdw
OPTIONS (host 'second-db-hostname.region.rds.amazonaws.com', port '5432', dbname 'second_database');
```

#### 3. Create a User Mapping

Map your local database user to the remote database credentials:

```sql
CREATE USER MAPPING FOR current_user
SERVER foreign_server
OPTIONS (user 'remote_username', password 'remote_password');
```

#### 4. Create Foreign Tables

Create foreign tables that reference the tables in your second database:

```sql
CREATE FOREIGN TABLE foreign_table (
    id integer,
    name text,
    value numeric,
    created_at timestamp
)
SERVER foreign_server
OPTIONS (schema_name 'public', table_name 'original_table_name');
```

#### 5. Create a View Joining Local and Foreign Tables

Now you can create a view that joins local tables with the foreign tables:

```sql
CREATE VIEW combined_view AS
SELECT 
    l.id, 
    l.customer_id, 
    l.order_date,
    f.name AS product_name,
    f.value AS product_price
FROM 
    local_orders l
JOIN 
    foreign_table f ON l.product_id = f.id;
```

#### 6. Create a Materialized View (for performance)

For better performance, especially with larger datasets, you can create a materialized view:

```sql
CREATE MATERIALIZED VIEW combined_mat_view AS
SELECT 
    l.id, 
    l.customer_id, 
    l.order_date,
    f.name AS product_name,
    f.value AS product_price
FROM 
    local_orders l
JOIN 
    foreign_table f ON l.product_id = f.id;

-- Create an index on the materialized view
CREATE INDEX idx_combined_mat_view_id ON combined_mat_view(id);
```

You'll need to refresh the materialized view when data changes:

```sql
REFRESH MATERIALIZED VIEW combined_mat_view;
```

#### AWS-Specific Considerations

1. **Security Groups**: Ensure the security groups for both RDS instances allow traffic between them
2. **VPC Configuration**: Both databases should be in the same VPC or have VPC peering configured
3. **IAM Permissions**: If using IAM authentication, ensure proper permissions are set
4. **Network Performance**: Cross-database queries may have higher latency, so materialized views are often preferable for frequent access patterns
5. **RDS Parameter Groups**: You might need to modify parameter groups to enable FDW if it's not enabled by default

Using this approach, you can effectively join tables from two different PostgreSQL databases in AWS without the limitations of DMS for cross-database joins.
