# Lab: Scaling EC2 Using SQS

### Introduction

You are a solutions architect at an e-commerce firm. The company runs flash sales from time to time, and when there's a spike in orders, the fulfillment backend struggles to meet demand. In order to provide the extra instances necessary to handle those spikes in both a cost-efficient and sustainable way, you will implement Auto Scaling rules for EC2 based on metrics received from CloudWatch.

### Solution

Log in to the AWS Management Console using the credentials provided, and make sure you are in the N. Virginia (`us-east-1`) Region throughout the lab.

> **Note**: AWS may take a few minutes to populate the metrics in CloudWatch.

#### Create CloudWatch Alarms

**Connect to Your EC2 Instances**

1. Navigate to **EC2** using the **Services** menu or the unified search bar.
2. From the **Resources**, select **Instances (running)**.
3. Connect to and configure your **Bastion Host** instance:
   * Check the checkbox to the left of your **Bastion Host** instance.
   *   Along the top of the page, click **Connect**, and then click **Connect**.

       Your instance is provisioned in a separate browser tab. This may take a few minutes.
   *   After your instance is provisioned, switch the user from `root` to `cloud_user`:

       ```
       su - cloud_user
       ```
   *   List the contents of your current directory:

       ```
       ls
       ```

       You should see the `send_messages.py` script listed.
   *   Run the `send_messages.py` script:

       ```
       ./send_messages.py
       ```

       The script sends messages to your SQS queue to simulate orders coming into your instance.
4. Navigate back to the **EC2 Management Console** browser tab, and use the breadcrumb along the top of the page to select **Instances**.
5. Connect to and configure your **AutoScaling Group** instance:
   * Check the checkbox to the left of your **AutoScaling Group** instance.
   *   Along the top of the page, click **Connect**, and then click **Connect**.

       Again, your instance is provisioned in a separate browser tab.
   *   After your instance is provisioned, switch the user from _root_ to _cloud\_user_:

       ```
       su - cloud_user
       ```
   *   List the contents of your current directory:

       ```
       ls
       ```

       You should see the `receive_messages.log` file and the `receive_messages.py` script listed.
   *   Review the log file:

       ```
       tail -f receive_messages.log
       ```

       You should see that the instance is continuously retrieving messages from the queue. You'll need more EC2 instances to support these incoming messages.
6. Leave both terminals open and running.

**Create a Scale-Out Alarm**

1. In a separate browser tab, navigate to **CloudWatch** using the **Services** menu or the unified search bar.
2. From the **Get started with CloudWatch** options in the main pane, select **Create alarms**.
3. Click **Create alarm**.
4. In the **Metric** section, click **Select metric**.
5. From the **Metrics** options, select **SQS**, and then select **Queue Metrics**.
6. Check the checkbox to the left of the metric named `ApproximateNumberOfMessagesVisible`.
7. Click **Select metric**.
8. Configure the metric conditions:
   * **Period**: Use the dropdown to select **1 minute**.
   * **Threshold type**: Ensure that **Static** is selected.
   * **Whenever ApproximateNumberOfMessagesVisible is...**: Ensure that **Greater** is selected.
   * **than...**: Enter _500_ into the text field.
9. Leave all other default settings and click **Next**.
10. Configure the alarm actions:
    * **Alarm state trigger**: Ensure that **In alarm** is selected.
    * **Send a notification tot he following SNS topic**: Ensure that **Select an existing SNS topic** is selected.
    * **Send a notification to...**: Click into the text field and select the **AutoScalingTopic**.
11. Leave all other default settings and click **Next**.
12. In the **Alarm name** field, enter _Scale Out_.
13. Leave the existing alarm description and click **Next**.
14. Review the alarm details and then click **Create alarm**.

**Create a Scale-In Alarm**

1. In the top right corner, click **Create alarm**.
2. In the **Metric** section, click **Select metric**.
3. From the **Metrics** options, select **SQS**, and then select **Queue Metrics**.
4. Check the checkbox to the left of the metric named `ApproximateNumberOfMessagesVisible`.
5. Click **Select metric**.
6. Configure the metric conditions:
   * **Period**: Use the dropdown to select **1 minute**.
   * **Threshold type**: Ensure that **Static** is selected.
   * **Whenever ApproximateNumberOfMessagesVisible is...**: Select **Lower**.
   * **than...**: Enter _500_ into the text field.
