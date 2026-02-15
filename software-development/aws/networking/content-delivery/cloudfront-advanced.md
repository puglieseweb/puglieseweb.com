# CloudFront Part Duex

### Content Delivery Overview

CloudFront enhances content delivery performance through edge location caching, supporting both static and dynamic content delivery methods.

#### Content Types and Delivery Methods



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

<figure><img src="../../../../../.gitbook/assets/image (30) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Cache Management

#### Cache Invalidation Methods

When you need to remove content from CloudFront's edge caches before it expires, you have several options for cache invalidation:

1. Version or Path Management
   * Instead of invalidating objects, create a new path for updated content (recommended approach)
   * Use object versioning in the URL (e.g., /images/product-v2.jpg)
   * Most cost-effective and efficient method as it avoids invalidation fees
2. Origin File Management
   * Removing files from the origin alone doesn't invalidate the CloudFront cache
   * Objects remain in edge caches until their TTL expires
   * TTL values are defined in cache behaviors at the distribution level
   * Can be configured using Cache-Control and Expires headers
3. CloudFront Console Options
   * Create invalidations for specific paths using wildcards (e.g., /images/\*)
   * Perform full distribution invalidation (not recommended for production)
   * Monitor invalidation status and history
   * First 1,000 paths invalidated per month are free
4. Programmatic Invalidation
   * Use AWS SDK or AWS CLI to automate invalidations
   * Integrate with deployment pipelines using CreateInvalidation API
   * Monitor invalidation progress using GetInvalidation API
   * Implement rate limiting to avoid API throttling

Key Considerations:

* Invalidations propagate to all edge locations, which can take several minutes
* Each invalidation path counts toward your monthly quota
* Using versioning or path-based updates is more efficient than invalidation
* Wildcards (\*) count as one path but can match multiple objects

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
