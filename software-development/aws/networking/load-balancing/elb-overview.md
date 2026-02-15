# ELB

### Overview

AWS offers three types of Elastic Load Balancers (ELB) for distributing inbound connections to backend endpoints:

* Application Load Balancer (ALB)
* Network Load Balancer (NLB)
* Classic Load Balancer (Legacy)

### Key Characteristics

<figure><img src="../../../.gitbook/assets/image (6) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (7) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Application Load Balancer (ALB)

* Operates at Layer 7 (Application Layer)
* Handles HTTP and HTTPS traffic
* Cannot assign static IP addresses
* Requires using FQDN (Fully Qualified Domain Names) as endpoints (can be aliased via Route 53). Instead of using IP addresses directly, you must use complete domain names for your targets Example: `server1.us-east-1.compute.internal` rather than `10.0.1.23` &#x20;
* Supports Server Name Indication (SNI)

#### Network Load Balancer (NLB)

* Operates at Layer 4 (Transport Layer)
* Handles TCP, UDP, and TLS traffic
* **Supports static IP address assignment.** This is particularly useful when you need:&#x20;
  * Allowlisting/Whitelisting: External clients or services that need to allowlist specific IPs
  * DNS Records: Creating A records that point directly to IP addresses
  * Legacy Systems: Working with systems that can only connect to specific IP addresses
* Optimized for speed and throughput
* Excellent for TCP/UDP workloads
* You can select multiple subnets (typically across different Availability Zones for high availability)
* Each selected subnet will get an NLB endpoint with its own IP address

### Routing Capabilities

<figure><img src="../../../.gitbook/assets/image (8) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

**Application Load Balancer**

Rich routing options including:

* Domain-based routing
* Path-based routing (e.g., login.acme.com/customer)
* HTTP header elements
* HTTP methods (POST, PUT, GET)
* Source IP
* Query string parameters

<figure><img src="../../../.gitbook/assets/image (11) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

**Network Load Balancer**

Limited routing options:

* Source port-based routing
* TCP connections persist for the duration of the connection
* Ideal for raw throughput requirements

<figure><img src="../../../.gitbook/assets/image (9) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Common Features Across All Load Balancers

* Zonal failover capability
* Cross-zone load balancing
* Health checks
* CloudWatch metrics integration
* SSL/TLS termination support
* Resource-based IAM permissions

### Implementation Considerations

#### VPC Integration

* ALB and NLB: VPC-only operation
* Classic Load Balancer: Supports both VPC and EC2-Classic (legacy)

#### Scaling and IP Address Management

* Load balancers auto-scale as needed
* Consume IP addresses from VPC subnet
* Require adequate spare IP addresses for scaling
* Must account for reserved addresses in VPC

#### Sticky Sessions

Important for web applications that maintain session state:

* Helps maintain client session continuity
* Ensures clients return to the same backend server
* Critical for applications using session IDs
* Supports features like shopping carts and session parameters



<figure><img src="../../../.gitbook/assets/image (10) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Best Practices

* Use ALB for HTTP/HTTPS applications requiring advanced routing
* Use NLB for high-performance TCP/UDP workloads
* Ensure sufficient IP address space in VPC subnets
* Implement proper health checks
* Consider sticky sessions for stateful applications
* Avoid Classic Load Balancer for new implementations
