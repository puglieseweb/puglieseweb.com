# Queue and Notification

* **Amazon SQS** (Simple Queue Service) is a message queuing service that decouples and scales microservices, maintaining message delivery and durability. It stores messages in a queue to ensure reliable message processing.
* **Amazon SNS** (Simple Notification Service) is a pub/sub messaging service enabling message delivery to multiple recipients. It supports various protocols like HTTP, HTTPS, email, and SQS, facilitating message broadcasting
* Kinesis:
  * Kinesis Stream is used for realtime data ingestion&#x20;
  * Kinesis Firehose is used for near-realtime&#x20;
  * Kinesis Data Analytics is used process and transform data going through Kinesis using SQL



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
