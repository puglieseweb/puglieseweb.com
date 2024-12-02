# Transaction VS Operation DBs

Transactional Databases (OLTP - Online Transaction Processing):

1. Primarily handle day-to-day transactions and real-time operations
2. Optimized for fast, small-scale read/write operations
3. Focus on maintaining data consistency and ACID properties (Atomicity, Consistency, Isolation, Durability)
4. Typically use normalized data structures to minimize redundancy
5. Example use cases: banking transactions, order processing, inventory management
6. Usually handle current, in-the-moment data
7. Designed for many concurrent users making small changes

Operational Databases (OLAP - Online Analytical Processing):

1. Designed for business analysis and decision support
2. Optimized for complex queries and large-scale data aggregation
3. Focus on data analysis and reporting rather than transaction processing
4. Often use denormalized data structures for faster querying
5. Example use cases: business intelligence, trend analysis, forecasting
6. Usually contain historical data spanning longer time periods
7. Designed for fewer users running complex queries on large datasets



### Common Architectural patterns

For Transactional Databases:

1. Primary-Replica Pattern
   * Primary node handles writes
   * Multiple read replicas for scaling reads
   * Synchronous or asynchronous replication
2. Sharding Pattern
   * Data distributed across multiple nodes
   * Horizontal partitioning based on key ranges or hash values
   * Each shard handles subset of total data

For Operational Databases:

1. Star Schema
   * Fact tables in the center
   * Dimension tables around the edges
   * Optimized for analytical queries
2. Data Lake Architecture
   * Raw data storage
   * Schema-on-read approach
   * Multiple processing engines
3. Lambda Architecture
   * Batch processing layer
   * Speed layer for real-time processing
   * Serving layer for query results

```mermaid
graph TD
    subgraph "Transactional DB Patterns"
        A[Client Application] --> B[Load Balancer]
        B --> C1[Primary DB]
        C1 --> D1[Replica 1]
        C1 --> D2[Replica 2]
        
        style C1 fill:#f9f,stroke:#333
        style D1 fill:#bbf,stroke:#333
        style D2 fill:#bbf,stroke:#333
        
        subgraph "Sharding Pattern"
            E[Router] --> F1[Shard 1]
            E --> F2[Shard 2]
            E --> F3[Shard 3]
        end
    end
    
    subgraph "Operational DB Patterns"
        G[ETL Process] --> H[Data Warehouse]
        H --> I1[Data Mart 1]
        H --> I2[Data Mart 2]
        
        J[Stream Processing] --> K[Real-time Analytics]
        
        style H fill:#f96,stroke:#333
        style I1 fill:#9f6,stroke:#333
        style I2 fill:#9f6,stroke:#333
    end
```





#### Star Schema Pattern

Here are some official resources about Star Schema:

IBM Documentation: https://www.ibm.com/docs/en/ida/9.1.2?topic=schemas-star-schema

Microsoft Documentation: https://learn.microsoft.com/en-us/power-bi/guidance/star-schema

Let me create a visual representation of a typical star schema:



```mermaid
erDiagram
    FACT_SALES ||--o{ DIM_CUSTOMER : has
    FACT_SALES ||--o{ DIM_PRODUCT : has
    FACT_SALES ||--o{ DIM_DATE : has
    FACT_SALES ||--o{ DIM_STORE : has

    FACT_SALES {
        int sale_id PK
        int customer_id FK
        int product_id FK
        int date_id FK
        int store_id FK
        decimal amount
        int quantity
        decimal discount
    }

    DIM_CUSTOMER {
        int customer_id PK
        string name
        string email
        string segment
        string address
    }

    DIM_PRODUCT {
        int product_id PK
        string name
        string category
        string sub_category
        decimal price
    }

    DIM_DATE {
        int date_id PK
        date full_date
        int year
        int quarter
        int month
        string month_name
    }

    DIM_STORE {
        int store_id PK
        string name
        string city
        string state
        string country
    }

```

Key characteristics of Star Schema:

1. Central Fact Table:
   * Contains business metrics (measurements)
   * Foreign keys to all dimension tables
   * Typically numeric and additive
2. Dimension Tables:
   * Contain descriptive attributes
   * Connected to fact table via primary keys
   * Denormalized for query performance
3. Advantages:
   * Simple to understand and navigate
   * Optimized for OLAP queries
   * Efficient for aggregations and joins
4. Common Use Cases:
   * Sales analysis
   * Financial reporting
   * Inventory management
   * Customer behavior analysis

Would you like me to explain any specific aspect of star schema design or show how it compares to other schema types like snowflake schema?

### Resources



Transactional (OLTP) Database Resources:

1. PostgreSQL Official Documentation on High Availability: [https://www.postgresql.org/docs/current/high-availability.html](https://www.postgresql.org/docs/current/high-availability.html)
2. MySQL Documentation on Replication: [https://dev.mysql.com/doc/refman/8.0/en/replication.html](https://dev.mysql.com/doc/refman/8.0/en/replication.html)
3. Microsoft SQL Server Always On Architecture: [https://learn.microsoft.com/en-us/sql/database-engine/availability-groups/windows/always-on-availability-groups-sql-server](https://learn.microsoft.com/en-us/sql/database-engine/availability-groups/windows/always-on-availability-groups-sql-server)

Operational (OLAP) Database Resources:

1. Apache Hadoop Documentation: [https://hadoop.apache.org/docs/stable/](https://hadoop.apache.org/docs/stable/)
2. Snowflake Architecture Guide: [https://docs.snowflake.com/en/user-guide/intro-key-concepts](https://docs.snowflake.com/en/user-guide/intro-key-concepts)
3. Amazon Redshift Architecture: [https://docs.aws.amazon.com/redshift/latest/dg/c\_high\_level\_system\_architecture.html](https://docs.aws.amazon.com/redshift/latest/dg/c_high_level_system_architecture.html)
