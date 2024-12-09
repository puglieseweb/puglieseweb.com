# ECS

Amazon Elastic Container Service  (ECS) is a service that allows you to easily launch and manege Docker containers running on AWS compute.

* ECS can manage thousands of containers
* Containers are appropriately registered with chosen load balancers and they come online and go offline.
* Contains can have individual roles attached, making security a breeze
* Extremely easy to set up and scale to handle any workload

## Deployment Options

There are three Amazon ECS (Elastic Container Service) deployment options.

### Amazon ECS on AWS Fargate

* Serverless compute engine where AWS manages the underlying infrastructure
* You don't need to provision, configure, or scale clusters of virtual machines
* Best for applications that don't require specific customization of the underlying host
* Pay-per-use model based on vCPU and memory consumed by containers
* Ideal for microservices and applications with variable workloads
* Lower operational overhead but typically higher cost per container

### Amazon ECS on Amazon EC2

* You manage the EC2 instances that form your ECS cluster
* Provides more control over infrastructure and instance types
* Better for workloads requiring specific instance configurations or GPU support
* More cost-effective for stable, predictable workloads with high utilization
* Requires more operational management (patching, scaling, security)
* Supports placement strategies and constraints for container placement

### Amazon ECS Anywhere

* Extends ECS to run containers on your own on-premises infrastructure
* Supports both virtual machines and bare metal servers
* Useful for hybrid cloud scenarios and edge computing
* Provides consistent container orchestration across cloud and on-premises
* You maintain full control and responsibility for infrastructure
* Requires more operational overhead compared to cloud options
* Good for workloads with data residency requirements or existing hardware investments

Key Decision Factors:

1. Control requirements: Fargate offers least control but lowest maintenance, EC2 provides balance, Anywhere gives most control
2. Cost optimization: EC2 typically most cost-effective for stable workloads, Fargate for variable workloads
3. Operational overhead: Fargate requires least management, followed by EC2, then Anywhere
4. Infrastructure requirements: Consider specific hardware needs, compliance requirements, or existing investments
5. Scaling needs: Fargate provides seamless scaling, EC2 requires more planning, Anywhere depends on your infrastructure

## Task vs Service

* A Task is a one-time run of containers
* A Service maintains a specified number of Tasks running continuously
* Services can integrate with load balancers and handle task replacement if tasks fail

For example, if you have a task definition for a web application that includes both a web container and a Redis container, each "task" would be a running instance of both these containers, deployed together as a unit.
