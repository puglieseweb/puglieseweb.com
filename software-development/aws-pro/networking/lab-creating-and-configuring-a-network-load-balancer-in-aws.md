# Lab: Creating and Configuring a Network Load Balancer in AWS

### Introduction

In this hands-on lab, you will prepare the AWS environment for the Network Load Balancer (configuring subnets, network ACL, and EC2 instances). When the preparation is complete, you will create and configure a Network Load Balancer. After configuration of the load balancer, you will work from the CLI to run a small test on the load balancer and view the results in the CloudWatch service.

### Solution

Log in to the live AWS environment using the credentials provided. Make sure you're in the N. Virginia (**us-east-1**) Region throughout the lab.

#### Prepare the Environment

**Create and Configure a Subnet**

1. Navigate to the **VPC Management Console** by searching for **VPC** in the search bar at the top of the AWS Console and selecting **VPC** from the list of services.
2. Click **Subnets** in the left-hand menu, and then click **Create subnet**.
   * **VPC ID**: Select the listed VPC
   * **Subnet name**: **PublicB**
   * **Availability Zone**: **us-east-1b**
   * **IPv4 CIDR block**: **10.0.2.0/24**
3. Click **Create subnet**.
4. On the **Subnets** page, with the **PublicB** subnet selected, click the **Route table** tab below.
5. Click the **Route table** link.
6. With the route table selected, click the **Routes** tab below.
7. Click **Edit routes**, and then **Add route**.
8. Set the following values for the new route:
   * **Destination**: **0.0.0.0/0**
   * **Target**: Select **Internet Gateway**, and then select the listed internet gateway
9. Click **Save changes**.
10. Click the **Subnet associations** tab.
11. For **Explicit subnet associations** section of the page, click **Edit subnet associations**.
12. Select the **PublicB** subnet and click **Save associations**.

**Edit the Network ACL**

1. Click **Subnets** in the left-hand menu.
2. Select the **PublicB** subnet.
3. Click the **Network ACL** tab below, and then click the **Network ACL** link.
4. With the NACL selected, click on the **Actions** button and select **Edit inbound rules** from the dropdown list.
5. Update **Rule 100** with the following:
   * **Rule number**: **100**
   * **Type**: **HTTP (80)**
   * **Protocol**: **TCP (6)**
   * **Port range**: **80**
   * **Source**: **0.0.0.0/0**
   * **Allow/Deny**: **Allow**
6. Click **Add new rule**.
7. Set the following values:
   * **Rule number**: **110**
   * **Type**: **HTTPS (443)**
   * **Protocol**: **TCP (6)**
   * **Port range**: **443**
   * **Source**: **0.0.0.0/0**
   * **Allow/Deny**: **Allow**
8. Click **Add new rule**.
9. Set the following values:
   * **Rule number**: **120**
   * **Type**: **SSH (22)**
   * **Protocol**: **TCP (6)**
   * **Port Range**: **22**
   * **Source**: **0.0.0.0/0**
   * **Allow/Deny**: **Allow**
10. Set the following values:
    * **Rule number**: **130**
    * **Type**: **Custom TCP**
    * **Protocol**: **TCP (6)**
    * **Port Range**: **1024-65535**
    * **Source**: **0.0.0.0/0**
    * **Allow/Deny**: **Allow**
11. Click **Save changes**.

**Create EC2 Instances**

**Create the WebA Instance**

1. Navigate to the **EC2 Management Console** and click on **Instances** in the left-hand menu.
2. Click **Launch instances**.
3. Set the name to **WebA**.
4. On the AMI page, select the latest Amazon Linux 2 AMI.
5. Leave **t3.micro** selected.
6. Click in the drop down and select **Proceed without a key pair**
7. In the **Network settings** section, click **Edit**.
8. Set the following values:
   * **Subnet**: Set to the **Public** subnet in **us-east-1a**
   * **Auto-assign Public IP**: **Enable**
9. Check the option "Select existing Security Group" and from the dropdown, select the EC2SecurityGroup that was created for the lab.
10. In the **Advanced details** section, scroll down to the **User data -optional** section and paste in the following script:

    ```
    #!/bin/bash
    yum update -y && yum -y install httpd && systemctl enable httpd && systemctl start httpd
    usermod -a -G apache ec2-user
    chown -R ec2-user:apache /var/www
    chmod 2775 /var/www
    find /var/www -type d -exec chmod 2775 {} \;
    find /var/www -type f -exec chmod 0664 {} \;
    echo "Request Handled by: WebA" >> /var/www/html/index.html
    ```
11. Click **Launch Instance**.
12. Click **View all instances**

**Create the WebB Instance**

