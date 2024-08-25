# Aurora

There are versions: Aurora and Aurora Serverless

### Aurora

Amazon Aurora provides up to 5 times better performance than MySQL and 3 times better performance then PostgresSql databases at a much lower price point, while delivering similar performance and availability.

Keys caracteristics:

* Provides Storage Auto Scaling starting from 10 GB, scales in 10-GB increments to 128 TB
* Compute resources can scale up to 96 vCPUs and 768 GB of memory.
* 2 copies of your data are contained in each Availability Zone, with a minimum of 3 Availability Zones (**Aurora has at least 6 copies of your data)**.
* Transparently handle the loss of up to 2 copies of data whiout affecting database write availability and up to 3 copies without affecting read availability.
* Storage is self-healing. Data blocks and disks are continuously scanned for errors and repaired automatically.
* **Automated failover is only available with Aurora DB**

### Aurora is Serverless

&#x20;Aurora Serveless provides a relatively simple, c**ost-effective option for infrequent, intermittent, or unpredictable workloads.**

* An on-demand, auto-scaling configuration for MySQL and Postgres compatible edition
* Aurora Serverless DB cluster automatically stands up, shout down, and scales capacity up or down based on your application's needs.

### Read Replicas

Aurora can have the following read replicas:

* 15 read replicas with Aurora Replicas
* 5 read replicas with MySQL Replicas
* 5 read replicas with PostgresSQL

Replica type is always asynchronous.&#x20;

MySQL Replica advantages over Aurora Replica are:

* MySQL replica can happen cross-region
* Support user-defined replication delay
* Support different data or schema vs primary



Aurora Replica advantages over MySQL Replica are:

* Aurora has the lowest performance impact on the primary DB instance
* Aurora  replica takes milliseconds VS MySQL replica takes seconds
* Aurora allows auto Failover VS No autofailover for MySQL

Backups:

* Always enabled&#x20;
* snapshots and backups do not have performace impact
* Aurora snapshots can be shared with other AWS accaunts&#x20;
