# Dynamo DB

### Core Characteristics

* Managed, multi-AZ NoSQL data store
* Supports cross-region replication
* Originally based on Amazon's Dynamo paper (2007)
* Eventual consistency model with strongly consistent read options

### Consistency Models

* Default: Eventually consistent reads
  * Propagation across AZs typically takes less than a second
  * May return stale data immediately after writes
* Optional: Strongly consistent reads
  * Returns latest data regardless of AZ propagation
  * May fail during network delays or AZ outages
* ACID Transactions support via DynamoDB transactions

### Capacity Management

#### Provisioned Capacity

* Priced based on read/write capacity units
* Auto-scaling available but has limitations:
  * Triggers based on consistent elevated requests
  * Slow to scale down
  * May require synthetic transactions for scale-down

#### On-Demand Capacity

* Alternative to provisioned capacity
* Automatically scales to meet demand
* Higher cost than well-provisioned capacity
* Ideal for unpredictable workloads

### Data Structure

#### Primary Keys

1. Simple Primary Key
   * Single partition key
   * Must be unique
   * Used for internal hash creation
2. Composite Primary Key
   * Partition key + Sort key
   * Same partition key allowed with different sort keys
   * Records stored in sort key order

<figure><img src="../../../../.gitbook/assets/image (8) (1).png" alt=""><figcaption></figcaption></figure>

#### Secondary Indexes

<figure><img src="../../../../.gitbook/assets/image (9) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (11) (1).png" alt=""><figcaption></figcaption></figure>

**Global Secondary Index (GSI)**

* Can use different partition and sort keys from main table
* "Global" - not restricted by table's partition key
* Use cases:
  * Fast queries outside primary key
  * Example: Query sales orders by customer number

**Local Secondary Index (LSI)**

* Must use same partition key as table
* Can have different sort key
* Use cases:
  * Fast queries when partition key is known
  * Example: Query specific sales order by material number

<figure><img src="../../../../.gitbook/assets/image (10) (1).png" alt=""><figcaption></figcaption></figure>

#### Index Design Considerations

* Limited to 20 attributes across all indexes
* Projection options impact performance and cost:
  * Few attributes: Minimal cost, lowest latency
  * Many attributes: Higher cost, more flexibility
  * Full table projection: Highest cost, maximum flexibility

###

### Advanced Design Patterns

#### Sparse Indexes

* Indexes only exist for items with specified attributes
* Reduces index size and read/write costs
* Useful for selective data access

#### Table Replicas via GSI

* Create replicas using identical keys
* Use cases:
  * Tiered customer access (premium vs free)
  * Separate read/write workloads
  * Note: Eventually consistent with main table

<figure><img src="../../../../.gitbook/assets/image (14) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (15) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (16) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (17) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (13) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (12) (1).png" alt=""><figcaption></figcaption></figure>

#### Flexible Schema

* NoSQL design allows varying attributes
* Can store different record types in same table
* Enables efficient querying through strategic key design

### Best Practices

* Choose partition keys that distribute data evenly
* Consider access patterns when designing indexes
* Use GSIs for frequently accessed non-key attributes
* Leverage sparse indexes to optimize costs
* Consider eventual consistency implications in design
