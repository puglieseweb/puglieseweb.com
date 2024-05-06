# AWS course

### througputHow computer communicate

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

* &#x20;Purchase unused capacity at a discount of up to 90%. Prices fluctuate with supply and demand.

### Dedicated Host

A physical EC2 server dedicated for your use. The most expensive option. Allows you to use your existing per-socket, per-core, or per-VM software lincences, including Windoes Server, Microsoft SQL Server, and SUSE Linux Entrerprise Server.

Use cases are:&#x20;

* compliance: Regulatory requirements that may not support multi-tenant virtualization
* Licensing: for licensing that does not support multi-tenancy or cloud deployments.











