---
description: EBS volumes VS Instance Store
---

# Amazon Machine Images

An Amazon Machine Image (AMI) provides the information required to launch an instance. Yo can base an AMI on:

1. Region
2. Operatin system&#x20;
3. Architecture (32-bit or 64-bit)
4. Launch permissions
5. Storage for the root deivce (root device volume), where the OS is installed.

AMIs are either backed by:

* Amazon EBS: The root device for an instance launched from an AMI is an Amazon EBS volume created from an Amazon EBS shapshot.
* Instance Store: The root device for an insance launched from an AMI is an instance store volume created from a template stored in Amazon S3.

