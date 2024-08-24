# Storage

* Volumes exist on EBS, whereas Snapshots exist on S3.



## Store Options Use Cases

* **S3**: Used for serveles object storage.
* **Glacier**: Used for archiving objects
* **EFS**: Network File System (NFS) for Linux instances. Centralized?? storage solution across multiple AZs
* **FSx for Lustre**: File storage for high performace computing Linux file systems.
* **EBS Volumes**: Persistence storage for EC2 instances
* **Instance Storage**: Ephemoral storage for EC2 instances&#x20;
* **FSx for Windows**: File System for Windows instances. Centralized storage solution across multiple AZs.&#x20;

## How to encrypt unencrypted volumes

1. Create a snapshot of the unencrypted root device volume.
2. Create a copy of the snapshot and select the encrypt option.
3. Create an AMI from the ecrypted shapshot.
4. Use the AMI to launch new encrypted instances

