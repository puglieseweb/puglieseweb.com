# CloudTrail

## AWS CloudTrail Overview

AWS CloudTrail serves as a comprehensive logging and monitoring service for AWS accounts, functioning like a CCTV system for **user activities and AWS API usage.** It records and stores detailed logs of all API calls made to your AWS account in Amazon S3 buckets.

### Key Capabilities

CloudTrail enables organizations to:

* Perform after-the-fact security incident investigation
* Implement near real-time intrusion detection
* Maintain industry and regulatory compliance requirements
* Track changes to AWS resources
* Troubleshoot operational issues

### Information Logged

Every API call record includes:

* Complete metadata around the AWS API call
* Identity of the API caller (IAM user/role, AWS service, root account)
* Timestamp of the API call
* Source IP address of the API caller
* Request parameters
* Response elements returned by the AWS service

### Event Types

#### Data Events

* Records object-level API operations
* Examples include:
  * S3: GetObject, PutObject, DeleteObject
  * Lambda: Invoke
  * DynamoDB: GetItem, PutItem, DeleteItem
* Disabled by default due to high volume
* Can be enabled selectively for specific resources

#### Management Events

* Records control plane operations
* Examples include:
  * S3: CreateBucket, DeleteBucket, PutBucketPolicy
  * EC2: RunInstances, TerminateInstances
  * IAM: CreateUser, DeleteRole, AttachRolePolicy
* Enabled by default
* Also known as "control plane operations"

### Additional Features

#### Log File Integrity Validation

* Ensures logs haven't been tampered with
* Uses industry-standard hash algorithms
* Enables compliance with security standards

#### Multi-Region Logging

* Can aggregate logs from multiple regions into a single S3 bucket
* Simplifies log analysis and management
* Helps maintain a consolidated audit trail

#### Integration Capabilities

* CloudWatch Logs: For real-time monitoring
* EventBridge: For automated responses to API calls
* Security Hub: For security findings and compliance checks
* Athena: For SQL-based log analysis

### Best Practices

1. Enable CloudTrail in all regions
2. Enable log file validation
3. Encrypt log files using KMS
4. Configure CloudWatch Logs integration for real-time monitoring
5. Use resource-level tags for better organization
6. Implement appropriate retention policies
7. Set up SNS notifications for important changes

### Security Considerations

* Use IAM policies to control access to CloudTrail logs
* Enable MFA Delete on S3 buckets containing CloudTrail logs
* Configure log file encryption
* Monitor CloudTrail logging status through AWS Config
* Regularly analyze logs for suspicious activity