1. Navigate to the **EC2 Management Console** and click on **Instances** in the left-hand menu.
2. Click **Launch instances**.
3. Set the name to **WebB**.
4. On the AMI page, select the latest Amazon Linux 2 AMI.
5. Leave **t3.micro** selected.
6. Click in the drop down and select **Proceed without a key pair**
7. In the **Network settings** section, click **Edit**.
8. Set the following values:
   * **Subnet**: Set to the **Public** subnet in **us-east-1b**
   * **Auto-assign Public IP**: **Enable**
9. Check the option "Select existing Security Group" and from the dropdown, select the EC2SecurityGroup that was created for the lab.
10. In the **Advanced details** section, scroll down to the **User data -optional** section and paste in the following script:

    ```
    #!/bin/bash
    yum update -y && yum -y install httpd && systemctl enable httpd && systemctl start httpd
    usermod -a -G apache ec2-user
    chown -R ec2-user:apache /var/www
    chmod 2775 /var/www
    find /var/www -type d -exec chmod 2775 {} \;
    find /var/www -type f -exec chmod 0664 {} \;
    echo "Request Handled by: WebB" >> /var/www/html/index.html
    ```
11. Click **Launch Instance**.
12. Click **View all instances**, and give the **WebA** and **WebB** instances a few minutes to pass their status checks.

#### Create and Configure a Network Load Balancer

1. Click on **Load Balancers** in the **Load Balancing** section of the left-hand menu.
2. Click **Create Load Balancer**.
3. In the **Network Load Balancer** card, click **Create**.
4. In the **Basic Configuration** section, set the following values:
   * **Load Balancer name**: **NLB4LAB**
   * **Scheme**: **Internet-facing**
5. In the **Network mapping** section, from the **Select a VPC** dropdown, select the listed VPC and then, under **Mappings**, check both the **us-east-1a** and **us-east-1b** checkboxes.
6. **Make sure your Network Load Balancer is created without security groups**.
7. In the **Listeners and routing** section, click **Create target group**. It will open a new browser tab.
8. On the **Specify group details** page, in the **Basic configuration** section, set the following values:
   * **Choose a target type**: **Instances**
   * **Target group name**: **nlbTargets**
   * **Protocol**: **TCP**
   * **Port**: **80**
9. In the **Health checks** section, set the following values:
   * **Health check protocol**: **TCP**
10. Leave the settings in the **Advanced health check settings** section as-is.
11. Click **Next**.
12. On the **Register targets** page, select the **WebA** and **WebB** instances you created (not the **AdminInstance**), and click **Include as pending below**.
13. Click **Create target group**.
14. Close the **Target groups** tab and navigate back to the **Load balancers** tab.
15. On the **Create Network Load Balancer** page, under **Listeners and routing**, click on the refresh button next to the **Default action | Forward to** field.
16. From the **Forward to** dropdown, select the **nlbTargets** target group we just created.
17. Scroll down and click **Create load balancer**.
18. Click **View load balancer**.
19. Scroll down to the **Load Balancers** section in the left-hand menu, right-click on **Target Groups**, and open it in a new tab.
20. In the new **Target groups** tab, select the **nlbTargets** target group.
21. Click the **Targets** tab. After a few minutes and once your NLB is active, you should see both the **WebA** and **WebB** instances display a **healthy** status.

#### Test and Monitor the Network Load Balancer

1. Click **Load Balancers** in the left-hand menu.
2. Select the **NLB4LAB** Network Load Balancer and, in the **Description** tab below, copy the contents of the **DNS name** field.
3. Paste the DNS name you copied into a new browser tab and press **Enter**. It should result in a web page that says **Request handled by: WebA** or **Request handled by: WebB**, depending on which instance the request is routed to.
4. In a second tab, navigate to the **EC2 Management Console**, and click **Instances** in the left-hand menu.
5. From the list of running instances, select the **AdminInstance** and, from the **Details** tab below, copy the contents of the **Public IPv4 address** field.
6.  In the terminal of your choice, log in to the **AdminInstance** server using the public IP address you just copied:

    ```
    ssh cloud_user@<PUBLIC IP ADDRESS>
    ```

    > **Note:** The unique password is provided in your lab credentials, under **Cloud Server public instance**.
7. In the first tab with the **Load Balancers** page open, with **NLB4LAB** still selected, from the **Description** tab, copy the DNS name again.
8.  In the terminal, bombard your load balancer with requests with the following command, using the DNS name you just copied:

    ```
    while true; do curl <LOAD BALANCER DNS NAME>; done
    ```
9. Hit **Enter**. Your terminal will most likely be flooded by a lot of scrolling text telling you which web instance — **WebA** or **WebB** — the request was handled by.
10. Hit **Ctrl+C** to break out of the loop.
11. In the first tab with the **Load Balancers** page open, click the **Monitoring** tab to keep an eye on the CloudWatch metrics. It may take a few minutes, but you should see the spikes in the different charts representing the simulated traffic.

\
