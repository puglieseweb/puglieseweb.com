# Questions notes

### SCP Explicit Deny

AWS's security principle: "**An explicit DENY always overrides an ALLOW**." Even if the root OU's SCP allows access to S3, if there's any DENY statement in the evaluation path (including in child OUs), the access will be denied.

### API Gateway's response modifications

API Gateway's response modifications allow you to transform or customize the responses that are sent back to clients before they leave the API Gateway. This is useful for:

1. Adding or modifying response headers
2. Transforming error messages into a standard format
3. Modifying response status codes
4. Adding CORS headers
5. Customizing response bodies

Gateway responses are typically for error handling, not for modifying successful responses.



### Error page for intermittent errors

Best Combination is A and E

A. Create S3 static webpage

* Valid approach as S3 can host static error pages
* Low operational overhead
* Can be integrated with CloudFront

C. Route 53 health checks with fallback

* Complex to implement
* Higher operational overhead
* DNS changes take time to propagate
* Not suitable for intermittent errors

E. CloudFront custom error page

* Built-in feature of CloudFront
* Very low operational overhead
* Can directly handle HTTP error codes
* Works well with existing architecture







