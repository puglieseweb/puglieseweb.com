# AWS Prep

* A solutions architect is designing a database solution that must support a high rate of random disk reads and writes. It must provide consistent performance, and requires long-term persistence.

Provisioned IOPS volumes support a high rate of random disk reads and writes. Provisioned IOPS volumes handle I/O-intensive workloads (particularly database workloads) that are sensitive to storage performance and consistency. Provisioned IOPS volumes use a consistent IOPS rate that you specify when you create them. Amazon EBS delivers the provisioned performance 99.9% of the time.



**A solutions architect at an ecommerce company wants to store application log data using Amazon S3. The solutions architect is unsure how frequently the logs will be accessed or which logs will be accessed the most. The company wants to keep costs as low as possible by using the appropriate S3 storage class.**

**Which S3 storage class should be implemented to meet these requirements?**

. S3 Intelligent-Tiering provides automatic cost savings for data with unknown or variable access patterns without retrieval fees, performance impact, or operational overhead by automatically moving data to the most cost-effective access tier based on access frequency.

S3 Intelligent-Tiering monitors access patterns and moves objects that have not been accessed for 30 consecutive days to the Infrequent Access tier. After 90 days without access, S3 Intelligent-Tiering moves the data to the Archive Instant Access tier. For data that does not require immediate retrieval, you can set up S3 Intelligent-Tiering to monitor and automatically move objects that have not been accessed for 180 days or more to the Deep Archive Access tier to achieve up to 95% in storage cost savings.



**A development team is deploying a new product on AWS and is using AWS Lambda as part of the deployment. The team allocates 512 MB of memory for one of the Lambda functions. With this memory allocation, the function is completed in 2 minutes. The function runs millions of times monthly, and the development team is concerned about cost. The team conducts tests to see how different Lambda memory allocations affect the cost of the function.**

**Which steps will reduce the Lambda costs for the product? (Select TWO.)**



A

Increase the memory allocation for this Lambda function to 1,024 MB if this change causes the run time of each function to be less than 1 minute.

Correct. In this case, the team increases the memory allocation by 100%. The new run time is more than 100% faster (less than 1 minute), so a reduction in the overall costs will occur.

Duration is calculated from the time your code begins running until it returns or otherwise terminates, rounded up to the nearest 100 milliseconds. The price depends on the amount of memory that you allocate to your function. In the Lambda resource model, you choose the amount of memory that you want for your function, and you receive proportional CPU power and other resources. **An increase in memory size initiates an equivalent increase in CPU that is available to your function.**

