# Volume Gateway vs File Gateway

AWS Volume Gateway:

1. Presents block-level storage volumes that can be mounted as iSCSI devices by your on-premises applications
2. Comes in two modes:
   * Cached volumes: Primary data stored in S3, frequently accessed data cached locally
   * Stored volumes: Complete copy stored locally, asynchronously backed up to S3 as snapshots
3. Ideal for backup, disaster recovery, and **on-premises applications needing cloud storage**
4. Works well with applications requiring low-latency access to entire datasets

AWS File Gateway:

1. Presents a file interface (SMB/NFS) that directly stores and retrieves objects in S3
2. Files are stored as objects in S3, with local cache for recently accessed data
3. Perfect for file-based applications and workloads
4. Integrates well with existing file-based applications without modification
5. Supports both SMB and NFS protocols making it compatible with Windows and Linux systems

Main differences:

* Interface type: Volume Gateway is block-based (iSCSI), while File Gateway is file-based (SMB/NFS)
* Storage model: Volume Gateway can store complete copies locally, while File Gateway primarily uses S3 with local caching
* Use cases: Volume Gateway is better for applications requiring block storage, while File Gateway is ideal for file shares and file-based applications
