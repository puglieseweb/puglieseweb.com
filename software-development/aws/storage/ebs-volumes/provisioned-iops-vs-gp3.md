# Provisioned IOPS vs GP3

Provisioned IOPS (io1/io2):

* Up to 64,000 IOPS (256,000 for io2 Block Express)
* Up to 1,000 MB/s throughput (4,000 MB/s for Block Express)
* Higher durability with io2 (99.999%)
* Pay for provisioned IOPS regardless of use
* Best for critical, high-performance databases
* More expensive
* Predictable performance

GP3:

* Up to 16,000 IOPS
* Up to 1,000 MB/s throughput
* 3,000 IOPS baseline included
* Pay only for what you provision above baseline
* Good for most production workloads
* More cost-effective
* No bursting needed
