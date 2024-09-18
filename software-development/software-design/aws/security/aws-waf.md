# AWS WAF

Operte at Layer 7.

AWS WAF is a web application firewall that lets monitor the HTTP and HTTPS requests that are forwarded to Amazon CloudFront or an Application Load Balancer.

AWS WAF also lets control access to the content.

You can configure:

* conditions such as what IP address are allowed and make requests
* query string parameters that are allowed to be passed&#x20;
* etc.

Gives 403 status code if the content is not allowed



3 different behaviours:

1. Allow all requests except the ones you specify.
2. Block all requests except the one you specify.
3. Count the requests that match the properties you specify

You can define condition by:

1. IP addresses the requests originate from
2. Country that requests originated from
3. Values in request headers.
4. Presence of SQL code that is likely to e malicious (SQL injection attack)
5. Presence of a script that is likely to be malicious (know as cross-site scripting)
6. String that appear in requests - either specific strings or string that match regular expression patterns.
