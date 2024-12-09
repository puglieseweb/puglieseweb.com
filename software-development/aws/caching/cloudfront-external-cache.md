# CloudFront (External Cache)

Amazon CloudFront is a Content Delivery Network (CDN) that securely delivers data, videos, applications, and APIs to customers globally through a network of edge locations. Content is served from the edge location closest to the user, which significantly reduces latency by minimizing the distance data needs to travel.

**CloudFront automatically encrypts data in transit using HTTPS/TLS,** ensuring secure content delivery. For additional security, you can control access to your content using either:

* **Signed URLs** - generate per-file access tokens with customizable expiration times
* **Signed cookies** - grant access to multiple restricted files without requiring separate URLs for each file

While CloudFront automatically routes users to the optimal edge location based on factors like latency and server health, you cannot manually select specific edge locations. The service's intelligent routing ensures the best possible performance for your end users.A CDN that deliver data, video, applications, and APIs to customers globally. It helps reduce latency.

### CloudFront Origin Group

A CloudFront Origin Group is a feature that enables high availability configuration for your CloudFront distribution by providing automatic failover capabilities between multiple origins. You can specify a primary origin for your distribution to serve content and a secondary origin that CloudFront automatically switches to when the primary origin is unavailable or returns specific HTTP error status codes (400-599).

Typically used in scenarios where you need to ensure continuous content delivery even if your primary origin experiences issues, such as serving website content from multiple S3 buckets in different regions, or having a backup data source for your applications. The origin group effectively acts as a single origin to the CloudFront distribution, handling the failover logic transparently while maintaining optimal performance through CloudFront's global edge network.

Common use cases include:

* Disaster recovery setups where you need a reliable failover mechanism to maintain service availability during outages or disruptions in your primary infrastructure
* Implementing cross-region redundancy to protect against regional AWS service disruptions while maintaining optimal performance
* Ensuring high availability for business-critical content delivery where downtime must be minimized, such as e-commerce platforms or media streaming services

The failover process is automatic and seamless to end users, making it an essential tool for building resilient content delivery architectures.
