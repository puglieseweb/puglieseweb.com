# CloudFront Part Duex

### Content Delivery Overview

CloudFront enhances content delivery performance through edge location caching, supporting both static and dynamic content delivery methods.

#### Content Types and Delivery Methods



<figure><img src="../../../../.gitbook/assets/image (30) (1).png" alt=""><figcaption></figcaption></figure>

1. Dynamic Content
   * Leverages **HTTP cookies forwarded from origin sites**
   * Enables personalized content delivery
   * Maintains user context and preferences
2. Media Streaming Capabilities
   * Supports Adobe Flash Media Server's RTMP protocol (requires specific RTMP delivery method)
   * Web distributions (HTTP/HTTPS) support:
     * Media streaming
     * Live streaming
     * Standard content delivery

### Origin Configuration

#### Supported Origin Types

* Amazon S3 buckets
* EC2 instances
* Elastic Load Balancers
* External web servers
* Multiple origins within single distribution

#### URL Path-Based Behaviors

Example WordPress Configuration:

* Route 53 → CloudFront Distribution
* Static content → S3 bucket
* Dynamic content → Load balancer with EC2 fleet

### Cache Management

#### Cache Invalidation Methods

1. Origin File Deletion
   * Remove file from origin
   * Wait for TTL (Time To Live) expiration
   * TTL is configurable per distribution
2. AWS Console Options
   * Full content invalidation
   * Path-specific invalidation
3. Additional Invalidation Methods
   * CloudFront API requests
   * Third-party tools
   * Programmatic invalidation

### DNS and Domain Configuration

#### Zone Apex Support

* Allows bare domain usage (without www)
* Example: domain.com instead of www.domain.com
* Direct DNS resolution to CloudFront distribution

#### Geographic Access Control

**Geo-Restriction Features**

* Whitelist: Explicitly allow specific countries
* Blacklist: Explicitly block specific countries

**Use Cases**

* Regulatory compliance
* Content licensing restrictions
* Regional access management
* Geographic content distribution control

### Best Practices

1. Content Organization
   * Structure content paths logically
   * Utilize path-based behaviors effectively
   * Separate static and dynamic content
2. Cache Strategy
   * Configure appropriate TTL values
   * Plan invalidation strategy
   * Monitor cache hit ratios
3. Security
   * Implement appropriate geo-restrictions
   * Configure HTTPS when needed
   * Manage origin access properly
4. Performance Optimization
   * Use multiple origins strategically
   * Configure behaviors based on content type
   * Monitor and adjust based on usage patterns
