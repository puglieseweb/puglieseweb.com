# File Gateway



AWS Storage Gateway's File Gateway caches local files.

Primary Storage:

* All data is stored in Amazon S3
* S3 is the authoritative source for all files

Local Cache:

* Frequently accessed data is cached locally
* Cache is maintained on the local File Gateway appliance
* Size of cache depends on the storage allocated to the gateway
* Only stores a subset of your data based on access patterns
* Cache can be refreshed or invalidated

<figure><img src="../../../../.gitbook/assets/image (21) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

&#x20;Key features and details about AWS File Gateway:

1. Amazon S3 Integration: AWS File Gateway provides a file interface that **allows you to store and retrieve objects in Amazon S3** using standard file protocols.
2. Supported Protocols: **It supports both NFS** **and SMB** protocols, making it compatible with a wide range of applications and operating systems.
3. Local Cache: The gateway maintains a local cache for low-latency access to recently used data.
4. File Locking: It supports file locking for the SMB protocol to help maintain data consistency in multi-user environments.
5. Lifecycle Management: You can use S3 lifecycle policies to automatically move data to lower-cost storage classes like S3 Glacier.
6. Security Features:
   * Data is encrypted in transit using SSL/TLS
   * Data stored in S3 is encrypted at rest
   * Integrates with AWS Identity and Access Management (IAM) for access control
7. Hybrid Cloud Support: It allows for easy implementation of hybrid cloud architectures, combining on-premises systems with AWS cloud storage.
8. Backup and Disaster Recovery: Can be used as part of a backup or disaster recovery solution, storing copies of important data in S3.
9. Cost-Effective: Helps reduce storage costs by leveraging S3's pricing model and eliminating the need for large local storage arrays.
10. VMware Integration: **Can be deployed as a virtual machine in VMware environments.**
11. Monitoring: Integrates with Amazon CloudWatch for monitoring and alarming.

AWS File Gateway is particularly useful for organizations looking to extend their on-premises file-based applications to use S3 storage, enabling cloud-based workflows while maintaining local access speeds for frequently used data.



AWS is the provider of the VMs.
