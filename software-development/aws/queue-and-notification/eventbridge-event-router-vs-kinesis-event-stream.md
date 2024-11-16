# EventBridge(Event Router) VS Kinesis(Event Stream)

[Check out What are event-driven architectures](https://serverlessland.com/event-driven-architecture/what-are-event-driven-architectures)



The key differences between AWS Event Router (EventBridge) and Event Streams (Kinesis) are:

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
