# EventBridge VS Kinesis(Event Stream)

[Check out What are event-driven architectures](https://serverlessland.com/event-driven-architecture/what-are-event-driven-architectures)



The key differences between AWS Event Router (EventBridge) and Event Streams (Kinesis) are:

<figure><img src="../../../../.gitbook/assets/image (33) (1) (1).png" alt=""><figcaption></figcaption></figure>

Here's a detailed comparison between Amazon EventBridge and Amazon Kinesis:

| Feature             | Amazon EventBridge                                                                   | Amazon Kinesis                                                                                     |
| ------------------- | ------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------- |
| Primary Purpose     | Event routing and serverless event bus                                               | Real-time data streaming and processing                                                            |
| Data Retention      | No built-in retention                                                                | 24 hours to 365 days                                                                               |
| Maximum Record Size | 256KB                                                                                | 1MB                                                                                                |
| Ordering            | No guaranteed ordering                                                               | Ordered within same partition key                                                                  |
| Throughput          | Soft limit of 10,000 events/sec per region                                           | Up to 1MB/sec per shard or unlimited with on-demand                                                |
| Scaling             | Automatic                                                                            | Manual (adding/removing shards) or on-demand                                                       |
| Cost Model          | Pay per event                                                                        | Pay per shard-hour or on-demand capacity                                                           |
| Processing Model    | Push-based                                                                           | Pull-based                                                                                         |
| Replay Capability   | Yes, through archiving                                                               | Yes, using sequence numbers                                                                        |
| Fan-out Support     | Yes, multiple targets per rule                                                       | Yes, multiple consumers per stream                                                                 |
| Real-time Analytics | No built-in analytics                                                                | Yes, with Kinesis Data Analytics                                                                   |
| Data Transformation | Yes, via input transformer                                                           | Only with additional processing                                                                    |
| Cross-Region        | Yes                                                                                  | Yes, with replication                                                                              |
| Cross-Account       | Yes                                                                                  | Yes                                                                                                |
| Use Cases           | <p>• Serverless event routing<br>• Application integration<br>• SaaS integration</p> | <p>• Log aggregation<br>• Real-time metrics<br>• IoT data streaming<br>• Click stream analysis</p> |
| Latency             | Milliseconds                                                                         | Sub-second                                                                                         |
| Consumption Pattern | Push to targets                                                                      | Pull via GetRecords or Enhanced Fan-Out                                                            |

#### Event Router (EventBridge)

* Acts like a serverless event bus for routing events between AWS services, SaaS apps, and custom applications
* Best for event-driven architectures where you need to connect multiple services based on rules/patterns
* Supports complex routing rules and filtering
* Pay per event, no capacity planning needed
* Lower throughput (max 10,000 events/sec per account)
* Maximum event size of 256KB
* Near real-time delivery with some latency

#### Event Streams (Kinesis)

* Designed for real-time streaming data ingestion and processing
* Best for high-throughput streaming use cases like log aggregation, analytics, IoT
* Sequential processing of records in order
* Need to manage capacity through shards
* Much higher throughput (1MB/sec or 1000 records/sec per shard)
* Maximum record size of 1MB
* Sub-second latency

Key considerations for choosing between them:

1. If you need complex routing and integration between services → EventBridge
2. If you need high-throughput sequential processing → Kinesis
3. If cost is primary concern → EventBridge (pay per event)
4. If latency is critical → Kinesis
5. If you need ordering guarantees → Kinesis
