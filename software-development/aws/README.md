# AWS



## AWS Secrets Manager VS AWS Systems Manager's Parameter Store&#x20;

Both AWS Secrets Manager and AWS Systems Manager Parameter Store are services for storing and managing configuration data, but they have some key differences that make them suitable for different use cases. Here's a brief overview of when to use each:

AWS Secrets Manager:

1. Sensitive information: Use for storing highly sensitive data like database credentials, API keys, or encryption keys.
2. Automatic rotation: When you need built-in secret rotation capabilities, especially for supported AWS services.
3. Fine-grained access control: When you require detailed AWS Identity and Access Management (IAM) policies for accessing secrets.
4. Cross-region replication: If you need to replicate secrets across multiple AWS regions.
5. Larger secrets: For storing secrets up to 65,536 bytes in size.

AWS Systems Manager Parameter Store:

1. Non-sensitive configuration: For storing non-sensitive application configuration data, environment variables, or settings.
2. Hierarchical storage: When you want to organize parameters in a hierarchical structure.
3. Version tracking: If you need to maintain a history of parameter changes.
4. Integration with other AWS services: When working closely with other AWS Systems Manager features or services that integrate well with Parameter Store.
5. Cost-sensitive scenarios: Parameter Store has a free tier and is generally less expensive for storing a large number of small parameters.
6. Smaller parameter values: For storing values up to 8,192 bytes in size (4,096 bytes for standard parameters).

In summary, use Secrets Manager for sensitive data that requires advanced features like automatic rotation, and use Parameter Store for general configuration data and when cost is a significant factor.



| Service                 | Synchronous/Asynchronous | Decoupling Type         | Message Order                             | Application Load Type           |
| ----------------------- | ------------------------ | ----------------------- | ----------------------------------------- | ------------------------------- |
| Amazon SQS              | Asynchronous             | Queue-based             | Not guaranteed (Standard), FIFO available | Variable, burst-capable         |
| Amazon SNS              | Asynchronous             | Pub/Sub                 | Not guaranteed                            | High throughput, fan-out        |
| Amazon EventBridge      | Asynchronous             | Event-driven            | Not guaranteed                            | Serverless event routing        |
| AWS Step Functions      | Both                     | Workflow                | Guaranteed (within workflow)              | Long-running, complex processes |
| Amazon MQ               | Both                     | Message broker          | Guaranteed (depends on protocol)          | Enterprise messaging            |
| Amazon Kinesis          | Asynchronous             | Stream processing       | Ordered within shards                     | Real-time data streaming        |
| AWS Lambda              | Both                     | Serverless compute      | N/A (depends on trigger)                  | Event-driven, scalable compute  |
| Amazon API Gateway      | Synchronous              | API management          | N/A                                       | HTTP-based API requests         |
| Amazon ECS/EKS          | Both                     | Container orchestration | N/A                                       | Containerized applications      |
| AWS AppSync             | Both                     | GraphQL API             | N/A                                       | Real-time data synchronization  |
| Amazon DynamoDB Streams | Asynchronous             | Change data capture     | Ordered within shards                     | Database change events          |
| AWS Batch               | Asynchronous             | Job queue               | FIFO within job queue                     | Batch processing workloads      |

###

###

###





### througput How computer communicate

* Linux SSH uses port 22
* Windows RDP uses port 3389
* HTTP Web browsing uses port 89
* HTTPS Web browsing uses port 443&#x20;

### Security groups&#x20;

Security groups are virtual firewall for EC2 instances. By default everything is blocked.

To let everything in: 0.0.0.0/0



Tip1: Changes to security groups takes effect immediatily&#x20;

Tip 2: You can have any number of EC2 instances withing a security group&#x20;

Tip 3: YOu can have multiple security groups attached to EC2 instances.

Tip 4: All inbound traffic is blocked by default

Tip 5: All outbound traffic is allowed.

### Bootstrap Scripts

A script that runs when the instance first runs. It has root level permissions

NOTE: Adding tasks at boot time adds to the amount of time it takes to boot the instances. However, it allows you to automate the installation of applications

## Placement Groups

There are 3 types of placement  groups:

1. Cluster Placement Group: Grouping of instances within a single Availability Zone. Recommended for applications that need low network latency, high network throughput, or both. NOTE: Only certain type of EC2 instances can be lunched in this placement mode. Low network latency, high network throughput.&#x20;
2. Spread Placement groups: group of instances that are each placed on distinct underlying hardware.  Recommended for application that have a small number of critical instances that should be kept separate from each other. This is used for individual instances (e.g. separate the primary database from the secondary one). E.g. Individual critical EC2 instances.
3. Partition Placement Groups: Each partion placement group has its own set of racks. Each rack has its own network and power source. No two partitions within a placement group share the same racks allowing you to isolate the impact of hardware failure within your application. This is used for individual instances on separated racks and power sources (e.g. Multiple EC2 instances; HDFS, HBase, and Cassandra)

A cluster placement group can't span multiple Availability Zones, whereas a spread placement group and partition placement group can.

