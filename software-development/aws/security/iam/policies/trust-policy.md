# Trust policy

A trust policy in AWS is a JSON document that defines which principals (entities) are allowed to assume a role. It's a crucial component of IAM roles that determines who can use the role's permissions.

Here's a breakdown with an example:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "Service": "ec2.amazonaws.com",
                "AWS": "arn:aws:iam::123456789012:user/DevUser"
            },
            "Action": "sts:AssumeRole",
            "Condition": {
                "StringEquals": {
                    "aws:RequestedRegion": "us-east-1"
                }
            }
        }
    ]
}

```

Key components of a trust policy:

1. Version: Always "2012-10-17" (current version of the policy language)
2. Principal: Specifies who can assume the role:
   * AWS services (like EC2, Lambda)
   * AWS accounts
   * IAM users
   * IAM roles
3. Effect: Either "Allow" or "Deny"
4. Action: Usually "sts:AssumeRole" for trust policies
5. Condition (optional): Additional requirements that must be met

Common use cases:

* Allowing EC2 instances to access other AWS services
* Cross-account access
* Enabling AWS services to perform actions on your behalf
* Federation with external identity providers

Would you like me to provide more specific examples for any particular use case?
