# Storage Gateway

There are three different types of AWS Storage Gateway services:

1. Amazon S3 File Gateway (**good** **file sharing**):
   * Provides a file interface to Amazon S3
   * Supports SMB and NFS protocols
   * Files are stored as objects in S3 buckets
   * Maintains frequently accessed data in local cache for low-latency access
2. Volume Gateway:
   * Presents iSCSI block storage volumes to applications
   * Comes in two modes:
     * Cached Volumes: Primary data in S3, frequently accessed data cached locally (**good for data migration**)
     * Stored Volumes: Primary data stored locally, asynchronously backed up to S3 as EBS snapshots (**good for backups**)&#x20;
3. Tape Gateway (previously called "AWS Storage Gateway – Virtual Tape Library"):
   * Provides a virtual tape infrastructure
   * Compatible with major backup software applications
   * Data is stored in virtual tapes backed by S3 and Glacier
   * Helps organizations replace physical tape infrastructure

Each gateway type serves different use cases:

* File Gateway: Best for file shares, data lakes, and general file storage
* Volume Gateway: Ideal for disaster recovery, backup, and cloud migration
* Tape Gateway: Perfect for organizations wanting to replace physical tape backup systems

### Where is the data stored?

It depends on the gateway type:

1. File Gateway - Store data in S3, with local caching.
2. Volume Gateway:
   * Cached volumes - Store data in S3, cache frequently accessed data locally
   * Stored volumes - Store all data locally, backup to S3
3. Tape Gateway - Uses S3 and S3 Glacier

NOTE 1: **While File Gateway and Volume Gateway Cached volumes seem similar, File Gateway provides file-level access (NFS/SMB) while Volume Gateway provides block-level access (iSCSI).**

NOTE 2:  **Block storage uses iSCSI protocol, not SMB. SMB is for file-level access, while iSCSI is for block-level access.**
