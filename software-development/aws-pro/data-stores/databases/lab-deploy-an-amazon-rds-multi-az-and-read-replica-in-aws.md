# LAB - Deploy an Amazon RDS Multi-AZ and Read Replica in AWS

### Introduction

In this hands-on lab, we will work with Relational Database Service (RDS). This lab will give you hands-on experience with enabling Multi-AZ and backups, creating a read replica, promoting a read replica, and updating the RDS endpoint in Route 53. Multi-AZ and read replicas serve different purposes with RDS. Multi-AZ is strictly for failover, as the standby instances cannot be read from by an application. Read replicas are used for improved performance and migrations. With read replicas, you can write to the primary database and read from the read replica. Since a read replica can be promoted to be the primary database, it makes for a great tool in disaster recovery and migrations.

<figure><img src="../../../../.gitbook/assets/image (122).png" alt=""><figcaption></figcaption></figure>

### Solution

Log in to the AWS Management Console using the credentials provided. Make sure you're in the N. Virginia (`us-east-1`) Region throughout the lab.

#### Enable Multi-AZ Deployment

**Configure Your Multi-AZ Instance**

> **Note:** The target group for the load balancer may need additional time to provision. Before starting the lab, navigate to **EC2** > **Target Groups** and select **TG1** to ensure you have at least one instance registered as healthy. If your target group shows no instances are healthy, then wait a few more minutes until one changes.

1. From the EC2 service page, scroll down to **Load Balancing** and select **Load Balancers**.
2. Select the load balancer and then copy the DNS name of the load balancer.
3.  Open a new browser tab, and paste your copied DNS name.

    You will use this web page to test failovers and promotions in this lab.
4. Go back to the AWS Management Console, and navigate to **RDS** using the **Services** menu or the unified search bar.
5. In the **Amazon RDS** sidebar menu, select **Databases**.
6. Select the radio button to the left of your **wordpress** database details.
7.  Along the top of the page, click **Modify**.

    The database's configuration options may take a moment to load.
8. Enable Multi-AZ deployment:
   * In the **Availability & durability** section, select **Create a standby instance (recommended for production usage)**.
   * Scroll to the bottom of the page, and click **Continue**.
   * In the **Schedule modifications** section, select **Apply immediately**.
   * Click **Modify DB instance**.
9.  After a few moments, click **Refresh**.

    You should see the **Status** change to **Modifying**. If you don't see this change, you may need to click **Refresh** again. The instance may take up to 10 minutes to enable Multi-AZ.

**Verify the Multi-AZ Deployment**

1. After the instance is available again, verify that Multi-AZ is enabled:
   * In the **Amazon RDS** sidebar menu, select **Events**.
   *   Review the events history for your instance.

       You should see an event for converting the instance to Multi-AZ.
2.  Reboot the instance to simulate the Multi-AZ redundancy:

    * In the **Amazon RDS** sidebar menu, select **Databases**.
    * Ensure the radio button to the left of your instance is selected.
    * Use the **Actions** dropdown to select **Reboot**.
    * On the **Reboot DB Instance** page, select **Reboot With Failover?** and click **Confirm**.

    This ensures the secondary instance takes over when this instance goes down. The reboot takes a few moments.
3.  Navigate to the DNS web page tab and refresh the page.

    The web page will experience only a small amount of downtime while the database is being modified (which causes a temporary 502 error). After the reboot is complete, the Multi-AZ standby is now the primary.
4. Navigate back to the AWS Management Console and use the **Amazon RDS** sidebar to select **Events** (you may need to expand the sidebar menu).
5.  Review the events history for your instance.

    You should see events for the Multi-AZ failover listed.

#### Create a Read Replica

1. In the **Amazon RDS** sidebar menu, select **Databases** to return to your instance details.
2. Ensure the radio button to the left of your instance is selected.
3. Use the **Actions** dropdown to select **Create read replica**.
4. In the **Settings** section, enter _wordpress-rr_ into the **DB instance identifier** field.
5. In the **AWS Region** section, ensure the **Destination Region** is set to **US East (N. Virginia)**.
6.  Leave the other default settings and click **Create read replica** at the bottom of the page.

    The read replica will take about 5-10 minutes to become available.
7.  Navigate to the DNS web page tab and refresh the page.

    The web page should stay up because the read replica changes are asynchronous.

#### Promote the Read Replica and Change the CNAME Record in Route 53 to Point to the New Endpoint

**Promote the Read Replica**

1. Navigate back to the AWS Management Console.
2. After the read replica is available, select the radio button to the left of it.
3. Use the **Actions** dropdown to select **Promote**.
4. Leave all the defaults and click **Promote read replica** at the bottom of the page.
5.  After a few moments, click **Refresh**.

    You should see that the read replica's **Role** has changed from **Replica** to **Instance**, and that the **Status** is now **Modifying**.

**Change the CNAME Record to Point to the New Endpoint**

1. After the read replica instance is available, select the **wordpress-rr** instance name.
2. On the **Connectivity & security** tab, copy the **Endpoint** to your clipboard.
3.  Open Route 53 in a new tab.

    You can disregard any error messages that display.
4. In the **Route 53** sidebar menu, select **Hosted zones**.
5.  Select the **mydomain.local** hosted zone name.

    In the hosted zone details, you should see a CNAME record that points to your **wordpress** database endpoint.
6. Check the checkbox to the left of the CNAME record.
7. In the **Record details** pane on the right, click **Edit record**.
8. Replace the existing **Value** with your copied read replica endpoint.
9. Click **Save**.
10. Navigate to the DNS web page tab and refresh the page.

    You should see no downtime.
