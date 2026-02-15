# AWS IAM Policies Study Guide

### Core Concepts

#### Policy Types

1. **Identity-based Policies**
   * Attached to IAM users, groups, or roles
   * Define what actions the identity can perform
   * Can be AWS managed, customer managed (CMP), or inline
2. **Resource-based Policies**
   * Attached directly to resources (e.g., S3 buckets)
   * Define who can access the resource and what actions they can perform
   * Always inline policies
3. **Permission Boundaries**
   * Set maximum permissions an identity can have
   * Used to delegate administration to other users
   * Does not grant permissions by itself

#### Policy Structure

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": ["s3:GetObject", "s3:PutObject"],
            "Resource": "arn:aws:s3:::mybucket/*",
            "Condition": {
                "IpAddress": {
                    "aws:SourceIp": "203.0.113.0/24"
                }
            }
        }
    ]
}
```

### Key Elements

#### Effect

* **Allow**: Explicitly grants permissions
* **Deny**: Explicitly denies permissions
* Explicit deny always overrides allows

#### Action

* Services use namespace (e.g., `s3:`, `ec2:`)
* Can use wildcards (`*`)
* Common patterns:
  * `service:*` - All actions in a service
  * `service:Get*` - All Get actions
  * `service:*Object` - All actions ending in Object

#### Resource

* Specified using ARN (Amazon Resource Name)
* Format: `arn:partition:service:region:account-id:resource-type/resource-id`
* Can use wildcards for multiple resources
* Must match the actions specified

#### Condition

Common condition operators:

* String conditions: `StringEquals`, `StringLike`
* Numeric conditions: `NumericEquals`, `NumericGreaterThan`
* Date conditions: `DateEquals`, `DateGreaterThan`
* Boolean conditions: `Bool`
* IP address conditions: `IpAddress`, `NotIpAddress`

### Important Concepts for SAA-C03

#### Policy Evaluation Logic

1. By default, all requests are denied (implicit deny)
2. Explicit allow in a policy overrides implicit deny
3. Explicit deny in any policy overrides any allows
4. Policies are evaluated in aggregate

#### Best Practices

1. Follow least privilege principle
2. Use groups to assign permissions to users
3. Use roles for applications and AWS services
4. Use policy conditions to enhance security
5. Regularly review and monitor policies

#### Common Exam Scenarios

1. **Cross-Account Access**
   * Resource-based policies vs. IAM roles
   * When to use each approach
2. **Application Access to AWS Resources**
   * Using IAM roles with EC2 instances
   * Using roles with ECS tasks
   * Using roles with Lambda functions
3. **Security Controls**
   * MFA enforcement
   * IP address restrictions
   * Time-based restrictions
   * Resource tag-based access
4. **Troubleshooting**
   * Understanding policy evaluation
   * Identifying why access is denied
   * Using policy simulator

### Example Scenarios

#### EC2 Instance Accessing S3

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:GetObject",
                "s3:PutObject"
            ],
            "Resource": [
                "arn:aws:s3:::product-data/*",
                "arn:aws:s3:::production-logs/*"
            ]
        }
    ]
}
```

#### Cross-Account Access

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:iam::123456789012:root"
            },
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::mybucket/*"
        }
    ]
}
```

### Exam Tips

* Understand the difference between resource-based and identity-based policies
* Know when to use roles vs. resource-based policies
* Be familiar with policy evaluation logic
* Understand how permission boundaries work
* Know common troubleshooting scenarios
* Be able to identify the most secure and cost-effective solution
