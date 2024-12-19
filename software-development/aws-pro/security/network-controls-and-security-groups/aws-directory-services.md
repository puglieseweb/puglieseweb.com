# AWS Directory Services

<figure><img src="../../../../.gitbook/assets/image (18) (1) (1).png" alt=""><figcaption></figcaption></figure>

### AWS Cloud Directory

* Cloud-native directory designed for application use
* Optimized for sharing access to hierarchical data
* Best suited for applications that need to:
  * Access hierarchical data structures
  * Maintain complex relationships within data

### Amazon Cognito

* Provides scalable sign-up and sign-in functionality
* Capable of handling millions of users
* Features federation with popular social media services
* Ideal for:
  * Consumer-facing applications
  * Software as a Service (SaaS) implementations
* Key benefit: Eliminates need to build custom authentication systems

### AWS Directory Service for Microsoft Active Directory

* Fully managed Microsoft AD implementation
* Runs on Windows Server 2012 R2
* Available in Standard or Enterprise editions
* Designed for organizations requiring hosted Microsoft AD solution

### AD Connector

* Connects AWS to existing on-premises Active Directory
* Key features:
  * Enables single sign-on for existing AD users
  * Allows EC2 instances to join AD domain
  * Requires existing Active Directory infrastructure
  * Can use existing AD users with IAM roles for access
  * Supports multi-factor authentication with existing RADIUS-based MFA infrastructure

### Simple AD

* Low-cost, low-scale Active Directory implementation
* Built on Samba
* Ideal for basic user directory authentication needs
* Features:
  * Supports user accounts, groups, policies, and domains
  * Provides Kerberos-based SSO
* Limitations:
  * Does not support multi-factor authentication
  * No trust relationship capabilities
  * Cannot integrate with existing AD infrastructure

<figure><img src="../../../../.gitbook/assets/image (19) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Key Differences: AD Connector vs Simple AD

#### AD Connector

* Requires existing Active Directory
* Supports MFA with RADIUS
* Enables integration with existing AD infrastructure
* Provides IAM role integration for AD users

#### Simple AD

* Standalone directory service
* No MFA support
* No trust relationships
* Basic AD-compatible features
* Kerberos SSO support
