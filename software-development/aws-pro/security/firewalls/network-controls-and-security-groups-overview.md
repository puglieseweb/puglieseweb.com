# Network Controls and Security Groups Overview

### Best Practices

* Combined Usage of Security Groups and NACLs. **Implement both controls to create defense in depth**
* Explicitly allow and deny only necessary traffic
* Consider both controls when planning network security architecture

#### Benefits

1. NACLs provide backup security in case of misconfigured security groups
2. Subnet-wide protection ensures new instances are protected even if assigned incorrect security groups
3. Supports principle of least privilege by allowing explicit traffic control

### Security Groups

Security groups function as virtual firewalls for individual assets or groups of assets. They provide granular control over network traffic with the following characteristics:

#### Traffic Control

* Can manage both inbound and outbound traffic
* Supports multiple protocols:
  * TCP
  * UDP
  * ICMP
  * Custom protocols
* Works based on ports and port ranges

#### Rule Configuration

* Inbound rules are configured by:
  * Source IP
  * Subnet
  * Other security groups
* Outbound rules are defined by:
  * Destination IPs
  * Subnets
  * Other security groups

### Network Access Control Lists (NACLs)

NACLs provide an additional security layer for VPCs, acting as a firewall at the subnet level rather than the individual asset level.

#### Key Characteristics

* Applies to entire subnets (unlike security groups which apply to individual assets)
* Default configuration allows all inbound and outbound traffic
* Stateless operation - outbound traffic must explicitly comply with outbound rules
* No connection state maintenance or address translation
* Can work alongside security groups to either duplicate or further restrict access
* Important to consider ephemeral ports in configuration
