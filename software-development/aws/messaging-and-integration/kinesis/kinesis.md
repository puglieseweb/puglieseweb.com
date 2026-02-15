# Kinesis (Event Stream)

Kinesis is an Event stream that handles real-time data streaming.

Kinesis allows to ingest, process, and analyse real-time streaming data.

**The maximum size for a single record in Amazon Kinesis Data Streams is 1 MB. The API has a per-request limit of 1 MB/s for writes to a single shard, or 1,000 records per second.**\


**Kinesis Data Streams ensures ordered processing in shareds**



AWS Kinesis effectively provides alternatives to Apache Kafka, for real-time data streaming, Apache Flink (Kinesis Data Analytics) for both handling stream processing and analytics.&#x20;



The are two versions of Kinesis:

|            | Kinesis Data Stream                                                   | Kinesis Data Firehose                                                           |
| ---------- | --------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| Purpose    | ingestion of data                                                     | data transfer tool to get information to S3, Redshift, Elasticsearch, or Splunk |
| Speed      | Real time                                                             | near real time (within 60s)                                                     |
| Difficulty | you are are responsible for crating consumers and scaling the streams | Plug and play with AWS Architecture. Scaling and consumers are handled by AWS!  |

### Kinesis Data Stream

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/mo..56Qw57hr?a=5970&#x26;x=79&#x26;y=-10&#x26;w=1767&#x26;h=1096&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%20a176c76df5d1240feaa550122bc826b7e81d6088e72e89e545d97aa5ddaae387-ts%3D1726396829" alt=""><figcaption></figcaption></figure>



Kinesis Data Streams has some specific limitations on the number of consumers that can read from a stream:

For shared consumer mode (classic):

* You can have up to 5 consumer applications (using the same Group ID) reading from a stream simultaneously
* These share the throughput of the shard

For enhanced fan-out:

* You can register up to 20 consumers per stream
* Each consumer gets its own dedicated 2MB/second throughput per shard
* You need to register each consumer explicitly using the RegisterStreamConsumer API

This is quite different from SNS, which allows you to have practically unlimited subscribers (there are soft limits but they're very high).

If you need to broadcast data to many consumers, SNS would be a better choice than Kinesis. Alternatively, you could set up a Lambda function to read from Kinesis and then fan out the data to multiple destinations, but this adds complexity and latency.

### Kinesis Firehose

<figure><img src="https://documents.lucid.app/documents/53875b19-93a1-4800-81d1-8c84d6351a09/pages/~y..sptvYpEQ?a=6054&#x26;x=103&#x26;y=-10&#x26;w=1233&#x26;h=1079&#x26;store=1&#x26;accept=image%2F*&#x26;auth=LCA%203aca1f13ff10b47be25406ff77bb0c529ddc6c04812a7cb6e2f2a8799c700bd7-ts%3D1726396829" alt=""><figcaption></figcaption></figure>

## Kinesis Data Analytics

* Analyze data using standard SQL.
* Data Analytics supports both Kinesis Data Stream and Data Firehose.
* There are no services to manage&#x20;
* You only pay for what you are using



## Kafka Partitions VS Kinesis Shards

Conceptual Similarities:

* Both are the base unit of parallelism
* Both guarantee ordered processing within a single shard/partition
* Both are used to distribute data across consumers

Key Differences:

1. Capacity Model

* Kafka Partition:
  * No built-in capacity limits
  * Bounded only by broker hardware
  * Can handle variable throughput
* Kinesis Shard:
  * Fixed capacity limits:
    * 1MB/sec input
    * 2MB/sec output
    * 1000 records/sec writes
  * Like a "metered pipe" with strict limits

2. Consumer Behavior

* Kafka Partition:
  * One partition can be read by one consumer in a consumer group
  * No throttling on reads
  * Multiple consumer groups can read at full speed
* Kinesis Shard:
  * Limited to 5 reads/sec per shard
  * Need Enhanced Fan-out for multiple consumers
  * More restrictive consumer model

3. Scaling Characteristics

* Kafka Partition:
  * Partitions can be added but not split
  * Rebalancing happens automatically
  * No throughput guarantees per partition
* Kinesis Shard:
  * Shards can be split/merged
  * Fixed throughput per shard makes capacity planning simpler
  * More predictable but less flexible

So while they serve similar purposes in distributing data, they operate on different models:

* Kafka partition is more like an unbounded queue
* Kinesis shard is more like a metered pipeline with strict capacity controls

This difference impacts how you design and scale applications on each platform.

