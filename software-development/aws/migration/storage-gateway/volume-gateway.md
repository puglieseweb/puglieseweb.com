# Volume gateway

AWS Storage Gateway - Volume Gateway

* Presents storage volumes using iSCSI protocol
* Two operating modes:
  * **Cached mode:** Primary data in S3, frequently accessed data cached locally
  * **Stored mode**: Primary data stored locally, asynchronously backed up to S3

Key Features:

* All data is automatically backed up to S3
* Supports point-in-time snapshots
* Can be used for:
  * Data migration to AWS
  * Disaster recovery
  * Backup and restore
* EBS snapshots can be created from the backed-up data in S3
  * These snapshots can be used to restore volumes in AWS

Common Use Cases:

```
CopyOn-premises → Volume Gateway → S3 → EBS Volumes
(iSCSI)      (Cache/Store)    (Backup) (Restore in AWS)
```



<figure><img src="../../../../.gitbook/assets/image (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>
