# Standing Up an Apache Web Server EC2 Instance and Sending Logs to Amazon CloudWatch

During this hands-on lab you will learn how to leverage the AWS Secrets Manager to securely create and rotate an administrator password for an Amazon Aurora deployment

## 1Install and Configure the Amazon CloudWatch Agent

Connect to the **ApacheWebServer** EC2 instance using Session Manager.

Once connected, use the commands listed in the lab [GitHub repo](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/01-week-1/1.3%20-%20Standing%20up%20an%20Apache%20Web%20Server%20EC2%20Instance%20%26%20Sending%20Logs%20to%20Amazon%20CloudWatch/cloudwatch-agent-commands.md) to install the Amazon CloudWatch agent and create the agent configuration file.

Copy and paste the [JSON configuration code](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/01-week-1/1.3%20-%20Standing%20up%20an%20Apache%20Web%20Server%20EC2%20Instance%20%26%20Sending%20Logs%20to%20Amazon%20CloudWatch/cloudwatch-agent-config.json) into the new file then start the agent with the new config file using the command listed on [GitHub](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/01-week-1/1.3%20-%20Standing%20up%20an%20Apache%20Web%20Server%20EC2%20Instance%20%26%20Sending%20Logs%20to%20Amazon%20CloudWatch/cloudwatch-agent-commands.md).

## 2Create a Metric Filter for 404 Status Codes

Within the Amazon CloudWatch **Apache-Access-Logs** Log Group, create a new metric filter looking for `404` status codes within the log entries.

For the _Filter pattern_ enter the following text to create a metric filter that matches for any 404 status codes.

```
    %\b404\b%
```

Metric Filter Settings:

* **Filter Name**: `404`
* **Metric namespace**: `Apache`
* **Metric name**: `404`
* **Metric value**: 1
* **Default value**: _blank_
* **Unit**: Count

For testing, open the public IP of the **ApacheWebServer** instance in your browser, change the URL to **http** and append a fake page on the end (Example: **http**://YOUR\_PUBLIC\_IP/**fake.html**).

## 3Create SNS Topic and CloudWatch Alarm

Now, we can put it all together and set up a new CloudWatch Alarm to send a message through Amazon SNS to notify us whenever a `404` is detected in our Log Group.

Create a new alarm via the CloudWatch Alarms dashboard for the newly created **Apache** namespace.

> Hint: There is a shortcut from within the graphed metric section.

Alarm Metric Settings:

* **Metric namespace**: `Apache`
* **Metric name**: `404`
* **Statistic**: `Sum`

Alarm Conditions Settings:

* **Threshold type**: Static
* **Whenever 404 is...**: Greater/Equal
* **than**: 0
* **Alarm state trigger**: In alarm
* **Alarm name**: `404-Detections`

Then, create a new SNS topic named `404-Alerts` and confirm subscription to it. Refresh the fake ApacheWebServer page to generate 404 errors and test the alarm.
