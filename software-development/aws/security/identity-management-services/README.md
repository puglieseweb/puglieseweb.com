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



```mermaid
flowchart TD
    subgraph "IAM User"
        User[IAM User]
        Perms[Permanent Credentials\nAccess Key & Secret]
    end

    subgraph "IAM Group"
        Group[IAM Group]
        GPerms[Group Permissions\nAttached Policies]
    end

    subgraph "IAM Role"
        Role[IAM Role]
        TPerms[Temporary Credentials\nSession Token]
        TP[Trust Policy]
    end

    User --> |Member of| Group
    User --> |Can assume| Role
    Group --> |Can grant\nassume role\npermission| Role
    User --> Perms
    Group --> GPerms
    Role --> TPerms
    TP --> |Controls who\ncan assume| Role

    note1[Users inherit\ngroup permissions]
    note2[Group can grant\nsts:AssumeRole permission]

    classDef user fill:#e1f3d8,stroke:#333,stroke-width:2px;
    classDef group fill:#f9d1ff,stroke:#333,stroke-width:2px;
    classDef role fill:#dae8fc,stroke:#333,stroke-width:2px;
    classDef note fill:#fff2cc,stroke:#333,stroke-width:2px;

    class User,Perms user;
    class Group,GPerms group;
    class Role,TPerms,TP role;
    class note1,note2 note;
```





```mermaid
flowchart TD
    subgraph "Identity Types"
        Users[IAM Users\nLong-term credentials]
        Groups[IAM Groups\nCollection of users]
        Roles[IAM Roles\nTemporary credentials]
    end

    subgraph "Policy Types"
        MP[Managed Policies\nStandalone reusable]
        CP[Customer Managed\nPolicies]
        IP[Inline Policies\nEmbedded directly]
        TP[Trust Policies\nWho can assume role]
        BP[AWS Managed\nPolicies]
    end

    subgraph "Common Use Cases"
        HU[Human Users\nEmployees/Developers]
        SA[Service Accounts\nApplications]
        FC[Federated Users\nExternal Identity]
        CS[Cross-Account\nAccess]
    end

    %% Relationships
    Users --> MP
    Users --> IP
    Groups --> MP
    Groups --> IP
    Roles --> MP
    Roles --> IP
    Roles --> TP

    MP --> CP
    MP --> BP

    Users --> Groups
    
    %% Use case connections
    Users --> HU
    Users --> SA
    Roles --> FC
    Roles --> CS
    Roles --> SA

    %% Styling
    classDef identity fill:#e1f3d8,stroke:#333,stroke-width:2px;
    classDef policy fill:#dae8fc,stroke:#333,stroke-width:2px;
    classDef usecase fill:#fff2cc,stroke:#333,stroke-width:2px;

    class Users,Groups,Roles identity;
    class MP,CP,IP,TP,BP policy;
    class HU,SA,FC,CS usecase;

    %% Notes
    note1[Policy Evaluation:\nExplicit DENY overrides any ALLOW]
    note2[Best Practice:\nUse least privilege principle]
    note3[Groups:\nEasier permission management]
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
* **IAM provides the underlying authorization mechanism used by both services**
* They can be used together: e.g., Cognito for customer access to applications, AWS Identity Center for employee access to AWS accounts

