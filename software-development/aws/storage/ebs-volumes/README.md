---
description: >-
  Elastic Block Store (EBS) is highly available and scalable storage volumes you
  can attach to EC2 instances.
---

# EBS Volumes

EBS volumes provide durable, block-level storage that can be attached to Amazon EC2 instances. Key points include:

1. **Usage**:
   * Primarily designed for use with EC2 instances.
   * Can be used as storage for some AWS services like Amazon RDS and Amazon WorkSpaces.
2. **Compatibility**:
   * Not directly usable with Amazon Redshift or VMware Cloud on AWS.
   * Redshift uses its own cluster storage.
   * VMware Cloud on AWS uses vSAN for storage.
3. **Flexibility**:
   * Volumes can be resized on the fly without detaching from instances.
   * Volume types can be changed dynamically (e.g., from GP2 to GP3).
4. **Data Persistence**:
   * EBS volumes persist independently from the life of an EC2 instance.
5. **Snapshots**:
   * Point-in-time snapshots can be created for backup or volume recreation.
6. **Encryption**:
   * Supports encryption at rest using AWS Key Management Service (KMS).
7. **Multi-Attach**:
   * Some volume types support attaching to multiple EC2 instances simultaneously.

## EBS Volume tyeps

| Feature          | sc1                        | st1                         | GP2                           | GP3                                                            | io1                             | io2                                               |
| ---------------- | -------------------------- | --------------------------- | ----------------------------- | -------------------------------------------------------------- | ------------------------------- | ------------------------------------------------- |
| Type             | HDD                        | HDD                         | SSD                           | SSD                                                            | SSD                             | SSD                                               |
| Use Case         | Infrequently accessed data | Big data, warehouses, ETL   | Boot volumes, general-purpose | Broad range of workloads                                       | I/O-intensive, databases        | I/O-intensive, ultra-low latency                  |
| Throughput       | Up to 250 MB/s             | Up to 500 MB/s              | Up to 250 MiB/s               | Up to 1,000 MiB/s                                              | Up to 1,000 MiB/s               | Up to 1,000 MiB/s (4,000 MiB/s for Block Express) |
| Max IOPS         | 250                        | 500                         | 16,000                        | 16,000                                                         | 64,000                          | 64,000 (256,000 for Block Express)                |
| Baseline IOPS    | 12 per TB                  | 40 per TB                   | 3 IOPS/GiB (min 100)          | 3,000 (Additional IOPS can be added in increments of 500 IOPS) | Provisioned (up to 50 IOPS/GiB) | Provisioned (up to 500 IOPS/GiB)                  |
| Burst Capability | Yes (up to 80 MB/s per TB) | Yes (up to 250 MB/s per TB) | Yes (up to 3,000 IOPS)        | No (consistent performance)                                    | No                              | No                                                |
| Volume Size      | 125 GiB - 16 TiB           | 125 GiB - 16 TiB            | 1 GiB - 16 TiB                | 1 GiB - 16 TiB                                                 | 4 GiB - 16 TiB                  | 4 GiB - 16 TiB                                    |
| Durability       | 99.8-99.9%                 | 99.8-99.9%                  | 99.8-99.9%                    | 99.8-99.9%                                                     | 99.8-99.9%                      | Up to 99.999%                                     |
| Boot Volume      | No                         | No                          | Yes                           | Yes                                                            | Yes                             | Yes                                               |
| Cost             | Lowest                     | Low                         | Moderate                      | Lower than GP2                                                 | High                            | Same as io1                                       |

## AWS SSD Volume Types

### GP2 (General Purpose SSD)

**GP2 volumes provide millisecond latency and are well-suited for small, random I/O operations.**

**GP2 volumes provide 3 IOPS per GB, capped at 16,000 IOPS.**

* Suitable for boot volumes and general-purpose workloads.
* Baseline performance of 3 IOPS/GiB, with a minimum of 100 IOPS.
* Can burst up to 3,000 IOPS for volumes 334 GiB and smaller.
* Maximum of 16,000 IOPS per volume (for volumes larger than 5,334 GiB).
* Throughput up to 250 MiB/s.
* 99.8-99.9% durability.

#### GP2 IOPS calculation

**General Purpose SSD (gp2) IOPS are dependent on volume size at a rate of 3 IOPS/GB.**

For GP2 (General Purpose SSD) volumes, the IOPS calculation follows these rules:

1. Baseline: 3 IOPS per GB
2. Minimum: 100 IOPS
3. Maximum: 16,000 IOPS

For a 300 GB volume: 300 GB × 3 IOPS/GB = 900 IOPS

To get 1000 IOPS with GP2, you would need approximately 334 GB of storage (1000 ÷ 3 = 333.33).



### GP3 (Latest Generation General Purpose SSD)

General Purpose SSD (gp3) includes 3,000 IOPS at no additional cost independent of volume size.

* Suitable for a broad range of workloads.
* Predictable 3,000 IOPS baseline performance and 125 MiB/s regardless of volume size.
* Can provision up to 16,000 IOPS and 1,000 MiB/s per volume.
* More cost-effective than GP2 for most workloads.
* 99.8-99.9% durability.

### io1 (Provisioned IOPS SSD):



**Provisioned IOPS SSD (io1) suitable Sub-millisecond latency and vest for I/O-intensive workloads.**

**PIOPS would let you provision exactly the IOPS needed without overprovisioning storage.**

* Suitable for I/O-intensive workloads, particularly database workloads.
* Provision up to 50 IOPS per GiB.
* Up to 64,000 IOPS per volume.
* Up to 1,000 MiB/s of throughput per volume.
* High performance and higher cost.
* 99.8-99.9% durability.

### io2 (Provisioned IOPS SSD):

* Latest generation of Provisioned IOPS volumes.
* Designed for I/O-intensive workloads requiring consistent ultra-low latency.
* Provision up to 500 IOPS per GiB.
* Up to 64,000 IOPS per volume (256,000 IOPS for io2 Block Express).
* Up to 1,000 MiB/s of throughput per volume (4,000 MiB/s for io2 Block Express).
* Higher durability: up to 99.999%.
* Same price as io1 with better performance and durability.

## AWS HDD Volume Types

### st1 (Throughput Optimized HDD):

* Designed for frequently accessed, throughput-intensive workloads.
* Ideal for big data, data warehouses, log processing, and ETL workloads.
* Baseline throughput of 40 MB/s per TB.
* Ability to burst up to 250 MB/s per TB.
* Maximum throughput of 500 MB/s per volume.
* Baseline IOPS of 40 per TB, with ability to burst up to 500 IOPS per TB.
* Maximum IOPS of 500 per volume.
* Cannot be used as a boot volume.
* Minimum volume size of 125 GiB.
* 99.8-99.9% durability.

### sc1 (Cold HDD):

* Designed for less frequently accessed workloads.
* Suitable for scenarios where the lowest storage cost is important.
* Baseline throughput of 12 MB/s per TB.
* Ability to burst up to 80 MB/s per TB.
* Maximum throughput of 250 MB/s per volume.
* Baseline IOPS of 12 per TB, with ability to burst up to 80 IOPS per TB.
* Maximum IOPS of 250 per volume.
* Cannot be used as a boot volume.
* Minimum volume size of 125 GiB.
* 99.8-99.9% durability.
* Lower cost compared to st1, making it ideal for infrequently accessed data.
