# Amazon Glacier: AWS Archive Storage Service

### Introduction

Amazon Glacier is designed as a long-term archival storage solution, characterized by:

* Very low storage costs
* Slower access speeds
* Infrequent data retrieval
* Often referred to as "cold storage" or "offline storage"

### Core Components

#### Glacier Vault

* Equivalent to an S3 bucket
* Primary storage container
* Managed through its own API
* Can exist independently of S3

#### Archives

* Equivalent to S3 objects
* Maximum size: 40 TB
* Immutable: cannot be modified once uploaded
* Can be files, ZIP files, or TAR files
* Can be deleted or overwritten, but not changed

### Security and Access Control

<figure><img src="../../../../.gitbook/assets/image (10) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Vault Policies

* Define rules for vault behavior
* Can enforce deletion restrictions
* May require multi-factor authentication
* Control archive management

#### Access Management

* Managed through IAM
* Roles assigned for vault administration
* Separate from vault policies

### Vault Lock Process

<figure><img src="../../../../.gitbook/assets/image (11) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Create Glacier Vault Lock
2. Initiate lock process
3. 24-hour confirmation window:
   * Can abort during this period
   * Can complete lock before window expires
   * Auto-aborts if not confirmed within 24 hours
4. Once completed, lock becomes permanent and immutable

### Integration and Usage

* Backend storage for AWS Storage Gateway Virtual Tape Library
* Integrated with S3 through lifecycle management
* Can be used independently through Glacier API
* Available within S3 console for convenience

### Retrieval Options

* Primary focus on long-term archival
* Recent introduction of faster retrieval options:
  * Available at higher cost
  * Still significantly slower than S3
  * Not suitable for online content delivery

### Key Characteristics

* Immutable storage
* Designed for infrequent access
* Focus on data integrity
* Long-term preservation
* Cost-effective for archival purposes

### Best Practices

* Use for truly archival data
* Plan for slow retrieval times
* Consider vault lock policies carefully
* Implement appropriate access controls
* Verify vault locks during confirmation window
