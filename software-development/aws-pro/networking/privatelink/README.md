# PrivateLink

### Overview

AWS PrivateLink provides a secure way to connect VPCs to VPC endpoints without requiring VPC peering or public internet access. PrivateLink is implemented using VPC Interface Endpoints. It leverages AWS's highly available and scalable network backbone to establish private connections to various services and endpoints **within the same region.**

* PrivateLink connections are private by design and don't require public internet access
* The ENIs should be placed in private subnets as the traffic flows through AWS private network
* Traffic stays within the AWS network backbone even when connecting to services outside AWS
* Private subnet placement enhances security by avoiding exposure to the public internet

The typical setup is:

1. Create private subnets in your VPC
2. Place the PrivateLink ENIs in these private subnets
3. Configure your route tables for the private subnets
4. Use AWS Direct Connect or VPN for the connection to external networks/cloud platforms

### Key Features

* Enables private connectivity to services outside your VPC
* Operates on AWS's network backbone (no public internet traversal)
* Provides fine-grained control over accessible API endpoints and services
* **Regional limitation: endpoints must be in the same region as the VPC**

### Compatible Endpoints and Services

AWS PrivateLink can connect to multiple types of endpoints:

* Native AWS service endpoints (e.g., API Gateway, DynamoDB, S3)
* Endpoints across multiple AWS accounts within an organization
* Applications behind Network Load Balancers
* AWS Marketplace services and solutions
* Custom endpoint services within your AWS environment

<figure><img src="../../../../.gitbook/assets/image (116).png" alt=""><figcaption></figcaption></figure>

### Example 2

<figure><img src="../../../../.gitbook/assets/image (144) (1).png" alt=""><figcaption></figcaption></figure>

[Source diagarm](https://aws.amazon.com/blogs/networking-and-content-delivery/how-to-use-aws-privatelink-to-secure-and-scale-web-filtering-using-explicit-proxy/).

### Common Use Cases

<figure><img src="../../../../.gitbook/assets/image (117).png" alt=""><figcaption></figcaption></figure>

#### AWS Marketplace Solutions

PrivateLink enables secure API connectivity to purchased Marketplace solutions, providing direct and private access to third-party services without exposing traffic to the public internet.

#### Internal Service Communication

Organizations can connect to endpoint services across different AWS accounts without establishing full VPC connectivity, making it ideal for maintaining service isolation while enabling necessary communication paths.

#### Third-Party Application Access

PrivateLink facilitates secure, scalable access to endpoints for applications hosted outside AWS or on other cloud platforms, maintaining privacy through private subnet connections.

### Exam Tips and Key Points

1. Service Scope
   * Primary purpose is connecting to services outside your VPC
   * Enables networking from private subnets to specific endpoints
   * Maintains security without full VPC peering
2. Native Service Integration
   * Works with numerous AWS services (API Gateway, DynamoDB, S3)
   * All service endpoints must be in the same region as the PrivateLink connection
3. Regional Considerations
   * PrivateLink connections are region-specific
   * Cannot connect to endpoints in different regions
   * VPC and target endpoints must reside in the same region
4. Marketplace Integration
   * Commonly used for accessing AWS Marketplace solutions
   * Provides secure API connectivity to third-party services
   * Regional co-location requirement applies to Marketplace solutions

### Security Benefits

* Traffic remains on AWS network backbone
* No exposure to public internet
* Granular control over endpoint access
* Secure connectivity from private subnets

### Best Practices

* Use PrivateLink when requiring specific endpoint access without full VPC peering
* Ensure regional alignment between VPC and target endpoints
* Consider PrivateLink for secure access to Marketplace solutions
* Implement in private subnets for enhanced security
