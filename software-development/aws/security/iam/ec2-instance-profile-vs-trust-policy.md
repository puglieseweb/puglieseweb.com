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



## Examples

A practical examples of both approaches to help illustrate the differences.

Option A (Recommended Approach for EC2 Instances):

1. First, create the IAM policy for DynamoDB access:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "dynamodb:GetItem",
                "dynamodb:PutItem",
                "dynamodb:Query"
            ],
            "Resource": "arn:aws:dynamodb:us-east-1:123456789012:table/MyTable"
        }
    ]
}
```

2. Create the IAM role with this policy:

```bash
aws iam create-role --role-name EC2DynamoDBRole --assume-role-policy-document '{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}'
```

3. Create and attach the instance profile:

```bash
# Create instance profile
aws iam create-instance-profile --instance-profile-name EC2DynamoDBProfile

# Add role to instance profile
aws iam add-role-to-instance-profile --instance-profile-name EC2DynamoDBProfile --role-name EC2DynamoDBRole

# Attach profile to EC2 instance
aws ec2 associate-iam-instance-profile --instance-id i-1234567890abcdef0 --iam-instance-profile Name=EC2DynamoDBProfile
```

4. In your application code, you don't need to handle credentials:

```python
import boto3

# No need to specify credentials - they're automatically retrieved from instance profile
dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('MyTable')

# Access DynamoDB
response = table.get_item(
    Key={
        'id': '123'
    }
)
```

Option B (Not Recommended for EC2 Instances):

1. Create similar IAM policy as above
2. Create IAM role with specific EC2 instance in trust relationship:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:ec2:us-east-1:123456789012:instance/i-1234567890abcdef0"
            },
            "Action": "sts:AssumeRole"
        }
    ]
}
```

3. In your application, you'd need to explicitly assume the role:

```python
import boto3

# Need to explicitly assume role
sts_client = boto3.client('sts')
assumed_role = sts_client.assume_role(
    RoleArn='arn:aws:iam::123456789012:role/EC2DynamoDBRole',
    RoleSessionName='EC2Session'
)

# Create DynamoDB client with temporary credentials
dynamodb = boto3.resource(
    'dynamodb',
    aws_access_key_id=assumed_role['Credentials']['AccessKeyId'],
    aws_secret_access_key=assumed_role['Credentials']['SecretAccessKey'],
    aws_session_token=assumed_role['Credentials']['SessionToken']
)

table = dynamodb.Table('MyTable')
response = table.get_item(
    Key={
        'id': '123'
    }
)
```

Key differences that make Option A better:

1. Simpler application code - no credential management needed
2. Automatic credential rotation
3. No need to store or manage any credentials in your application
4. Uses AWS's built-in instance profile mechanism
5. Follows AWS best practices for EC2 instance permissions
6. Easier to maintain and more secure

With Option B, you'd need to:

1. Manage credential rotation yourself
2. Handle credential errors and refreshing
3. Store role ARN in your application
4. Deal with more complex error scenarios
5. Maintain more complex code
