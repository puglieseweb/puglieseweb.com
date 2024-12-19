# ELB

### Overview

AWS offers three types of Elastic Load Balancers (ELB) for distributing inbound connections to backend endpoints:

* Application Load Balancer (ALB)
* Network Load Balancer (NLB)
* Classic Load Balancer (Legacy)

### Key Characteristics

<figure><img src="../../../.gitbook/assets/image (6) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../.gitbook/assets/image (7) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Application Load Balancer (ALB)

* Operates at Layer 7 (Application Layer)
* Handles HTTP and HTTPS traffic
* Cannot assign static IP addresses
* Requires using FQDN as endpoints (can be aliased via Route 53)
* Supports Server Name Indication (SNI)

#### Network Load Balancer (NLB)

* Operates at Layer 4 (Transport Layer)
* Handles TCP, UDP, and TLS traffic
* Supports static IP address assignment
* Optimized for speed and throughput
* Excellent for TCP/UDP workloads

#### Routing Capabilities

<figure><img src="../../../.gitbook/assets/image (8) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

**Application Load Balancer**

Rich routing options including:

* Domain-based routing
* Path-based routing (e.g., login.acme.com/customer)
* HTTP header elements
* HTTP methods (POST, PUT, GET)
* Source IP
* Query string parameters

<figure><img src="../../../.gitbook/assets/image (11) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

**Network Load Balancer**

Limited routing options:

* Source port-based routing
* TCP connections persist for the duration of the connection
* Ideal for raw throughput requirements

<figure><img src="../../../.gitbook/assets/image (9) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

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
*

    <figure><img src="../../../.gitbook/assets/image (10) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Best Practices

* Use ALB for HTTP/HTTPS applications requiring advanced routing
* Use NLB for high-performance TCP/UDP workloads
* Ensure sufficient IP address space in VPC subnets
* Implement proper health checks
* Consider sticky sessions for stateful applications
* Avoid Classic Load Balancer for new implementations
