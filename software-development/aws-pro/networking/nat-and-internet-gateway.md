# NAT & Internet Gateway

### Internet Gateway

An Internet Gateway serves as the primary connection point between a VPC and the internet, offering the following capabilities:

* Horizontally scaled, redundant, and highly available component enabling communication between VPC resources and the internet
* Provides unrestricted bandwidth without availability risks or constraints
* Supports both IPv4 and IPv6 protocols
* Designates any subnet with an internet route as a public subnet
* Serves two main purposes:
  * Acts as a route table target for internet-bound traffic
  * Performs NAT for instances with public IP addresses

#### Key Limitation

Does not perform NAT for instances with private IPs only.

### Egress-Only Internet Gateway

A specialized gateway designed specifically for IPv6 traffic with the following characteristics:

* Handles IPv6 addresses, which are globally unique and public by default
* Enables outbound internet access for IPv6-addressed instances
* Blocks inbound access to IPv6 instances
* Maintains stateful connection tracking
* Requires custom ::/0 route configuration
* Replaces traditional NAT functionality for IPv6 traffic

### NAT Instance

A legacy solution implemented as an EC2 instance with specific properties:

* Deployed from a specialized AWS-provided AMI
* Translates traffic between private IP instances and a single public IP
* Blocks inbound connections from the public internet
* Must be deployed in a public subnet with Internet Gateway route
* Requires private instances to route through it (typically via 0.0.0.0/0)
* Not supported for IPv6 traffic
* Limited by instance type bandwidth

### NAT Gateway

A fully-managed AWS service offering sophisticated NAT capabilities:

#### Key Features

* Eliminates need for self-managed NAT instances
* Must be provisioned in a public subnet
* Assigns a permanent Elastic IP for the gateway's lifetime
* Routes private subnet traffic via 0.0.0.0/0
* Provides AZ-level redundancy
* Supports bandwidth from 5Gbps to 45Gbps
* Requires specific routing for VPC peering, VPN, or Direct Connect

#### Multi-AZ Configuration

* Deploy separate NAT Gateways in each AZ
* Configure private subnets to use local Gateway
* Ensures optimal routing and redundancy

### NAT Gateway vs NAT Instance Comparison

| Feature         | NAT Gateway                | NAT Instance                     |
| --------------- | -------------------------- | -------------------------------- |
| Availability    | Highly available within AZ | Self-managed                     |
| Bandwidth       | Up to 45 Gbps              | Instance-dependent               |
| Maintenance     | AWS-managed                | Self-managed                     |
| Performance     | NAT-optimized              | Based on Linux AMI configuration |
| Public IP       | Non-detachable Elastic IP  | Detachable Elastic IP            |
| Security Groups | Not supported              | Supported                        |
| Bastion Host    | Not supported              | Supported                        |

### Best Practices

1. Use NAT Gateway instead of NAT Instance for production workloads requiring high availability
2. Implement Egress-Only Internet Gateway for IPv6 requirements
3. Deploy NAT Gateways in multiple AZs for regional redundancy
4. Configure specific routes for VPC peering, VPN, and Direct Connect connections
5. Monitor bandwidth usage to ensure appropriate scaling
