# Lab: Connect Multiple VPCs with Transit Gateway

### Introduction

In this lab, we are going to connect two VPCs via AWS Transit Gateway and adjust route tables to establish private connectivity. This is important, as it will allow communication to flow through your private network without traversing the public internet.

### Solution

Log in to the AWS Management Console using the credentials provided on the lab instructions page. Make sure you're using the `us-east-1` Region.

#### Create a VPC

1. Under **Recently visited**, select **VPC**. (If **VPC** doesn't show up as an option under **Recently visited**, you can navigate to it by entering _vpc_ in the search bar on top of the console and selecting **VPC** from the search results.)
2. Click the **Create VPC** button.
3. Under **VPC Settings**, set the following parameters:

* **Auto-generate**: Make sure the box is checked.
* \*\*For the value of the name tag, use **project**.
* **IPv4/CIDR block**: Enter _172.21.0.0/16_ (or anything you want besides the default CIDR block).
* **Number of Availability Zones**: Select **1**.
* **Number of public subnets**: Select **0**.
* **Number of private subnets**: Select **1**

4. Click the **Create VPC** button.
5. Click the **View VPC** button.

#### Create Transit Gateway

1. In the left-hand navigation menu, select **Transit gateway**.
2. Click the **Create transit gateway** button.
3. Under **Name tag**, enter _Transit_.
4. Click the **Create transit gateway** button. It may take a minute or two for these to be created.

#### Attach the Transit Gateway to Both VPCs

1. In the left-hand navigation menu, select **Transit gateway attachments**.
2. Click the **Create transit gateway attachments** button.
3. On the **Create transit gateway attachment** page, set the following parameters:

* **Name tag - optional**: Enter _Attachment-1_.
* **Transit gateway ID**: Select the **Transit** gateway that you created earlier.
* **VPC ID**: Select the first VPC option corresponding to **10.0.0/16**.
* **Subnet ID**: Check ONLY the first subnet listed (us-east-1a). Uncheck the other Subnet IDs.

4. Click the **Create transit gateway attachment** button.
5. While the first attachment is creating, click the **Create transit gateway attachment** button.
6. On the **Create transit gateway attachment** page, set the following parameters:

* **Name tag - optional**: Enter _Attachment-2_.
* **Transit gateway ID**: Select the **Transit** gateway that you created earlier.
* **VPC ID**: Select the second VPC option corresponding to **172.21.0.0/16** (or the CIDR block that you entered earlier when creating the VPC).

7. Click the **Create transit gateway attachment** button.

#### Edit Route Tables

1. Once both transit gateway attachments are showing as **Available**, go to the left-hand navigation menu and select **Route tables**.
2. From the list of route tables, select the _project-rtb-private1-us-east-1a_ route table ID. (If you don't see it in the route table list, wait a couple of minutes and then hit the refresh button.)
3. Click the **Edit routes** button.
4. Click the **Add route** button.
5. For the new route, in the **Destination** column, enter _10.0.0.0/16_.
6. In the **Target** column, select **Transit Gateway**.
7. When it locates the transit gateway associated with **Attachment-2**, select that gateway.
8. Click the **Save Changes** button.
9. In the breadcrumb trail on top of the page, select **Route tables**.
10. Select the route table ID that has three subnets associated with it.
11. Click the **Edit routes** button.
12. Click the **Add route** button.
13. For the new route, in the **Destination** column, enter _172.21.0.0/16_ (or the CIDR block that you entered earlier when creating the VPC).
14. In the **Target** column, select **Transit Gateway**.
15. When the transit gateway associated with **Attachment-1** is located, select that gateway.
16. Click the **Save Changes** button. Now, both subnets are able to communicate with each other.

\
