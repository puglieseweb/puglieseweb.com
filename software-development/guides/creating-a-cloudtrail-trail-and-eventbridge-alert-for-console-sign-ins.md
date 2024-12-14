# Creating a CloudTrail Trail and EventBridge Alert for Console Sign-Ins

<figure><img src="../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>

##

### Introduction

In this Hands-on Lab, you will create several resources, including a CloudTrail Trail, SNS Topic and Subscription, and an Amazon EventBridge Rule.

### Solution

Log in to the AWS Management Console using the credentials provided on the lab instructions page. Make sure you're using the `us-east-1` Region.

#### 1. Create Multi-Region CloudTrail Trail

By default, CloudTrail displays the last 90 days worth of events that occur within your AWS Account. Let's quickly explore that then get to work on creating our custom CloudTrail Trail!

1. Navigate to **CloudTrail**.
2. Find and select **Dashboard**.
3. Under the dashboard, you will see a pane titled _Event history_ where you can view the last 90 days of captured events.
4. Explore some of the events to get familiar with their format. (_Hint: Look for some sign-in events if possible!_).
5. Find and select **Trails** on the left-hand menu.
6. Select **Create trail**. This will bring you to the _Choose trail attributes_ screen.
7. Provide the following _Trail name_: `OurTrail`.
8. For _storage location_ select **Create new S3 bucket**.
9. Under _Trail log bucket and folder_ verify the bucket name has been populated.
10. (_Optional_) Provide a prefix for your logs if desired.

***

#### 2. Create CloudTrail-specific KMS Key

1. Ensure that the checkbox for **Log file SSE-KMS encryption** is selected and enabled.
2. Select the **New KMS key** radio button.
3. For _AWS KMS alias_ enter `CloudTrailKey` (_This creates a new KMS key specific to encrypting our CloudTrail Trail logs_).
4. Under _Additional settings_ ensure that **Log file validation** is enabled. (_This enables you to leverage AWS to determine whether a log file was modified, deleted, or unchanged after AWS CloudTrail delivered it to the destination_).
5. (_Optional_) Provide tags if desired.
6. Select **Next**.

**Choose Log Events**

1. Under the _Events_ pane, for _Event type_, select **Management events**.
2. Under the _Management events_ pane, for _API activity_, ensure you have selected **Read** and **Write**.
3. Select **Next**.
4. Review the details, and then select **Create trail**.

***

#### 3. Create SNS Topic and Subscription

**Set Topic Attributes**

1. Navigate to Amazon **Simple Notification Service** (SNS).
2. Select **Topics** from the left menu.
3. Select **Create topic**.
4. Under the _Details_ pane, for _Type_, select **Standard**.
5. For _Name_, enter: `ConsoleAlerts`.
6. Leave all other defaults, scroll to the bottom and select **Create topic**.

**Create the Subscription**

1. Navigate to your recently created `ConsoleAlerts` topic.
2. Find and select **Subscription** in the console window.
3. Select **Create subscription**.
4. Ensure the topic ARN is correct, and then select **Email** from the _Protocol_ dropdown.
5. Input your email, or a temporary email, within the _Endpoint_ textbox.
6. Select **Create subscription**.

**Confirm the Subscription**

1. Navigate to your email inbox and wait for the SNS confirmation email.
2. Within the email, click the **Confirm subscription** link.
3. Verify the confirmation by looking under the _Subscriptions_ tab on the SNS Topic overview window (_It should say confirmed_).

**Test the Subscription**

1. Find and select **Publish message** under the topic dashboard screen.
2. (_Optional_) Enter a subject.
3. Under _Message body_ select \`**Identical payload for all delivery protocols**.
4. Enter your test message in the _Message body to send to the endpoint_ textbox.
5. Select **Publish message**.

***

#### 4. Setting up Amazon EventBridge

Let's work on putting it all together!

**Create Amazon EventBridge Rule**

1. Navigate to **Amazon EventBridge**.
2. Find and select **Rules** from the menu on the left.
3. Ensure the default bus is selected and then select **Create rule**.
4. Under _Name_ enter: `ConsoleSigninAlerts`.
5. (_Optional_) Enter a description.
6. Ensure **default** is selected under _Event bus_.
7. Make sure the toggle is on for _Enable the rule on the selected event bus_.
8. Under _Rule type_ select **Rule with event pattern**.
9. Select **Next**.
10. For _Event source_ choose **AWS events or EventBridge partner events**.
11. Under _Sample events_ choose **AWS Console Sign In via CloudTrail**.
12. Under _Creation method_ choose **Custom pattern (JSON editor)**.
13. Paste the below code into the _Event pattern_ section. Be sure to update both references of `REPLACE_ME` with your account ID.

    ```json
    {
      "detail-type": ["AWS Console Sign In via CloudTrail"],
      "source": ["aws.signin"],
      "detail": {
        "userIdentity": {
          "type": ["IAMUser", "Root"],
          "arn": [
            "arn:aws:iam::REPLACE_ME:user/cloud_user",
            "arn:aws:iam::REPLACE_ME:root"
          ]
        }
      }
    }
    ```
14. Scroll back up to the _Sample events_ section, find and select **AWS Console Sign In via CloudTrail** and copy the resulting code.
15. Switch _Sample event type_ to **Enter my own**, then paste the copied sample code back into the code block. 16. Replace the default ID references with your account ID in four places: lines 6, 14 , 15, and 16.
16. Scroll back down to the _Event pattern_ section and click **Test pattern**.
17. After you get a successful match message, click **Next**.

**Select the Target**

1. Under the _Target 1_ pane, for _Target types_ select **AWS service**.
2. For _AWS service_, select **SNS Topic** within the dropdown.
3. Under _Topic_ find and select your **ConsoleAlerts** SNS Topic.
4. Click **Skip to Review and create**.
5. Select **Create rule**.

**Test it out!**

1. Sign-out of your AWS account.
2. Sign back in to your account as the `cloud_user` using the lab credentials and URL.
3. You should receive an email containing the JSON of the captured event!