For more information about Lambda pricing, see [AWS Lambda Pricing](https://aws.amazon.com/lambda/pricing/).

B

Increase the memory allocation for this Lambda function to 1,024 MB if this change causes the run time of each function to be less than 90 seconds.

Incorrect. In this case, the team increases the memory allocation by 100%. For the change to increase cost-effectiveness, the run time would need to be more than 100% faster (less than 1 minute instead of less than 90 seconds).

For more information about Lambda pricing, see [AWS Lambda Pricing](https://aws.amazon.com/lambda/pricing/).

C

Reduce the memory allocation for this Lambda function to 256 MB if this change causes the run time of each function to be less than 4 minutes.

Correct. In this case, the team reduces the memory by 50%. The new run time is less than 200% slower (less than 4 minutes), so a reduction in the overall costs will occur.

Duration is calculated from the time your code begins running until it returns or otherwise terminates, rounded up to the nearest 100 milliseconds. The price depends on the amount of memory that you allocate to your function. In the Lambda resource model, you choose the amount of memory that you want for your function, and you receive proportional CPU power and other resources. An increase in memory size initiates an equivalent increase in CPU that is available to your function.

For more information about Lambda pricing, see [AWS Lambda Pricing](https://aws.amazon.com/lambda/pricing/).

D

Increase the memory allocation for this Lambda function to 2,048 MB if this change causes the run time of each function to be less than 1 minute.

Incorrect. In this case, the team increases the memory allocation by 300%. For the change to increase cost-effectiveness, the run time would need to be more than 300% faster (less than 30 seconds instead of less than 1 minute).

For more information about Lambda pricing, see [AWS Lambda Pricing](https://aws.amazon.com/lambda/pricing/).

E

Reduce the memory allocation for this Lambda function to 256 MB if this change causes the run time of each function to be less than 5 minutes.

Incorrect. In this case, the team reduces the memory allocation by 50%. For the change to increase cost-effectiveness, the run time would need to be less than 200% slower (less than 4 minutes instead of less than 5 minutes).

For more information about Lambda pricing, see [AWS Lambda Pricing](https://aws.amazon.com/lambda/pricing/).\




**A company is using Amazon DynamoDB to stage its product catalog, which is 1 TB in size. A product entry consists of an average of 100 KB of data, and the average traffic is about 250 requests each second. A database administrator has provisioned 3,000 read capacity units (RCUs) of throughput.**

**However, some products are popular among users. Users are experiencing delays or timeouts because of throttling. The popularity is expected to continue to increase, but the number of products will stay constant.**

**What should a solutions architect do as a long-term solution to this problem?**



\
Increase the provisioned throughput to 6,000 RCUs.

Incorrect. This solution would address the immediate situation, but it would not address the long-term needs that the scenario identifies. Eventually, the 6,000 RCUs would be consumed, and users would experience the same problem.

For more information about DynamoDB and provisioning capacity tables, see [Managing Settings on DynamoDB Provisioned Capacity Tables](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/ProvisionedThroughput.html).

B

Use DynamoDB Accelerator (DAX) to maintain the frequently read items.

Correct. DAX provides increased throughput for read-heavy workloads. DAX also provides potential cost savings by reducing the need to overprovision RCUs. This feature is especially beneficial for applications that require repeated reads for popular products.

For more information about DAX, see [Amazon DynamoDB Accelerator (DAX)](https://aws.amazon.com/dynamodb/dax/).

For more information about DAX, see [In-Memory Acceleration with Dynamo DB Accelerator (DAX)](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DAX.html).

C

Augment DynamoDB by storing only the key product attributes, with the details stored in Amazon S3.

Incorrect. This solution would address the immediate situation, but it would not address the long-term needs that the scenario identifies. The storage of the details in Amazon S3 could increase query times because the company would need to query the S3 objects and the DynamoDB table to gather the complete information about a product.

For more information about DynamoDB table exports, see [Exporting DynamoDB table data to Amazon S3](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DataExport.html).

For more information about DynamoDB table exports, see [New – Export Amazon DynamoDB Table Data to Your Data Lake in Amazon S3, No Code Writing Required](https://aws.amazon.com/blogs/aws/new-export-amazon-dynamodb-table-data-to-data-lake-amazon-s3/).

D

Change the partition key to consist of a hash of the product key and product type instead of just the product key.

Incorrect. In this scenario, a change of the partition key to distribute data would not work. Some products are more popular than other products are, so the product key and product type are not randomized elements.

For more information about primary keys, see [How do I choose the right primary key for a DynamoDB table?](https://aws.amazon.com/premiumsupport/knowledge-center/primary-key-dynamodb-table/)

For more information about partition keys, see [Choosing the Right DynamoDB Partition Key](https://aws.amazon.com/blogs/database/choosing-the-right-dynamodb-partition-key/).





**A company designs a mobile app for its customers to upload photos to a website. The app needs a secure login with multi-factor authentication (MFA). The company wants to limit the initial build time and the maintenance of the solution.**

**Which solution should a solutions architect recommend to meet these requirements?**



A

Use Amazon Cognito Identity with SMS-based MFA.

Correct. Amazon Cognito user pools are user directories that provide sign-up and sign-in options for web users and mobile app users. You can add MFA to Amazon Cognito user pools for secondary validation.

For more information about Amazon Cognito, see [Common Amazon Cognito Scenarios](https://docs.aws.amazon.com/cognito/latest/developerguide/cognito-scenarios.html).

For more information about MFA SMS text messages for users, see [SMS Text Message MFA](https://docs.aws.amazon.com/cognito/latest/developerguide/user-pool-settings-mfa-sms-text-message.html).

B

Edit IAM policies to require MFA for all users.

Incorrect. The process of editing IAM policies for each user is time-consuming and ongoing work as new users start to use the mobile app. Moreover, the company wants to limit the initial build time. Creation and management of IAM users would be a high-maintenance solution.

For more information about the creation of IAM policies, see [Creating IAM Policies](https://docs.aws.amazon.com/IAM/latest/UserGuide/access\_policies\_create.html).

C

Federate IAM against the corporate Active Directory that requires MFA.

Incorrect. When you design a public application that has a growing number of users, it is best practice to federate. However, Active Directory does not provide the sign-up feature of Amazon Cognito.

For more information about how to confirm users for Amazon Cognito, see [Signing Up and Confirming User Accounts](https://docs.aws.amazon.com/cognito/latest/developerguide/signing-up-users-in-your-app.html).

D

Use Amazon API Gateway and require server-side encryption (SSE) for photos.

Incorrect. Although API Gateway supports in-transit and at-rest SSE for secure upload of photos, this solution does not address the need for MFA. The question is about secured login and MFA.

For more information about data encryption in API Gateway, see [Data encryption in Amazon API Gateway](https://docs.aws.amazon.com/apigateway/latest/developerguide/data-protection-encryption.html).



**A solutions architect needs to allow developers to have SSH connectivity to web servers. The requirements are as follows:**

* **Limit access to users originating from the corporate network.**
* **Web servers cannot have SSH access directly from the internet.**
* **Web servers reside in a private subnet.**

**Which combination of steps must the architect complete to meet these requirements? (Select TWO.)**



A

Create a bastion host that authenticates users against the corporate directory.

Incorrect. A bastion host meets the requirement to prevent direct SSH access from the internet. However, simple authentication of users against the corporate directory would not prevent those users from logging in to the bastion host from a location outside the corporate network.

For more information about bastion hosts, see [Linux Bastion Hosts on AWS](https://aws.amazon.com/quickstart/architecture/linux-bastion/).

B

Create a bastion host with security group rules that only allow traffic from the corporate network.

Correct. A bastion host meets the requirement to prevent direct SSH access from the internet. The addition of a security group rule that only allows SSH access from the corporate network meets the requirement to limit access accordingly.

For more information about bastion hosts, see [Linux Bastion Hosts on AWS](https://aws.amazon.com/quickstart/architecture/linux-bastion/).

For more information about security group rules, see [Security group rules](https://docs.aws.amazon.com/vpc/latest/userguide/VPC\_SecurityGroups.html#SecurityGroupRules).

C

Attach an IAM role to the bastion host with relevant permissions.

Incorrect. The IAM permissions that are assigned to a bastion host would not prevent users from logging in to the host from locations outside the corporate network. A role that is assigned to an instance would control what AWS API calls that instance can perform.

For more information about bastion hosts, see [Linux Bastion Hosts on the AWS Cloud: Quick Start Reference Deployment](https://docs.aws.amazon.com/quickstart/latest/linux-bastion/architecture.html).

For more information about IAM permissions for Amazon EC2, see [IAM roles for Amazon EC2](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/iam-roles-for-amazon-ec2.html).

D

Configure the web servers' security group to allow SSH traffic from a bastion host.

Correct. The configuration of the web servers' security group to only allow SSH access from the bastion host will prevent access from other systems.

For more information about security group rules, see [Security group rules](https://docs.aws.amazon.com/vpc/latest/userguide/VPC\_SecurityGroups.html#SecurityGroupRules).

E

Deny all SSH traffic from the corporate network in the inbound network ACL.

Incorrect. This solution fails to solve the problem because the Deny statement would block external traffic only from the corporate network. This solution would not prevent users from outside the corporate network from attempting to access the web server directly. Even if the instance were in a private subnet, this solution would not block SSH access from any other instance. Therefore, sideways SSH traffic would be permitted.

For more information about network ACL rules, see [Network ACL rules](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-network-acls.html#nacl-rules).





**A solutions architect is responsible for a new highly available three-tier architecture on AWS. An Application Load Balancer distributes traffic to two different Availability Zones with an auto scaling group that consists of Amazon EC2 instances and a Multi-AZ Amazon RDS DB instance. The solutions architect must recommend a multi-Region recovery plan with a recovery time objective (RTO) of 30 minutes. Because of budget constraints, the solutions architect cannot recommend a plan that replicates the entire architecture. The recovery plan should not use the secondary Region unless necessary.**

**Which disaster recovery strategy will meet these requirements?**



A

Backup and restore

Incorrect. A backup and restore strategy can meet the cost objectives, but this strategy would not meet the RTO requirement.

B

Multi-site active/active

Incorrect. A multi-site active/active strategy would conflict with the budget constraints. Although a multi-site active/active strategy would provide a low RTO, this strategy is not appropriate for this scenario.

C

Pilot light

Correct. A pilot light strategy meets all the requirements. This strategy does not have a large increase in cost. This strategy offers an RTO within 10s of minutes.

For more information about disaster recovery, see [Disaster Recovery Options in the Cloud](https://docs.aws.amazon.com/whitepapers/latest/disaster-recovery-workloads-on-aws/disaster-recovery-options-in-the-cloud.html).

D

Warm standby

Incorrect. A warm standby strategy might be an alternative to a pilot light strategy. However, the solutions architect in this scenario must not use the secondary Region unless necessary. This strategy would keep resources in the secondary Region that would not be used, which would increase costs unnecessarily. Therefore, it is not the correct answer.





**An application provides a feature that allows users to securely download private and personal files. The web server is currently overwhelmed with serving files for download. A solutions architect must find a more effective solution to reduce the web server load and cost, and must allow users to download only their own files.**

**Which solution meets all requirements?**



A

Store the files securely on Amazon S3 and have the application generate an Amazon S3 presigned URL for the user to download.

Correct. You can use presigned URLs to share access to your S3 buckets. When you create a presigned URL, you associate it with a specific action and an expiration date. Anyone who has access to the URL can perform the action embedded in the URL as if they were the original signing user.

For more information about presigned URLs, see [Using presigned URLs](https://docs.aws.amazon.com/AmazonS3/latest/userguide/using-presigned-url.html).

For more information about how to share an object with a presigned URL, see [Sharing an object with a presigned URL](https://docs.aws.amazon.com/AmazonS3/latest/userguide/ShareObjectPreSignedURL.html).

B

Store the files in an encrypted Amazon Elastic Block Store (Amazon EBS) volume, and use a separate set of servers to serve the downloads.

Incorrect. Amazon EBS is a high-performance, block-storage service for use with Amazon EC2 for throughput and transaction-intensive workloads at any scale. The addition of a second set of servers would shift the work to another EC2 instance and would not reduce costs. Encryption of the EBS volume would not allow specific users to access only their specific files.

For more information about Amazon EBS, see [Amazon Elastic Block Store (Amazon EBS)](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/AmazonEBS.html).

C

Have the application encrypt the files and store them in the local Amazon EC2 instance store prior to serving them up for download.

Incorrect. An instance store is not appropriate for this scenario. An instance store is ideal for temporary storage of information that changes frequently, such as buffers, caches, scratch data, and other temporary content. An instance store also is ideal for data that is replicated across a fleet of instances, such as a load-balanced pool of web servers.

For more information about instance stores, see [Amazon EC2 instance store](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/InstanceStorage.html).

D

Create an Amazon CloudFront distribution to distribute and cache the files.

Incorrect. CloudFront is a content delivery network service that securely delivers data, videos, applications, and APIs globally with low latency and high transfer speeds. The use of a CloudFront cache alone would not allow users to download only their own files. You can use CloudFront signed URLs to allow users to download only their own files, but signed URLs are not mentioned in this response.

For more information about CloudFront distributions, see [Overview of distributions](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/distribution-overview.html).





**A company is planning to use a third-party service for application analytics. A solutions architect sets up a VPC peering connection between the company's VPC on AWS and the third-party analytics provider's VPC on AWS.**

**Which additional step should the solutions architect take so that network traffic can flow between the two VPCs?**



A

Resolve any overlapping CIDR ranges.

Incorrect. This step is not necessary because the solutions architect would not be able to create the VPC peering relationship if the CIDR ranges overlapped.

For more information about VPC peering, see [VPC peering basics](https://docs.aws.amazon.com/vpc/latest/peering/vpc-peering-basics.html).

B

Configure the route tables for both VPCs.

Correct. A route table contains a set of rules, known as routes. These routes determine where to direct network traffic from your subnet or gateway. Routes give you control of whether network traffic is routed over the peering connection.

For more information about VPC peering, see [VPC peering basics](https://docs.aws.amazon.com/vpc/latest/peering/vpc-peering-basics.html).

For more information about route tables for VPC peering, see [Updating your Route tables for a VPC peering connection](https://docs.aws.amazon.com/vpc/latest/peering/vpc-peering-routing.html).

C

Verify that neither VPC has additional peering connections.

Incorrect. Multiple peering connections are allowed. The default quota for the number of peering connections to a VPC is 50, and the maximum quota is 125. You would not be able to establish the peering connection if you were already at the maximum number.

For more information about VPC peering quotas, see [Amazon VPC quotas](https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html).

For more information about VPC peering, see [VPC peering basics](https://docs.aws.amazon.com/vpc/latest/peering/vpc-peering-basics.html).

D

Verify that internet gateways are attached to each VPC.

Incorrect. An internet gateway is not required for VPC peering.

For more information about VPC peering, see [VPC peering basics](https://docs.aws.amazon.com/vpc/latest/peering/vpc-peering-basics.html).





**An application runs on Amazon EC2 instances in multiple Availability Zones behind an Application Load Balancer. The load balancer is in public subnets. The EC2 instances are in private subnets and must not be accessible from the internet. The EC2 instances must call external services on the internet. Each Availability Zone must be able to call the external services, regardless of the status of the other Availability Zones.**

**How should these requirements be met?**



\
&#x20;Question

An application runs on Amazon EC2 instances in multiple Availability Zones behind an Application Load Balancer. The load balancer is in public subnets. The EC2 instances are in private subnets and must not be accessible from the internet. The EC2 instances must call external services on the internet. Each Availability Zone must be able to call the external services, regardless of the status of the other Availability Zones.

How should these requirements be met?

Report Content ErrorsA

Create a NAT gateway attached to the VPC. Add a route to the gateway that connects to each private subnet route table.

Incorrect. Unlike internet gateways, which apply to the entire VPC, NAT gateways must reside within a subnet and within a single Availability Zone. The use of a single NAT gateway would result in a single point of failure.

For more information about NAT gateways, see [NAT gateway basics](https://docs.aws.amazon.com/AmazonVPC/latest/UserGuide/vpc-nat-gateway.html#nat-gateway-basics).

B

Configure an internet gateway. Add a route to the gateway that connects to each private subnet route table.

Incorrect. This solution would turn the private subnets into public subnets and would make the EC2 instances accessible from the internet.

For more information about internet gateways, see [Internet gateways](https://docs.aws.amazon.com/vpc/latest/userguide/VPC\_Internet\_Gateway.html).

C

Create a NAT instance in the private subnet of each Availability Zone. Update the route tables for each private subnet to direct internet-bound traffic to the NAT instance.

Incorrect. To securely route traffic from private subnets to the internet, you must create NAT instances in public subnets.

For more information about NAT instances, see [NAT instances](https://docs.aws.amazon.com/vpc/latest/userguide/VPC\_NAT\_Instance.html).

D

Create a NAT gateway in each Availability Zone. Update the route tables for each private subnet to direct internet-bound traffic to the NAT gateway.

Correct. A NAT gateway is assigned to a public subnet and is associated with a single Availability Zone. This solution ensures that each Availability Zone is independent of the others for internet routing.

For more information about NAT gateways, see [NAT gateway basics](https://docs.aws.amazon.com/AmazonVPC/latest/UserGuide/vpc-nat-gateway.html#nat-gateway-basics).





**A user is designing a new service that receives location updates from 3,600 rental cars every hour. The cars upload their location to an Amazon S3 bucket. Each location must be checked for distance from the original rental location.**

**Which services will process the updates and automatically scale?**

A

Amazon EC2 and Amazon Elastic Block Store (Amazon EBS)

Incorrect. This solution has no S3 component, so it does not meet the question's requirements.

B

Amazon Kinesis Data Firehose and Amazon S3

Incorrect. Kinesis Data Firehose could upload the data to an S3 bucket. However, the data already has been uploaded. The scenario requires a service that will perform an action (in this case, a calculation of the distance from the rental car to the original rental location).

For more information about Kinesis Data Firehose, see [What Is Amazon Kinesis Data Firehose?](https://docs.aws.amazon.com/firehose/latest/dev/what-is-this-service.html)

For more information about how to use Kinesis Data Firehose to upload data to an S3 bucket, see [Choose destination](https://docs.aws.amazon.com/firehose/latest/dev/create-destination.html).

C

Amazon Elastic Container Service (Amazon ECS) and Amazon RDS

Incorrect. This solution has no S3 component, so it does not meet the question's requirements.

D

Amazon S3 events and AWS Lambda

Correct. When an object is placed in an S3 bucket, that action needs to invoke an action that calculates the distance from the car to the original rental location. S3 Event Notifications calls the Lambda function, and Lambda runs the code to do the calculation.

For more information about S3 Event Notifications, see [Amazon S3 Event Notifications](https://docs.aws.amazon.com/AmazonS3/latest/userguide/NotificationHowTo.html).

For more information about how to create reliable event processing with S3 Event Notifications, see [Reliable event processing with Amazon S3 event notifications](https://aws.amazon.com/blogs/storage/reliable-event-processing-with-amazon-s3-event-notifications/).



**A company is reviewing a recent migration of a three-tier application to a VPC. The security team discovers that the principle of least privilege is not being applied to Amazon EC2 security group ingress and egress rules between the application tiers.**

**What should a solutions architect do to correct this issue?**



A

Create security group rules using the instance ID as the source or destination.

Incorrect. Security group rules are used to control inbound and outbound traffic that can reach instances associated with a security group. You cannot specify an instance ID as a source or destination in a security group.

For more information about about security group rules, see [Security Group Rules](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/security-group-rules.html).

B

Create security group rules using the security group ID as the source or destination.

Correct. Security group rules are used to control inbound and outbound traffic that can reach instances associated with a security group. You can specify the security group ID as the source or destination. This solution ensures that the security group rules are implemented correctly and that application tiers are able to communicate.

For more information about security group rules, see [Security Group Rules](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/security-group-rules.html).

For more information about controlling traffic to AWS resources, see [Control Traffic to Your AWS Resources Using Security Groups](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-security-groups.html).

C

Create security group rules using the VPC CIDR blocks as the source or destination.

Incorrect. VPC CIDR blocks are groups of IP addresses that are assigned to your VPC. Although this solution is scoped to a private VPC address space, that space is shared with other applications. The other applications may not need access to the hosted application services. Therefore, this solution does not follow the principle of least privilege.

For more information about VPC CIDR blocks, see [VPC CIDR Blocks](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-cidr-blocks.html).

D

Create security group rules using the subnet CIDR blocks as the source or destination.

Incorrect. Subnet CIDR blocks are groups of IP addresses that are assigned to a specific subnet within your VPC. Although it is possible to create security group rules by using the subnet CIDR blocks as the source or destination, it would not follow the principle of least privilege. This is because other applications in the VPC subnet may not need access to the hosted application.

For more information about subnet CIDR blocks, see [Subnet CIDR Blocks](https://docs.aws.amazon.com/vpc/latest/userguide/subnet-sizing.html).





**A company built a food ordering application that captures user data and stores it for future analysis. The application's static front-end is deployed on an Amazon EC2 instance. The front-end application sends the requests to the backend application running on a separate EC2 instance. The backend application then stores the data in Amazon RDS.**

**What should a solutions architect do to decouple the architecture and make it scalable?**\


Use Amazon S3 to serve the static front-end application, which sends requests to Amazon EC2 to run the backend application. The backend application will process and store the data in Amazon RDS.

Incorrect. A solution that uses one EC2 instance for the backend application is an example of a tightly coupled architecture. This solution cannot scale without additional architectural improvements such as an EC2 Auto Scaling group and/or an Elastic Load Balancer.

For more information about Elastic Load Balancing and EC2 Auto Scaling, see [Use Elastic Load Balancing to Distribute Traffic Across the Instances in Your Auto Scaling Group](https://docs.aws.amazon.com/autoscaling/ec2/userguide/autoscaling-load-balancer.html).

B

Use Amazon S3 to serve the static front-end application and write requests to an Amazon Simple Notification Service (Amazon SNS) topic. Subscribe the backend Amazon EC2 instance HTTP/S endpoint to the topic, and process and store the data in Amazon RDS.

Incorrect. Amazon SNS decouples the architecture and is scalable. However, messages that are published to the SNS topic are not stored and cannot be re-examined if processing fails. Additionally, a solution that uses one EC2 instance for the backend application is an example of a tightly coupled architecture. This type of solution cannot scale without additional architectural improvements such as an EC2 Auto Scaling group and/or an Elastic Load Balancer.

For more information about Amazon SNS, see [What Is Amazon SNS?](https://docs.aws.amazon.com/sns/latest/dg/welcome.html)

For more information about Elastic Load Balancing and EC2 Auto Scaling, see [Use Elastic Load Balancing to Distribute Traffic Across the Instances in Your Auto Scaling Group](https://docs.aws.amazon.com/autoscaling/ec2/userguide/autoscaling-load-balancer.html).

C

Use an EC2 instance to serve the static front-end application and write requests to an Amazon SQS queue. Place the backend instance in an Auto Scaling group, and scale based on the queue depth to process and store the data in Amazon RDS.

Incorrect. A solution that uses one EC2 instance for the frontend application is an example of a tightly coupled architecture. This solution cannot scale without additional architectural improvements such as an EC2 Auto Scaling group and/or an Elastic Load Balancer.

For more information about Elastic Load Balancing and EC2 Auto Scaling, see [Use Elastic Load Balancing to Distribute Traffic Across the Instances in Your Auto Scaling Group](https://docs.aws.amazon.com/autoscaling/ec2/userguide/autoscaling-load-balancer.html).

D

Use Amazon S3 to serve the static front-end application and send requests to Amazon API Gateway, which writes the requests to an Amazon SQS queue. Place the backend instances in an Auto Scaling group, and scale based on the queue length to process and store the data in Amazon RDS.

Correct. A solution that moves the static frontend application to Amazon S3 will decouple the frontend application from the backend application. This solution will allow for scalability and improve application availability by removing the EC2 instance as a single point of failure.

A serverless managed services like API Gateway and Amazon SQS would also eliminate single points of failure and further decouple the frontend requests from processing operations. API Gateway is an AWS service that creates, publishes, maintains, monitors, and secures REST, HTTP, and WebSocket APIs at any scale. Amazon SQS provides a highly available and scalable message queuing service.

The addition of a backend EC2 instance to an EC2 Auto Scaling group will improve backend scalability and remove another single point of failure.

For more information about how to host static websites by using Amazon S3, see [Hosting a Static Website Using Amazon S3](https://docs.aws.amazon.com/AmazonS3/latest/userguide/WebsiteHosting.html).

For more information about API Gateway, see [What Is Amazon API Gateway?](https://docs.aws.amazon.com/apigateway/latest/developerguide/welcome.html)

For more information about Amazon SQS, see [What Is Amazon Simple Queue Service?](https://docs.aws.amazon.com/AWSSimpleQueueService/latest/SQSDeveloperGuide/welcome.html)

For more information about EC2 Auto Scaling, see [Get Started with Amazon EC2 Auto Scaling](https://docs.aws.amazon.com/autoscaling/ec2/userguide/GettingStartedTutorial.html).



**A company is designing a website that will be hosted on Amazon S3.**

**How should users be prevented from linking directly to the assets in the S3 bucket?**

Create a static website, then update the bucket policy to require users to access the resources with the static website URL.

Incorrect. You can use S3 bucket policies to grant permissions to S3 resources. However, the users still would be able to access the objects directly in the S3 bucket.

For more information about S3 bucket policies, see [Using Bucket Policies](https://docs.aws.amazon.com/AmazonS3/latest/userguide/bucket-policies.html).

B

Create an Amazon CloudFront distribution with an origin access control (OAC) and update the bucket policy to grant permission to the OAC only.

Correct. To restrict access to the assets in S3 buckets, you can create an OAC and associate it with the website distribution. You would configure the S3 bucket permissions so that CloudFront can use the OAC to access the files in the S3 bucket and serve the files to the users. Afterward, the users can access the static website files only through CloudFront, not directly from the S3 bucket.

For more information about how to use an OAC to restrict access to S3 content, see [Restricting Access to an Amazon S3 Origin](https://docs.aws.amazon.com/AmazonCloudFront/latest/DeveloperGuide/private-content-restricting-access-to-s3.html).

C

Create a static website, then configure an Amazon Route 53 record set with an alias pointing to the static website. Provide this URL to users.

Incorrect. Route 53 is a highly available and scalable DNS web service. This solution still would allow the users to access the objects directly in the S3 bucket.

For more information about Route 53, see [Amazon Route 53 FAQs](https://aws.amazon.com/route53/faqs/).

D

Create an Amazon CloudFront distribution with an AWS WAF web ACL that permits access to the origin server through the distribution only.

Incorrect. AWS WAF will block or allow requests based on conditions that you specify, but the web ACL would not prevent the users from linking directly to the resources in the origin of the S3 bucket.

For more information about AWS WAF, see [AWS WAF FAQs](https://aws.amazon.com/waf/faqs/).



**A company needs to implement a relational database with a multi-Region disaster recovery Recovery Point Objective (RPO) of 1 second and a Recovery Time Objective (RTO) of 1 minute.**

**Which AWS solution can achieve this?**

A

Amazon Aurora Global Database

Correct. This solution provides your application with an effective RPO of 1 second and an RTO of less than 1 minute.

For more information about Aurora global database, see [Using Amazon Aurora global database](https://docs.aws.amazon.com/AmazonRDS/latest/AuroraUserGuide/aurora-global-database.html).

B

Amazon DynamoDB global tables

Incorrect. The company needs to implement a relational database, but DynamoDB is a nonrelational database.

For more information about DynamoDB global tables, see [Amazon DynamoDB global tables](https://aws.amazon.com/dynamodb/global-tables/).

C

Amazon RDS for MySQL with Multi-AZ turned on

Incorrect. Although this solution achieves high availability, Multi-AZ deployment does not meet the company's requirement for a multi-Region disaster recover solution.

For more information about Multi-AZ deployment with Amazon RDS, see [Amazon RDS Multi-AZ](https://aws.amazon.com/rds/features/multi-az/).

D

Amazon RDS for MySQL with a cross-Region snapshot copy

Incorrect. The solution will not allow the company to meet the RPO and RTO requirements.

For more information about cross-Region snapshots, see [Cross-Region Snapshot Copy for Amazon RDS](https://aws.amazon.com/blogs/aws/cross-region-snapshot-copy-for-amazon-rds/).



**Cost Explorer is showing charges higher than expected for Amazon Elastic Block Store (Amazon EBS) volumes connected to application servers in a production account. A significant portion of the charges from Amazon EBS are from volumes that were created as Provisioned IOPS SSD (io2) volume types. Controlling costs is the highest priority for this application.**

**Which steps should the user take to analyze and reduce the EBS costs without incurring any application downtime? (Select TWO.)**

A

Use the Amazon EC2 ModifyInstanceAttribute action to enable EBS optimization on the application server instances.

Incorrect. EBS optimization can increase EBS performance of the instance, but it will not contribute to cost analysis or cost reduction.

For more information about EBS-optimized instances, see [Amazon EBS–optimized instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-optimized.html).

B

Use the Amazon CloudWatch GetMetricData action to evaluate the read/write operations and read/write bytes of each volume.

Correct. The CloudWatch GetMetricData action can show the IOPS and throughput of an io2 volume to help you determine if the io2 volume is a good candidate for modification to a lower-cost volume type.

For more information about I/O characteristics and monitoring, see [I/O characteristics and monitoring](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-io-characteristics.html).

C

Use the Amazon EC2 ModifyVolume action to reduce the size of the underutilized io2 volumes.

Incorrect. You cannot use the EC2 ModifyVolume action to reduce the size of a volume. ModifyVolume can only expand the size of a volume.

For more information about EBS Elastic Volumes, see [Amazon EBS Elastic Volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-modify-volume.html).

D

Use the Amazon EC2 ModifyVolume action to change the volume type of the underutilized io2 volumes to General Purpose SSD (gp3).

Correct. You can make a change with the EC2 ModifyVolume action without incurring any volume downtime. First, use CloudWatch to get metrics on the underutilized io2 volumes. Then use ModifyVolume to change from io2 to gp3 to reduce costs.

For more information about how to request modifications to EBS volumes, see [Request modifications to your EBS volumes](https://docs.aws.amazon.com/AWSEC2/latest/WindowsGuide/requesting-ebs-volume-modifications.html).

E

Use an Amazon S3 PutBucketPolicy action to migrate existing volume snapshots to Amazon S3 Glacier Flexible Retrieval.

Incorrect. EBS volume snapshots are managed by AWS and cannot be migrated to S3 Glacier (with either instant or flexible retrieval tier). Amazon Data Lifecycle Manager (Amazon DLM), not Amazon S3, handles the current EBS snapshot lifecycle.

For more information about Amazon DLM, see [Amazon Data Lifecycle Manager](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/snapshot-lifecycle.html).



**A solutions architect is designing a new workload in which an AWS Lambda function will access an Amazon DynamoDB table.**

**What is the MOST secure means of granting the Lambda function access to the DynamoDB table?**



Create an IAM role with the necessary permissions to access the DynamoDB table. Assign the role to the Lambda function.

Correct. An IAM role is an IAM entity that defines a set of permissions for making AWS service requests. IAM roles are not associated with a specific user or group. Instead, trusted entities such as IAM users, applications, or AWS services assume roles.

For more information about IAM roles, see [IAM roles](https://docs.aws.amazon.com/IAM/latest/UserGuide/id\_roles.html).

B

Create a DynamoDB user name and password and give them to the developer to use in the Lambda function.

Incorrect. DynamoDB has no concept of user names or passwords. You can grant access to the Lambda function by using an IAM role.

For more information about DynamoDB, see [What Is Amazon DynamoDB?](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Introduction.html)

C

Create an IAM user, and create access and secret keys for the user. Give the user the necessary permissions to access the DynamoDB table. Have the developer use these keys to access the resources.

Incorrect. The use of static keys in code makes it possible to compromise access to the environment. This solution is not a secure way for the Lambda function to access DynamoDB. This solution also is not scalable because the keys must be shared with every user.

For more information about IAM users, see [IAM users](https://docs.aws.amazon.com/IAM/latest/UserGuide/id\_users.html).

D

Create an IAM role allowing access from AWS Lambda. Assign the role to the DynamoDB table.

Incorrect. The IAM role should be assigned to the Lambda function. The Lambda function then would use that role to access DynamoDB. This response reverses that logic.

For more information about IAM roles, see [IAM roles](https://docs.aws.amazon.com/IAM/latest/UserGuide/id\_roles.html).



**A company is planning to use Amazon S3 to store images uploaded by its users. The images must be encrypted at rest in Amazon S3. The company does not want to spend time managing and rotating the keys, but it does want to control who can access those keys.**

**What should a solutions architect use to accomplish this?**



A

Server-Side Encryption with encryption keys stored in an S3 bucket

Incorrect. A solution that stores the encryption keys in an S3 bucket would require the customer to rotate and manage access to the keys.

For more information about Amazon S3 security best practices, see [Amazon S3 Preventative Security Best Practices](https://docs.aws.amazon.com/AmazonS3/latest/userguide/security-best-practices.html#security-best-practices-prevent).

B

Server-Side Encryption with Customer-Provided Keys (SSE-C)

Incorrect. SSE-C requires the customer to manage the encryption keys. In this scenario, the customer does not want to manage the keys. Amazon S3 does not store, rotate, or manage access to the encryption keys.

C

Server-Side Encryption with encryption keys stored in AWS Systems Manager Parameter Store

Incorrect. A solution that stores the encryption keys in AWS Systems Manager Parameter Store would require the customer to rotate the keys.

For more information about AWS Systems Manager Parameter Store, see [AWS Systems Manager Parameter Store](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-parameter-store.html).

D

Server-Side Encryption with AWS KMS-Managed Keys (SSE-KMS)

Correct. SSE-KMS uses AWS Key Management Service (AWS KMS) to create, manage, rotate, and control access to encryption keys.

For more information about SSE-KMS, see [Using Server-Side Encryption with AWS KMS Keys (SSE-KMS)](https://docs.aws.amazon.com/AmazonS3/latest/userguide/UsingKMSEncryption.html).

For more information about AWS KMS, see [AWS Key Management Service](https://docs.aws.amazon.com/kms/latest/developerguide/overview.html).





**A company wants to build an immutable infrastructure for its software applications. The company wants to test the software applications before sending traffic to them. The company seeks an efficient solution that limits the effects of application bugs.**

**Which combination of steps should a solutions architect recommend? (Select TWO.)**



A

Use AWS CloudFormation to update the production infrastructure and roll back the stack if the update fails.

Incorrect. Updates to and rollbacks of the production infrastructure would impact the applications and users. The company would need a separate environment to test the software applications before the company deploys updates to production.

For more information about how to use CloudFormation to create multiple environments and test changes before you release updates to a production environment, see [Reuse Templates to Replicate Stacks in Multiple Environments](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/best-practices.html#reuse).

B

Apply Amazon Route 53 weighted routing to test the staging environment and gradually increase the traffic as the tests pass.

Correct. Route 53 weighted routing gives you the ability to send a percentage of traffic to multiple resources. You can use a blue/green deployment strategy to deploy software applications predictably and to quickly roll back deployments if tests fail.

For more information about weighted routing, see [Weighted Routing](https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/routing-policy-weighted.html).

C

Apply Amazon Route 53 failover routing to test the staging environment and fail over to the production environment if the tests pass.

Incorrect. Route 53 failover routing provides disaster recovery by routing traffic to a healthy resource. If the resource becomes unhealthy, Route 53 will route traffic to another healthy resource.

For more information about failover routing, see [Failover Routing](https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/routing-policy-failover.html).

D

Use AWS CloudFormation with a parameter set to the staging value in a separate environment other than the production environment.

Correct. The company could use a separate environment to test changes before the company deploys changes to production.

For more information about how to use CloudFormation to create multiple environments and test changes before you release updates to a production environment, see [Reuse Templates to Replicate Stacks in Multiple Environments](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/best-practices.html#reuse).

E

Use AWS CloudFormation to deploy the staging environment with a snapshot deletion policy and reuse the resources in the production environment if the tests pass.

Incorrect. A snapshot deletion policy does not support all resources and may not retain all required resources.

For more information about deletion policies and snapshot options, see [DeletionPolicy Options](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-attribute-deletionpolicy.html#aws-attribute-deletionpolicy-options).
