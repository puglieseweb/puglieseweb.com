# AWS Messaging Services: SQS and Amazon MQ

### Amazon Simple Queue Service (SQS)

Amazon SQS is a highly scalable, hosted messaging queue service that provides several key features and capabilities for modern cloud architectures.

#### Key Characteristics

* **Message Retention**: Messages can be stored for up to 14 days (default is 4 days)
* **Message Size**:
  * Standard limit: 256 KB per message
  * Extended limit: Up to 2 GB using the Java SDK (stores message in S3 with SQS pointer)
* **Security**: Integrates with AWS KMS for message encryption, enabling end-to-end encryption
* **Storage Type**: Designed for transient storage, not long-term message persistence

#### Queue Types

**Standard Queue**

* No guaranteed message ordering
* Messages may be processed in any sequence
* Suitable for scenarios where exact order isn't critical
* Higher throughput potential

**FIFO Queue (First-In-First-Out)**

* Guarantees message processing order
* Maintains sequence of operations
* Trade-off: Processing delays if messages fail or get stuck
* Best for scenarios requiring strict ordering

#### Example Architecture

Consider an ERP (Enterprise Resource Planning) system generating master data updates:

1. ERP system generates messages
2. Messages land in SQS queue
3. EC2 worker instances process messages (transformation/augmentation)
4. Processed data updates DynamoDB
5. **System can scale horizontally by adding more workers for burst processing**

<figure><img src="../../../../.gitbook/assets/image (2) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Message Processing Example

In a standard queue scenario with updates:

1. Update 1: Received
2. Update 2: Sent to warehouse
3. Update 3: Picked

Without FIFO queuing, these updates might process out of order, potentially causing issues with record consistency. FIFO queues maintain the correct processing order but may introduce latency.

### Amazon MQ

Amazon MQ is AWS's managed implementation of Apache ActiveMQ, offering a different approach to message queuing.

#### Key Features

* Fully managed Apache ActiveMQ implementation
* High availability within regions
* Supports multiple protocols and APIs:
  * ActiveMQ API
  * JMS (Java Message Service)
  * NMS (.NET Message Service)
  * WebSockets
  * MQTT (for IoT implementations)

### Use Case Comparison

When to choose each service:

**Choose SQS when:**

* Building new applications from scratch
* Need tight integration with other AWS services
* Want access to the latest AWS messaging features

**Choose Amazon MQ when:**

* Migrating existing applications using message brokers
* Need a "lift and shift" solution for cloud migration
* Using applications designed for traditional message brokers (like IBM MQ)
* Require drop-in replacement for on-premises messaging solutions

Amazon MQ serves as an excellent bridge for organizations moving to the cloud while maintaining compatibility with existing messaging protocols and applications.