Only certain types of instances can be launched in a placement group (compute optimized, GPU, memory optimized, storage optimized

AWS recommends homogenous instances within cluster placement groups.&#x20;

You can't merge placement groups.

You can move an existing instance into a placement group. Before moving the instance, the instance mush be in the stopped state. You can move or remove an instance using the AWS CLI or an AWS SDK, but you cannot do it via the console yet.&#x20;



## Pricing&#x20;

### On-demand

* pay by the hour or the second, depending on the type of instance your run&#x20;

### Reserved

* Reserved capacity for 1 or 3 years. Up to 72% discount on the hourly charge.&#x20;

### Spot

Purchase unused capacity at a discount of up to 90%. Prices fluctuate with supply and demand. Can be used for Stateless, fault-tolerant, or flexible applications. This cannot be used for a web service, that needs to be running all the time, but for applications such as big data, containerized workloads, CI/CD, high-performance computing (HPC), Image and media rendering, and other test and development workloads.&#x20;

To use Spot instances, you must provide your maximum Stop price. The instance will be provisioned so long as the Spot price is below you maximum Spot price. The hourly Spot price varies depending on the capacity and region. If the Spot price goes above your maximum, you have 2 minutes to choose whether to stop or terminate your instances.

#### Stop Blocks

You may also use a Spot block to stop your Spot Instances from being terminated even if the Spot price goes over your max Stop price. You can set Spot blocks for between 1 to 6 hours currently.

#### Spot Fleets&#x20;

A Spot Fleet is a collection of Stop Instances and (optionally) On-Demand instances to meet the target capacity you specified in the Spot Fleet. The request for Stop Instances is fulfilled if there is available capacity and the maximum price you specified in the request exceeds the current Stop price.  The Spot Fleet also attempts to maintain its target capacity fleet if your Spot Instances are interrupted.

Spot Fleets will try and match the target capacity with your price restraints.&#x20;

1. Set up different launch pools. Define things like EC2 instance type, operating system, and Availability Zone.&#x20;
2. You can have multiple pools, and the fleet will choose the best way to implement depending on the strategy you define
3. Stot fleets will stop launching instances once you reach your price threshold or capacity desire.

#### Use Cases

#### Terminating Stop Instances

#### Launch Pools

#### Strategies&#x20;

* capacityOptimized. The Stop fleet come from the pool with optimal capacity for the number of instances launching.&#x20;
* diversified. The Spot Instances are distributed across all pools
* lowestPrice. The Spot Instances come from the pool with the lowest price.  This is the default strategy.
* InstancePoolsToUseCount. The Spot Instances are distribuited across the number of Stop Instances pools you specify. This parameter is valid only when used in combination with lowerstPrice.



### Dedicated Host

A physical EC2 server dedicated for your use. The most expensive option. Allows you to use your existing per-socket, per-core, or per-VM software lincences, including Windoes Server, Microsoft SQL Server, and SUSE Linux Entrerprise Server.

Use cases are:&#x20;

* compliance: Regulatory requirements that may not support multi-tenant virtualization
* Licensing: for licensing that does not support multi-tenancy or cloud deployments.





## Deploying vCenter in AWS with VMware Cloud on AWS

Use Cases fro VMware:

* Hybrid Cloud: Connect your on-premises cloud to the AWS public cloud, and manage a hybrid workload.&#x20;
* Cloud Migration. Migrate your existing cloud environment to AWS using VMware's built-in tools
* Disaster Recovery. VMware is famous for its disaster recovery technology. Using hybrid cloud, you can have an inexpensive disaster recovery environment on AWS.
* Leverage AWS.  Use over 200 AWS services to update your application or to create new ones.&#x20;

How is it deployed?&#x20;

* It runs on dedicated hardware hosted in AWS using a single AWS account.
* Each host has two sockets with 18 cores per socket, 512 GiB RAM, and 15.2 TB Raw SSD storage
* Each host is capable of runing multiple VMware instances (up to teh hundreds).&#x20;
* Clusters can start with two hosts up to a maximum of 16 hosts per cluster.



## Outposts

Outposts brings the AWS data center directly to you, on-premises. Outposts allows you to have the large variety of AWS services in your data center. You can have Outposts in sizes such as 1U and 2 U servers all the way up to 42U racks and multiple-rack deployments.&#x20;

Benefits of Outposts:

* Hybrid Cloud: create a hybrid cloud where you can leverage AWS services inside your own data center.
* Consistency: Bring the AWS Management Console, APIs, and SDKs into your data center, allowing uniform consistency in your hybrid environments&#x20;
* Fully Managed Infrastructure: AWS can manage the infrastructure for you. You do not need a dedicated team to look after your Outposts infrastructure.

Outpost Family members:

#### Outposts Rack

Hardware: available starting with a sigle 42 rack and scale up to 9 racks

Services: provides AWS compute, storage, database, and other services locally

Results: Gives the same AWS infrastructure, services, and APIs in your ownd data center&#x20;

#### Outpost Servers

Hardware: individual servers in 1U or 2U form factor

Use Cases: Useful for small space requirements, such as retail stores, branch offices, healthcare provider locations, or factory floors.

Results: provide local compute and network services



## Networking&#x20;

There are different networking devices:



### ENI



### EFA



### Enhanced Networking



