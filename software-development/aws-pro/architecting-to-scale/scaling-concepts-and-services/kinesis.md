# Kinesis

### Core Concepts

#### Overview

Kinesis is a suite of services designed for processing streaming data with the following characteristics:

* Processes data in shards
* Each shard handles 1,000 records per second
* Default limit of 500 shards (can be increased upon request)
* Functions as a transient data store

<figure><img src="../../../../.gitbook/assets/image (17).png" alt=""><figcaption></figcaption></figure>

#### Data Records

Each record consists of:

* Partition key
* Sequence number
* Data payload (up to 1 MB)

<figure><img src="../../../../.gitbook/assets/image (18).png" alt=""><figcaption></figcaption></figure>

#### Data Retention

* Default: 24 hours
* Maximum: 7 days
* Not designed for persistent storage

### Service Components

1. **Kinesis Data Streams**
   * Ingests high-volume data
   * Multiple processing options
   * Primary focus for exam

<figure><img src="../../../../.gitbook/assets/image (14).png" alt=""><figcaption></figcaption></figure>

1. **Kinesis Firehose**
   * Automated data delivery
   * Multiple destination options
   * No immediate processing required

<figure><img src="../../../../.gitbook/assets/image (15).png" alt=""><figcaption></figcaption></figure>

1. **Kinesis Analytics**
   * Real-time data analysis
   * Processing during ingestion
   * Pre-warehouse analytics

<figure><img src="../../../../.gitbook/assets/image (16).png" alt=""><figcaption></figcaption></figure>

1. **Kinesis Video Streams**
   * Video stream processing
   * Less relevant for exam

<figure><img src="../../../../.gitbook/assets/image (13).png" alt=""><figcaption></figcaption></figure>

### Technical Architecture

#### Shard Processing

* Shards function like highway lanes
* More shards = higher throughput
* Data distributed across shards

#### Data Identification

* Each shard has unique 128-bit MD5 hash partition key
* Sequential numbering within shards
* Unique identification requires both:
  * Partition key
  * Sequence number

#### Implementation Example

Real-world architecture:

<figure><img src="../../../../.gitbook/assets/image (19).png" alt=""><figcaption></figcaption></figure>

1. Twitter API → Kinesis ingestion
2. Firehose → S3 storage
3. Lambda processing:
   * DynamoDB storage
   * Sentiment analysis
   * Text processing

This architecture demonstrates Kinesis's capability to handle real-time data processing and integration with other AWS services.
