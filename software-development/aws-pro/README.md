# AWS Pro

### Defining Serverless

A service is considered serverless when it meets these key criteria:

1. **No Server Management**
   * Provider handles patches and OS updates
   * Example: AWS Lambda
2. **Flexible Scaling**
   * Automatic scaling based on traffic
   * Scaling within defined boundaries
3. **Built-in High Availability**
   * Default fault tolerance
   * Inherent availability features
4. **Scale to Zero** Capability
   * No idle capacity
   * No costs when inactive

#### Note on "Serverless" Services

Some AWS services labeled as "serverless" (e.g., Aurora Serverless, Neptune Serverless) may not fully meet all criteria, particularly zero-scaling. These services represent the best-fit options for serverless applications despite limitations.



## How AWS resources operate across multiple regions

### Global Services (No Region Concept)

* **Route 53** - DNS service operates globally
* **CloudFront** - CDN with global edge locations
* **AWS Global Accelerator** - Global network optimization
* **IAM** - Identity and Access Management (users, roles, policies)
* **AWS Organizations** - Account management across all regions
* **AWS WAF** - Web Application Firewall (when used with CloudFront)

### Cross-Region Capable Resources

**Storage & Data:**

* **S3 Cross-Region Replication** - Replicate objects between regions
* **DynamoDB Global Tables** - Multi-region, multi-master replication
* **RDS Cross-Region Read Replicas** - Read replicas in different regions
* **EBS Snapshots** - Can be copied to other regions
* **AMIs** - Can be copied to other regions

**Networking:**

* **VPC Peering** - Connect VPCs across regions
* **Transit Gateway Inter-Region Peering** - Connect Transit Gateways
* **Direct Connect** - Can connect to multiple regions
* **Route 53 Resolver** - DNS forwarding across regions

**Security & Compliance:**

* **AWS Certificate Manager** - SSL/TLS certificates (global for CloudFront)
* **AWS Secrets Manager** - Cross-region secret replication
* **AWS Config** - Compliance monitoring across regions
* **CloudTrail** - Can log to S3 buckets in different regions

**Management & Monitoring:**

* **CloudWatch** - Cross-region dashboards and alarms
* **AWS Systems Manager** - Parameter Store cross-region replication
* **AWS Backup** - Cross-region backup copying

**Development & Deployment:**

* **CodePipeline** - Can deploy across regions
* **CloudFormation StackSets** - Deploy stacks to multiple regions
* **AWS Lambda@Edge** - Functions run at CloudFront edge locations
