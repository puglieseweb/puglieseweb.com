# Cross-Account Infrastructure Deployment

<figure><img src="../../../../../../.gitbook/assets/image (65).png" alt=""><figcaption></figcaption></figure>

### Infrastructure as Code (IaC) Foundation

#### Core Concepts

* Version-controlled cloud infrastructure
* Standardized deployment across organization
* Trackable resource provisioning
* Simplified change management

#### Implementation Options

* CloudFormation templates (JSON/YAML)
* AWS CDK (Cloud Development Kit)
* AWS SAM (Serverless Application Model)
* Terraform
* Other frameworks abstracting CloudFormation

### CloudFormation StackSets

<figure><img src="../../../../../../.gitbook/assets/image (60).png" alt=""><figcaption></figcaption></figure>

#### Overview

* Deploy resources across multiple accounts and regions
* Centralized management from admin account
* Automated updates across all target accounts
* Consistent resource configuration

#### Implementation Requirements

* Admin account with StackSets configuration
* Execution role in member accounts
* Proper IAM permissions and trust relationships
* Target account and region selection

#### Use Cases

* Deploying standard Config Rules
* Implementing organization-wide policies
* Managing compliance requirements
* Standardizing security controls

### AWS Control Tower Integration

<figure><img src="../../../../../../.gitbook/assets/image (61).png" alt=""><figcaption></figcaption></figure>





#### Baseline Management

* Automated StackSet provisioning
* Core organizational unit configuration
* Account provisioning automation
* Config guardrails implementation

#### Features

* Log aggregation
* Cross-account changes
* Compliance monitoring
* Standardized account setup

### AWS Service Catalog

<figure><img src="../../../../../../.gitbook/assets/image (62).png" alt=""><figcaption></figcaption></figure>

#### Purpose

* Curated list of approved AWS products
* Controlled resource provisioning
* Delegation of deployment capabilities
* Standardized architecture implementation

#### Key Components

* Portfolios (collections of products)
* Products (approved resources/architectures)
* Account-level sharing
* Organization-wide distribution

#### Advantages

* Limited access to underlying services
* Standardized deployment options
* Controlled resource provisioning
* Simplified user experience

### Deployment Strategies

#### StackSets vs Service Catalog

**StackSets**

* Administrator-driven deployment
* Mandatory infrastructure implementation
* Push-based deployment model
* Consistent across all target accounts

**Service Catalog**

* User-initiated deployment
* Optional resource provisioning
* Pull-based deployment model
* Flexibility in implementation

### Implementation Example: Microservices Pipeline

<figure><img src="../../../../../../.gitbook/assets/image (63).png" alt=""><figcaption></figcaption></figure>

#### Service Catalog Approach

1. Define pipeline as code
2. Create portfolio with pipeline product
3. Share portfolio with target accounts
4. Enable on-demand provisioning
5. Allow multiple instance deployment

#### Benefits

* Standardized pipeline architecture
* Flexible deployment options
* Controlled access to resources
* Centralized management

### Best Practices

#### Code Management

* Integrate infrastructure and application code
* Use CDK for reusable constructs
* Maintain version control
* Document deployment processes

#### Access Control

* Implement least-privilege access
* Use role-based permissions
* Regular access review
* Audit trail maintenance

#### Resource Management

* Centralized deployment control
* Regular compliance checking
* Standardized naming conventions
* Resource tagging strategy

#### Monitoring and Maintenance

* Regular updates and patches
* Performance monitoring
* Cost tracking
* Security compliance verification

### Key Considerations

#### Infrastructure Management

* Code and infrastructure integration
* Framework selection
* Deployment strategy
* Version control implementation

#### Account Structure

* Administrative hierarchy
* Permission boundaries
* Resource sharing
* Cross-account access

#### Compliance and Security

* Regulatory requirements
* Security controls
* Audit capabilities
* Access management

#### Operational Efficiency

* Automation capabilities
* Resource standardization
* Deployment consistency
* Maintenance overhead
