# System Manager Parameter Store

AWS Systems Manager Parameter Store is a capability that provides secure, hierarchical storage for configuration data management and secrets management. While it offers a free tier, understanding both its benefits and limitations is important.

Key Features:

* Secure, scalable key-value storage
* Hierarchical organization of parameters
* Integration with other AWS services
* Version tracking of parameter changes
* Protection through AWS IAM policies
* Option for standard (free) and advanced tiers

Storage Capabilities:

* Plain text parameters
* Encrypted secure strings (using AWS KMS)
* Parameter types supported:
  * Passwords and secrets
  * Database connection strings
  * API keys
  * Amazon Machine Image (AMI) IDs
  * License codes
  * Configuration data

Standard (Free) Tier Limitations:

1. Maximum of 10,000 parameters per AWS account and Region
2. Parameter value size limit of 4KB
3. No automated parameter rotation
4. No parameter policies
5. Standard throughput (40 transactions per second)

To overcome these limitations, you can upgrade to the Advanced Tier, which offers:

* Up to 100,000 parameters
* Parameter value size up to 8KB
* Parameter policies for expiration and notification
* Higher throughput (100 transactions per second)
* Automated parameter rotation capability
