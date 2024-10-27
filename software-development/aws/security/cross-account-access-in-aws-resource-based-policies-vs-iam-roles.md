# Cross-Account Access in AWS: Resource-Based Policies vs IAM Roles

##

### Resource-Based Policies

#### Overview

Resource-based policies are attached directly to resources (like S3 buckets, SQS queues, SNS topics) and specify who can access that resource and what they can do.

#### Example Resource-Based Policy (S3 Bucket)

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowCrossAccountAccess",
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:iam::123456789012:root"  // Account B
            },
            "Action": [
                "s3:GetObject",
                "s3:ListBucket"
            ],
            "Resource": [
                "arn:aws:s3:::example-bucket",
                "arn:aws:s3:::example-bucket/*"
            ]
        }
    ]
}
```

#### Best Use Cases

1. **Simple Resource Sharing**
   * When you want to share specific resources with specific accounts
   * When the resource natively supports resource-based policies
2. **Service-to-Service Access**
   * Lambda accessing S3
   * CloudWatch Logs sending data to S3
3. **Public Access Requirements**
   * When you need to grant access to anonymous users (like public S3 buckets)

#### Advantages

* No need to switch roles
* Direct access without assuming additional credentials
* Easier to manage for simple use cases
* Works with AWS services that can't assume roles

### IAM Roles

#### Overview

IAM roles are AWS identity types that provide temporary credentials. For cross-account access, users or services from one account can assume a role in another account.

#### Example Role Trust Policy

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:iam::987654321098:root"  // Account A
            },
            "Action": "sts:AssumeRole",
            "Condition": {
                "StringEquals": {
                    "aws:PrincipalOrgID": "o-example123"
                }
            }
        }
    ]
}
```

#### Example Role Permission Policy

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "dynamodb:GetItem",
                "dynamodb:PutItem",
                "dynamodb:UpdateItem"
            ],
            "Resource": "arn:aws:dynamodb:region:account-id:table/example-table"
        }
    ]
}
```

#### Best Use Cases

1. **Complex Access Patterns**
   * When users need access to multiple resources
   * When you need to enforce MFA
   * When you need session-based access
2. **Application Access**
   * EC2 instances accessing resources in another account
   * Lambda functions requiring cross-account access
3. **Administrative Access**
   * DevOps teams managing multiple accounts
   * Security audit teams requiring temporary access

#### Advantages

* More secure (temporary credentials)
* Centralized permission management
* Supports granular access control
* Works with AWS Organizations service control policies (SCPs)

### Decision Matrix

| Requirement             | Resource-Based Policy | IAM Role |
| ----------------------- | --------------------- | -------- |
| Temporary access needed | ❌                     | ✅        |
| Multiple resource types | ❌                     | ✅        |
| Service-to-service      | ✅                     | ⚠️       |
| Public access needed    | ✅                     | ❌        |
| Session control         | ❌                     | ✅        |
| MFA enforcement         | ❌                     | ✅        |

### Best Practices

1. **Security**
   * Always use least privilege principle
   * Use conditions to restrict access (IP, time, MFA)
   * Regularly audit cross-account access
2. **Organization**
   * Document all cross-account relationships
   * Use meaningful names and tags
   * Implement consistent naming conventions
3. **Monitoring**
   * Enable CloudTrail for cross-account activity
   * Set up alerts for suspicious activities
   * Regular access pattern reviews

### Common Exam Scenarios

1. **Scenario: Multiple Team Access**
   * Multiple teams need access to various resources
   * Solution: Use IAM roles with specific permission sets
   * Reasoning: Better access control and audit capabilities
2. **Scenario: S3 Data Sharing**
   * Share specific S3 bucket with partner account
   * Solution: Use bucket policy (resource-based)
   * Reasoning: Simpler to manage, direct access
3. **Scenario: DevOps Management**
   * DevOps team managing multiple AWS accounts
   * Solution: Use IAM roles with Organizations
   * Reasoning: Centralized control, temporary access
