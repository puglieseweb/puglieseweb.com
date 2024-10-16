# Elastic Network Interface (ENI)

### Definition

An Elastic Network Interface (ENI) is a logical networking component in Amazon Web Services (AWS) that represents a virtual network card. ENIs are used in Amazon EC2 instances to facilitate network connectivity.

### Key Characteristics

1. **Virtual Network Card**: An ENI functions as a virtual network card for your EC2 instance.
2. **VPC Association**: Each ENI is associated with a specific VPC subnet.
3. **Multiple ENIs**: An EC2 instance can have multiple ENIs attached to it.
4. **Attributes**: An ENI can include the following attributes:
   * A primary private IPv4 address
   * One or more secondary private IPv4 addresses
   * One Elastic IP address per private IPv4 address
   * One public IPv4 address
   * One or more IPv6 addresses
   * One or more security groups
   * A MAC address
5. **Persistence**: ENIs can be created independently of EC2 instances and can persist separately from the lifecycle of any single instance.
6. **Hot Attach/Detach**: ENIs can be attached to or detached from EC2 instances while they're running ("hot-attach" and "hot-detach").

### Use Cases

1. **Create a Management Network**: Attach a separate ENI for SSH access, isolating it from your production traffic.
2. **Network and Security Appliances**: Use in network and security appliances in your VPC.
3. **Dual-homed Instances**: Create dual-homed instances with workloads/roles on distinct subnets.
4. **Low-budget High Availability**: Create a low-budget, high-availability solution by moving an ENI from a failed instance to a hot standby instance.
5. **Multi-IP Applications**: Use for applications that require multiple IP addresses.
6. **Multi-interface Applications**: Useful for applications that require multiple interfaces, like a web server with a separate backend interface to a database.

### Key Points to Remember

* ENIs are bound to a specific Availability Zone (AZ). They cannot be attached to an EC2 instance in a different AZ.
* The number of ENIs you can attach to an EC2 instance depends on the instance type.
* When you move an ENI from one instance to another, network traffic is redirected to the new instance.
* Each ENI has a unique MAC address, which doesn't change even if the ENI is detached and reattached to a different instance.
* Security groups are associated with ENIs, not with instances directly. This allows for fine-grained network security control.

Understanding ENIs is crucial for designing robust and flexible network architectures in AWS, especially for scenarios requiring complex networking setups or high availability configurations.
