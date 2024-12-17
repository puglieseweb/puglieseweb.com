# AWS Elastic File System (EFS): From Sun Microsystems to Modern Cloud Storage

## AWS Elastic File System (EFS): From Sun Microsystems to Modern Cloud Storage

### Historical Context: Sun Microsystems Legacy

Sun Microsystems dominated high-performance computing in the 1980s and 1990s, contributing significantly to modern computing through:

* Creation of the Java programming language
* Development of the Solaris operating system
* Pioneering work in virtualized computing
* Introduction of Network File System (NFS) in 1984 as part of SunOS

### Understanding EFS (Elastic File System)

#### Core Characteristics

* Modern implementation of the NFS file share protocol
* Elastic storage capacity with pay-for-what-you-use pricing
* Distributed across multiple Availability Zones (AZs) within a region
* Configurable mount points in single or multiple AZs

#### Key Differences from EBS

* No need to pre-provision storage space (unlike EBS)
* Approximately **3x more expensive than EBS**
* Approximately **20x more expensive than S3**
* Higher availability and redundancy across AZs

#### Connectivity Options and Security Considerations

1. On-premises Connection Requirements:
   * Recommended: Direct Connect for stable, fast connection
   * Not recommended: Raw internet connection without security
   * NFSv4: Some features not supported (check documentation)
2. Amazon DataSync Integration:
   * Purpose-built protocol for secure synchronization
   * Supports syncing between:
     * On-premises storage to EFS
     * On-premises storage to S3
     * EFS to EFS
   * Works over Direct Connect or internet

### Sample Architecture: Dynamic Web Application

<figure><img src="../../../../.gitbook/assets/image (50).png" alt=""><figcaption></figcaption></figure>

#### Components

1. On-site Elements:
   * Web developers
   * Staging location on on-site data store
2. AWS Infrastructure:
   * DataSync for on-prem to EFS synchronization
   * EFS mount points across multiple AZs
   * Fleet of web servers accessing EFS via NFS
   * PHP application requiring dynamic storage

#### Benefits

* Scalability across zones
* Built-in redundancy
* Consistent data access for web servers
* Secure synchronization with on-premises systems

### Best Practices and Considerations

* Review cost implications before implementation
* Verify NFSv4 feature compatibility
* Ensure secure connectivity for on-premises access
* **Consider DataSync for secure data transfer**
* Plan mount point distribution based on availability needs
