# Interface Endpoint VS Elastic Network Interface (ENI)

Interface Endpoints actually use ENIs under the hood to provide their functionality.

Interface Endpoints:

* These are VPC endpoints that enable you to privately connect your VPC to supported AWS services (like S3, DynamoDB, etc.)
* They are powered by AWS PrivateLink
* Create a private entry point within your VPC that AWS services can be accessed through
* Have their own DNS entries and security groups
* Main purpose is to keep traffic between your VPC and AWS services within the AWS network, avoiding the public internet

Elastic Network Interfaces (ENIs):

* These are **virtual network cards that you can attach to EC2 instances**
* **More general-purpose networking component** that provides network connectivity to AWS resources
* Can have multiple private IP addresses, public IP addresses, and security groups
* Can be moved between instances (useful for failover scenarios)
* Main purpose is to provide network connectivity for EC2 instances and other AWS resources
