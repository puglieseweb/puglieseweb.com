# NAT

NAT (Network Address Translation) gateways are not allowed by default in AWS. When you create a new VPC, by default you only get:

1. The VPC itself with its CIDR range
2. A main route table
3. A network ACL
4. A security group

To enable NAT functionality for private subnets to access the internet, you need to explicitly:

1. Create a NAT Gateway in a public subnet
2. Configure route table entries to route traffic from private subnets through the NAT Gateway
3. Ensure you have an Internet Gateway attached to your VPC
4. Have the proper routing set up in your public subnet

Keep in mind that NAT Gateways incur additional AWS charges, which is another reason they're not enabled by default.
