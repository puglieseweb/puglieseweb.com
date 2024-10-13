# General considerations

## OLTP and OLAP&#x20;

| Aspect               | OLTP                                                                          | OLAP                                                                               |
| -------------------- | ----------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| Purpose              | Manages day-to-day transactions and operational data                          | Analyzes historical data for business intelligence and decision-making             |
| Primary AWS Services | - Amazon RDS\<br>- Amazon Aurora\<br>- Amazon DynamoDB (NoSQL)                | - Amazon Redshift\<br>- Amazon Athena\<br>- Amazon EMR                             |
| Data Structure       | Normalized data models                                                        | Denormalized, dimensional models (star or snowflake schemas)                       |
| Query Complexity     | Simple, predefined queries                                                    | Complex queries involving aggregations and joins                                   |
| Data Volume          | Smaller datasets, frequent updates                                            | Large historical datasets, less frequent updates                                   |
| Performance Focus    | Fast insert/update/delete operations                                          | Fast read and aggregation operations                                               |
| Concurrency          | High concurrency, many simultaneous users                                     | Lower concurrency, fewer simultaneous users                                        |
| Typical Data Age     | Current, operational data                                                     | Historical data, often spanning months or years                                    |
| Backup and Recovery  | Point-in-time recovery, frequent backups                                      | Less frequent backups, focus on data retention                                     |
| Scalability Approach | Vertical and horizontal scaling (e.g., RDS Multi-AZ, Aurora Serverless)       | Massive parallel processing (e.g., Redshift clusters)                              |
| Data Consistency     | ACID compliance crucial                                                       | Eventually consistent models acceptable                                            |
| Typical Use Cases    | - E-commerce transactions\<br>- Banking operations\<br>- Inventory management | - Sales trend analysis\<br>- Financial reporting\<br>- Customer behavior analytics |
| Query Response Time  | Milliseconds to seconds                                                       | Seconds to minutes                                                                 |
| Data Redundancy      | Minimal (normalized)                                                          | Accepted for performance (denormalized)                                            |
| AWS Integration      | Tight integration with application layer (e.g., through API Gateway, Lambda)  | Integration with BI tools (e.g., QuickSight, third-party tools)                    |

##

## Cross-region data replication

Several AWS storage services offer cross-region capabilities. Here's a concise overview:

1. Amazon S3 (Simple Storage Service):
   * Cross-Region Replication (CRR)
   * Can replicate objects to buckets in different regions
2. Amazon EFS (Elastic File System):
   * EFS Replication
   * Allows creating read-only replicas in different regions
3. Amazon RDS (Relational Database Service):
   * Cross-Region Read Replicas
   * Supports asynchronous replication to different regions
4. Amazon DynamoDB:
   * Global Tables
   * Multi-region, multi-active database replication
5. Amazon Aurora:
   * Global Database
   * Allows read replicas across multiple regions
6. AWS Backup:
   * Cross-Region Backup
   * Can copy backups to different regions
7. Amazon FSx:
   * Cross-Region Replication for FSx for Windows File Server
   * Allows replicating file systems to different regions

Key benefits of cross-region storage:

* Disaster recovery
* Low-latency access for global users
* Compliance with data sovereignty requirements

## Cross-region bidirectional data replication

DynamoDB Global Tables

DynamoDB Global Tables provide bidirectional (multi-active) replication across multiple AWS regions. Here are the key points:

1. Multi-active: All replicas can accept read and write operations.
2. Bidirectional: Changes made in any region are replicated to all other regions.
3. Eventual consistency: Updates are propagated to all regions, typically within seconds.
4. Conflict resolution: Uses a "last writer wins" approach for conflicting concurrent updates.

Other services mentioned primarily offer unidirectional replication:

* S3 Cross-Region Replication: One-way replication from source to destination.
* EFS Replication: Creates read-only replicas.
* RDS Cross-Region Read Replicas: Read-only replicas in other regions.
* Aurora Global Database: One primary write region, read-only replicas in other regions.
* FSx Cross-Region Replication: One-way replication to a secondary region.

It's worth noting that while not fully bidirectional, some services like Aurora Global Database allow for manual failover, which can change the write region if needed.

