# Storage Gateway

There are three different types of AWS Storage Gateway services:

1. Amazon S3 File Gateway:
   * Provides a file interface to Amazon S3
   * Supports SMB and NFS protocols
   * Files are stored as objects in S3 buckets
   * Maintains frequently accessed data in local cache for low-latency access
2. Volume Gateway:
   * Presents iSCSI block storage volumes to applications
   * Comes in two modes:
     * Cached Volumes: Primary data in S3, frequently accessed data cached locally (good for data migration)
     * Stored Volumes: Primary data stored locally, asynchronously backed up to S3 as EBS snapshots (good for backups)&#x20;
3. Tape Gateway (previously called "AWS Storage Gateway – Virtual Tape Library"):
   * Provides a virtual tape infrastructure
   * Compatible with major backup software applications
   * Data is stored in virtual tapes backed by S3 and Glacier
   * Helps organizations replace physical tape infrastructure

Each gateway type serves different use cases:

* File Gateway: Best for file shares, data lakes, and general file storage
* Volume Gateway: Ideal for disaster recovery, backup, and cloud migration
* Tape Gateway: Perfect for organizations wanting to replace physical tape backup systems



