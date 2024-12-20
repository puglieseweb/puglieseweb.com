# EBS Volumes

## AWS Storage Options for High Availability: A Comprehensive Guide

### Amazon EBS (Elastic Block Store)

#### Key Characteristics

* Annual failure rate: < 0.2% (vs. 4% for commodity drives)
* Availability target: 99.999%
* Single-AZ replication
* Snapshot support with S3 storage
* Cross-region snapshot copying available

#### RAID Configuration Options

<figure><img src="../../../.gitbook/assets/image (27) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (28) (1).png" alt=""><figcaption></figcaption></figure>

**RAID 0 (Striping)**

* No redundancy
* Data distributed across multiple drives
* Advantages:
  * Improved read/write performance
  * Full capacity utilization
  * Double throughput
* Use Case:
  * When performance is priority over redundancy

**RAID 1 (Mirroring)**

* Full redundancy with duplicate copies
* Characteristics:
  * 50% capacity reduction
  * Slightly slower than RAID 0
  * Same throughput as single volume
* Use Case:
  * When data redundancy is critical

**RAID 5 (Not Recommended for AWS)**

* Minimum 3 drives required
* Uses parity for redundancy
* Not recommended due to:
  * Network I/O overhead
  * 20-30% IOPS consumption for parity
  * Performance impact

**RAID 6 (Not Recommended for AWS)**

* Minimum 4 drives required
* Dual parity methods
* Can survive two drive failures
* Not recommended for same reasons as RAID 5

#### EBS Performance Examples

1. **Single Volume Configuration**
   * 1 TB volume
   * 4000 IOPS
   * 500 Mbps throughput
   * 1 TB usable space
2. **RAID 0 Configuration**
   * 2 x 500 GB volumes
   * 8000 total IOPS
   * 1000 Mbps throughput
   * 1 TB usable space
3. **RAID 1 Configuration**
   * 2 x 500 GB volumes
   * 4000 total IOPS
   * 500 Mbps throughput
   * 500 GB usable space

### Amazon S3 Storage Classes

<figure><img src="../../../.gitbook/assets/image (29) (1).png" alt=""><figcaption></figcaption></figure>

#### Standard Storage

* Availability: 99.99%
* Multi-AZ durability
* Durability: 11 nines (99.999999999%)
* Immediate access
* Use Case: Frequently accessed data

#### Standard Infrequent Access (IA)

* Slightly lower availability
* Multi-AZ durability
* 8.7 hours/year potential unavailability
* Lower cost than Standard
* Use Case: Less frequently accessed data

#### One-Zone Infrequent Access

* Single-AZ durability
* 1.8 days/year potential unavailability
* Maintains 11 nines durability
* Lowest cost for IA tier
* Use Case: Reproducible, infrequently accessed data

### Amazon EFS (Elastic File System)

<figure><img src="../../../.gitbook/assets/image (30) (1).png" alt=""><figcaption></figcaption></figure>

#### Features

* NFS implementation
* Multi-AZ redundancy
* Scalable file sharing
* Automatic replication

#### Architecture Benefits

* Multiple mount points across AZs
* Continued operation during AZ failure
* Automatic scaling support
* Integrated with EC2 auto-scaling

### Additional Storage Solutions

#### Amazon Storage Gateway

* Ideal for on-premises to AWS migration
* Supports ongoing synchronization
* Provides hybrid storage capabilities
* Good for offsite backups

#### AWS Snowball

* Suited for batch data transfers
* Physical data transport solution
* Various capacity options
* Secure data migration

### Best Practices

#### EBS Implementation

* Use snapshots for backup
* Consider RAID 0 or 1 only
* Monitor IOPS utilization
* Plan for AZ failures

#### S3 Usage

* Choose appropriate storage class
* Utilize lifecycle policies
* Consider cross-region replication
* Monitor availability metrics

#### EFS Deployment

* Implement multi-AZ mount points
* Plan for redundancy
* Monitor throughput
* Consider backup strategies

### Availability Considerations

#### Single-AZ Services

* Plan for AZ failures
* Implement snapshot strategies
* Consider replication options
* Monitor service health

#### Multi-AZ Services

* Leverage built-in redundancy
* Implement cross-AZ architecture
* Plan for regional failures
* Consider cross-region options
