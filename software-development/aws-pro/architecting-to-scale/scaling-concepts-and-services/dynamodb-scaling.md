# DynamoDB Scaling

### Core Scaling Dimensions

DynamoDB scaling operates across two primary dimensions:

1. Throughput Capacity: Managed through read and write capacity units
2. Storage Size: Individual items capped at 400KB, with unlimited total storage potential

<figure><img src="../../../../.gitbook/assets/image (20) (1).png" alt=""><figcaption></figcaption></figure>

### Partition Management

#### Partition Fundamentals

* Each partition is a 10GB physical storage unit
* Records are organized using two key types:
  * Partition Key (Hash Key): Primary identifier for record distribution
  * Sort Key (Range Key): Optional secondary key for record organization

<figure><img src="../../../../.gitbook/assets/image (21) (1).png" alt=""><figcaption></figcaption></figure>



#### Partition Calculation Formula

<figure><img src="../../../../.gitbook/assets/image (22) (1).png" alt=""><figcaption></figcaption></figure>

The total number of partitions is determined by the maximum of:

1. Capacity-based calculation: (Total Read Capacity Units + Total Write Capacity Units) / 3000
2. Size-based calculation: Total Size / 10GB

Example:

* Table Size: 10GB
* Read Capacity: 2000 units
* Write Capacity: 2000 units
* Capacity Calculation:  2000/3000 + 2000 / 1000 = 2.66
* Size Calculation: 10GB / 10GB = 1
* Final Partition Count: 3 (rounded up from 2.66)

<figure><img src="../../../../.gitbook/assets/image (23) (1).png" alt=""><figcaption></figcaption></figure>

### Data Distribution and Hot Partition Prevention

#### Hash Distribution

* Partition keys are converted to hash values
* Hash ranges are divided equally among partitions
* With 2 partitions: 50% per partition

<figure><img src="../../../../.gitbook/assets/image (25) (1).png" alt=""><figcaption></figcaption></figure>

* With 3 partitions: 33.3% per partition

<figure><img src="../../../../.gitbook/assets/image (24) (1).png" alt=""><figcaption></figcaption></figure>

#### Avoiding Hot Partitions

Poor Design Example:

* Using date as partition key for sensor data
* Problem: Concentrates writes on single partition for current date
* Result: Underutilized capacity on other partitions



<figure><img src="../../../../.gitbook/assets/image (26) (1).png" alt=""><figcaption></figcaption></figure>



Optimized Design:

* Use sensor\_id as partition key
* Use date as sort key
* Benefit: Distributes load across partitions naturally

<figure><img src="../../../../.gitbook/assets/image (27) (1).png" alt=""><figcaption></figcaption></figure>

### Scaling Methods

#### Auto Scaling

<figure><img src="../../../../.gitbook/assets/image (28) (1).png" alt=""><figcaption></figcaption></figure>

1. Configuration:
   * Set provisioned capacity limits
   * Define target utilization
   * CloudWatch monitors usage
   * Application Auto Scaling adjusts capacity
2. Limitations:
   * **Doesn't effectively scale down with zero usage**
   * Workarounds:
     * Maintain minimal request flow
     * Manually set max capacity to match minimum
3. Features:
   * Supports global secondary indexes
   * Uses target tracking for utilization

#### On-Demand Scaling

* Alternative to traditional auto scaling
* Advantages:
  * No advance capacity provisioning
  * Immediate scaling response
* Disadvantages:
  * Higher cost premium
  * Best for unpredictable workloads or new applications

### DynamoDB Accelerator (DAX)

<figure><img src="../../../../.gitbook/assets/image (29) (1).png" alt=""><figcaption></figcaption></figure>

#### Overview

* In-memory caching layer
* Compatible with DynamoDB API
* Provides microsecond response times

#### Use Cases

Recommended for:

* Applications requiring ultra-fast lookups
* Read-intensive workloads

Not recommended for:

* Write-intensive applications
* Applications with built-in local caching

### Best Practices

1. Partition Key Design:
   * Choose keys with high cardinality
   * Ensure even distribution of access patterns
   * Avoid temporal or sequential keys for high-volume data
2. Scaling Strategy:
   * Start with auto scaling for predictable workloads
   * Consider on-demand scaling for new applications
   * Monitor and adjust based on usage patterns
3. Performance Optimization:
   * Implement DAX for read-heavy workloads
   * Design key schema to prevent hot partitions
   * Regular monitoring of capacity utilization
