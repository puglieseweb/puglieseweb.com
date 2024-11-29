# IAM roles cannot be attached to IAM Groups

IAM roles cannot be directly attached to IAM Groups due to AWS's fundamental design principle of how IAM roles work. Here's why:

Roles are intended to be assumed temporarily and provide dynamic, time-limited credentials. They work through a trust relationship where an entity (like a user, service, or application) can assume the role to get temporary security credentials.

When you use a role, you're essentially saying "I want to switch to become this role temporarily." This concept doesn't translate well to groups because:

1. Groups are just containers for users - they're meant to simplify permission management by allowing you to assign permissions to multiple users at once
2. Groups can't "become" something else or assume an identity, as they're not active entities that can make requests

However, you can still achieve similar functionality through these approaches:

1. Create an IAM policy that allows users to assume a specific role, then attach this policy to a group
2. Users in that group can then use AWS STS (Security Token Service) to assume the role when needed

For example, you could create a policy like:

```json
jsonCopy{
    "Version": "2012-10-17",
    "Statement": [{
        "Effect": "Allow",
        "Action": "sts:AssumeRole",
        "Resource": "arn:aws:iam::ACCOUNT-ID:role/ROLE-NAME"
    }]
}
```

This policy, when attached to a group, would allow all users in that group to assume the specified role when needed.
