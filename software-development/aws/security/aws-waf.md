# AWS WAF

Operates at Layer 7 (Application Layer)

AWS WAF (Web Application Firewall) lets you monitor HTTP and HTTPS requests that are forwarded to Amazon CloudFront, Application Load Balancer, API Gateway, or AppSync.

AWS WAF lets you control access to your content based on conditions you configure.

You can configure rules based on:

* IP addresses that are allowed to make requests
* Geographic locations (countries) requests originate from
* Query string parameters
* HTTP headers and their values
* Request body content
* URI paths
* Size constraints

Returns a 403 Forbidden status code if the request is blocked.

3 different behaviors (actions):

1. Allow - Allow all requests except the ones you specify
2. Block - Block all requests except the ones you specify
3. Count - Count the requests that match the properties you specify (useful for testing rules)

You can define conditions using Web ACL rules based on:

1. IP addresses that requests originate from (IP sets)
2. Countries that requests originate from (geo-matching)
3. Values in request headers
4. Presence of malicious SQL code (SQL injection attacks)
5. Presence of malicious scripts (Cross-Site Scripting/XSS attacks)
6. Strings that appear in requests - either specific strings or regex patterns
7. Rate-based rules to prevent DDoS attacks
8. Size constraints
9. Custom rules using AWS Managed Rules or your own rule groups
