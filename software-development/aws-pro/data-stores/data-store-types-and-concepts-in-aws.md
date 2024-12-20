# Data Store Types and Concepts in AWS



### Types of Data Stores

#### 1. Persistent Data Store

* Data is durable and persists across reboots or power cycles
* Examples:
  * Amazon Glacier
  * Amazon RDS (Relational Database Service)

#### 2. Transient Data Store

* Data is temporarily stored and passed through to another process
* Examples:
  * Amazon SQS (Simple Queue Service)
  * Amazon SNS (Simple Notification Service)

#### 3. Ephemeral Data Store

* Data is temporary and does not persist when instances are stopped or services rebooted
* Examples:
  * EC2 Instance Store
  * Memcached (under Elastic Cache portfolio)

### Performance Metrics

#### IOPS (Input/Output Operations Per Second)

* Measures how fast we can read and write to a device
* Analogous to a small, fast sports car:
  * High speed
  * Smaller data payload
  * Optimized for quick operations

#### Throughput

* Measures how much data can be moved at once
* Analogous to a dump truck:
  * Larger data payload
  * Slower speed
  * Optimized for bulk data movement

### Consistency Models

<figure><img src="../../../.gitbook/assets/image (4) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### ACID Model

Primarily used in relational databases, implementing features like row locking and rollbacks.

Characteristics:

* **Atomic**: Transactions are all-or-nothing
* **Consistent**: Transactions must be valid and uncorrupted
* **Isolated**: Transactions don't interfere with each other
* **Durable**: Committed transactions persist reliably

#### BASE Model

Alternative approach focused on scalability.

Characteristics:

* **Basically Available**: Prioritizes availability, even with stale data
* **Soft State**: May not be instantly consistent across data stores
* **Eventually Consistent**: All data stores will eventually sync and update

**Why Choose BASE over ACID?**

* ACID models face scalability challenges
* BASE accommodates parallel operations more effectively
* Useful for services requiring high scalability
* Examples of BASE implementation:
  * Amazon S3
  * Amazon DynamoDB

### Key Considerations

* ACID provides strict consistency but may face performance challenges at scale
* BASE isn't inconsistent; it prioritizes availability and parallel processing
* Choice between ACID and BASE depends on specific use case requirements



