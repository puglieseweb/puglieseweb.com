# Amazon EventBridge vs Apache Kafka

From an application perspective the key benefits are:

### EventBridge Benefits

* Near-zero application code needed for AWS service integration - just configure rules and targets
* Built-in error handling and dead letter queues require minimal custom code
* Event filtering and transformation happens at the service level, keeping application code clean
* Easy schema validation and enforcement without extra application logic
* Simple JSON-based event patterns make event routing logic straightforward
* Application can leverage built-in retry policies without implementing them

### Kafka Benefits

* Applications can replay messages from any point in time (great for recovery/debugging)
* Stronger ordering guarantees for messages within partitions
* Applications can scale horizontally by adding more consumers to a consumer group
* Custom serialization formats beyond JSON can be used efficiently
* Applications can implement backpressure mechanisms for better flow control
* Multiple applications can process the same events independently (consumer groups)
* Fine-grained control over message delivery semantics (at-least-once, exactly-once)

| Feature                       | Amazon EventBridge                     | Apache Kafka                                            |
| ----------------------------- | -------------------------------------- | ------------------------------------------------------- |
| **Infrastructure Management** | Fully managed by AWS                   | Self-managed or using managed services (MSK, Confluent) |
| **Maximum Throughput**        | Up to 10,000 events/second per account | Millions of events per second                           |
| **Latency**                   | Milliseconds                           | Sub-millisecond                                         |
| **Message Retention**         | 24 hours by default                    | Configurable, can retain indefinitely                   |
| **Message Size**              | Maximum 256KB                          | Configurable, default 1MB                               |
| **Ordering**                  | Best-effort ordering                   | Strict ordering within partitions                       |
| **Replay Capability**         | Limited (archive/replay feature)       | Full replay from any offset                             |
| **Development Complexity**    | Low - simple JSON rules                | Higher - requires more configuration                    |
| **Event Filtering**           | Rich pattern matching built-in         | Client-side filtering                                   |
| **AWS Integration**           | Native with 100+ AWS services          | Requires custom integration                             |
| **Scalability Model**         | Automatic                              | Manual partition management                             |
| **Consumer Groups**           | Not applicable                         | Built-in consumer group concept                         |
| **Dead Letter Queues**        | Built-in support                       | Manual implementation                                   |
| **Schema Registry**           | Built-in schema registry               | Separate service required                               |
| **Error Handling**            | Automatic retries and DLQ              | Manual implementation needed                            |
| **Cost Model**                | Pay per event                          | Infrastructure + data transfer                          |
| **Security**                  | IAM integration, encryption at rest    | Manual security configuration                           |
| **Monitoring**                | CloudWatch integration                 | Custom monitoring setup                                 |
| **Cross-Region**              | Built-in global endpoints              | Manual replication setup                                |
| **Backpressure Handling**     | Not available                          | Built-in protocol support                               |
| **Protocol Support**          | HTTP/HTTPS                             | Multiple protocols (PLAINTEXT, SSL, SASL)               |
| **Client Libraries**          | AWS SDKs                               | Multiple language clients                               |
| **Use Case Focus**            | Serverless event routing               | High-throughput stream processing                       |



## Does Event Bridge have a concept of partitions, similar to kafka?

EventBridge does not have a direct equivalent to Kafka's partitions. Instead, EventBridge uses different concepts for message distribution:

1. **Event Buses** - These are the streams that collect events, but they don't have internal partitioning like Kafka
2. **Rules** - These determine where events go, but don't provide the same ordering guarantees as Kafka partitions

"EventBridge processes **events at least once**, which means that a single event might be delivered to a target more than once. However, EventBridge attempts to deliver events in order to targets, but does not guarantee event ordering."

**EventBridge Pipes** provides "ordered event delivery within a pipe", but this is still different from Kafka's partition model where you can have multiple partitions with strict ordering within each partition.
