# AWS Resource Access Manager (RAM): Cross-Account Resource Sharing

### Overview

AWS Resource Access Manager (RAM) is a **centralized service that enables secure resource sharing across multiple AWS accounts.** This document outlines the key concepts, implementation process, and best practices for using RAM effectively within your AWS organization.

<figure><img src="../../../../.gitbook/assets/image (30).png" alt=""><figcaption></figcaption></figure>

### Core Concepts

#### Resource Sharing

* Resources can be shared across individual accounts or organizational units
* Shared resources appear as native resources in recipient accounts
* Original sharing account maintains resource ownership
* **Organization must explicitly enable resource sharing functionality**

#### Principals

* Defined entities that can access shared resources
* Can include specific AWS accounts or roles
* Must be within the same AWS organization
* Can be individual accounts or entire organizational units

### Implementation Process

<figure><img src="../../../../.gitbook/assets/image (27).png" alt=""><figcaption></figcaption></figure>

#### 1. Resource Selection

* Identify specific resources for sharing
* Common shareable resources include:
  * VPC subnets
  * Network firewall settings
  * Certificate authorities
  * Aurora/RDS clusters
  * App Mesh configurations

#### 2. Permission Configuration

* Apply managed policies to resource shares
* Define allowable actions for principals
* Set appropriate access levels
* Configure resource-specific permissions

#### 3. Principal Definition

* Specify target accounts and roles
* Define access scope
* Establish sharing boundaries
* Configure organizational unit access if applicable

#### 4. Share Activation

* Send share invitations to principal accounts
* Recipients must accept invitations through RAM
* Verify access post-acceptance
* Monitor sharing status

### Common Use Cases

<figure><img src="../../../../.gitbook/assets/image (28).png" alt=""><figcaption></figcaption></figure>

#### Network Infrastructure Sharing

* Share VPC subnets across accounts
* Eliminate need for complex VPC peering
* Maintain centralized network control
* Enable consistent network security

#### Certificate Management

* Centralize private certificate authorities
* Share certificates across multiple accounts
* Reduce certificate management complexity
* Lower overall certificate costs

#### Database Access

* Share Aurora/RDS clusters cross-account
* Enable cross-account database cloning
* Maintain centralized database control
* Simplify database access management

#### Application Networking

* Share App Mesh configurations
* Enable cross-account application networking
* Simplify service discovery
* Maintain consistent mesh policies

### Technical Considerations

<figure><img src="../../../../.gitbook/assets/image (29).png" alt=""><figcaption></figcaption></figure>

#### Regional Limitations

* **Shared resources must exist in the same region**
* Cross-region sharing not supported
* Plan regional resource deployment carefully
* Consider regional boundaries in architecture design

#### Permission Management

* Recipient accounts have limited permissions
* Cannot destroy shared resources
* Maintain careful access control
* Regular permission audits recommended

### Best Practices

1. Enable resource sharing at organization level before implementation
2. Document all shared resources and their principals
3. Regularly review and audit sharing permissions
4. Implement consistent naming conventions for shared resources
5. Monitor resource utilization across accounts
6. Maintain clear documentation of sharing relationships
7. Regularly validate sharing configurations

### Implementation Testing

For effective implementation testing:

* Set up multiple test accounts
* Verify sharing functionality in controlled environment
* Test permission boundaries
* Validate resource access
* Confirm regional limitations
* Document sharing procedures
* Clean up test resources after validation

This documentation provides a foundation for implementing AWS Resource Access Manager effectively within your organization while maintaining security and operational efficiency.
