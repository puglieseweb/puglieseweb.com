# Containers

Containers are a great option for long running applications.

Containers can be run in:

* Amazon ECS:&#x20;
  * Proprietary AWS container management solution.&#x20;
  * Best used when you are all in AWS. &#x20;
  * Simple to orchestrate containers.&#x20;
* Amazon EKS:
  * AWS-managed version of open-source Kubernates container management solution
  * Best used when you're not all in on AWS
  * Significantly more work to configure and integrate with AWS



## ECS Launch Types

Amazon ECS is a container orchestration service that lets you run Docker containers. When using ECS, you have two launch types for running your containers:

### ECS with EC2

* You manage your own EC2 instances that form the cluster
* You're responsible for patching, scaling, and maintaining the EC2 instances
* You have more control over the infrastructure
* Generally more cost-effective for steady, predictable workloads
* Better for applications requiring specific instance types or GPU access

### ECS with Fargate

* Serverless compute engine - no EC2 instances to manage
* AWS manages the underlying infrastructure completely
* You only pay for the resources your containers actually use
* Easier to scale and maintain
* Better for variable workloads or when you want minimal infrastructure management
* Higher cost per container compared to EC2 launch type

## EKS with Fargate

AWS Fargate can be used with Amazon EKS (Elastic Kubernetes Service).

When you deploy a pod:

1. EKS makes scheduling decisions
2. Fargate creates the necessary compute resources
3. Pod gets its own isolated CPU, memory, and networking
4. You pay only for resources used by the pods

Key differences from standard EKS:

* No node management required
* No capacity planning needed
* Each pod is more isolated
* Not all Kubernetes features are supported (like DaemonSets)
* Cannot run privileged containers

## EC2 VS Fargate

Infrastructure Management:

* ECS (EC2): You manage the EC2 instances, networking, and capacity planning
* Fargate: AWS manages everything; you just define container specs

Cost Model:

* ECS (EC2): You pay for EC2 instances whether fully utilized or not
* Fargate: Pay-per-use based on CPU and memory allocated to containers

Control:

* ECS (EC2): More control over instance types, networking, and customization
* Fargate: Less control but simpler management

Use Case Examples:

* ECS (EC2): Good for large applications with steady workloads, when you need specific instance types
* Fargate: Ideal for microservices, batch jobs, or applications with variable loads



