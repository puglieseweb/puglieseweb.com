# SCP (Service Control Policy) implementation types

This is an  In-Depth Analysis of SCP Implementation Types:

### 1. Deny Lists (Deny-based SCPs)

#### Core Characteristics

* Acts as a guardrail by explicitly blocking specific actions
* Default state is permissive - everything not explicitly denied is allowed
* Often used to enforce specific compliance or security requirements
* Can be layered with IAM policies for granular control
* Easier to maintain as your AWS environment grows

#### Common Use Cases

* Preventing deletion of specific resources
* Blocking access to unauthorized AWS regions
* Restricting creation of public resources
* Enforcing tagging standards

#### Examples

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "DenyPublicS3Buckets",
            "Effect": "Deny",
            "Action": [
                "s3:PutBucketPublicAccessBlock",
                "s3:PutBucketPolicy",
                "s3:DeletePublicAccessBlock"
            ],
            "Resource": "*",
            "Condition": {
                "StringEquals": {
                    "s3:PublicAccessBlockConfiguration": "false"
                }
            }
        },
        {
            "Sid": "DenyUnauthorizedRegions",
            "Effect": "Deny",
            "Action": "*",
            "Resource": "*",
            "Condition": {
                "StringNotEquals": {
                    "aws:RequestedRegion": [
                        "us-east-1",
                        "us-west-2",
                        "eu-west-1"
                    ]
                }
            }
        }
    ]
}
```

### 2. Allow Lists (Allow-based SCPs)

#### Core Characteristics

* Starts with zero permissions - denies everything by default
* Requires explicit permission for any allowed action
* Provides strongest security posture
* More complex to maintain and update
* Requires careful planning to avoid disrupting necessary services

#### Common Use Cases

* Strictly controlled environments requiring maximum security
* Regulatory compliance scenarios requiring explicit approval
* Development or testing environments with limited service access
* Critical production accounts with specific service requirements

#### Examples

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowSpecificServices",
            "Effect": "Allow",
            "Action": [
                "ec2:*",
                "rds:*",
                "s3:*"
            ],
            "Resource": "*"
        },
        {
            "Sid": "AllowIAMUserManagement",
            "Effect": "Allow",
            "Action": [
                "iam:CreateUser",
                "iam:DeleteUser",
                "iam:ListUsers",
                "iam:GetUser"
            ],
            "Resource": "arn:aws:iam::*:user/*",
            "Condition": {
                "StringEquals": {
                    "aws:PrincipalTag/Department": "IT"
                }
            }
        }
    ]
}
```

### Implementation Considerations

#### Deny List Advantages

* Easier to implement incrementally
* More flexible for growing organizations
* Lower risk of accidentally blocking critical services
* Simpler to troubleshoot
* Better suited for organizations new to AWS

#### Allow List Advantages

* Maximum security control
* Clear audit trail of permitted actions
* Reduced risk of configuration errors
* Better suited for highly regulated industries
* Easier to maintain compliance requirements

### Best Practices

1. Start with Deny lists for most organizations
2. Document all SCP decisions and rationales
3. Test SCPs in non-production environments first
4. Implement change control processes for SCP modifications
5. Regular review and audit of SCP effectiveness
6. Maintain separate strategies for development and production environments
7. Consider using both types in different organizational units based on requirements

**Remember that SCPs work in conjunction with IAM policies, forming a layered security approach where both sets of permissions must allow an action for it to be permitted.**
