# Identity, Permission, Trust and Resource Policies



```mermaid
graph LR
    subgraph "Identity-Based Policies"
        U[IAM User]
        G[IAM Group]
        IBP[Identity-Based Policy]
        U -->|attached to| IBP
        G -->|attached to| IBP
    end

    subgraph "Role & Trust Policies"
        R[IAM Role]
        TP[Trust Policy]
        PP[Permission Policy]
        R -->|has| TP
        R -->|has| PP
        TP -->|defines who can\nassume role| R
        PP -->|defines what role\ncan do| R
    end

    subgraph "Resource-Based Policies"
        S3[S3 Bucket]
        RBP[Resource-Based Policy]
        S3 -->|has| RBP
        RBP -->|controls access to| S3
    end

    IBP -->|can allow\nassume role| R
    R -->|can access| S3
    RBP -->|can grant direct\naccess to| U

    classDef policy fill:#f9f,stroke:#333,stroke-width:2px
    classDef resource fill:#bbf,stroke:#333,stroke-width:2px
    classDef identity fill:#bfb,stroke:#333,stroke-width:2px
    
    class IBP,TP,PP,RBP policy
    class S3 resource
    class U,G,R identity
```



There are the following types of policies:&#x20;



### **Identity-based** Policies

* **Identity-based** policies are attached to users/groups and define what those identities can do across AWS services

```json
{
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



### Role Policies

<figure><img src="../../../../../../.gitbook/assets/image (141).png" alt=""><figcaption></figcaption></figure>

IAM role must have two types of policies:

1. **Trust Policy** (only one):
   1. Defines WHO can assume the role
   2. Sometimes called "trust relationship policy"
   3. Always uses the **sts:AssumeRole** action
   4. Must include Principal element

```json
{
    "Version": "2012-10-17",
    "Statement": [{
        "Effect": "Allow",
        "Principal": {
            "Service": "lambda.amazonaws.com"
        },
        "Action": "sts:AssumeRole"
    }]
}
```

2. **Permission Policies** (can have multiple):
   1. Define WHAT the role can do
   2. Lists allowed/denied AWS actions
   3. No Principal element needed
   4. Can attach multiple permission policies to a role

```json
{
    "Version": "2012-10-17",
    "Statement": [{
        "Effect": "Allow",
        "Action": [
            "s3:GetObject",
            "s3:PutObject"
        ],
        "Resource": "arn:aws:s3:::my-bucket/*"
    }]
}
```

Think of it this way:

* Trust Policy = Who can use this role?
* Permission Policy = What can this role do?

Both are required for a role to be functional. You can't have a role without a trust policy, and a role without permission policies wouldn't be able to do anything.

### **Resource-based**

**Resource-based** (e.g. S3 bucket policy) policies are attached to resources (like S3 buckets) . Resource-based policy must declare the "Principle" element to define who can access that resource.

```json
{
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



### Example

A common real-world example might combine these:

1. A developer has an identity-based policy allowing them to assume certain roles
2. Those roles have permission policies defining what AWS services they can access
3. The resources they need to access might have resource-based policies allowing access from those roles
