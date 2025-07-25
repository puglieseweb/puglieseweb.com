# Amazon S3 (Simple Storage Service)

#### Object Storage Basics

* S3 is an object store, not a file system.
* What looks like file paths are actually keys (similar to database records).
* Maximum object size: 5 TB.
* Single PUT operation limit: 5 GB.
* Recommended to use multi-part uploads for files >100 MB.

### Consistency Model

**Read-after-write consistency**  model applies to all S3 operations including GET, PUT, DELETE, HEAD, and LIST operations across all S3 storage classes. The only exception would be in rare events of **simultaneous updates** to the same key from different clients, where S3 uses **"last writer wins"** semantics.

**Single Key Updates**

* Atomic operations - only one update can succeed at a time.
* Processed in timestamp order.
* All changes are immediately visible to subsequent reads.
* Concurrent updates to the same key are resolved based on the last-write-wins principle.

### Security Features



<figure><img src="../../../../../.gitbook/assets/image (137).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../../.gitbook/assets/image (2) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Access Control Methods

* Resource-based controls:
  * Object ACLs.
  * Bucket policies.
* User-based controls:
  * IAM policies.
* Multi-factor authentication for deletions.

#### Versioning

* Creates new version with each write.
* Enables rollback capabilities.
* Provides undelete functionality via Delete markers.
* Old versions count toward billable storage.
* Can be managed via lifecycle policies.

### Advanced Features

#### Cross-Region Replication

Benefits:

* Enhanced security.
* Compliance requirements.
* Reduced latency for global access.
* Geographic redundancy.

#### Storage Classes

<figure><img src="../../../../../.gitbook/assets/image (3) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

* Multiple tiers available
* Intelligent-Tiering:
  * Automatically moves data between tiers.
  * Based on access patterns.
  * Premium pricing for management.
  * Includes archive options to Glacier/Deep Glacier.

#### Lifecycle Management

* Automates object transitions between storage classes
* Rule-based management:
  * Based on prefixes.
  * Based on tags.
  * Applies to current/previous versions.
* Useful for implementing retention policies.
* Automated deletion capabilities.

#### Analytics Capabilities

<figure><img src="../../../../../.gitbook/assets/image (4) (1) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

* Data lake functionality:
  * Compatible with Athena.
  * Works with Redshift Spectrum.
  * Integrates with QuickSight.
* IoT streaming support via Kinesis Firehose.
* Machine learning/AI storage capabilities.
* Storage class analysis for cost optimization.

#### Encryption Options

<figure><img src="../../../../../.gitbook/assets/image (138).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../../.gitbook/assets/image (5) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Native S3 encryption (AES-256).
2. Customer-provided keys.
3. AWS KMS integration.
4. Client-side encryption.

#### Additional Features

<figure><img src="../../../../../.gitbook/assets/image (6) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

* Transfer acceleration (via CloudFront).
* Requester Pays option.
* Tagging support for cost allocation and management.
* Event notifications (SNS, SQS, Lambda).
* Static website hosting.
* BitTorrent protocol support:
  * Generated .torrent files.
  * Peer-to-peer distribution network.
  * Reduces direct S3 bandwidth usage.

### Best Practices

* Implement appropriate encryption methods.
* Use multi-part uploads for large files.
* Leverage lifecycle policies for cost management.
* Monitor storage class usage.
* Implement appropriate security controls.
* Consider geographic distribution for global access.
