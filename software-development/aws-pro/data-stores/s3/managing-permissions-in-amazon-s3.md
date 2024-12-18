# Managing Permissions in Amazon S3

### Access Control Methods Overview

#### Bucket Policies vs. IAM

**Bucket Policies**

* Resource-based policies defined at the bucket level
* Can explicitly allow or deny access to specific principals
* Takes precedence over other permissions
* Applied to all objects or a subset within the bucket
* Defined directly on the bucket resource

**IAM (Identity and Access Management)**

* Identity-based policies
* Grants temporary permissions through roles
* Can be assigned to users, services, or applications
* Manageable through AWS Console or SDK
* Access keys automatically rotate for enhanced security

### Permission Evaluation Process

#### Single Account Access

1. Bucket Policy Check:
   * Explicit deny: Access blocked
   * Explicit allow: Access granted regardless of IAM permissions
   * Neither: Proceeds to IAM check
2. IAM Permission Check:
   * Evaluates user's IAM policies
   * Checks group memberships
   * Verifies role permissions

#### Cross-Account Access

<figure><img src="../../../../.gitbook/assets/image (7) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

1. Source Account Requirements:
   * Create role with appropriate permissions
   * Establish trust policy allowing external account access
2. Destination Account Requirements:
   * Create role to assume the source account's role
   * Grant necessary permissions to principals
3. Permission Flow:
   * Principal assumes local role
   * Local role assumes cross-account role
   * Access granted based on cross-account role permissions

### Networking Considerations

<figure><img src="../../../../.gitbook/assets/image (8) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Access Methods

1. Public Internet Access:
   * Via Internet Gateway
   * Higher data transfer costs
   * Standard internet latency
2. S3 Gateway Endpoint:
   * Uses AWS private network
   * Lower data transfer costs
   * Reduced latency
   * Requires VPC route table configuration
   * Uses Elastic Network Interface

### Best Practices and Key Considerations

<figure><img src="../../../../.gitbook/assets/image (9) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Security

* Prefer IAM roles over bucket policies for cross-account access
* Take advantage of automatic key rotation with IAM roles
* Use explicit denies in bucket policies for critical restrictions

#### Networking

* Implement S3 gateway endpoints for cost-effective internal access
* Configure appropriate route tables for VPC connectivity
* Consider data transfer costs when choosing access methods

#### Access Management

* Clearly define trust relationships for cross-account access
* Use temporary credentials through IAM roles when possible
* Maintain principle of least privilege
* Regular audit of access patterns and permissions

### Implementation Examples

#### Principal Types That Can Assume Roles

* IAM Users
* EC2 Instances
* Lambda Functions
* Custom Applications

#### Network Configuration

```
VPC --> S3 Gateway Endpoint --> S3 Bucket
         (Elastic Network Interface)
```

This configuration provides:

* Private network access
* Reduced costs
* Improved latency
* Secure communication path
