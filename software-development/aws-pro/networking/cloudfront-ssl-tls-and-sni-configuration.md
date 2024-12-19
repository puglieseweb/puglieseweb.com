# CloudFront SSL/TLS and SNI Configuration

### Overview

Amazon CloudFront is a content delivery network (CDN) service that can distribute content from static assets to 4K live streaming. While basic CloudFront distribution setup is straightforward, understanding its SSL/TLS and SNI configurations is crucial for secure content delivery.

### SSL/TLS Background

SSL (Secure Sockets Layer) and TLS (Transport Layer Security) are protocols that ensure secure communication over networks. While these terms are often used interchangeably, TLS is the modern standard that succeeded SSL.

Key points about SSL/TLS in CloudFront:

* TLS is the current security standard
* TLS 1.2 support is essential
* Earlier SSL versions should typically be disabled due to security vulnerabilities

### Certificate Options

#### Default CloudFront Certificate

* Provided automatically by AWS
* Works with default CloudFront domain names
* No additional cost
* Limited to \*.cloudfront.net domains

#### Custom SSL Certificates

Required when using custom domains. Two options for obtaining certificates:

1. AWS Certificate Manager (ACM)
   * Preferred method for AWS integration
   * Free certificate management
   * Automatic renewal
   * Must be in us-east-1 region for CloudFront use
2. Third-party Certificate
   * Must be uploaded to IAM
   * Requires manual renewal
   * Additional cost from certificate provider
   * More management overhead

#### Wildcard Certificates

* Support multiple subdomains (e.g., \*.example.com)
* Single certificate covers www.example.com, cdn.example.com, etc.
* Simplifies certificate management for multiple subdomains

### SSL/TLS Implementation Options

#### Option 1: Dedicated IP Addresses

Advantages:

* Works with all clients
* Supports legacy browsers

Disadvantages:

* Additional cost per edge location
* Requires dedicated IP for each edge location
* Higher maintenance overhead

#### Option 2: Server Name Indication (SNI)

SNI allows multiple SSL certificates to be served from the same IP address.

Advantages:

* No additional cost
* Simplified management
* Supported by all modern browsers
* Scalable solution

Disadvantages:

* Not supported by some legacy browsers (e.g., Opera Mobile for Symbian)
* Browsers older than 5 years may have compatibility issues

### Security Policies

CloudFront offers various security policies to control SSL/TLS protocol support:

#### Key Considerations:

* All policies support TLS 1.2
* Modern browsers automatically select the most secure protocol available
* Can enforce TLS 1.2-only using TLS 1.2\_2018 policy
* Policy selection affects browser compatibility vs. security trade-off

<figure><img src="../../../.gitbook/assets/image (4) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Best Practices:

1. Use SNI unless legacy browser support is crucial

<figure><img src="../../../.gitbook/assets/image (5) (1) (1).png" alt=""><figcaption></figcaption></figure>

2. Implement the most restrictive security policy that meets your requirements
3. Regular review and updates of security policies
4. Monitor for certificate expiration
5. Use ACM for simplified certificate management



### Troubleshooting

Common issues to watch for:

1. Certificate Mismatch Warnings
   * Verify domain names match certificate
   * Check certificate installation in correct region
   * Ensure proper DNS configuration
2. Browser Compatibility
   * Test with target browser versions
   * Monitor access logs for client capabilities
   * Consider maintaining browser support documentation

### Implementation Checklist

* [ ] Determine custom domain requirements
* [ ] Choose between default and custom certificates
* [ ] Select SNI vs. dedicated IP based on browser support needs
* [ ] Configure appropriate security policy
* [ ] Test with target browser matrix
* [ ] Document configuration decisions
