# Amazon RDS (Relational Database Service)

### Overview

* Managed database service for popular database flavors
* Best suited for structured, relational data
* Functions as drop-in replacement for on-premises databases
* Automates administrative tasks

### Key Features

* Automated administration:
  * Backups
  * Patching
* Customer-defined maintenance windows
* Push-button scaling
* Simplified replication management

### Anti-Patterns

<figure><img src="../../../../.gitbook/assets/image (55) (1).png" alt=""><figcaption></figcaption></figure>

Avoid RDS for:

1. Large Binary Objects (BLOBs)
   * Use S3 instead
2. Automated Scalability Needs
   * Use DynamoDB instead
   * RDS requires manual instance type changes
3. Name-Value Pair Data Structures
   * Better suited for DynamoDB or NoSQL
4. Unstructured/Unpredictable Data
   * Use DynamoDB or NoSQL
   * Difficult to create schemas for unpredictable data
5. Unsupported Database Platforms
   * Examples: DB2, SAP HANA
   * Use EC2 instead
6. Complete Database Control Requirements
   * Use EC2 for deep feature access
   * RDS focuses on commoditization

### Replication Architecture

<figure><img src="../../../../.gitbook/assets/image (56) (1).png" alt=""><figcaption></figcaption></figure>

#### Multi-AZ Setup

* Master database in primary AZ
* Standby databases in other AZs
* Synchronous replication
* Automatic failover to standby
* Requirements:
  * Must use InnoDB/XtraDB (MariaDB)
  * MyISAM doesn't support replication

#### Read Replicas

* Can be set up across regions
* Asynchronous replication
* Slight lag (seconds to minutes)
* Useful for regional user access

### Failover Scenarios

#### Regional Failover

1. Master Database Failure
   * Automatic promotion of standby
   * Continues serving read replicas
   * Maintains multi-AZ configuration
2. Complete Region Failure
   * Two-step process:
     1. Promote read replica to standalone instance
     2. Reconfigure to multi-AZ
   * Can be scripted with CloudWatch alarms
   * Manual promotion recommended for verification

#### Best Practices

* Consider manual intervention for major failovers
* Verify region failure before promotion
* Script carefully if automating failover
