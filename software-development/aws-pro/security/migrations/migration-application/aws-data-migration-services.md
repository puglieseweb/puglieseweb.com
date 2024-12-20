# AWS Data Migration Services

### Database Migration Service (DMS)

Database Migration Service is AWS's primary solution for relational database migration. Key features include:

* Supports migration of relational databases to RDS, Aurora, or EC2-based databases
* Includes built-in schema conversion tool for heterogeneous migrations (different source and target database types)
* Compatible with non-relational databases like MongoDB and DynamoDB
* Enables active replication from on-premises databases to:
  * AWS databases
  * S3 buckets
  * Snowball devices
* Maintains continuous updates during migration until cutover

#### DMS Implementation

* Requires VPN connection to AWS cloud
* Uses replication instance to:
  * Receive data from on-premises source
  * Deliver to target database
* Alternative path using Snowball device when direct connection isn't available:
  * Data replicated to Snowball
  * Delivered to S3 bucket
  * Transferred to replication instance
  * Finally delivered to target database

<figure><img src="../../../../../.gitbook/assets/image (16) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### DataSync

DataSync is AWS's modern solution for file system and object storage migration. Features include:

* Provides secure, automated data transfer from on-premises to AWS
* Compatible with multiple file systems:
  * SMB (Server Message Block)
  * HDFS (Hadoop Distributed File System)
  * NFS (Network File System)
* Supports migration of self-managed object storage to S3
* Can sync data to:
  * Amazon Elastic File System
  * Amazon FSx

<figure><img src="../../../../../.gitbook/assets/image (17) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### DataSync Implementation

* Requires DataSync agent installation on-premises
* Can deliver data via:
  * Direct transfer to AWS services
  * Snowball device
  * S3 on Outposts
* Uses VPN connection to AWS cloud

### Transfer Family

Transfer Family facilitates secure file transfers for specific use cases. Key features include:

* Multi-availability zone architecture
* Supports FTP and AS2 protocols
* Designed for Managed File Transfer workflows
* Automated file processing:
  * Encryption
  * Filtering
  * Tagging
  * Compression
* External user authentication for secure file transfers

<figure><img src="../../../../../.gitbook/assets/image (18) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Transfer Family Implementation

* Enables external user access to AWS-stored files
* Supports SFTP and FTPS protocols
* Integrates with applications using local caching and Storage Gateway
* Provides authenticated access to specific subsets of S3-stored objects

### Key Takeaways

<figure><img src="../../../../../.gitbook/assets/image (19) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. **Database Migration Service (DMS)**
   * Primary tool for database migrations
   * Handles both homogeneous and heterogeneous migrations
   * Supports migrations from on-premises and other cloud databases
2. **DataSync**
   * Automates file and object migration
   * Supports multiple file system types
   * Direct migration to EFS, FSx, or S3
3. **Transfer Family**
   * Facilitates secure file transfers
   * Supports third-party authentication
   * Enables controlled access to AWS-stored files
