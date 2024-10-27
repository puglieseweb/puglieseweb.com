# EBS Multi-Attach

You can attach EBS volumes to ECS cluster instances. However, you can attach an EBS volume to multiple instances only if those instances are in the same Availability Zone. The scenario mentions multiple Availability Zones, so this solution would not work for this use case.

For more information about EBS Multi-Attach, see [Attach a volume to multiple instances with Amazon EBS Multi-Attach](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-volumes-multi.html).
