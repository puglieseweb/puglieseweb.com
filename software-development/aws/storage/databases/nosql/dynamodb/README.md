# DynamoDB

DynamoDB is a fully managed NoSQL database service offering consistent, single-digit millisecond latency at any scale. It supports both **document and key-value data models**, making it ideal for mobile, web, gaming, ad-tech, IoT, and many other applications.

### Core Features

#### Storage and Consistency

* Stored on SSD storage
* Data spread across 3 geographically distinct data centers
* Two consistency models:
  * Eventually consistent reads (default): Updates typically propagate within one second
  * Strongly consistent reads: Returns results reflecting all successful writes prior to the read

#### DynamoDB Accelerator (DAX)

* Fully managed, highly available, in-memory cache
* Improves performance by 10x
* Reduces request time from milliseconds to microseconds
* Automatically handles caching logic
* Compatible with DynamoDB API calls

#### Capacity Options

**On-Demand Capacity:**

* Pay-per-request pricing
* No minimum capacity requirements
* Charges only for storage and backups
* Higher per-request cost compared to provisioned capacity
* Ideal for new product launches

#### Security Features

* KMS encryption at rest
* Site-to-site VPN support
* Direct Connect (DX) integration
* IAM policies and roles
* Fine-grained access control
* CloudWatch and CloudTrail integration
* VPC endpoints

### Advanced Features

#### DynamoDB Transactions

Supports ACID transactions across multiple tables within the same AWS account and region:

* Atomic: All changes must succeed or none are applied
* Consistent: Data remains consistent before and after transactions
* Isolated: Transactions are protected from interference
* Durable: Changes persist after completion

Transaction options:

* Read options: eventual consistency, strong consistency, or transactional
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