## Log file validation

Log file validation in AWS typically refers to the process of verifying the integrity, authenticity, and completeness of log files generated by various AWS services. Here's a concise overview:

1. Purpose:
   * Ensure logs haven't been tampered with
   * Verify all expected log entries are present
   * Detect any unauthorized modifications
2. Common methods:
   * Checksum verification
   * Digital signatures
   * Log file hashing
3. AWS services that support log validation:
   * CloudTrail: Provides log file integrity validation
   * S3: Supports server-side encryption and versioning for log files
   * CloudWatch Logs: Offers encrypted log groups
4. CloudTrail log file validation process:
   * Creates a hash for each log file
   * Generates a digitally signed digest file containing hashes
   * Users can verify integrity using the AWS CLI or SDK
5. Benefits:
   * Enhanced security and compliance
   * Improved forensic capabilities
   * Increased trust in log data for auditing



## CloudTrail data events and management events

CloudTrail distinguishes between data events and management events, which serve different purposes in AWS logging. Here's a concise comparison:

Management Events:

1. Focus: Control plane operations
2. Examples:
   * Creating/deleting IAM users
   * Starting/stopping EC2 instances
   * Configuring security groups
3. Default: Logged by default
4. Volume: Generally lower volume
5. Use case: Audit and compliance, tracking administrative actions

Data Events:

1. Focus: Data plane operations (resource-level activity)
2. Examples:
   * S3 object-level API activity (GetObject, PutObject, DeleteObject)
   * Lambda function executions
   * DynamoDB item-level operations
3. Default: Not logged by default (must be explicitly enabled)
4. Volume: Can be very high volume
5. Use case: Detailed monitoring of resource access and changes

Key differences:

* Scope: Management events are broader, data events are more granular
* Enabling: Management events are on by default, data events require explicit configuration
* Cost: Data events typically incur higher costs due to volume
* Detail level: Data events provide more in-depth information about resource interactions

## Data Transfer

VPC Endpoints:

1. Security: Provide a private connection within the VPC, not traversing the public internet
2. Performance: Can offer lower latency due to direct connectivity
3. Cost: May reduce data transfer costs, especially for large volumes of data
4. Complexity: Require some additional configuration and management

Internet Public Transport:

1. Security: Data travels over the public internet, requiring encryption
2. Performance: Subject to internet congestion and potential latency issues
3. Cost: Standard data transfer costs apply
4. Complexity: Generally simpler to set up and use

## AWS multi-accounts

Using multiple AWS accounts is a best practice for many organizations. Here are the key reasons why you might need or want to use multi-accounts in AWS:

1. Security and isolation:
   * Separate accounts provide strong security boundaries between different environments, applications, or business units.
   * It reduces the blast radius of potential security incidents.
2. Cost management and allocation:
   * Each account gets its own billing, making it easier to track and allocate costs to different projects, departments, or clients.
3. Compliance and governance:
   * Different accounts can have different compliance requirements, making it easier to manage and audit specific regulatory needs.
4. Resource limits:
   * AWS imposes certain service limits per account. Multiple accounts allow you to overcome these limits for large-scale operations.
5. Environment segregation:
   * Separate accounts for development, testing, staging, and production environments prevent accidental changes to production resources.
6. Organizational structure alignment:
   * Accounts can be structured to mirror your organization's departments or business units.
7. Access control:
   * Granular control over who has access to which resources across different accounts.
8. Workload isolation:
   * Critical workloads can be isolated in their own accounts to ensure performance and availability.
9. Experimentation and innovation:
   * Separate accounts for proof-of-concept or experimental projects without risking core infrastructure.
10. Mergers and acquisitions:
    * Easier to integrate or separate AWS resources during corporate restructuring.
11. Multi-tenant architectures:
    * For service providers, separate accounts can be used for different customers.
12. Disaster recovery:
    * Separate accounts can be used for backup and recovery strategies across different regions.

AWS provides tools like AWS Organizations and AWS Control Tower to help manage multi-account setups efficiently. These allow for centralized governance, security policies, and account management across your entire AWS organization
