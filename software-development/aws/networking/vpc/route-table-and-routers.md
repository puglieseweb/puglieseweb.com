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



##

