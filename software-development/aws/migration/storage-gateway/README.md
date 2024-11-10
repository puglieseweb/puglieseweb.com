# Storage Gateway

Hybrid cloud storage service that helps to merge on-premises resources with the cloud. It can help with a one-time migration or a long-term pairing of you architecture with AWS.



Here's a clear comparison between Volume Gateway (Storage Mode) and File Gateway:

**Volume Gateway - Storage Mode**

* Protocol: iSCSI block-level storage
* Use Case: Applications requiring block storage
* Primary Data: Stored locally, async backup to S3
* Access Method: Mount as iSCSI volumes
* Best For:
  * Applications needing low-latency
  * Block storage requirements
  * Database backups
  * On-premises applications that require iSCSI

**File Gateway**

* Protocol: NFS/SMB file-level storage
* Use Case: File share and file-based applications
* Primary Data: Stored in S3, cached locally
* Access Method: Mount as network file share
* Best For:
  * File shares
  * Document repositories
  * Object storage access
  * Direct S3 integration needs

Key Differences:

```
Volume Gateway (Storage)     |  File Gateway
---------------------------|------------------
Block Storage (iSCSI)      |  File Storage (NFS/SMB)
Local Primary Storage      |  S3 Primary Storage
Async Backup to S3         |  Local Cache only
Full volume required       |  Partial file cache ok
```

Common Use Cases:

* Volume Gateway Storage Mode:
  * Enterprise backup solutions
  * Database storage
  * Applications requiring block devices
* File Gateway:
  * Shared file storage
  * Content repositories
  * Data lake ingestion
  * Cloud migration of file shares
