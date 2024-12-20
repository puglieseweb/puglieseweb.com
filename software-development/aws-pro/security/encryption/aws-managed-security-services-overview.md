# AWS Managed Security Services Overview

<figure><img src="../../../../.gitbook/assets/image (15) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Security Hub

<figure><img src="../../../../.gitbook/assets/image (12) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Primary Function

Security Hub serves as a centralized security management system for AWS environments, particularly valuable in multi-account scenarios.

#### Key Features

* Integrates with multiple AWS security services:
  * Amazon Inspector
  * Amazon GuardDuty
  * AWS Firewall Manager
  * Amazon Macie
* Generates prioritized recommendations based on AWS best practices
* Supports multi-account security monitoring
* Enables third-party security service integration via AWS Marketplace

### Network Firewall vs. Web Application Firewall (WAF)

<figure><img src="../../../../.gitbook/assets/image (13) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Network Firewall

* **Position**: Operates outside VPC perimeter
* **Traffic Sources**:
  * Transit Gateways
  * Direct Connect
  * VPN
  * Internet Gateway
* **Capabilities**:
  * IP address filtering
  * Custom packet pattern rules
* **Use Case**: VPC-centric applications requiring network-level protection

#### Web Application Firewall (WAF)

* **Protected Endpoints**:
  * Application Load Balancers
  * CloudFront
  * API Gateway
  * AppSync
* **Use Case**: Distributed or serverless applications with internet exposure
* **Scope**: Broader protection spectrum across AWS services

### AWS Shield

<figure><img src="../../../../.gitbook/assets/image (14) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Standard Shield

* **Protection Level**: Infrastructure (Layer 3 and 4) attacks
* **Coverage**: Automatic protection across all AWS services
* **Support**: Standard AWS support channels

#### Shield Advanced

* **Enhanced Features**:
  * Layer 7 (application) attack protection
  * Resource-specific protection configuration
  * 24/7 DDoS expert access
  * Detailed attack logging and analysis
* **Use Case**: Critical applications requiring advanced DDoS protection
* **Cost**: Premium pricing tier

### Firewall Manager

#### Core Functionality

* Centralized security management across organization
* Multi-account and multi-VPC deployment capabilities

#### Managed Services

* Security Groups
* WAF configurations
* Shield deployments
* Network Firewall settings
* Third-party security tools

#### Integration

* Delivers logs and insights to Security Hub
* Enables standardized security policies across organization

### GuardDuty

#### Operational Model

* Utilizes machine learning for threat detection
* Continuous API log inspection
* CloudFront integration for comprehensive monitoring

#### Response Capabilities

* Security alert generation
* Security Hub integration
* EventBridge trigger support
* Automated remediation options

### Implementation Guidelines

#### Security Hub Implementation

* Deploy for centralized security monitoring
* Configure multi-account visibility
* Establish alert priorities
* Integration with third-party tools as needed

#### Firewall Strategy

1. Assess application architecture
2. Choose appropriate firewall solution:
   * Network Firewall for VPC-centric applications
   * WAF for distributed services
3. Implement Firewall Manager for multi-account scenarios

#### DDoS Protection

1. Enable standard Shield for basic protection
2. Evaluate Shield Advanced requirements based on:
   * Application criticality
   * Required protection level
   * Budget considerations

#### Threat Detection

1. Deploy GuardDuty across organization
2. Configure appropriate alerting mechanisms
3. Establish automated response procedures
4. Integrate with Security Hub for centralized visibility

### Best Practices

1. Implement layered security approach
2. Maintain consistent security policies across accounts
3. Regular security posture assessment
4. Automated response to security events
5. Continuous monitoring and logging
6. Regular review of security recommendations

This document provides a foundation for understanding AWS managed security services. Regular updates based on AWS service enhancements and evolving security requirements are recommended.
