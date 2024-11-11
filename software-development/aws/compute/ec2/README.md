---
description: Amazon Elastic Compute Cloud.
---

# EC2

### 1. Overview

Amazon EC2 (Elastic Compute Cloud) is a web service that provides resizable compute capacity in the cloud. It's designed to make web-scale cloud computing easier for developers.

Key features:

* Scalable virtual servers in the cloud
* Complete control of your computing resources
* Reduced time required to obtain and boot new server instances
* Pay only for the capacity you actually use

### 2. EC2 Instance Types

EC2 offers a wide variety of instance types optimized for different use cases:

* General Purpose (e.g., T3, M5)
* Compute Optimized (e.g., C5)
* Memory Optimized (e.g., R5)
* Storage Optimized (e.g., I3, D2)
* Accelerated Computing (e.g., P3, G4)

Each instance type offers different combinations of CPU, memory, storage, and networking capacity.

### 3. Amazon Machine Images (AMIs)

An AMI is a template that contains a software configuration (operating system, application server, and applications).

* You can select from a library of AMIs or create your own
* AMIs can be shared across AWS accounts
* AMIs are regional but can be copied across regions

### 4. EC2 Purchasing Options

* On-Demand Instances: Pay for compute capacity by the hour or second with no long-term commitments
* Reserved Instances: Purchase instances in advance for significant discounts (1 or 3-year terms)
* Spot Instances: Bid on spare Amazon EC2 computing capacity for up to 90% off the On-Demand price
* Dedicated Hosts: Physical EC2 servers dedicated for your use

| Feature              | On-Demand Instances                           | Reserved Instances                                                                                            | Spot Instances                                                      | Dedicated Hosts                                                                      |
| -------------------- | --------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| Description          | Pay for compute capacity by the second        | Purchase instances in advance for significant discounts                                                       | Bid on spare Amazon EC2 computing capacity                          | Physical EC2 servers dedicated for your use                                          |
| Commitment           | No long-term commitment                       | 1 or 3-year terms                                                                                             | No long-term commitment                                             | On-Demand or Reserved (1 or 3-year terms)                                            |
| Price Discount       | No discount                                   | Up to 72% compared to On-Demand                                                                               | Up to 90% compared to On-Demand                                     | Can be combined with Reserved Instances for additional savings                       |
| Recommended Use Case | Short-term, spiky, or unpredictable workloads | Steady-state or predictable usage                                                                             | Flexible start and end times, fault-tolerant or stateless workloads | Compliance requirements, licensing restrictions, or high-performance computing needs |
| Instance Control     | Full control                                  | Full control                                                                                                  | Can be terminated by AWS with 2-minute warning                      | Full control                                                                         |
| Capacity Reservation | No                                            | Yes                                                                                                           | No                                                                  | Yes                                                                                  |
| Billing Granularity  | Per second                                    | Pre-pay options: All Upfront, Partial Upfront, No Upfront                                                     | Per second                                                          | Per hour                                                                             |
| Availability         | Always available                              | Limited to capacity in a specific AZ                                                                          | Available only when there's spare EC2 capacity                      | Limited availability                                                                 |
| Convertibility       | N/A                                           | Standard RIs are not convertible, Convertible RIs can change instance family, OS, tenancy, and payment option | N/A                                                                 | N/A                                                                                  |
| Transferability      | N/A                                           | Can be transferred between AZs in the same region                                                             | N/A                                                                 | N/A                                                                                  |



### 5. EC2 Networking

* Virtual Private Cloud (VPC): Launch EC2 instances in a logically isolated section of the AWS Cloud
* Security Groups: Act as a virtual firewall for your instances to control inbound and outbound traffic
* Elastic IP Addresses: Static IPv4 addresses designed for dynamic cloud computing

### 6. EC2 Storage Options

* Amazon EBS (Elastic Block Store): Persistent block storage volumes for use with EC2 instances
* Instance Store: Temporary block-level storage for your instance
* Amazon EFS (Elastic File System): Fully managed file storage service for use with EC2

### 7. EC2 Auto Scaling

Automatically adjust the number of EC2 instances in your deployment according to conditions you define.

* Maintain application availability
* Scale your Amazon EC2 capacity up or down automatically according to conditions you define
* Automatically register new instances to a load balancer

### 8. Load Balancing

Elastic Load Balancing automatically distributes incoming application traffic across multiple targets, such as EC2 instances.

Types of load balancers:

* Application Load Balancer (ALB)
* Network Load Balancer (NLB)
* Classic Load Balancer (CLB)

### 9. EC2 Instance Metadata and User Data

* Instance Metadata: Data about your instance that you can use to configure or manage the running instance
* User Data: Script that you can provide when launching your instance to perform common automated configuration tasks

### 10. EC2 Hibernation

EC2 Hibernation preserves the in-memory RAM on persistent storage (EBS).

* Faster boot-up because it doesn't need to reload the operating system
* Instance RAM must be less than 150 GB
* Available for specific instance families (C3, C4, C5, M3, M5, R3, R4, R5)
* Supported on Windows, Amazon Linux 2 AMI, and Ubuntu
* Instances cannot be hibernated for more than 60 days
* Available for On-Demand and Reserved Instances

### 11. EC2 Placement Groups

Placement groups influence the placement of a group of interdependent instances to meet the needs of your workload.

Types:

* Cluster: Packs instances close together inside an Availability Zone
* Partition: Spreads instances across logical partitions
* Spread: Strictly places instances on distinct hardware

### 12. EC2 Enhanced Networking

Enhanced networking uses single root I/O virtualization (SR-IOV) to provide high-performance networking capabilities on supported instance types.

### 13. EC2 Monitoring

* Basic Monitoring: Metric data available automatically in 5-minute periods at no charge
* Detailed Monitoring: Metric data available in 1-minute periods for an additional cost

Remember to review AWS documentation for the most up-to-date information, as service features and limits may change over time.

