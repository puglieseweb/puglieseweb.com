# AWS Specialized Database Services

### Amazon Athena

* SQL engine overlay for S3 using Presto
* Queries raw data directly in S3
* Supports formats:
  * JSON, CSV (standard performance)
  * Parquet (optimized performance)
* Best for: Data lake queries without joins
* Differs from Redshift Spectrum (which handles joins with Redshift data)

### Amazon Quantum Ledger Database (QLDB)

* Centralized blockchain-based database
* Features:
  * Immutable journal
  * Append-only architecture
  * Hash-chain record linking
  * Higher performance than decentralized blockchains
* Operation:
  * Each record contains previous record's hash
  * Modifications break the hash chain
  * Ensures data immutability

### Amazon Managed Blockchain

* Hosted blockchain framework service
* Supports:
  * Hyperledger Fabric
  * Ethereum
* Characteristics:
  * Distributed architecture
  * Consensus-based network
  * Cross-account member support
  * Uses QLDB for transaction ordering
  * Supports external network members

### Amazon Timestream

* Purpose-built for time-series data
* Features:
  * Built-in analytics
  * Value interpolation
  * Data smoothing
* Use cases:
  * Industrial machinery monitoring
  * Equipment telemetry
  * Long-term performance tracking

### Amazon DocumentDB

* MongoDB-compatible database service
* Features:
  * MongoDB API compatibility
  * Easy migration path from MongoDB
  * AWS-managed redundancy
  * Built-in scalability
  * High availability

### Amazon Elasticsearch Service (ES)

* Primary function: Search engine
* Architecture (ELK Stack):
  1. Elasticsearch (storage/search layer)
  2. Intake options:
     * LogStash
     * CloudWatch
     * Firehose
     * Greengrass (IoT)
  3. Kibana (analytics/visualization)

<figure><img src="../../../../.gitbook/assets/image (62) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (63) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (64) (1).png" alt=""><figcaption></figcaption></figure>

* Characteristics:
  * JSON document storage
  * Analytics capabilities
  * Mature development community
  * Highly scalable
* Best for:
  * Search functionality
  * Analytics tools
  * Real-time dashboards
