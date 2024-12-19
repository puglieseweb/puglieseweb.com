# History and Modern Implementation of Encryption in AWS

### Historical Context: The Spartan Scytale

The Spartan scytale (700 BC) represents one of the earliest forms of symmetric encryption:

* Used two identical wooden rods and leather strips
* Messages written across wrapped leather
* Required identical rod diameter for decryption
* Vulnerable to brute force attacks using various rod diameters

### Modern Encryption States

#### Encryption at Rest

Protects data in its stored state across various AWS services:

* EBS volumes
* S3 buckets
* RDS databases
* SQS queues

#### Encryption in Transit

Secures data as it moves between systems:

* SSL/TLS for HTTPS
* IPsec for VPN connections
* Protects application-to-application communication

### AWS Key Management Service (KMS)

#### Core Features

* Manages encryption keys
* Tight integration with AWS services:
  * Lambda
  * S3
  * DynamoDB
* Supports imported and generated keys
* Fine-grained access control via IAM
* CloudTrail auditing capability

#### Compliance

* PCI DSS Level 1 certified
* FIPS 140-2 Level 2 validated
* Multi-tenant architecture
* Built-in high availability

### AWS CloudHSM

#### Overview

* Dedicated hardware security module
* Single-tenant architecture
* VPC-required deployment
* Custom application integration needed

#### Use Cases

* SSL/TLS offloading
* Certificate Authority (CA)
* Oracle TDE support

#### Versions Comparison

**Classic Version**

* Based on SafeNet Luna SA
* $5,000 upfront cost
* Manual high availability setup
* FIPS 140-2 Level 2 certified

**Current Version**

* Pay-per-hour model
* Built-in clustering
* FIPS 140-2 Level 3 certified
* No upfront costs

#### CloudHSM vs KMS Comparison

<figure><img src="../../../../.gitbook/assets/image (32) (1).png" alt=""><figcaption></figcaption></figure>

### AWS Certificate Manager (ACM)

#### Key Features

* Manages SSL/TLS certificates
* Integrated with AWS services:
  * CloudFront
  * Elastic Load Balancer
* Free public certificate generation
* Third-party certificate import support
* Wildcard domain support (the same certificate can be used for different subdomains, for example \*.domain.com)
* Automatic certificate renewal

#### Certificate Types

1. Public Certificates
   * Free generation
   * AWS service integration
   * Automatic renewal
2. Private Certificates
   * Internal certificate authority
   * Proprietary services support
   * Custom certificate generation

#### Management Features

* Domain validation
* Automated renewal process
* Multiple subdomain support
* Integration with AWS services
* Elimination of manual certificate management
