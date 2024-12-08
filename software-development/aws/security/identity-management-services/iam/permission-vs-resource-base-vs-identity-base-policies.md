# Permission VS Resource Base vs Identity Base policies

Key Differences:

* Permission policies (role policies) are attached to roles and define what that role can do
* Resource-based policies are attached to resources (like S3 buckets) and define who can access that resource
* Identity-based policies are attached to users/groups and define what those identities can do across AWS services

A common real-world example might combine these:

1. A developer has an identity-based policy allowing them to assume certain roles
2. Those roles have permission policies defining what AWS services they can access
3. The resources they need to access might have resource-based policies allowing access from those roles



Let me show examples of each type:

1. Permission Policy (Role Policy):

```json
jsonCopy{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "dynamodb:GetItem",
                "dynamodb:PutItem",
                "dynamodb:UpdateItem"
            ],
            "Resource": "arn:aws:dynamodb:us-west-2:123456789012:table/MyTable"
        }
    ]
}
```

This policy would be attached to a role and defines what actions that role can perform.

2. Resource-Based Policy (e.g., S3 Bucket Policy):

```json
jsonCopy{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowCrossAccountAccess",
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:iam::123456789012:role/CrossAccountRole"
            },
            "Action": [
                "s3:GetObject",
                "s3:PutObject"
            ],
            "Resource": "arn:aws:s3:::my-bucket/*"
        }
    ]
}
```

This policy is attached directly to the S3 bucket and controls access to that specific resource.

3. Identity-Based Policy (attached to IAM user/group):

```json
jsonCopy{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ec2:StartInstances",
                "ec2:StopInstances"
            ],
            "Resource": "*",
            "Condition": {
                "StringEquals": {
                    "aws:ResourceTag/Environment": "Production"
                }
            }
        },
        {
            "Effect": "Allow",
            "Action": [
                "ec2:DescribeInstances"
            ],
            "Resource": "*"
        }
    ]
}
```

This policy would be attached directly to an IAM user or group and allows them to start/stop EC2 instances tagged as "Production" and view all instances.
