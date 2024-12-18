# Routing

### VPC Routing Overview

Virtual Private Clouds (VPCs) in AWS implement a sophisticated routing system that enables efficient network traffic management. The routing infrastructure is organized into three main contexts: VPC Context, Route 53 Context, and Elastic Load Balance Context.

#### VPC Routing Tables

VPCs are equipped with an implicit router and a main routing table by default. Key characteristics include:

* The ability to modify the main routing table or create new custom tables
* Each route table automatically contains a local route for the associated CIDR block
* Route selection follows the principle of "most specific route wins" for any given destination address
* CIDR (Classless Inter-Domain Routing) blocks, which can be pronounced as either "cedar" or "cider," are fundamental to the routing structure

#### Border Gateway Protocol (BGP)

BGP serves as the primary routing protocol for internet connectivity in AWS environments. Notable features include:

1. Core Functionality:
   * Acts as the standard routing protocol for internet traffic
   * Dynamically propagates network information to enable flexible routing
   * Essential for Direct Connect implementations
   * Optional but supported for VPN connections
2. Technical Requirements:
   * Operates on TCP port 179 plus ephemeral ports
   * Utilizes Autonomous System Numbers (ASN) as unique endpoint identifiers
   * Implements local weighting systems where higher weights indicate preferred paths for outbound traffic
3. AWS-Specific Features:
   * Supports community tagging for traffic scope control and route preference management
   * Provides static routes as an alternative when not using BGP with AWS VPC

<figure><img src="../../../../.gitbook/assets/image (97).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (98).png" alt=""><figcaption></figcaption></figure>

### Route Table Implementation

The routing tables in AWS contain several standard elements as demonstrated in the example configurations:

#### Example Route Table Entries:

```
Destination         Target
10.0.0.0/16        local
192.168.0.0/24     vpg-xxxxxxx
0.0.0.0/0          nat-xxxxxxx
pl-xxxxxxx         vpce-xxxxxxx
```

using the above routing table the below IP are resolved as follows:

```
10.0.45.34         local
64.56.34.1         nat-xxxxxxx
192.168.0.7        vpg-xxxxxxx
[S3 IP address]    vpce-xxxxxxx
10.0.255.255       [various targets]
```

These entries illustrate the hierarchical nature of AWS routing, where specific routes take precedence over more general ones, ensuring precise traffic control and optimal path selection.

### Best Practices

1. Maintain clear documentation of routing rules and their purposes
2. Regularly audit route tables for unnecessary or outdated entries
3. Use BGP community tagging when available to enhance routing control
4. Consider implementing both primary and backup routing paths for critical connections
5. Monitor routing table size and complexity to maintain optimal performance

The routing system integrates seamlessly with other AWS services, providing a robust foundation for cloud networking infrastructure while maintaining flexibility for various deployment scenarios.
