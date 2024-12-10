# Burst Capacity & Baseline IOPS

This page explain the difference between burst capacity and baseline IOPS in EBS volumes:

Baseline IOPS is the guaranteed minimum performance level that your EBS volume will deliver consistently. This is the sustained performance you can expect during normal operations. For example, a gp3 volume has a baseline of 3,000 IOPS.

Burst capacity is like a performance credit system that allows your volume to temporarily exceed its baseline IOPS for periods of high demand. It works like this:

* When your volume uses less than its baseline IOPS, it accumulates I/O credits
* These credits can be spent later to "burst" above the baseline performance
* Bursting can continue until the credit balance is depleted
* Once credits are exhausted, performance returns to baseline

For example, with gp2 volumes:

* If you have a 100 GB gp2 volume with 300 baseline IOPS
* It can burst up to 3,000 IOPS using credits
* If you maintain high IOPS usage, eventually you'll exhaust credits and return to 300 IOPS

This is particularly useful for workloads with variable I/O patterns, like boot volumes or development environments, where you need occasional periods of high performance but don't want to pay for consistently high IOPS.
