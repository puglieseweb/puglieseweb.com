---
description: Virtual Private Cloud
---

# VPC

Key characteristics:

* Think of a VPC as a Virtual Data Center in the cloud.
* Consists of internet gateway (or virtual privare gateways), route tables, network ACLs, subnets, and security groups.
* 1 subnet is always in 1 AZ. Eeach AZ is associated to 1 Subnet.

Wen creating a VPC automatically we crate:

* a Route table
* a Network ACL&#x20;
* a Security Group

A VPC an only have one Internet Gatway

Fully Customizable Network. You can leverage multiple layers of security, including security groups and network access control lists, to help control access to Amazon EC2 instances in each subnet.



Every AWS account in a region comes with a Default VPC that is setup automatically.

Default VPC vs Custom VPC:

* Default VPC:
  * All subnets have a route out to the internet.&#x20;
  * Each EC2 instance has both a public and a private IP address.
* Custome VCP:
  * Fully customizable.

Logically isolate part of AWS cloud where you can define your own network.

A VPC gives complete control of Virtual Network, including IP address ranges, subnets, route tables, and network gateways.



In a three-tier architecture we have:

1. Web Server tier. Public-facing subnet (port 80 or port 443)
2. Application tier. Private subnet. Can only speak to web tier or database tier.&#x20;
3. Database tier. Priavte subnet. Can only speak to application tier.

Additional, you can create a hardware Virtual Private Network (VPN) connecting a corporate data center to the VPC and leverage the AWS Cloud as an extension of your corporate data center.

{% @lucid/lucid-component url="https://lucid.app/lucidchart/53875b19-93a1-4800-81d1-8c84d6351a09/edit?invitationId=inv_20ab51d1-0eaa-47f7-8d87-4aaee89152c1&page=4UR63qqFV6Co" %}

You can calculate the IP address's ranges using [https://cidr.xyz/](https://cidr.xyz/)

These IP address ranges are used for private networks. Here's when to use each:

1. **10.0.0.0/8** (10.0.0.0 to 10.255.255.255):
   * For large enterprise networks
   * Provides the most available IP addresses (over 16 million)
   * Best for organizations needing many subnets or devices
2. **172.16.0.0/12** (172.16.0.0 to 172.31.255.255):
   * For medium-sized networks
   * Offers about 1 million IP addresses
   * Useful for mid-sized companies or when 192.168.0.0 is too small
3. **192.168.0.0/16** (192.168.0.0 to 192.168.255.255):
   * For small networks
   * Provides 65,536 IP addresses
   * Commonly used in home networks and small offices

Choose based on your network size and potential growth. For most home and small business users, 192.168.0.0 is sufficient. Larger organizations may opt for 10.0.0.0 to accommodate more devices and subnets.



### What can we do with a VPC?

* Launch instances. Launch instances into a subnet of your choosing.
* Custom IP addresses. Assign custom IP addresses ranges in each subnet.
* Route Tables. Configure route tables between subnets.
* Internet Gateway. Create an internet gateway and attach to the VPC.
* More Control. Much better security control over AWS resources.
* Access control Lists. Subnet network access control lists.

## Can VPC span different AZs?&#x20;

A Virtual Private Cloud (VPC) can and often does span across multiple Availability Zones (AZs). This is a common and recommended practice in cloud architecture, especially in AWS. Here's some key information about VPCs spanning multiple AZs:

1. Design: A VPC is a logically isolated section of the cloud that can span all AZs in a region.
2. Subnets: While the VPC spans AZs, each subnet within the VPC exists in only one AZ.
3. High Availability: Spanning a VPC across multiple AZs allows you to create highly available and fault-tolerant architectures.
4. Resource Distribution: You can distribute your resources (like EC2 instances, RDS databases, etc.) across multiple AZs within the same VPC for better reliability.
5. Network ACLs and Route Tables: These can be configured to control traffic between subnets in different AZs within the VPC.
6. Inter-AZ Communication: Resources in different AZs but within the same VPC can communicate with each other, typically using private IP addresses.
7. Costs: Be aware that data transfer between AZs within a VPC usually incurs charges.

The cost difference between data transfer within the same Availability Zone (AZ) and between different AZs in a VPC is significant. Here's a breakdown:

1. Data transfer within the same AZ:
   * Generally free when using private IP addresses within the same VPC.
2. Data transfer between different AZs:
   * Charged at a per-GB rate, which is typically lower than internet data transfer rates but still represents a cost.

### Costs

* Data transfer within the same AZ: $0.00 per GB
* Data transfer between AZs: \~$0.01 to $0.02 per GB

This means that transferring data between AZs can add up, especially for applications with high data transfer volumes.

Key considerations:

1. The cost difference encourages architects to design applications that minimize unnecessary cross-AZ traffic.
2. Despite the cost, cross-AZ architecture is often necessary for high availability and disaster recovery.
3. Some services may have different pricing models for replication across AZs (e.g., Amazon RDS).
4. Inbound data transfer is typically free, while outbound is charged.

To optimize costs, it's important to monitor inter-AZ data transfer and design your application to balance between high availability and cost-efficiency.



### Can I attach an Interned Gateway to multiple VPCs?

No, you cannot attach an Internet Gateway to multiple VPCs. Each Internet Gateway is associated with a single VPC. This is an important design principle in cloud networking, particularly in AWS. Here are some key points to understand:

1. One-to-one relationship: An Internet Gateway has a one-to-one relationship with a VPC. Each VPC can have only one Internet Gateway, and each Internet Gateway can be attached to only one VPC.
2. Purpose: The Internet Gateway serves as a connection point between the VPC and the internet, allowing communication between instances in your VPC and the internet.
3. Alternative for multiple VPCs: If you need internet connectivity for multiple VPCs, you would need to create a separate Internet Gateway for each VPC.
4. Shared internet access: If you want to share internet access across multiple VPCs, you can use other networking constructs such as:
   * Transit Gateway:
     * Acts as a central hub to interconnect multiple VPCs and on-premises networks
     * Can route traffic between VPCs and to the internet through a shared Internet Gateway
     * Scalable and can support thousands of VPCs
   * VPC Peering:
     * Allows direct networking connection between two VPCs
     * Useful for smaller setups but doesn't scale well for many VPCs
     * Internet access can be shared through one VPC to others
   * Hub and Spoke Model:
     * One central VPC (hub) with an Internet Gateway connects to multiple spoke VPCs
     * The hub VPC routes internet traffic for the spokes
     * Can be implemented using Transit Gateway or VPC peering
   * Shared Services VPC:
     * Similar to hub and spoke, but focused on providing shared services including internet access
     * Other VPCs connect to this VPC for internet and other common services
   * AWS PrivateLink:
     * Provides private connectivity between VPCs, AWS services, and on-premises networks
     * Can be used to access services in another VPC that has internet access
   * AWS Direct Connect:
     * Provides a dedicated network connection from on-premises to AWS
     * Can be used in conjunction with other solutions to provide internet access to multiple VPCs
   * NAT Gateway in shared VPC:
     * Place a NAT Gateway in a shared VPC and route traffic from other VPCs through it
5. Detaching and reattaching: You can detach an Internet Gateway from one VPC and attach it to another, but it can only be attached to one VPC at a time.
6. Region-specific: Internet Gateways are specific to a region, just like VPCs.
