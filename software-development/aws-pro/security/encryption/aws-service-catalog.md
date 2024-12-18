# AWS Service Catalog

### Overview

AWS Service Catalog provides a framework for standardizing and controlling resource deployment across an organization, enabling centralized IT management while maintaining security and compliance standards.

### Core Concepts

#### Portfolios and Products

* **Portfolios**: Collections of standardized products
* **Products**: Pre-configured CloudFormation templates
* **Version Control**: Administrators can version and remove products without affecting existing deployments

#### Access Management

* Granular control over product and portfolio access
* Utilizes adopted IAM roles
* Eliminates need for extensive individual IAM permissions
* Enables self-service deployment while maintaining security

### Constraint Types

<figure><img src="../../../../.gitbook/assets/image (126).png" alt=""><figcaption></figcaption></figure>

#### 1. Launch Constraints

* Defines IAM role for product deployment
* Enables user deployment without extensive permissions
* Associates with specific products within portfolios

#### 2. Notification Constraints

* Specifies SNS topic for stack notifications
* Enables automated monitoring of deployments
* Facilitates operational awareness

#### 3. Template Constraints

* Creates guided deployment experiences
* Implements conditional provisioning logic
* Examples:
  * PII data storage requirements
  * Environment-specific instance sizing
  * Compliance-driven configurations

### Multi-Account Implementation

<figure><img src="../../../../.gitbook/assets/image (127).png" alt=""><figcaption></figcaption></figure>

#### Portfolio Sharing

* Master account can share service portfolios
* Recipient accounts can import shared portfolios
* Automatic synchronization of:
  * Products
  * Launch constraints
  * Template constraints

#### Local Portfolio Management

* Recipient administrators can create local portfolios
* Ability to add additional constraints
* Can make deployment criteria more restrictive
* Products remain synchronized with master portfolio

#### IAM Configuration

* IAM users/groups/roles not inherited
* Recipient administrator must configure local IAM access
* Launch roles can be:
  * Inherited from shared portfolio (default)
  * Overridden with local launch roles

### Implementation Best Practices

#### Portfolio Design

1. Standardize deployment templates
2. Implement version control
3. Define clear access boundaries
4. Establish naming conventions

#### Security Configuration

1. Minimize direct IAM permissions
2. Use launch constraints effectively
3. Implement principle of least privilege
4. Regular access review

#### Multi-Account Strategy

1. Centralize portfolio management
2. Define sharing boundaries
3. Document local modifications
4. Maintain launch role clarity

### Operational Considerations

#### Version Management

* Regular template updates
* Backward compatibility
* Communication strategy
* Deployment validation

#### Access Control

* Regular access review
* Role assignment audit
* Permission boundaries
* Cross-account access management

#### Monitoring and Compliance

1. Implementation of notification constraints
2. Audit trail maintenance
3. Compliance validation
4. Usage monitoring

### Best Practices

1. Regular portfolio review
2. Template standardization
3. Clear documentation
4. Access control maintenance
5. Regular compliance checks
6. User training and support

This document provides a foundation for understanding and implementing AWS Service Catalog. Regular updates based on organizational needs and AWS service enhancements are recommended.
