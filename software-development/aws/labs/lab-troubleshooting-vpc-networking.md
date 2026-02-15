# Lab: Troubleshooting VPC Networking

### Introduction

In this hands-on lab, you will attempt to untangle a mess left by another cloud engineer. You will update the CloudFormation stack, troubleshoot, and fix the environment, so that you can successfully reach the website. Troubleshooting errors is an essential skill that you will likely encounter in real-world situations, so it is important to be as prepared as possible when these situations could arise.

### Solution

Log in to the AWS Management Console using the credentials provided on the lab instructions page. Make sure you're using the `us-east-1` Region.

#### Run Update for CloudFormation Stack

1. Navigate to the lab's [GitHub repo](https://github.com/ACloudGuru-Resources/Course_Certified_Solutions_Architect_Professional/blob/master/lab-networking/Step_2_LaFours_Disaster.yaml), and click **Raw**.
2. Copy the content to a new fle, and save the file as `Step_2_LaFours_Disaster.yaml`.
3. From the AWS Management Console, navigate to **CloudFormation**.
4. Select the provided stack, and click **Update**.
5. For **Prerequisite - Prepare template**, select **Replace current template**.
6. For **Specify template**, select **Upload a template file**.
7. Upload the `Step_2_LaFours_Disaster.yaml` you previously saved.
8. Click **Next** > **Next** > **Next** > **Submit**. It may take a minute or two to update. You will know the update is ready when the **Status** is **UPDATE\_COMPLETE**.

#### Check and Fix VPC Errors

1. Open **VPC** in a new browser tab. (Keep your CloudFormation tab open for later.)
2. On the left navigation panel under **Virtual private cloud**, select **Your VPCs**.
3. It looks like the VPC is running. On the left navigation panel, select **Subnets**.
4. The subnets should look okay. On the left navigation, select **Route tables**.
5. Click the link for the **Route table ID** where the route table has a value in the **Explicit subnet association** column.
6. Observe the internet gateway isn't set to the default. Click **Edit routes**.
7. Change the **Destination** to `0.0.0.0/0`.
8. Click **Save changes**.
9. On the left navigation, click **Internet gateways**. It should still be attached.
10. On the left navigation, click **Elastic IPs**. Elastic IPs should look good.
11. On the left navigation under **Security**, select **Network ACLs**.
12. Click the link for the **Network ACL ID** where there is a value in the **Associated with** column. Observe that there are some issues.
13. At the top, use the breadcrumb navigation to go back to the **Network ACLs** page.
14. To change the association, select the check box to the left of the network ACL that _doesn't_ have a current association.
15. Click **Actions** > **Edit subnet associations**.
16. Select the available subnet, and click **Save changes**.
17. From the left navigation menu, select **Security groups**.
18. Select the **ACG CSAP Networking Lab** security group, click the **Inbound rules tab**, and observe the **Source** should be updated.
19. Click **Edit inbound rules**.
20. Change the value for the inbound rule from `10.0.0.0/16` to `0.0.0.0/0`, and click **Save rules**.

#### Navigate to the Website

1. Go back to CloudFormation, and select the the **Outputs** tab at the top. (You may need to drag the **Stacks** pane over to see this option.)
2. Open the link for the **FQDN** value in a new browser tab.
3. You should see the message "Keep Being Awesome Cloud Gurus!", indicating everything was fixed and is working.
