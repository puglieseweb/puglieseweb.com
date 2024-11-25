# AWS Best Practices

Follows principle of least privilege

## Use Private subnets

When deploying EC2 instances for an e-commerce application, there are very few legitimate use cases for placing them in public subnets. Let me break this down:

Instances in Private Subnets (Best Practice):

1. Web/Application servers
2. Database servers
3. Payment processing systems
4. Order management systems
5. Inventory systems

Limited Use Cases for Public Subnets:

1. Bastion Hosts (Jump Boxes)
   * Used for secure SSH access to instances in private subnets
   * Acts as a controlled entry point for administrative access
2. NAT Instances (Legacy)
   * Before NAT Gateway was available
   * Used to provide internet access for private instances
   * Now mostly replaced by managed NAT Gateway service
3. Public-facing static content servers (Not Recommended)
   * Better alternatives exist:
     * Amazon S3 for static content
     * CloudFront for content delivery
     * ALB for dynamic content

For an e-commerce application specifically, there's virtually no reason to put the application servers in public subnets because:

1. Security best practices recommend minimal public exposure
2. ALB can handle all incoming traffic
3. NAT Gateway can handle outbound internet needs
4. You get better security controls with private placement

The correct architecture should keep all e-commerce application components in private subnets with:

* ALB in public subnets for incoming traffic
* NAT Gateway for outbound internet access
* VPC endpoints for AWS services access
* Bastion host (if needed) as the only other EC2 instance in public subnet
