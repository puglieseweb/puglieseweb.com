# Cross-Account Route 53

### Overview

This document provides a comprehensive guide to implementing cross-account Route 53 configurations for managing subdomains across different AWS accounts. The document covers the key concepts, implementation steps, and important considerations for exam preparation.

### Prerequisites

* Understanding of Route 53 basics
* Familiarity with hosted zones
* Access to multiple AWS accounts

### Core Concepts

#### Hosted Zones

A hosted zone in Route 53 serves as a collection of records that define how traffic should be routed for a specific domain. Each hosted zone contains essential records:

* NS (Name Server) records
* SOA (Start of Authority) record

<figure><img src="../../../.gitbook/assets/image (33).png" alt=""><figcaption></figcaption></figure>

#### Cross-Account Structure

The cross-account setup involves:

* Parent DNS Account: Manages the primary domain (e.g., example.com)
* Child Accounts: Manage subdomains (e.g., dev.example.com, test.example.com)

### Implementation Guide

#### Parent Account Configuration

1. Establish the primary hosted zone in the parent DNS account
2. Verify the presence of default NS and SOA records
3. Prepare for subdomain delegation

#### Subdomain Creation Process

1. In the child account (e.g., development):
   * Create a new private hosted zone
   * Specify the subdomain name (e.g., dev.example.com)
   * Record the automatically generated NS records



2. In the parent DNS account:

* Access the primary domain's hosted zone
* Create a new NS record set
* Configure it for the subdomain
* Input the NS records from the child account

<figure><img src="../../../.gitbook/assets/image (2) (1).png" alt=""><figcaption></figcaption></figure>

3. Repeat this process for additional subdomains in other accounts

<figure><img src="../../../.gitbook/assets/image (3) (1).png" alt=""><figcaption></figcaption></figure>

### Best Practices

#### Security Considerations

* Implement principle of least privilege
* Maintain separate permissions for each environment
* Regular audit of DNS configurations

#### Management Guidelines

* Centralize DNS observation in the parent account
* Document NS record changes
* Maintain clear naming conventions for subdomains

### Architecture Benefits

#### Organizational Advantages

* Improved security through account separation
* Enhanced visibility of DNS management
* Granular access control

#### Operational Benefits

* Environment-specific monitoring
* Simplified permissions management
* Reduced risk of cross-environment conflicts

### Common Use Cases

* Development environments (dev.example.com)
* Testing environments (test.example.com)
* Staging environments (staging.example.com)
* Regional deployments (us-east.example.com)

### Troubleshooting

#### Common Issues

* NS record propagation delays
* Incorrect NS record configuration
* Permission-related issues

#### Resolution Steps

1. Verify NS records in both parent and child accounts
2. Confirm proper hosted zone configuration
3. Check IAM permissions in relevant accounts

### Conclusion

Cross-account Route 53 configuration provides a robust solution for managing complex DNS requirements across multiple AWS accounts. This approach enables organizations to maintain security and operational efficiency while providing the necessary flexibility for different environments.
