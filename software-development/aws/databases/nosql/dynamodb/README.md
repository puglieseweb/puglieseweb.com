# DynamoDB

DynamoDB is a fully managed NoSQL database service offering consistent, single-digit millisecond latency at any scale. It supports both **document and key-value data models**, making it ideal for mobile, web, gaming, ad-tech, IoT, and many other applications.

### Key Points

1. **Switching between modes**:
   * Can switch between Provisioned and On-Demand **once per 24 hours**
2. **Global Tables**:
   * Both modes support **DynamoDB Global Tables for multi-region replication**
3. **Backup and Restore**:
   * Both modes support on-demand backup and restore
4. **Read Consistency**:
   * **Both modes support eventually consistent and strongly consistent reads**
5. **DAX (DynamoDB Accelerator)**:
   * Compatible with both capacity modes
6. **Use case identification**:
   * Be prepared to recommend the appropriate capacity mode based on given scenarios

###

### Core Features

#### Storage and Consistency

* Stored on SSD storage
* Data spread across 3 geographically distinct Availability Zones (AZs) withign the same Region.
* Two consistency models:
  * **Eventually consistent reads** (default): Updates typically propagate within one second
  * **Strongly consistent reads**: Returns results reflecting all successful writes prior to the read

#### DynamoDB Accelerator (DAX)

* Fully managed, highly available, in-memory cache
* Improves performance by 10x
* Reduces request time from milliseconds to **microseconds**
* Automatically handles caching logic
* Compatible with DynamoDB API calls

## Capacity Modes Options

DynamoDB has three capacity modes for managing throughput:

### **1. Provisioned Capacit**y (default)

* You specify Read Capacity Units (RCU) and Write Capacity Units (WCU)
* Pay for provisioned capacity whether used or not
* Can enable Auto Scaling
* Good for predictable workloads

#### Capacity Units Calculation

* **Use Case**:
  * For predictable workloads
  * When you can forecast read and write capacity requirements
*   **How it works**:

    You specify Read Capacity Units (RCUs) and Write Capacity Units (WCUs)

    * 1 RCU = 1 strongly consistent read/sec for items up to 4KB
    * 1 RCU = 2 eventually consistent reads/sec for items up to 4KB
    * 1 WCU = 1 write/sec for items up to 1KB
* **Scaling**:
  * Can use Auto Scaling to automatically adjust provisioned capacity
  * Set upper and lower bounds for scaling
* **Cost**:
  * Most cost-effective for predictable workloads
  * Pay for provisioned capacity whether you use it or not
* **Performance**:
  * Consistent performance if capacity is set correctly
  * Can lead to throttling if capacity is set too low
* **Effort**:
  * Requires monitoring and adjusting capacity based on usage patterns
  * Need to review past usage to set appropriate scaling bounds



### **2. On-Demand Capacity**



* Pay-per-request pricing
* No capacity planning needed
* More expensive per request
* Good for unpredictable workloads
* No throttling
* **Use Case**:
  * For unpredictable workloads
  * New applications with unknown traffic patterns
  * Applications with sporadic, short-lived spikes in traffic
* **How it works**:
  * Automatically scales up and down based on actual traffic
  * No need to specify RCUs and WCUs
* **Scaling**:
  * Instantaneous and unlimited scaling
  * Can handle sudden and large spikes in traffic
* **Cost**:
  * Pay per request pricing
  * Generally more expensive than well-optimized Provisioned capacity
  * No charges for read and write capacity that you don't use
* **Performance**:
  * Consistent performance regardless of load
  * No throttling due to capacity misconfigurations
* **Effort**:
  * Simple to use - just select On-Demand mode
  * No capacity planning required

### **3. Global Tables**

* Multi-region write capability
* Uses On-Demand or Provisioned
* Active-active configuration
* Sub-second latency

## DynamoDB limits and constraints

Item Size Limits:

* **Maximum item size: 400 KB** (including attribute names and values)

Table Limits:

* No limit on number of items per table
* No limit on table size
* Maximum number of tables per account: 2500 by default (can be increased)
* Maximum partition key length: 2048 bytes
* Maximum sort key length: 1024 bytes

Throughput Limits (for provisioned capacity mode):

* Per table minimum: 1 RCU and 1 WCU
* Per table maximum: 40,000 RCU and 40,000 WCU (can be increased)
* Per partition: 3,000 RCU and 1,000 WCU

Secondary Indexes:

* Maximum number of local secondary indexes per table: 5
* Maximum number of global secondary indexes per table: 20
* Each secondary index adds another copy of data with different key schema

API Specific Limits:

* BatchGetItem: Up to 100 items or 16 MB per request
* BatchWriteItem: Up to 25 items or 16 MB per request
* Query and Scan: Result set size limit of 1 MB per call
* Transactional operations: Up to 25 items or 4 MB per transaction

Attribute Naming and Data Types:

* Attribute names: Maximum length of 64 KB
* Number of attributes per item: No fixed limit, but must fit within 400 KB item size
* Supports data types: String, Number, Binary, Boolean, Null, Lists, Maps, Sets

Time To Live (TTL):

* One TTL attribute per table
* Must be a Number data type representing epoch timestamp

## Auto Scaling Settings

* Target utilization (default 70%)
* Minimum capacity
* Maximum capacity

## Security Features

* KMS encryption at rest
* Site-to-site VPN support
* Direct Connect (DX) integration
* IAM policies and roles
* Fine-grained access control
* CloudWatch and CloudTrail integration
* VPC endpoints

## Advanced Features

#### DynamoDB Transactions

Supports ACID transactions across multiple tables within the same AWS account and region:

* Atomic: All changes must succeed or none are applied
* Consistent: Data remains consistent before and after transactions
* Isolated: Transactions are protected from interference
* Durable: Changes persist after completion

Transaction options:

* Read options:&#x20;
  * eventual consistency (default): When reading data, you might get a response that doesn't reflect the results of a recently completed write
  * strong consistency: Provides consistency for a single item operation. Doesn't provide isolation from concurrent modifications
  * transactional: Provides consistency across multiple items and tables (ACID transaction)
* Write options: standard or transactional
* Limits: Up to 25 items or 4 MB of data

#### Backup and Recovery

**On-Demand Backup:**

* Full backups available anytime
* No performance impact
* Consistent within seconds
* Same-region operation

**Point-in-Time Recovery:**

* **35-day recovery window**
* Incremental backup system
* **5-minute recovery point objective (RPO)**
* Optional feature

#### DynamoDB Streams

A **Stream** is divided into **Shards** and a shard contains **Stream Data**.

* Time-ordered sequence of item-level modifications
* 24-hour retention period
* Captures inserts, updates, and deletes
* Lambda function integration for stored procedure-like functionality

#### Global Tables

* Multi-master, multi-region replication
* Sub-second replication latency
* Built on DynamoDB streams
* Requires streams enablement
* No application modifications needed
* Replication latency is under **1 second**

### Important Considerations

#### Technical Aspects

* Partition management is automatic but requires understanding of partition keys
* Supports Global Secondary Indexes (GSI) and Local Secondary Indexes (LSI)
* Read/Write Capacity Units (RCU/WCU) calculations vary by item size and consistency
* Auto-scaling available for both provisioned and on-demand capacity

#### Integration and Optimization

* Seamless integration with AWS services
* Cost optimization through capacity mode selection, DAX usage, and TTL
* Key limits: 400 KB maximum item size, 2 KB maximum partition key size
* Common patterns include Lambda integration and session state management

\
