# VPC endpoints

A VPC endpoint is attached to a specific subnet within your VPC. The exact attachment depends on the type of endpoint:

1. Gateway Endpoints (for S3 and DynamoDB) are NOT network interfaces but e virtual devices that are targets in your route table used only for S3 and DynamoDB:

* Attached at the VPC level via route tables
* You specify which route tables should have routes to the endpoint
* No ENI (Elastic Network Interface) is created
* A route is added automatically to the specified route tables with a prefix list destination and endpoint as the target

2. Interface Endpoints (for all other services):

* Attached to specific subnets you choose during creation
* Creates an ENI (Elastic Network Interface) in each subnet you select
* Requires a private IP address from the subnet's CIDR range
* Can attach to multiple subnets for high availability, but must be in different Availability Zones
* Uses DNS entries to route traffic to the endpoint

Interface Endpoints ARE network interfaces (ENIs):

* Create an Elastic Network Interface (ENI) in each subnet where you deploy them
* Each ENI has a private IP address from your subnet
* Used for most AWS services (except S3 and DynamoDB)
* Example services: SQS, SNS, CloudWatch, etc.

A Network Interface (ENI - Elastic Network Interface in AWS) is used in several ways:

1. Primary Network Interface

* Every EC2 instance has a primary network interface (eth0)
* Cannot be detached from the instance
* Controls the primary private/public IP addressing of the instance

2. Secondary Network Interfaces (eth1, eth2, etc.)

* Can be created and attached/detached from instances
* Allows multiple private IPs per instance
* Can be moved between instances
* Useful for network appliances or failover scenarios

Common Use Cases:

* Management Network: Separate interface for admin traffic
* Multi-homed Instances: Instance with interfaces in different subnets
* Network Appliances: Firewalls, load balancers, NAT instances
* Workload Separation: Different security groups for different interfaces
* High Availability: Moving ENIs between instances for failover

Key Properties:

* Has a primary private IPv4 address
* Can have multiple secondary private IPv4 addresses
* Can have one Elastic IP per private IPv4 address
* Has one or more security groups attached
* Has a MAC address
* Can have source/destination check flag enabled/disabled
* Can have specific subnet and AZ assignment
