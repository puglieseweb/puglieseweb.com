# Creating a Custom AMI and Deploying an Auto Scaling Group behind an Application Load Balancer

During this hands-on lab you will undergo the process of standing up a working Web Server on Amazon EC2, creating a custom AMI using the instance, and then deploying an Auto Scaling Group using the newly created AMI.\


## Create AMI of Amazon EC2 Instance

* Create a new AMI using the running **Amazon EC2** instance and name it `WebServer`.

Run Progress CheckComplete: Last checked at 8:16PM2Create Launch Template

* Create a new Launch Template named `WebServerLT` using the recently created `WebServer` AMI.
* For _Firewall (security groups)_, select **Create security group**, name it `AllowVpcHTTP`, select (Your Custom VPC), and set up the **Inbound Security Group Rules** to allow HTTP from the VPC (_Should have a CIDR of_ `10.0.0.0/16`).
* Under **Advanced details**, for the _IAM instance profile_ menu, find and select `OurSystemsManagerEC2Profile`.
* Find **Detailed CloudWatch monitoring Info** and select `Enable` from the dropdown.
* Finally, paste the [User Data](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/02-week-2/2.1%20-%20Creating%20a%20Custom%20AMI%20and%20Deploying%20an%20Auto%20Scaling%20Group/user_data.sh) code from the GitHub repo into the `User data` field and create the launch template.

## **Create Auto Scaling Group**

* After you've confirmed your new AMI is available, create a new Auto Scaling Group called `WebServer-asg` using the recently created `WebServerLT` Launch Template.
* Set the Auto Scaling Group VPC to `Your Custom VPC`.
* Deploy the group into the 3 _private_ subnets.
* Attach the Auto Scaling Group to a new Internet-facing Application Load Balancer that lives within the 3 _public_ subnets in `Your Custom VPC`.
* Forward the HTTP traffic to a new Target Group containing the Auto Scaling Group.
* Set the Auto Scaling Group desired capacity to `1`.
* Set the Auto Scaling Group minimum capacity to `1`.
* Set the Auto Scaling Group maximum capacity to `3`.
* Create a Target Tracking scaling policy for your Auto Scaling Group.
* For the scaling policy set the metric type to `Average CPU Utilization`
* Set the target value to `50`.

## **Create New ALB Security Group**

* Create a new VPC Security Group for the Application Load Balancer (ALB).
* Name it `OurALBSecurityGroup`.
* Deploy it into `Your Custom VPC`.
* Add an inbound rule that allows all HTTP traffic from all IPv4 addresses.
* Attach the security group to your new Application Load Balancer fronting your Auto Scaling Group.

> If the settings are not discussed, leave as default.

Run Progress CheckComplete: Last checked at 6:59AM4Test Scaling

* Connect to the Auto Scaling Group instance using Session Manager.
* Stress test the CPU using the installed `stress` application through the following command:

```bash
sudo stress --cpu 2
```

> After a few minutes, this should trigger a scaling action for the Auto Scaling Group.

