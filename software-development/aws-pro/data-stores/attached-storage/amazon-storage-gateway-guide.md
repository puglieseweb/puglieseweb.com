# Amazon Storage Gateway Guide

### Overview

Amazon Storage Gateway is a virtual machine solution that:

* Can be run on-premises or on EC2
* Provides local storage resources backed by S3 and Glacier
* Enables data synchronization between on-premises and AWS cloud storage
* Includes bandwidth throttling capabilities

<figure><img src="../../../../.gitbook/assets/image (51) (1).png" alt=""><figcaption></figcaption></figure>

### Core Use Cases

1. Disaster Recovery
   * Using AWS as a "hot site"
   * Facilitating failover capabilities
   * Maintaining synchronized cloud backups
2. Cloud Migration
   * Gradual data synchronization to AWS
   * Enables phased transition to cloud resources
   * Reduces on-premises storage requirements over time

### Gateway Modes

#### 1. File Gateway

* Current name: File Gateway
* Exposes volumes as NFS or SMB shares
* Compatible with various operating systems
* Backend replication to S3

#### 2. Volume Gateway Stored Mode

* Old name: Gateway-stored volumes
* Features:
  * iSCSI interface
  * Asynchronous replication to S3
  * Primary data stored on-premises
  * Background synchronization to cloud

#### 3. Volume Gateway Cached Mode

* Old name: Gateway-cached volumes
* Features:
  * iSCSI interface
  * Primary data stored on S3
  * Local caching of recently accessed files
  * Reduced on-premises storage requirements

#### 4. Tape Gateway

* Old name: Gateway virtual tape library
* Features:
  * iSCSI interface
  * Virtual media changer
  * Virtual tape library
  * Compatible with popular backup software
  * Check documentation for specific software compatibility

### Implementation Example: Remote Office Scenario

<figure><img src="../../../../.gitbook/assets/image (52) (1).png" alt=""><figcaption></figcaption></figure>

#### Setup

* Uses cached volume mode
* Provides synchronized file experience
* Suitable for offices with limited bandwidth

#### Benefits

* Data stored on S3 with high durability (11 nines)
* Local caching for frequently accessed data
* Bandwidth throttling to prevent network saturation
* Efficient remote office access to cloud storage

### Migration Strategy Example

1. Start with Volume Gateway stored mode
2. Gradually synchronize data to S3
3. Switch to cached mode
4. Reduce on-premises storage
5. Complete transition to cloud storage as needed
