# AWS Identity and Access Management (IAM) and Security Services

Key security services are:

* IAM: AWS resource access control
* STS: Temporary AWS credentials
* Cognito: End-user authentication for applications

#### Best Practices

* Use fine-grained IAM policies to control access
* Implement automatic rotation where possible
* Monitor access through AWS CloudTrail
* Regular audits of stored secrets

<figure><img src="../../../../../.gitbook/assets/image (20) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

## IAM Core Components

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

## Security Token Service (STS)

AWS STS (Security Token Service) provides providers short-term AWS access temporary credentials for IAM users or federated users.

#### Key Features

* Supports federation with external Identity Providers
* Enables cross-account access
* Credentials are short-lived, reducing security risks

#### Common STS Operations

* **AssumeRole**: For cross-account access
* **AssumeRoleWithWebIdentity**: For federation with web identity providers
* **AssumeRoleWithSAML**: For SAML-based federation

<figure><img src="../../../../../.gitbook/assets/image (22) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Federation Flow



<figure><img src="../../../../../.gitbook/assets/image (23) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Application initiates request to identity broker
2. Broker authenticates against Active Directory
3. Authorization details are retrieved
4. STS generates temporary security token
5. Token is provided to application
6. Application uses token to access AWS services

<figure><img src="../../../../../.gitbook/assets/image (24) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../../.gitbook/assets/image (25) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

## Amazon Cognito

Use Amazon Cognito when you need user authentication and authorization for web/mobile applications with these requirements:

* User authentication and authorization
* User management (sign-up, sign-in, password reset)
* Social identity federation (login with Google, Facebook, etc.)
* Security features like MFA and adaptive authentication
* Easy integration with other AWS services
* Scalable user directories that sync with enterprise identity providers
* Support for OAuth 2.0 and OpenID Connect
* Built-in compliance with regulations like GDPR

It's particularly suitable for serverless applications and when you want to avoid building custom authentication systems. However, consider alternatives if you need highly customized authentication flows or have strict enterprise identity management requirements.

## Token Vending Machine (TVM)

Cognito effectively serves as a modern replacement for the traditional TVM pattern.

<figure><img src="../../../../../.gitbook/assets/image (26) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Anonymous Mode

* Provides basic AWS service access
* No user identity storage
* Limited functionality

#### Identity Mode

* Supports user registration and login
* Maintains user account information
* Enhanced security features

## AWS Secrets Manager

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
