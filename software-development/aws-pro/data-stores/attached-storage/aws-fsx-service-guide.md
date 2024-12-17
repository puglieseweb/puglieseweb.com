# AWS FSx Service Guide

### Overview

FSx is an AWS file sharing service that addresses scenarios where EFS (Elastic File System) isn't compatible. It provides a distributed file system with non-NFS options for file sharing, with AWS managing the scaling and availability of the data.

### FSx Variants

<figure><img src="../../../../.gitbook/assets/image (21).png" alt=""><figcaption></figcaption></figure>

1. FSx for NetApp ONTAP
2. FSx for OpenZFS
3. **FSx for Windows File Server**
4. **FSx for Lustre**

_Note: The most common variants for AWS Solutions Architect-Professional exam are Windows File Server and Lustre._

<figure><img src="../../../../.gitbook/assets/image (3) (1).png" alt=""><figcaption></figcaption></figure>

### How FSx Works

<figure><img src="../../../../.gitbook/assets/image (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Architecture Components

* Mount targets (e.g., EC2 instances in an auto-scaling group)
* Distributed file system with endpoint
* Elastic network interface for communication
* Authentication via:
  * IAM roles
  * Managed Microsoft ID

#### High Availability

* Stores redundant copies of files
* Built-in protection against AWS infrastructure outages

### FSx for Windows File Server

#### Use Cases

* Windows line-of-business applications
* Windows content management
* Media processing workflows on Windows machines
* Data analytics on Windows machines
* SMB-based file shares

#### Benefits

* Scalable
* Highly available
* Cloud-native solution
* Managed service

### FSx for Lustre

#### Overview

One of the most popular high-performance file storage systems currently in use.

<figure><img src="../../../../.gitbook/assets/image (2) (1).png" alt=""><figcaption></figcaption></figure>

#### Use Cases

* High-performance distributed applications
* High-performance compute clusters
* Large-scale file share solutions (thousands of EC2 instances)
* Big data applications
* Machine learning applications

#### Technical Features

* Backed by solid-state drives (SSDs)
* Optimized data arrangement for frequent access
* Integration with S3 for:
  * Snapshot storage
  * Cold storage for less frequently accessed files
* High-performance capabilities

### Key Exam Points

1. Focus on understanding use cases for:
   * FSx for Windows File Server
   * FSx for Lustre
2. Remember:
   * Windows File Server for SMB-based shares
   * Lustre for high-performance computing needs
   * AWS handles scaling and management
   * Lustre is optimal for scenarios requiring superfast access or thousands of mount targets
