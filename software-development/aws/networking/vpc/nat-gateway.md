# NAT Gateway

Network address translation (NAT) grateways enables instances in a private subnet to **connect to the internet or other AWS services while preventing the internet from initiating a connection with those instances.**&#x20;

NAT (Network Address Translation) gateways are not allowed by default in AWS. When you create a new VPC, by default you only get:

1. The VPC itself with its CIDR range
2. A main route table
3. A network ACL
4. A security group.

**Key characteristics:**

* Redundant inside the Availability Zone
* Start at a 5 Gbps and scales currently to 45 Gbps
* No need to patch
* Not associated with security groups
* Automatically assigned a public IP address

To enable NAT functionality for private subnets to access the internet, you need to explicitly:

1. Create a NAT Gateway in a public subnet
2. Configure route table entries to route traffic from private subnets through the NAT Gateway
3. Ensure you have an Internet Gateway attached to your VPC
4. Have the proper routing set up in your public subnet

Keep in mind that NAT Gateways incur additional AWS charges, which is another reason they're not enabled by default



## Reducting Costs

If you're looking to reduce costs, you could consider:

1. Using a NAT Instance instead of a NAT Gateway
   * Cheaper but requires more management
   * Less reliable as it's a single point of failure
   * You manage the instance yourself
2. Using other architectural patterns:
   * Placing instances in public subnets where appropriate
   * Scheduling NAT Gateway usage if you don't need 24/7 availability
   * Using VPC endpoints for AWS services instead of routing through NAT

