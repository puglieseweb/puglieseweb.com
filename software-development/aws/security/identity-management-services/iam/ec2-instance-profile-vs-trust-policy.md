# EC2 instance profile VS Trust policy

Let me break this down:

EC2 Instance Profile:

* A container for an IAM role specifically designed for EC2 instances
* Automatically manages temporary credentials and rotates them
* Credentials are automatically available to applications running on the EC2 instance
* Best practice for EC2 instances accessing AWS services
* Example use case: When your EC2 instance needs to access S3, DynamoDB, or other AWS services

Trust Relationship Policy (or Trust Policy):

* Defines which entities can assume an IAM role
* Used primarily for cross-account access or service-to-service authentication
* Part of every IAM role but serves a different purpose than instance profiles
* Example use cases:
  * Allowing AWS Lambda to assume a role to access other services
  * Enabling cross-account access between AWS accounts
  * Allowing external identity providers (like Azure AD) to assume roles
  * Enabling AWS services to act on your behalf (like CloudFormation)

When to use a Trust Policy:

1.  Cross-account access:

    ```json
    jsonCopy{
      "Version": "2012-10-17",
      "Statement": [{
        "Effect": "Allow",
        "Principal": {
          "AWS": "arn:aws:iam::ACCOUNT-ID:root"
        },
        "Action": "sts:AssumeRole"
      }]
    }
    ```
2.  Service-to-service authentication:

    ```json
    jsonCopy{
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

Key Distinction:

* EC2 instance profiles are specifically for EC2 instances accessing AWS services within the same account
* Trust relationship policies are broader and define who can assume the role, whether it's other AWS services, external identities, or cross-account access

Best Practice: For EC2 instances accessing AWS services in the same account, always use instance profiles rather than manually managing trust relationships. Trust relationship policies should be reserved for scenarios involving cross-account access or service-to-service interactions beyond simple EC2-to-AWS-service communication.
