# DynamoDB Scaling

### Core Scaling Dimensions

DynamoDB scaling operates across two primary dimensions:

1. Throughput Capacity: Managed through read and write capacity units
2. Storage Size: Individual items capped at 400KB, with unlimited total storage potential

<figure><img src="../../../../.gitbook/assets/image (20) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Partition Management

#### Partition Fundamentals

* Each partition is a 10GB physical storage unit
* Records are organized using two key types:
  * Partition Key (Hash Key): Primary identifier for record distribution
  * Sort Key (Range Key): Optional secondary key for record organization

<figure><img src="../../../../.gitbook/assets/image (21) (1) (1).png" alt=""><figcaption></figcaption></figure>



#### Partition Calculation Formula

<figure><img src="../../../../.gitbook/assets/image (22) (1) (1).png" alt=""><figcaption></figcaption></figure>

DynamoDB capacity is measured in two units:

1. Read Capacity Units (RCU)
2. Write Capacity Units (WCU)

Read Capacity Units (RCU):

* 1 RCU = 1 strongly consistent read per second for items up to 4KB
* 1 RCU = 2 eventually consistent reads per second for items up to 4KB
* For items larger than 4KB, additional RCUs are needed (rounded up)

Write Capacity Units (WCU):

* 1 WCU = 1 write per second for items up to 1KB
* For items larger than 1KB, additional WCUs are needed (rounded up)



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

<figure><img src="../../../../.gitbook/assets/image (23) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Data Distribution and Hot Partition Prevention

#### Hash Distribution

* Partition keys are converted to hash values
* Hash ranges are divided equally among partitions
* With 2 partitions: 50% per partition

<figure><img src="../../../../.gitbook/assets/image (25) (1) (1).png" alt=""><figcaption></figcaption></figure>

* With 3 partitions: 33.3% per partition

<figure><img src="../../../../.gitbook/assets/image (24) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Avoiding Hot Partitions

Poor Design Example is using date as partition key for sensor data:

* Problem: Concentrates writes on single partition for current date
* Result: Underutilized capacity on other partitions



<figure><img src="../../../../.gitbook/assets/image (26) (1) (1).png" alt=""><figcaption></figcaption></figure>



Optimized Design:

* Use sensor\_id as partition key
* Use date as sort key
* Benefit: Distributes load across partitions naturally

<figure><img src="../../../../.gitbook/assets/image (27) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Scaling Methods

#### Auto Scaling

This diagram below illustrates an AWS architecture for DynamoDB auto-scaling notification workflow. Let me break down the numbered components and their interactions:

1. A user or application initiates an interaction with the DynamoDB table (shown by arrow 1)
2. There's a direct connection from the DynamoDB table to Amazon CloudWatch (shown by arrow 2)
3. CloudWatch communicates with Amazon SNS (Simple Notification Service) (shown by arrow 3)
4. CloudWatch triggers the Application Auto Scaling service (shown by arrow 4)
5. The Application Auto Scaling service sends an UpdateTable command to the DynamoDB table (shown by arrow 5)
6. The DynamoDB table is updated with new capacity settings (shown by arrow 6)

The workflow demonstrates how AWS automatically handles scaling for DynamoDB:

* CloudWatch monitors the DynamoDB table's metrics
* When thresholds are reached, it triggers notifications through SNS
* The Auto Scaling service adjusts the table's capacity accordingly
* Changes are applied back to the DynamoDB table

This is a common pattern for implementing automatic scaling of DynamoDB resources based on actual usage patterns, helping to maintain performance while optimizing costs.

The warning symbol (⚠️) next to CloudWatch suggests that this component is responsible for monitoring and alerting on any issues or threshold breaches.

<figure><img src="../../../../.gitbook/assets/image (28) (1) (1).png" alt=""><figcaption></figcaption></figure>

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

Best for unpredictable workloads or new applications with unclear requirements.

* Alternative to traditional auto scaling
* Advantages:
  * No advance capacity provisioning
  * Immediate scaling response
* Disadvantages:
  * Higher cost premium

### DynamoDB Accelerator (DAX)

<figure><img src="../../../../.gitbook/assets/image (29) (1) (1).png" alt=""><figcaption></figcaption></figure>

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
