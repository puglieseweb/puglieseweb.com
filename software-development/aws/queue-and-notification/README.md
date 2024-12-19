# Messaging, Events and Streaming



* **Amazon SQS** (Simple Queue Service) is a message queuing service that decouples and scales microservices, maintaining message delivery and durability. It stores messages in a queue to ensure reliable message processing.
* **Amazon SNS** (Simple Notification Service) is a pub/sub messaging service enabling message delivery to multiple recipients. It supports various protocols like HTTP, HTTPS, email, and SQS, facilitating message broadcasting.
* **Amazon Kinesis**:
  * Kinesis Stream is used for realtime data ingestion&#x20;
  * Kinesis Firehose is used for near-realtime&#x20;
  * Kinesis Data Analytics is used process and transform data going through Kinesis using SQL
* **Event Bridge**:&#x20;
  * Serverless event bus service
  * Designed for building event-driven architectures
  * Key features:
    * Receives events from various AWS services and custom applications
    * Routes events to target services based on rules
    * Supports complex event filtering and transformation
    * Can integrate with many AWS services and SaaS applications

| Feature             | SQS                                     | SNS                                         | Kinesis                                            | EventBridge                                                    |
| ------------------- | --------------------------------------- | ------------------------------------------- | -------------------------------------------------- | -------------------------------------------------------------- |
| Type                | Queue service                           | Pub/sub messaging                           | Real-time data streaming                           | Event bus                                                      |
| Use Case            | Decoupling components, batch processing | Fanout messaging, mobile push notifications | Real-time analytics, log and event data processing | Application integration, serverless event-driven architectures |
| Message Retention   | Up to 14 days                           | No retention (deliver or fail)              | 24 hours to 365 days                               | No retention (deliver or fail)                                 |
| Throughput          | Virtually unlimited                     | Virtually unlimited                         | High (limited by shard capacity)                   | Virtually unlimited                                            |
| Message Size        | Up to 256 KB                            | Up to 256 KB                                | Up to 1 MB                                         | Up to 256 KB                                                   |
| Delivery            | Pull-based                              | Push-based                                  | Pull-based                                         | Push-based                                                     |
| Ordering            | Available with FIFO queues              | Not guaranteed                              | Guaranteed within a shard                          | Not guaranteed                                                 |
| Scalability         | Automatic                               | Automatic                                   | Manual (shard splitting/merging)                   | Automatic                                                      |
| Consumers           | Single consumer per message             | Multiple consumers                          | Multiple consumers                                 | Multiple consumers                                             |
| Replay Capability   | No built-in replay                      | No replay                                   | Yes, with enhanced fan-out                         | Limited replay with archive and replay feature                 |
| Latency             | Low (but higher than SNS)               | Lowest                                      | \~200 ms                                           | Low                                                            |
| Data Transformation | No built-in transformation              | No built-in transformation                  | Basic with Kinesis Data Analytics                  | Built-in transformation with EventBridge Pipes                 |

### Common Architecture Patterns

<figure><img src="../../../.gitbook/assets/image (28) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

## Key Differences

1. Message Persistence:
   * SQS: Messages are persisted until processed and deleted
   * SNS: Messages are not persisted, they're delivered immediately or lost
   * EventBridge: Events are not persisted, but can be archived or replayed
2. Delivery Model:
   * SQS: Pull model (consumers request messages)
   * SNS: Push model (messages are immediately pushed to subscribers)
   * EventBridge: Push model (events are immediately routed to targets)
3. Consumers:
   * SQS: Single consumer per message
   * SNS: Multiple consumers (subscribers) per message
   * EventBridge: Multiple consumers (targets) per event
4. Use Case Focus:
   * SQS: Decoupling and workload management
   * SNS: Real-time notifications and fanout scenarios
   * EventBridge: Complex event routing and application integration
5. Message Ordering:
   * SQS: Supports FIFO (First-In-First-Out) queues for ordering
   * SNS: Does not guarantee message ordering
   * EventBridge: Does not guarantee event ordering
6. Integration:
   * EventBridge has the most extensive built-in integrations with AWS services and third-party SaaS applications

## When to use each

* Use SQS when you need reliable, persisted message queuing with decoupled processing
* Use SNS when you need real-time notifications to multiple subscribers
* Use EventBridge when you need complex event routing, filtering, or integration across multiple services



## AWS Messaging Services Throughput Comparison

| Service     | Component                                     | Default Throughput                          | Notes                                                                                                     |
| ----------- | --------------------------------------------- | ------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| EventBridge | Event Bus                                     | 10,000 events/second per account per region | <p>- Can be increased by contacting AWS support<br>- No explicit throttling, but has "burst capacity"</p> |
| SQS         | Standard Queues                               | Nearly unlimited                            | Can handle very high volumes                                                                              |
| SQS         | FIFO Queues                                   | 300 TPS (up to 3,000 TPS with batching)     | TPS = Transactions Per Second                                                                             |
| SNS         | Publishing                                    | 30,000 publishes/second per topic           |                                                                                                           |
| SNS         | HTTP/S Subscriptions                          | 16,666 deliveries/second per topic          |                                                                                                           |
| SNS         | AWS Service Subscriptions (e.g., Lambda, SQS) | No specific limit mentioned                 | Likely very high                                                                                          |

Additional Notes:

* Message size limit for SQS and SNS: 256 KB
* Actual throughput may vary based on factors like message size, processing complexity, and target service capacity
* These limits can often be increased by contacting AWS support
* Always refer to the official AWS documentation for the most up-to-date information
