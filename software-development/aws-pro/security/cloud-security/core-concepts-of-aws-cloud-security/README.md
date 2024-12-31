# Core Concepts of AWS Cloud Security

### Shared Responsibility Model

AWS operates under a shared responsibility model that clearly delineates security responsibilities between AWS and its customers:

#### Customer Responsibilities (Security IN the Cloud)

* Customer data protection
* Platform and applications management
* Identity and Access Management (IAM)
* Operating system configuration
* Network and firewall configuration
* Data encryption (both at rest and in transit)

#### AWS Responsibilities (Security OF the Cloud)

* Hardware infrastructure
* Cloud management software
* Underlying cloud infrastructure security

<figure><img src="../../../../../.gitbook/assets/image (12) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Principle of Least Privilege

This fundamental security concept requires that:

* Users and services receive only the minimum privileges necessary for their function
* Privileges should be temporary rather than permanent
* Long-lasting access keys should be avoided
* Access should be specific and limited to required actions
* IAM roles should be leveraged for temporary access

### Identity Security Framework

<figure><img src="../../../../../.gitbook/assets/image (13) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Identity Components

* Types of Identities:
  * IAM users and roles
  * Root account users
  * Temporary security credentials
  * Can apply to humans, services, or IoT devices

#### Security Flow

1. Authentication: Proving identity validity
2. Authorization: Granting authenticated identities specific permissions via IAM policies
3. Trust: Validating identities through trusted third-party entities
   * Examples: SAML-based federation, web identity federation
   * Enables cross-account access

#### Typical Security Process

1. Identity requests access
2. Identity broker communicates with identity store
3. Authentication occurs (potentially including federation)
4. Authorization token/key is provided
5. Access to services is granted based on permissions

<figure><img src="../../../../../.gitbook/assets/image (14) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Network Security Considerations

<figure><img src="../../../../../.gitbook/assets/image (15) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

* VPC protection is paramount
* Assume malicious actors will attempt:
  * Harmful packet transmission
  * DDoS attacks on web applications
* Implementation of appropriate AWS services for threat mitigation

### Risk Management and Prevention

#### Key Assumptions

* Security vulnerabilities will be exploited
* Human error must be accounted for
* Passwords may be compromised
* Resources could be exposed
* Access keys might be leaked

<figure><img src="../../../../../.gitbook/assets/image (16) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Best Practices

1. Minimize Risk:
   * Enforce multi-factor authentication
   * Secure VPCs and public endpoints
   * Avoid static access keys in applications
2. Limit Security Event Impact:
   * Implement proactive monitoring
   * Use multiple accounts for resource isolation
   * Protect sensitive data through encryption
   * Monitor for suspicious behavior
3. Data Protection:
   * Encrypt data at rest
   * Encrypt data in transit
   * Regular security audits
