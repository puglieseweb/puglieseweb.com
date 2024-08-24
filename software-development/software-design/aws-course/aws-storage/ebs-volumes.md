---
description: Highly available and scalable storage volumes you can attach to EC2 instances.
---

# EBS Volumes

You can resize EBS volumes on the fly as well as changing the volume types.&#x20;

SSD Volumes:

* GP2 (General Purpose):
  * Suitable for boot disks and general applications.
  * Up to 16,000 IOPS per volume.
  * Up to 99.9% durability.
* GP3:
  * Suitable for high performance applications.
  * Predictable 3,000 IOPS baseline performance and 125 MiB/s regardless of the volume size.
  * Up to 99.9% durability.
* Io1:
  * Suitable for OLTP (online transaction processing) and letancy-sensitive applications.
  * 50 IOPS/GiB
  * Up to 64,000 IOPS per volume.
  * High performance and most expensive.
  * Up to 99.9% durability.
* Io2:
  * Suitable for OLTP (online transaction processing) and letancy-sensitive applications.
  * 500 IOPS/GiB
  * Up to 64,000 IOPS per volume.
  * Up to 99.999% durability.
  * Latest generation Provisioned IOPS volume.

HDD Volumes:&#x20;

* st1 (Throughput Optimized HDD):
  * Suitable for big data, data warehouse, ETL.
  * Max throughput is 500 MB/s per volume.
  * Cannot be  a boot volume.
  * Up to 99.9% durability.
* sc1 (Cold HDD):
  * Max throughput of 250 MB/s per volume.
  * Less frequently accessed data.
  * Cannot be a boot volume.
  * Lower cost.
  * Up to 99.9% durability.
