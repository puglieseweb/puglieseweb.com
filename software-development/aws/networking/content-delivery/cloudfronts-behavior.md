# CloudFront's Behavior

A CloudFront Behavior defines how Amazon CloudFront handles requests for content at the edge locations. Here are the key aspects:

Path Patterns and Precedence:

* Each behavior is associated with a path pattern (like "/images/_" or "_.jpg")
* CloudFront matches the request URL against these patterns in order of precedence
* The first matching pattern's behavior settings are applied
* Default behavior (\*) catches all unmatched requests

Key Configuration Options:

* Origin and Origin Protocol Policy
* Viewer Protocol Policy (HTTP/HTTPS)
* Allowed HTTP Methods
* Cache Settings
* Compression settings (Gzip/Brotli)
* Function associations (Lambda@Edge/CloudFront Functions)
* TTL settings (min, max, default)
* Cache key settings
* Headers/cookies to forward to origin

For example:

```javascript
// Example behavior configuration
{
  "PathPattern": "/images/*",
  "TargetOriginId": "S3-my-bucket",
  "ViewerProtocolPolicy": "redirect-to-https",
  "AllowedMethods": ["GET", "HEAD"],
  "CachePolicyId": "658327ea-f89d-4fab-a63d-7e88639e58f6",
  "OriginRequestPolicyId": "88a5eaf4-2fd4-4709-b370-b4c650ea3fcf"
}
```

Common Use Cases:

1. Different caching strategies for different content types
2. API request handling vs static content delivery
3. Security requirements for specific paths
4. Different origin sources based on content type
5. Custom error handling for specific paths
