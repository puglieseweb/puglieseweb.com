# Network Access Control List (NACL)

NACLs provide an additional layer of security beyond security groups, allowing you to control traffic at the subnet boundary. They are stateless, meaning you need to configure both inbound and outbound rules explicitly.



Network Access Control List (NACL) is associated with a subnet. Specifically:

1. NACLs are associated with subnets within a VPC (Virtual Private Cloud).
2. Each subnet in a VPC must be associated with a NACL.
3. A NACL can be associated with multiple subnets, but a subnet can only be associated with one NACL at a time.
4. NACLs operate at the subnet level, controlling traffic entering and exiting the subnet.
5. They are not directly associated with VPNs, Availability Zones (AZs), or Regions. However, since subnets exist within AZs, NACLs indirectly affect traffic at the AZ level.
6. NACLs are specific to a VPC, which exists within a Region. You can't use a NACL from one VPC in another VPC, even within the same Region.
7. By default, when you create a new VPC, AWS creates a default NACL that allows all inbound and outbound traffic.

