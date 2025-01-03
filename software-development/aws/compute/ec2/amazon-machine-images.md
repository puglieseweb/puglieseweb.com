---
description: Amazon Machine Images
---

# AMI

An Amazon Machine Image (AMI) provides the information required to launch an instance. Yo can base an AMI on:

1. Region
2. Operatin system&#x20;
3. Architecture (32-bit or 64-bit)
4. Launch permissions
5. Storage for the root deivce (root device volume), where the OS is installed.

AMIs are either backed by:

* Amazon EBS: The root device for an instance launched from an AMI is an Amazon EBS volume created from an Amazon EBS shapshot. EBS-based instances can be stopped without losing the data. On termination of an instance it is possible to keep the EBS volumes.
* Instance Store: The root device for an instance launched from an AMI is an instance store volume created from a template stored in Amazon S3. This are ephemeral storage that cannot be stopped. If the underlying host fails, you lose your data. You can reboot the instance without losing the data. Deleting the instance you lose the instance store volume.&#x20;

In short:

* AMI is JUST the server image
* Launch Template is the COMPLETE configuration including which AMI to use
* You need an AMI to create a Launch Template
* AMIs can be used across multiple Launch Templates
* Launch Templates are required for some newer features like mixed instance types in ASG

## How to create an AMI

&#x20;You can create an Amazon Machine Image (AMI) in two main ways:

1. From a running EC2 instance:

* Creates a point-in-time snapshot of the instance
* Can be done while the instance is running (though for consistency, it's better to stop it first)
* Captures all EBS volumes attached to the instance
* Preserves all installed software, configurations, and data

2. From an existing EBS snapshot:

* Creates an AMI directly from a snapshot of a root volume
* Useful when you have a snapshot but the original instance no longer exists
* You can modify some configurations during creation (like instance type compatibility)
* Can add additional EBS volume snapshots during AMI creation

Important considerations:

* Creating from a running instance might capture inconsistent state if applications are actively writing data
* For production systems, it's recommended to stop the instance first
* If you create from a snapshot, it must be a snapshot of a root volume
* The resulting AMI will retain the permissions and encryption settings of the source snapshots

