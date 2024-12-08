# Databases

## Relational Databases vs Data Warehouses

### Relational Databases (OLTP - Online Transaction Processing)

1. **Purpose**
   * Designed for day-to-day operations
   * Handles real-time transactions and updates
   * Manages current, operational data
2. **Characteristics**
   * Optimized for fast, small transactions
   * Normalized data structure (reduces redundancy)
   * Frequent updates/writes
   * Typically smaller data volume
   * Row-oriented storage
3. **Use Cases**
   * Customer orders
   * Inventory management
   * Banking transactions
   * User accounts
   * Hotel/airline reservations

### Data Warehouses (OLAP - Online Analytical Processing)

1. **Purpose**
   * Designed for business intelligence
   * Historical data analysis
   * Reporting and complex queries
2. **Characteristics**
   * Optimized for complex queries
   * Denormalized data structure (for query performance)
   * Primarily read operations
   * Much larger data volumes
   * Column-oriented storage
   * Stores historical data over time
3. **Use Cases**
   * Business reporting
   * Sales trend analysis
   * Financial forecasting
   * Customer behavior analysis
   * Performance metrics

### Key Differences

| Aspect             | Relational Database         | Data Warehouse       |
| ------------------ | --------------------------- | -------------------- |
| Data Updates       | Continuous, real-time       | Periodic batch loads |
| Query Type         | Simple, standardized        | Complex, ad-hoc      |
| Data Scope         | Current                     | Historical           |
| Size               | Gigabytes                   | Terabytes/Petabytes  |
| Users              | Operations staff, customers | Analysts, executives |
| Performance Metric | Transaction speed           | Query response time  |
| Data Model         | Highly normalized           | Often denormalized   |

### Example

Imagine a retail company:

* **Relational Database**: Handles current inventory, processing sales, managing customer accounts
* **Data Warehouse**: Analyzes sales patterns over years, customer buying behavior, seasonal trends, and store performance comparisons

## OLTP vs OLAP

OLTP (Online Transaction Processing) and OLAP (Online Analytical Processing) are two fundamental types of data processing systems, each designed for different purposes and use cases. Here's a comprehensive comparison:

### 1. Purpose

* **OLTP**: Manages transaction-oriented applications, typically for data entry and retrieval transaction processing.
* **OLAP**: Designed for complex analytical queries and business intelligence.

### 2. Data Model

* **OLTP**: Normalized data model to ensure data integrity and reduce redundancy.
* **OLAP**: Denormalized data model (often star schema or snowflake schema) for faster query performance.

### 3. Data Freshness

* **OLTP**: Real-time, up-to-date data.
* **OLAP**: Typically uses historical, summarized, and consolidated data.

### 4. Query Complexity

* **OLTP**: Simple queries involving few joins.
* **OLAP**: Complex queries with multiple joins and aggregations.

### 5. Performance Measure

* **OLTP**: Transactions per second.
* **OLAP**: Query response time.

### 6. Space Requirements

* **OLTP**: Relatively small if historical data is archived.
* **OLAP**: Large due to aggregating large historical datasets.

### 7. Backup and Recovery

* **OLTP**: Regular backups (often daily) with point-in-time recovery.
* **OLAP**: Periodic full backups, potentially less frequent.

### 8. User Type

* **OLTP**: Large number of end-users, clerks, clients, customers.
* **OLAP**: Small number of power users: data scientists, business analysts, executives.

### 9. Database Design

* **OLTP**: Optimized for write operations.
* **OLAP**: Optimized for read operations.

### 10. Typical Operations

* **OLTP**: INSERT, UPDATE, DELETE
* **OLAP**: SELECT (for analysis and reporting)

### 11. Data Retention

* **OLTP**: Current, up-to-date data, typically 60-90 days.
* **OLAP**: Historical data, often several years.

### 12. Example Use Cases

* **OLTP**:
  * ATM transactions
  * Online bookings
  * E-commerce sales
* **OLAP**:
  * Business reporting
  * Budget forecasting
  * Sales analytics

### 13. Concurrency

* **OLTP**: High number of concurrent transactions.
* **OLAP**: Lower concurrency, but each query often uses more resources.

Understanding these differences is crucial for designing efficient database systems and choosing the right tools for specific business needs.



RDS for OLTP (Online Transaction Processing): Processes data for transactions in real time (e.g. customer orders, banking transactions, payments, and booking systems). OLTP is all about data processing and completing large numbers of small transactions in real time.

Redshift for OLAP (Online Analytical Processing): Processes complex queries to analyzing historical data (e.g. analyzing net profit figures from the last 3 years and sales forcasting). OLAP is all about data analysis using large amounts of data, as well as complex queries that take a log time to complete.

## AWS Database Types

Amazon Web Services (AWS) offers a wide range of database services to cater to various application needs. Here's an overview of the main types of databases provided by AWS:

### 1. Relational Databases

#### Amazon RDS (Relational Database Service)

* Supports multiple database engines: MySQL, PostgreSQL, MariaDB, Oracle, and Microsoft SQL Server
* Managed service for easy setup, operation, and scaling
* Use cases: Traditional applications, ERP, CRM, e-commerce

#### Amazon Aurora

* MySQL and PostgreSQL-compatible relational database
* Offers up to 5x performance of MySQL and 3x of PostgreSQL
* Use cases: High-performance applications, SaaS products

### 2. Key-Value and Document Databases

#### Amazon DynamoDB

* Fully managed NoSQL database
* Provides consistent single-digit millisecond latency
* Supports both key-value and document data models
* Use cases: Mobile, web, gaming, ad-tech, IoT

### 3. In-Memory Databases

#### Amazon ElastiCache

* Fully managed in-memory data store
* Supports Redis and Memcached
* Use cases: Caching, session management, gaming leaderboards, geospatial applications

### 4. Graph Databases

#### Amazon Neptune

* Fully managed graph database service
* Supports property graph and RDF models
* Use cases: Social networking, fraud detection, recommendation engines

### 5. Time Series Databases

#### Amazon Timestream

* Fully managed time series database
* Automatically scales up or down to adjust capacity and performance
* Use cases: IoT applications, DevOps, industrial telemetry

### 6. Ledger Databases

#### Amazon QLDB (Quantum Ledger Database)

* Fully managed ledger database
* Provides a transparent, immutable, and cryptographically verifiable transaction log
* Use cases: Financial transactions, supply chain, insurance claims processing

### 7. Wide Column Stores

#### Amazon Keyspaces (for Apache Cassandra)

* Scalable, highly available, and managed Apache Cassandra-compatible database service
* Use cases: IoT device data, time-series data, high-volume data with flexible schemas

### 8. Search Databases

#### Amazon OpenSearch Service (formerly Amazon Elasticsearch Service)

* Distributed search and analytics engine
* Based on open-source OpenSearch and Elasticsearch
* Use cases: Log analytics, real-time application monitoring, clickstream analytics

Each of these database types is designed to excel at specific use cases, allowing developers to choose the best tool for their particular application requirements. AWS provides these as managed services, handling much of the operational overhead and allowing teams to focus on their core application development.
