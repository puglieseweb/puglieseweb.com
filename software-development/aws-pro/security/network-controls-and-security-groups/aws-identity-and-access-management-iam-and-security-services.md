# AWS Identity and Access Management (IAM) and Security Services

<figure><img src="../../../../.gitbook/assets/image (20) (1).png" alt=""><figcaption></figcaption></figure>

### IAM Core Components

IAM provides secure control over AWS resource access through several key components:

#### Identity-Based vs Resource-Based Policies

* **Identity-Based Policies**: Attached directly to IAM users, groups, or roles. Define what actions these identities can perform on which resources.
* **Resource-Based Policies**: Attached to resources themselves (like S3 buckets). Define who can access the resource and what actions they can perform.

#### Policy Structure

Policies are written in JSON format and consist of:

* **Services**: AWS services being accessed (e.g., S3, EC2)
* **Actions**: Specific operations allowed or denied (e.g., s3:GetObject)
* **Resources**: Specific AWS resources the policy applies to
* **Effect**: Whether to Allow or Deny the specified actions

### Security Token Service (STS)

STS is a critical service for temporary credential management:

#### Key Features

* Provides temporary security credentials for trusted users
* Supports federation with external identity providers
* Enables cross-account access
* Credentials are short-lived, reducing security risks

<figure><img src="../../../../.gitbook/assets/image (22).png" alt=""><figcaption></figcaption></figure>

#### Common STS Operations

* AssumeRole: For cross-account access
* AssumeRoleWithWebIdentity: For federation with web identity providers
* AssumeRoleWithSAML: For SAML-based federation

### Federation Flow

1. Application initiates request to identity broker
2. Broker authenticates against Active Directory
3. Authorization details are retrieved
4. STS generates temporary security token
5. Token is provided to application
6. Application uses token to access AWS services

<figure><img src="../../../../.gitbook/assets/image (23).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (24).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (25).png" alt=""><figcaption></figcaption></figure>

### Amazon Cognito

Designed specifically for mobile applications with built-in security features:

#### Key Features

* User authentication and authorization
* Integration with social identity providers
* Secure access to AWS resources
* Built-in security token handling

### Token Vending Machine (TVM)

<figure><img src="../../../../.gitbook/assets/image (26).png" alt=""><figcaption></figcaption></figure>

#### Anonymous Mode

* Provides basic AWS service access
* No user identity storage
* Limited functionality

#### Identity Mode

* Supports user registration and login
* Maintains user account information
* Enhanced security features

### AWS Secrets Manager

#### Core Functionality

* Secure storage for sensitive information
  * Passwords
  * API keys
  * Encryption keys
  * Other credentials

#### Key Features

* API-based access control through IAM
* Automatic credential rotation for RDS databases
  * MySQL
  * PostgreSQL
  * Aurora
* Eliminates need for hardcoded credentials
* Integration with application code
* Centralized secret management

#### Best Practices

* Use fine-grained IAM policies to control access
* Implement automatic rotation where possible
* Monitor access through AWS CloudTrail
* Regular audits of stored secrets