7. Leave all other default settings and click **Next**.
8. Configure the alarm actions:
   * **Alarm state trigger**: Ensure that **In alarm** is selected.
   * **Send a notification tot he following SNS topic**: Ensure that **Select an existing SNS topic** is selected.
   * **Send a notification to...**: Click into the text field and select the **AutoScalingTopic**.
9. Leave all other default settings and click **Next**.
10. In the **Alarm name** field, enter _Scale In_.
11. Leave the existing alarm description and click **Next**.
12. Review the alarm details and then click **Create alarm**.

    At this point, you should see that the **Scale Out** alarm is in an **In alarm** state.

**Test the Scale-Out Alarm**

1. In the **CloudWatch** sidebar menu, expand the **Metrics** section and select **All metrics**.
2. From the **Metrics** options, select **SQS**, and then select **Queue Metrics**.
3. Check the checkbox to the left of the metric named `ApproximateNumberOfMessagesVisible`.
4. Along the top of the page, select the **Custom** menu.
5. From the **Relative** timeframe, set the **Minutes** option to **30**.
6. To the right of the custom timeframe, use the **Line** dropdown to select **Stacked area** as the graph's display type.
7. To the right of the refresh icon, use the dropdown to select **10s** as the graph's refresh rate.
8. Leave this browser tab open so you can reference it later.

#### Create Simple Scaling Policies

1. Navigate to the **EC2 Management Console** browser tab.
2. In the sidebar menu, navigate to **Auto Scaling** and select **Auto Scaling Groups**.
3. Select the provided Auto Scaling group name and then select the **Automatic scaling** tab.
4. In the **Dynamic scaling policies** section, select **Create dynamic scaling policy**.
5. Fill in the scale-out policy details:
   * **Policy type**: Use the dropdown to select **Simple scaling**.
   * **Scaling policy name**: In the text field, enter _Scale Out_.
   * **CloudWatch alarm**: Use the dropdown to select **Scale Out**.
   * **Take the action**: Set the action to **Add 1 capacity units**.
   * **And then wait**: In the text field, enter _60_.
6.  After the policy details are complete, click **Create**.

    The policy should be successfully created and enabled.
7. In the **Dynamic policies** section, select **Create dynamic scaling policy** again.
8. Fill in the scale-in policy details:
   * **Policy type**: Use the dropdown to select **Simple scaling**.
   * **Scaling policy name**: In the text field, enter _Scale In_.
   * **CloudWatch alarm**: Use the dropdown to select **Scale In**.
   * **Take the action**: Set the action to **Remove 1 capacity units**.
   * **And then wait**: In the text field, enter _60_.
9.  After the policy details are complete, click **Create**.

    The policy should be successfully created and enabled.
10. Select the **Activity** tab.

    After a few minutes, you should see a new instance is launched as a result of the scale-out policy.

#### Observe the Auto Scaling Group's Behavior in CloudWatch

1.  Navigate to the **CloudWatch Management Console** browser tab.

    You should now see a number of messages reflected in your graph.
2. Navigate to the **EC2 Instance Connect** browser tab for your **Bastion Host** instance.
3.  Press **Ctrl+C** to stop the instance from sending messages.

    This simulates an end in your order sales.
4. After the messages stop sending, close out of the browser tab.
5. Navigate to the **EC2 Instance Connect** browser tab for your **AutoScaling Group** instance and note that it is still receiving messages.
6. Navigate to the **CloudWatch Management Console** browser tab and wait approximately 5–10 minutes to see the downward trend of messages reflected in your graph.
7.  After verifying the downward trend of messages is reflected in your graph, use the **CloudWatch** sidebar menu to expand **Alarms** and then select **In alarm**.

    You should now see that your **Scale In** alarm is in an **In alarm** state. If it's not, your message count may not be below 500 just yet.
8.  Navigate to the **Auto Scaling group** browser tab and refresh the **Activity history** section.

    You should see your EC2 instances beginning to terminate. Remember that you configured your scale-in policy to remove instances at a rate of one every 60 seconds.
9. After a minute, refresh the activity history again to confirm that another instance is terminating.

\
