# Identity Management Services

AWS Identity Management services are:



```mermaid
flowchart TB
    subgraph AWS_Identity_Services["AWS Identity & Authorization Services"]
        direction TB
        
        subgraph Enterprise["Enterprise Identity"]
            IAM_Center["AWS IAM Identity Center\n(formerly AWS SSO)"]
        end
        
        subgraph Customer["Customer Identity"]
            Cognito["Amazon Cognito"]
        end
        
        subgraph Core["Core Identity"]
            IAM["AWS IAM"]
        end
    end

    style AWS_Identity_Services fill:#f5f5f5,stroke:#333
    style Enterprise fill:#e1f5fe,stroke:#0288d1
    style Customer fill:#e8f5e9,stroke:#388e3c
    style Core fill:#fff3e0,stroke:#f57c00
```



1. AWS IAM (Identity and Access Management):

* The foundational service for managing access to AWS resources
* Creates and manages users, roles, and policies within AWS
* Best for service-to-service authentication and AWS admin access
* All other identity services ultimately translate to IAM roles/policies

2. Amazon Cognito:

* Focused on customer/end-user identity management
* Translates external identities into IAM roles
* Primarily used for web/mobile applications
* Provides two main components:
  * User Pools: Handle user registration, authentication, and profile management
  * Identity Pools: Provide temporary AWS credentials to access AWS services
* Can federate with social and enterprise identity providers

3. AWS Identity Center (formerly AWS SSO):

* Centralizes access management across multiple AWS accounts
* Primarily for enterprise workforce users
* Provides a single sign-on portal for AWS accounts and business applications
* Can integrate with existing identity providers (Active Directory, Okta, etc.)
* Maps enterprise identities to IAM roles across accounts

Key Relationships:

* Both Cognito and AWS Identity Center ultimately use IAM roles for access control
* Cognito is customer-facing while AWS Identity Center is workforce-facing
* IAM provides the underlying authorization mechanism used by both services
* They can be used together: e.g., Cognito for customer access to applications, AWS Identity Center for employee access to AWS accounts



```mermaid
flowchart TB
    subgraph AWS_Identity_Center["AWS Identity Center (formerly AWS SSO)"]
        IDSTORE[Identity Store]
        SSO_Portal[SSO Portal]
    end
    
    subgraph IAM["AWS IAM"]
        Roles[IAM Roles]
        Users[IAM Users]
        Policies[IAM Policies]
    end
    
    subgraph Cognito["Amazon Cognito"]
        UserPools[User Pools]
        IdentityPools[Identity Pools]
    end

    IDSTORE --> SSO_Portal
    SSO_Portal --> Roles
    
    UserPools --> IdentityPools
    IdentityPools --> Roles
    
    ExtIdentity[External Identity Providers] --> AWS_Identity_Center
    ExtIdentity --> UserPools
    
    Users --> Roles
    Roles --> Policies
    
    Client[Client Applications] --> UserPools
    WebApp[Web/Mobile Apps] --> IdentityPools
```
