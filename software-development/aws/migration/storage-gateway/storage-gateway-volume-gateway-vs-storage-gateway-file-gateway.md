# Volume Gateway VS File Gateway

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

**Volume Gateway - Cached Mode**

* Protocol: iSCSI block-level storage
* Use Case: Applications requiring block storage with less local storage
* Primary Data: Stored in S3, frequently accessed data cached locally
* Access Method: Mount as iSCSI volumes
* Best For:
  * Applications needing block storage with limited local storage
  * Large datasets with predictable access patterns
  * Cost-effective block storage solutions
  * Hybrid cloud architectures

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

| Volume Gateway (Storage) | Volume Gateway (Cached) | File Gateway           |
| ------------------------ | ----------------------- | ---------------------- |
| Block Storage (iSCSI)    | Block Storage (iSCSI)   | File Storage (NFS/SMB) |
| Local Primary Storage    | S3 Primary Storage      | S3 Primary Storage     |
| Async Backup to S3       | Local Cache of Hot Data | Local Cache only       |
| Full volume required     | Partial volume cache ok | Partial file cache ok  |

Common Use Cases:

* Volume Gateway Storage Mode:
  * Enterprise backup solutions
  * Database storage
  * Applications requiring block devices
* Volume Gateway Cached Mode:
  * Large datasets with limited local storage
  * Hybrid cloud storage solutions
  * Secondary storage tiers
  * Cloud-first block storage deployments
* File Gateway:
  * Shared file storage
  * Content repositories
  * Data lake ingestion
  * Cloud migration of file shares
