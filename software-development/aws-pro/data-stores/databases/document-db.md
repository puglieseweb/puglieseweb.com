# Document DB

<figure><img src="../../../../.gitbook/assets/image (18) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Core Characteristics

* AWS-native document database service
* MongoDB-compatible API
* Managed NoSQL database for JSON documents
* Automatic storage scaling
* Support for both write-then-read and eventual consistency

### Architecture

<figure><img src="../../../../.gitbook/assets/image (19) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Instance Configuration

* Single primary instance + up to 15 read replicas
* Document data redundantly stored across availability zones
* Primary instance handles all writes
* All instances (primary and replicas) can handle reads

#### High Availability Features

* Automatic failover to read replica if primary fails
* Multiple endpoint types:
  * Reader endpoint (all read instances)
  * Writer endpoint (primary instance)
  * Custom instance endpoints
  * Group endpoints for instance subsets

### Document Structure

* JSON-based documents
* Flexible, schema-less design
* Allows adding/removing fields without schema changes
* Documents retrieved as complete units
* Intuitive structure for developer usage

### Use Cases

* User profiles storage
  * Single document contains all user data
  * Intuitive format for user information
* Real-time big data applications
  * Efficient handling of document-based data streams
* Content management systems
  * Natural fit for content storage
  * Flexible schema supports varied content types

### Key Features for AWS Exam

<figure><img src="../../../../.gitbook/assets/image (20) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Scalability
   * Built to handle any data storage size
   * AWS-managed auto scaling
2. MongoDB Compatibility
   * Emulates MongoDB API
   * Suitable for MongoDB migrations
   * Primary solution for MongoDB workloads on AWS
3. Development Focus
   * Flexible JSON schema
   * Natural development experience
   * Fast iteration capability
