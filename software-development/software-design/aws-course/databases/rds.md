---
description: Relational Database Service
---

# RDS

RDS is suitable for OLTP.&#x20;



## Multi AZ

Multi AZ is used for Disaster Recovery and not to improve Performances.

Enabling Multiple-AZ, RDS creates an exact copy of the DB (standby DB).



RDS Types that can be either single instances or enabled for Multiple-AZ:

* SQL Server
* MySQL
* MariaDB
* Oracle
* Postgres&#x20;

Amazon Aurora is always enabled in Multiple-AZ (primary and standby instances).



In case the primary DB instance fails  the same connection string is maintained and AWS does a DNS failover from to the standby instance, which is then promoted to primary instance. This does not require any administrative intervention.



## Read Replica&#x20;

Read Replica is used to improve performances&#x20;
