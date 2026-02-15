# Lab: Troubleshooting Amazon EC2 Network Connectivity

### Introduction

The goal of this hands-on lab is to fix the connectivity issue in the AWS environment so we can update the `yum` package installer (from the command line) on the provided EC2 instance (named "web server"). Here, we'll go step-by-step through the scenario and offer detailed instructions on how to solve the connectivity issue.

<figure><img src="../../../.gitbook/assets/image (1) (1).png" alt=""><figcaption></figcaption></figure>

### Solution

Log in to the live AWS environment using the credentials provided. Make sure you're in the N. Virginia (`us-east-1`) region throughout the lab.

#### Fix SSH Ingress to Bastion Host

**The Issue**

_SSH traffic is being denied by the security group associated with the bastion host._

**How to Fix the Issue**

_Add an SSH (port 22) allow rule to the security group associated with the bastion host_

1. Locate the public IP address of the bastion host on the lab page and copy it. Alternately, in the AWS console, navigate to **EC2** > **Instances** and copy it from there.
2.  Open a terminal session, and log in via SSH:

    ```
    ssh cloud_user@&lt;PUBLIC IP ADDRESS>
    ```
3. It doesn't seem to connect. Head back to the AWS console to look at the bastion host.
4. Click the listed security group associated with the bastion host.
5. In the _Inbound_ rules tab, we'll see the only allow rule is port 80, which is for HTTP traffic and not SSH traffic.
6. Click **Edit**.
7. Delete the existing rule.
8. Click **Add Rule**, and set the following values:
   * _Type_: **SSH**
   * _Protocol_: **TCP**
   * _Port Range_: **22**
   * _Source_: **Anywhere**
9. Click **Save**.
10. Back in the terminal, we should see the prompt to continue connecting. Enter `yes`, and then enter the password for the instance (provided on the lab page). We've now successfully logged in to the bastion host.
11. Now, we need to log in to the "web server" instance. Copy the private IP address from the lab credentials page (or in the AWS console). In the terminal, enter:

    ```
    ssh cloud_user@&lt;PRIVATE IP ADDRESS>
    ```

    Enter `yes` at the prompt, and then enter the password provided on the lab page for the web server instance.
12. Now, run the YUM package installer:

    ```
    sudo yum update
    ```
13. Enter the password again.

There seems to be a hangup. Why is the EC2 instance not able to connect to the open internet in order to successfully update the YUM package installer?

#### Fix Egress from Web Server to Internet

**The Issue**

_The NACL protecting the web server only allows return traffic to the public subnet, not the internet._

**How to Fix the Issue**

_Add an outbound "All Traffic" allow rule to 0.0.0.0/0 to the NACL._

1. In the AWS console, navigate to **VPC** > **Network ACLs**.
2. Click the **Private Network** NACL listed.
3. In the **Outbound Rules** tab, click **Edit outbound rules**.
4. Change the _Destination_ to **0.0.0.0/0**.
5. Click **Save**.
6. Back in the terminal, run:

```
sudo yum update
```

It still won't connect.

#### Fix Web Server Route to Internet

**The Issue**

_The web server does not have a route to the NAT gateway._

**How to Fix the Issue**

_Add a route to the NAT gateway on the route table associated with the private subnet the web server is located in._

1. In the AWS console, navigate to the **VPC** > **Route Tables**.
2. Select the **Private** route table, and click the **Routes** tab. We'll see there isn't a route to the NAT gateway.
3. Click **Edit routes**.
4. Click **Add route**, and set the following values:
   * _Destination_: 0.0.0.0/0
   * _Target_: Type "nat", and select the pre-populated NAT gateway listed in the dropdown
5. Click **Save routes**.
6. Back in the terminal, run:

```
sudo yum update
```

Note: If you get a lock message, kill -9 PID (replace PID with the process number), then run the yum command again.

It should work this time.

\
