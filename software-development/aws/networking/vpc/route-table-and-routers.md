# Route Table (and Routers)

In AWS, route tables and routers are associated with different components of the network architecture. Let's break it down:

Route Tables:

1. Associated with: VPC (Virtual Private Cloud)
2. Can be associated with: Subnets within the VPC

Routers: In AWS, there isn't a specific resource called a "router" that you manage directly. Instead, AWS manages the routing infrastructure for you. The closest concept to a router in AWS is the implied router that exists in each VPC.

Key points:

1. Each VPC has an implied router managed by AWS.
2. Route tables are used to configure this implied router.
3. You can create multiple route tables within a VPC.
4. Each subnet must be associated with exactly one route table.
5. If you don't explicitly associate a subnet with a route table, it's associated with the main (default) route table of the VPC.

Regarding VPN, AZ, and Region:

* VPN: Route tables can include routes to VPN connections, but they're not directly associated with VPNs.
* AZ (Availability Zone): Route tables operate at the VPC level, which spans multiple AZs. However, since subnets are AZ-specific, the route table associated with a subnet affects that specific AZ.
* Region: Route tables are region-specific resources. You can't use a route table from one region in another region.

For VPN connections:

* You would add routes in your route table to direct traffic destined for your on-premises network to the virtual private gateway (VGW) associated with your VPN connection.



## Control traffic flow in VPC

If you're trying to control traffic flow in your VPC, you would use a combination of NACLs, route tables, and security groups, each serving its own purpose in the network architecture.

* Security Groups control inbound and outbound traffic at instance level (stateful firewall: return traffic is automatically allowed).
* NACLs control inbound and outbound traffic for the subnet (stateless firewall: return traffic must be explicitly allowed).
* Route tables determine where network traffic is directed.

Network Access Control Lists (NACLs) and route tables are separate entities in AWS networking, and they are not directly associated with each other. Let me explain:

1. NACLs are associated with subnets, not route tables.
2. Route tables are also associated with subnets, but independently of NACLs.
3. There is no direct association between NACLs and route tables.

To break it down:

* A subnet can have only one NACL associated with it at a time.
* A subnet can have only one route table associated with it at a time.
* A single NACL can be associated with multiple subnets.
* A single route table can be associated with multiple subnets.

## Differences between NACL and Security Groups

Security Groups and Network Access Control Lists (NACLs) are both important security tools in AWS, but they operate differently and at different levels.&#x20;

In practice, it's common to use both Security Groups and NACLs together for a defense-in-depth approach to network security in AWS.

Here are the key differences:

1. Scope:
   * Security Groups: Operate at the instance level
   * NACLs: Operate at the subnet level
2. State:
   * Security Groups: Stateful (return traffic is automatically allowed)
   * NACLs: Stateless (return traffic must be explicitly allowed)
3. Rule Processing:
   * Security Groups: All rules are evaluated before deciding to allow traffic
   * NACLs: Rules are processed in order, from lowest to highest number
4. Rule Types:
   * Security Groups: Allow rules only
   * NACLs: Both Allow and Deny rules
5. Association:
   * Security Groups: Associated with EC2 instances, ELBs, RDS instances, etc.
   * NACLs: Associated with subnets
6. Default Behavior:
   * Security Groups: By default, deny all inbound and allow all outbound
   * NACLs: Default NACL allows all inbound and outbound traffic
7. IP Addresses:
   * Security Groups: Can specify rules with IP addresses or other security groups
   * NACLs: Can only specify rules with IP addresses or CIDR blocks
8. Protocol and Port Range:
   * Security Groups: Can specify rules for all protocols and port ranges
   * NACLs: Must specify rules for each protocol and port range separately
9. Ephemeral Ports:
   * Security Groups: Automatically handle return traffic for ephemeral ports
   * NACLs: Must explicitly allow ephemeral ports in outbound rules
10. Response to Connectivity Issues:
    * Security Groups: Can be modified and changes take effect immediately
    * NACLs: Changes may take longer to propagate and affect traffic





## NOTEs

**Route table cannot directly reference an IP address in a different VPC** using standard routing. However, there are ways to route traffic between VPCs:

### Direct IP Routing Limitations:

* Route tables can only directly route to resources within the same VPC
* You cannot simply add a route like `10.1.0.0/16` → `10.2.0.5` where the target IP is in another VPC

### Ways to Route Between VPCs:

**1. VPC Peering:**

* Create a VPC peering connection between VPCs
* Route table entry: `10.1.0.0/16` → `pcx-12345` (peering connection ID)
* Traffic flows through the peering connection to reach the other VPC

**2. Transit Gateway:**

* Attach both VPCs to a Transit Gateway
* Route table entry: `10.1.0.0/16` → `tgw-attach-12345` (Transit Gateway attachment)

**3. VPN Connection:**

* Set up Site-to-Site VPN between VPCs
* Route table entry: `10.1.0.0/16` → `vgw-12345` (Virtual Private Gateway)

**4. NAT Gateway/Instance:**

* For outbound internet traffic that might reach another VPC via public internet
* Not typically used for direct VPC-to-VPC communication

