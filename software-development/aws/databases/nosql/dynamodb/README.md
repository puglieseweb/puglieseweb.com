# DynamoDB

Amazon DynameDB is a fast flexible NoSQL database service for all applications that need consistent, single-digit millisecond latency at any scale.

It is fully managed database and supports both **document and key-value data models**.

Its flexible data model and reliable performance make it a great fit for mobile, web, gaming, ad-tech, IoT, and may other applications.



### Key characteristics:&#x20;

* Stored on SSD storage
* Spread across 3 geographically distinct data centers
* Eventually consistent reads (default). Consistency across all copies of data is usually reached within a second. Repeating a read after a short time should return the updated data. Best read performance.
* Strongly consistent reads. Returns a result that reflects all writes that received a successful response prior to the read.

### DynameDB Accelerator (DAX)

* Fully managed, highly available, in-memory cache
* 10x performance improvement&#x20;
* Reduces request time from milliseconds to microseconds - even under load
* No need for developers to manage caching logic
* Compatible with DynamoDB API calls.

### On-Demand Capacity

* Pay-per-request pricing
* Balance cost and performance&#x20;
* No minimum capacity&#x20;
* No charge for read/write - only storage and backups
* pay more per request than with provisioned capacity
* Use for new product launches&#x20;

### Security

* Encryption at rest using KMS
* Site-to-site VPN
* Direct Connect (DX)
* IAM policies and roles
* Fine-grained access
* Integrates with CloudWatch and CloudTrail
* VPC endpoints&#x20;



### DynomoDB Transactions

DynomeDB Transactions enable ACID (all or nothing) across 1 or more tables withing a single AWS account and region.

ACID:

* Atomic: All changes to the data must be performed successfully or not at all
* Consistent: Data must be in a consistent state before and after the transaction
* Isolated: No other process can change the data while the transaction is running
* Durable: The changes made by a transaction must persist.

Transaction has:

* 3 options for reads:&#x20;
  * eventual consistency
  * strong consistency
  * transactional
* 2 options for write:
  * standard
  * transactional
* Up to 25 items or 4 MB of data



### On-Demand Backup and Restore

* Full backup at any time
* Zero impact on table performance or availability&#x20;
* Consistent within seconds and retained until deleted
* Operats within same region as teh source table

### Point-in-time recovery

* Protects against accidental writes or deletes&#x20;
* Restore to any point in the last **35 days**
* Incremental backups
* Not enabled by default
* Latest restorable: **5 minutes in the past**

### **DynamoDB Stream**

DynamoDB Steam: Time-ordered sequence of item-level changes in a table

A **Stream** is divided  in **Shards** and a Shard contains multiple **Stream Data.**

Streams are stored for 24 hours

Inserts, updates, and deletes&#x20;

Combine with Lambda functions for functionalities like stored procedures

## Global Tables&#x20;

Managed multi-master, multi-region replication

* Globally distributed applications
* Based on DynamoDB streams
* Multi-region redundancy for disaster recovery or high availability
* No application rewrites
* Replication latency under **1 second**&#x20;

### How to enable DynamoDB replica across reagion:

1. Enable Global tables
2. Create a replica on another rigion (not that DynamoDB Stream must be enabled)



## Key notes

* **Partitions**: DynamoDB automatically spreads data across partitions. Understanding partition keys and their impact on performance is crucial.
* **Secondary Indexes**: DynamoDB supports both Global Secondary Indexes (GSI) and Local Secondary Indexes (LSI). Know their differences and use cases.
* **Read/Write Capacity Units**: Understand how RCUs and WCUs are calculated, especially for different item sizes and consistency models.
* **Scaling**: Be familiar with both auto-scaling for provisioned capacity and the automatic scaling of on-demand capacity.
* **Integration**: DynamoDB integrates well with other AWS services. Know common patterns like using Lambda with DynamoDB Streams or using DynamoDB as a session store for applications.
* **Cost Optimization**: Understand strategies for optimizing DynamoDB costs, such as choosing the right capacity mode, using DAX for caching, and leveraging TTL (Time to Live) for automatic item deletion.
* **Limits**: Be aware of DynamoDB's various limits, such as maximum item size (400 KB) and maximum partition key size (2 KB).



