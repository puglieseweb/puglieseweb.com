# NACL vs NAT

NACL existence when creating a VPC:

* When you create a VPC, AWS automatically creates a default NACL (Network Access Control List)
* **This default NACL allows all inbound and outbound traffic by default**
* The default NACL is associated with all subnets in your VPC until you create custom NACLs and explicitly associate them with specific subnets
* **NACLs do not have IP addresses**
* NACLs are stateless network filters that operate at the subnet boundary
* They function as a virtual firewall controlling traffic entering and exiting subnets, but they don't have their own IP addresses
* They evaluate rules based on IP addresses, protocols, and ports of the traffic passing through them

NAT (either NAT Gateway or NAT Instance) does not exist by default when you create a VPC.

You need to manually:

1. Create the NAT Gateway and assign an Elastic IP to it
2. Place it in a public subnet
3. Update the route table of your private subnet(s) to route internet-bound traffic (0.0.0.0/0) to the NAT Gateway

This is different from an Internet Gateway (IGW) which is automatically created with a default VPC (but not with a custom VPC where you also need to create it manually).

This manual creation requirement allows you to control costs since NAT Gateways incur charges for both hourly usage and data processing.
