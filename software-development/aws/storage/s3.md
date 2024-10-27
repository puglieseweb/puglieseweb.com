# S3

### Exam notes

Query and retrieve the files that are in Amazon **S3 by using Amazon Athena.**&#x20;

Query and retrieve the files that are in S3 Glacier by using **S3 Glacier Select.**

If there is the need of accessing files randomly for S3 than use Intelligent-Tiering&#x20;

### 1. S3 Basics

* Object storage service for storing and retrieving any amount of data
* Highly scalable, durable, and available
* Objects are stored in buckets
* Max object size: 5TB
* Unlimited storage capacity

### 2. S3 Storage Classes

* Standard: Default, high durability, availability, and performance
* Intelligent-Tiering: Automatic cost savings for data with unknown or changing access patterns
* Standard-IA (Infrequent Access): Lower cost for infrequently accessed data
* One Zone-IA: Lower cost for infrequently accessed data that doesn't require multi-AZ resilience
* Glacier **Instant Retrieval:** Lowest cost storage for long-lived data accessed **once per quarter**
* Glacier **Flexible Retrieval:** Archived data with retrieval times **from minutes to hours**
* Glacier **Deep Archive:** Lowest cost storage for long-term retention, with retrieval time of **hours**
* Outposts: Object storage on AWS Outposts on-premises

As April 2024 this are the prices:&#x20;

| Storage Class              | Price per GB per Month        |
| -------------------------- | ----------------------------- |
| Standard                   | $0.023                        |
| Intelligent-Tiering        | $0.023 (frequent access tier) |
| Standard-IA                | $0.0125                       |
| One Zone-IA                | $0.01                         |
| Glacier Instant Retrieval  | $0.004                        |
| Glacier Flexible Retrieval | $0.0036                       |
| Glacier Deep Archive       | $0.00099                      |
| Outposts                   | Varies (contact AWS)          |

### 3. Data Consistency

* **Strong read-after-write** consistency for all S3 GET, PUT, and LIST operations

### 4. Security and Access Control

* All objects are private by default
* Bucket Policies: JSON-based policies at the bucket level
* Access Control Lists (ACLs): Can be applied to buckets and objects
* IAM Policies: For user-based and role-based access control
* Presigned URLs: Time-limited access to specific objects
* Presigned Cookies: Time-limited access to multiple objects

### 5. Encryption

* Server-Side Encryption (SSE):
  * SSE-S3: S3-managed keys
  * SSE-KMS: AWS KMS-managed keys
  * SSE-C: Customer-provided keys
* Client-Side Encryption
* Encryption in transit (SSL/TLS)

### 6. Versioning

* Keeps multiple versions of an object in the same bucket
* Protects against accidental deletions and modifications
* Can be used with lifecycle policies

### 7. Lifecycle Management

* Automate moving objects between storage classes
* Can be used to expire (delete) objects
* Can be applied to current versions and previous versions

### 8. Replication

* Cross-Region Replication (CRR): Replicate objects across different regions
* Same-Region Replication (SRR): Replicate objects within the same region
* Requires versioning to be enabled on source and destination buckets

### 9. Transfer Acceleration

* Uses CloudFront's globally distributed edge locations to accelerate uploads to S3

### 10. Event Notifications

* Can trigger Lambda functions, send messages to SQS or SNS based on bucket events

### 11. Static Website Hosting

* Can host static websites directly from S3 buckets

### 12. Performance

* Prefix naming for high request rates (>100 requests/second)
* Multipart upload for large objects
* S3 Select for server-side filtering and retrieving only necessary data

### 13. Access Points

* Simplify managing data access at scale for shared datasets in S3

### 14. Object Lock

* Write Once Read Many (WORM) model
* Helps prevent objects from being deleted or overwritten for a fixed time or indefinitely

### 15. Inventory

* Audit and report on the replication and encryption status of objects

### 16. Analytics

* Storage Class Analysis: Help determine when to transition objects to appropriate storage class
* S3 Analytics: Analyze storage access patterns to help you decide when to transition data

## Presigned URLs VS Presigned Cookies

### Presigned URLs

* Purpose: To grant temporary access to private S3 objects.
* Functionality:
  * Creates a URL with time-limited permissions.
  * Anyone with the URL can download the object within the specified time frame.
* Use case: Sharing specific objects temporarily without changing their permissions.

### Presigned Cookies

* Purpose: To provide access to multiple restricted files.
* Functionality:
  * The cookie is saved on the user's computer.
  * Allows browsing of restricted content.
* Use case: When users need access to multiple files without generating individual URLs.

### Key Differences:

* Presigned URLs: For single object access.
* Presigned Cookies: For multiple file access.

### Important Notes:

* Both methods provide temporary, controlled access to private S3 objects.
* Useful for scenarios where you want to keep objects private but provide temporary access.



