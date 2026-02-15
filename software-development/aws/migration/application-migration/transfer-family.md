# Transfer family

## AWS Transfer Family

A service that enables file transfers into and out of AWS storage services using standard transfer protocols. It allows applications (e.g. legacy applications) to read and write from S3 or EFS.

### Supported Protocols

* SFTP (Secure File Transfer Protocol)
* FTPS (File Transfer Protocol over SSL)
* FTP (File Transfer Protocol)

### Supported AWS Storage Services

* Amazon S3 (Simple Storage Service)
* Amazon EFS (Elastic File System)





```mermaid
graph LR
    subgraph "Transfer Protocols"
        SFTP[SFTP]
        FTPS[FTPS]
        FTP[FTP]
        AS2[AS2]
    end

    subgraph "Storage Backend"
        S3[(Amazon S3)]
        EFS[(Amazon EFS)]
    end

    SFTP -->|Supported| S3
    SFTP -->|Supported| EFS
    FTPS -->|Supported| S3
    FTP -->|Supported| S3
    AS2 -->|Supported| S3

    classDef protocol fill:#1EC9E7,stroke:#232F3E,stroke-width:2px,color:black
    classDef storage fill:#FF9900,stroke:#232F3E,stroke-width:2px,color:white
    
    class SFTP,FTPS,FTP,AS2 protocol
    class S3,EFS storage
```

### Key Benefit

* Enables cloud migration of legacy systems without modifying existing workflows
* Maintains compatibility with legacy applications while leveraging cloud storage benefits
* Bridges traditional file transfer methods with modern cloud storage



```mermaid
flowchart LR
    subgraph "Legacy Systems"
        Client1[Client using SFTP]
        Client2[Client using FTPS]
        Client3[Client using FTP]
    end

    subgraph "AWS Transfer Family"
        TF[Transfer Family Service]
    end

    subgraph "AWS Storage"
        S3[(Amazon S3)]
        EFS[(Amazon EFS)]
    end

    Client1 -->|SFTP| TF
    Client2 -->|FTPS| TF
    Client3 -->|FTP| TF
    
    TF -->|Read/Write| S3
    TF -->|Read/Write| EFS

    style TF fill:#FF9900,stroke:#FF9900,color:white
    style S3 fill:#3F8624,stroke:#3F8624,color:white
    style EFS fill:#3F8624,stroke:#3F8624,color:white
```
