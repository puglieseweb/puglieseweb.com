# DataSync

## AWS DataSync

A managed data transfer service designed for large-scale data migration between on-premises storage and AWS.

### Key Features

* Agent-based solution
* Optimized for one-time large data migrations
* Supports automated data transfer

### Supported Source Protocols

* Network File System (NFS)
* Server Message Block (SMB)

### Supported AWS Destinations

* Amazon S3 (Simple Storage Service)
* Amazon EFS (Elastic File System)
* Amazon FSx

### Primary Use Case

Ideal for:

* One-time migration of large datasets
* Initial data transfer to AWS storage services
* Bulk data movement to cloud storage



```mermaid
flowchart LR
    subgraph "On-Premises"
        NFS[NFS Share]
        SMB[SMB Share]
        Agent[DataSync Agent]
    end

    subgraph "AWS Cloud"
        subgraph "AWS Storage Services"
            S3[(Amazon S3)]
            EFS[(Amazon EFS)]
            FSx[(Amazon FSx)]
        end
    end

    NFS --> Agent
    SMB --> Agent
    
    Agent -->|Secure Transfer| S3
    Agent -->|Secure Transfer| EFS
    Agent -->|Secure Transfer| FSx

    style Agent fill:#FF9900,stroke:#FF9900,color:white
    style S3 fill:#3F8624,stroke:#3F8624,color:white
    style EFS fill:#3F8624,stroke:#3F8624,color:white
    style FSx fill:#3F8624,stroke:#3F8624,color:white

    classDef onPrem fill:#232F3E,stroke:#232F3E,color:white
    class NFS,SMB onPrem
```

