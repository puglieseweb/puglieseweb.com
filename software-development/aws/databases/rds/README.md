---
description: Relational Database Service
---

# RDS

Amazon RDS stands for Relational Database Service.

### Overview

* RDS is a managed relational database service
* Suitable for OLTP (Online Transaction Processing) workloads
* Supports multiple database engines

### Supported Database Engines

1. MySQL
2. PostgreSQL
3. MariaDB
4. Oracle
5. Microsoft SQL Server
6. Amazon Aurora (MySQL and PostgreSQL compatible)

### Key Features

#### Automatic Backups

* Enabled by default
* Retention period: 1 to 35 days
* **Important**: Automatic Backups must be enabled to deploy a read replica

#### Multi-AZ (Availability Zone) Deployment

* Purpose: High Availability and Disaster Recovery
* **Not** used for improving performance
* Creates an exact copy of the primary DB (standby DB) in another AZ
* Synchronous replication
* Automatic failover to standby in case of issues with primary
* Supported by all RDS engines except Aurora (Aurora has its own HA architecture)

### Multi-AZ Failover Process

1. Primary DB instance fails
2. AWS performs DNS failover to standby instance
3. Standby is promoted to new primary
4. Same connection string is maintained
5. No manual intervention required

#### Read Replicas

* Purpose: Improve read performance
* **Not** used for Disaster Recovery
* Asynchronous replication from primary
* Can be in the same AZ, different AZ, or different Region
* Each read replica has its own DNS endpoint
* Can be promoted to be standalone databases (e.g., for OLAP workloads)
  * Promotion breaks the replication
* Up to 5 Read Replicas per DB instance
* **Note**: Automatic Backups must be enabled to use Read Replicas

#### Scaling

* Vertical Scaling: Change instance type (requires downtime)
* Storage Scaling: Increase storage size (no downtime)
* Read Scaling: Add Read Replicas

### Security

* Encryption at rest using KMS
* Encryption in transit using SSL
* IAM DB Authentication for MySQL and PostgreSQL

### Monitoring

* Enhanced Monitoring: OS-level metrics
* Performance Insights: Database performance analysis

### Maintenance

* Automated minor version upgrades
* Maintenance windows for updates

### RDS vs. EC2 Hosted Databases

* RDS: Managed service, easier to administer
* EC2: More control, but requires more management

### Important Exam Points

1. RDS is for OLTP workloads, not suitable for OLAP (use Redshift for OLAP)
2. Multi-AZ is for HA/DR, not performance improvement
3. Read Replicas are for performance improvement, not HA/DR
4. Automatic Backups are prerequisite for Read Replicas
5. Aurora is always Multi-AZ enabled
6. RDS supports both single-AZ and Multi-AZ deployments (except Aurora)
7. Read Replicas can be promoted to standalone databases
8. Maximum of 5 Read Replicas per DB instance

### Pricing Considerations

* Instance hours
* Storage GB/month
* I/O requests/month
* Data transfer
* Additional features (Multi-AZ, Read Replicas)

Remember to review the AWS documentation for the most up-to-date information, as service features and limits can change over time.
