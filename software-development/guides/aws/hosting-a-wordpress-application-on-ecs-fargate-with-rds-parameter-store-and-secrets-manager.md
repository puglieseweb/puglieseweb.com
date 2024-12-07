# Hosting a Wordpress Application on ECS Fargate with RDS, Parameter Store, and Secrets Manager

How to deploy a WordPress application using several AWS services, including: Amazon RDS, AWS Systems Manager, Parameter Store, Amazon ECS, Amazon ECR, and Application Load Balancers.&#x20;



<figure><img src="../../../.gitbook/assets/image (37).png" alt=""><figcaption></figcaption></figure>



## 1Verify Resources and Create Database Subnet Group

*   Before you begin, please verify the required resources below exist and if any of these do not exist, please exit the lab and restart.

    | Type                      | Name                       |
    | ------------------------- | -------------------------- |
    | Application Load Balancer | OurApplicationLoadBalancer |
    | Security Group            | ALBAllowHttp               |
    | Cloud9 Environment        | OurCloud9Environment       |
    | IAM Role                  | OurEcsTaskExecutionRole    |
    | IAM Role                  | OurEcsTaskRole             |
* Create a new Database Subnet Group within RDS
* Name it `database-subnet-group`
* Select the `us-east-1a`, `us-east-1b`, and `us-east-1c` AZs
* Select the subnets with the CIDRs of `10.0.20.0/24`, `10.0.21.0/24`, and `10.0.22.0/24`

## 2Create the Amazon RDS Instance

Create a new RDS database:

* Select `MySQL` for the engine type
* Use the default engine version
* Ensure it is a `Free Tier` template
* Name your cluster identifier `wordpress`
* Set the _Master username_ to `admin`
* Enable `Manage master credentials in AWS Secrets Manager` with the default AWS KMS encryption key
* Select a `db.t4g.micro` burstable class instance type
* Create a `20 GiB` volume using a `gp3` volume type
* Use the `database subnet group` from the _DB subnet group_ dropdown
* Make sure it is **not** publicly accessible
* Create a new Security Group for it called `database-sg` with `No preference` for the _Availability Zone_
* Create an initial database named `wordpress`

## 3Update the RDS Security Group Rules

* Edit the inbound rules for the `database-sg` that you created
* Delete the existing rule and add a new inbound rule with the following information:

| Type         | Protocol | Port range | Source | Value         | Description - optional        |
| ------------ | -------- | ---------- | ------ | ------------- | ----------------------------- |
| MYSQL/Aurora | TCP      | 3306       | Custom | `10.0.0.0/16` | `Allow MySQL access from VPC` |

## 4Create the Parameter Store Parameters and Verify Secrets Manager Secret

* Within the AWS Systems Manager Parameter Store capability, create **2** new parameters with the below information.
* After complete, verify there is a new secret in Secrets Manager and retrieve the secret value to view your admin credentials.

| Name                     | Description                 | Tier     | Type                                | Data type | Value                                                                                                  |
| ------------------------ | --------------------------- | -------- | ----------------------------------- | --------- | ------------------------------------------------------------------------------------------------------ |
| /dev/WORDPRESS\_DB\_HOST | Wordpress RDS endpoint      | Standard | String                              | text      | **YOUR\_RDS\_ENDPOINT**`:3306` (Example: _wordpress-rds.cc5tzmus2oai.us-east-1.rds.amazonaws.com:3306_ |
| /dev/WORDPRESS\_DB\_NAME | Wordpress RDS Database Name | Standard | SecureString (_default encryption_) | text      | `wordpress`                                                                                            |

## 5Create the Private ECR Repository

Within the Amazon ECR service, create a new private repository:

* Select private repositories
* Create a new one
* Set _Visibility settings_ to `Private`
* For _Repository name_ enter `wordpress`
* Leave **Tag immutability** disabled
* Enable the **Scan on push** option
* Leave **KMS encryption** disabled

## 6Create IAM User Access Keys

Create a new set of access keys for your IAM user:

* Within the AWS IAM console, select your IAM user
* Create a new set of access keys
* Save the access key and secret access key values for later

## 7Configure AWS Cloud9

Navigate to Cloud9 in a new tab:

* Open the `OurCloud9Environment` environment
* Clear any messages that popup
* Run `aws configure --profile cloud_user`
* Paste in your recently created access keys when prompted
* Use `us-east-1` as the default Region
* Use `json` as the default output
* If you get a popup about **AWS managed temporary credentials**, select `Cancel` and then `Re-enable after refresh`
* Test you can perform an AWS CLIv2 command (Example: `aws s3 ls --profile cloud_user`)

## 8Push Image to ECR Repo from Cloud9

Navigate back to the ECR tab:

* Click on `View push commands`
* Copy and paste **Step 1** into your Cloud9 terminal. Before entering, add the `--profile cloud_user` to the portion before the pipe! Example below:

```shell
aws ecr get-login-password --region us-east-1 --profile cloud_user | docker login --username AWS --password-stdin 111111111111.dkr.ecr.us-east-1.amazonaws.com
```

* Pull the latest Docker image for Wordpress running this command:

```shell
   docker pull wordpress:latest
```

* Once complete, tag the image to push by copying and pasting **Step 3** from the ECR push commands prompt
* Now run **Step 4** from the ECR push commands.
* Verify the image exists within the ECR console after completion

## 9Create the Amazon ECS Task Definition

Navigate to the Amazon ECS service in a new tab:

* Find **task definitions** and create a new one
* Enter `wordpress-td` for the _Task definition family_
* Under **Infrastructure requirements**, for _Launch type_, select `AWS Fargate`
* Select `OurEcsTaskRole` for the _Task role_
* For the _Task execution role_, find and select the role `OurEcsTaskExecutionRole`
* Under **Container - 1**, enter the following settings for _Container details_:

| Name      | Image URI                                                                                                                      | Essential container |
| --------- | ------------------------------------------------------------------------------------------------------------------------------ | ------------------- |
| wordpress | Your ECR Image URI from the custom image you pushed (Example: _294991935974.dkr.ecr.us-east-1.amazonaws.com/wordpress:latest_) | Yes                 |

* Click on `Add environment variable` and then fill in the information for each of the following **4** variables. **PLEASE NOTE THE ARN SYNTAX OF THE SECRETS MANAGER SECRET.**

| Key                     | Value type | Value                                                                                                                                                                                                                                                 |
| ----------------------- | ---------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| WORDPRESS\_DB\_HOST     | ValueFrom  | ARN of the respective Parameter Store parameter (Example: _arn:aws:ssm:us-east-1:552898056824:parameter/dev/WORDPRESS\_DB\_HOST_                                                                                                                      |
| WORDPRESS\_DB\_NAME     | ValueFrom  | ARN of the respective Parameter Store parameter (Example: _arn:aws:ssm:us-east-1:552898056824:parameter/dev/WORDPRESS\_DB\_NAME_                                                                                                                      |
| WORDPRESS\_DB\_USER     | ValueFrom  | ARN of the respective Secrets Manager RDS secret with `:username::` added to the end specifying the specific key value (Example: _arn:aws:secretsmanager:us-east-1:552898056824:secret:rds!db-1380b131-f0b2-4ef3-833f-4ab7a78f29fd-BAsjMA:username::_ |
| WORDPRESS\_DB\_PASSWORD | ValueFrom  | ARN of the respective Secrets Manager RDS secret with `:password::` added to the end specifying the specific key value (Example: _arn:aws:secretsmanager:us-east-1:552898056824:secret:rds!db-1380b131-f0b2-4ef3-833f-4ab7a78f29fd-BAsjMA:password::_ |

* Create the task definition

## 10Create the Amazon ECS Cluster

Within ECS, select `Clusters`:

* Create a new cluster
* For _Cluster name_ enter `Wordpress-Cluster`
* Under **Infrastructure**, select `AWS Fargate (serverless)`
* Once completed, move on to creating our service

> Wait for the cluster creation to complete before moving on. If you get any service related errors, please navigate to the CloudFormation template that is created for you by the service and retry the deployment.

Mark Complete11Create the Amazon ECS Service

Navigate to your recently created `Wordpress-Cluster`:

* Under the **Services** tab, click on `Create`
* For _Compute options_ select `Launch type`
* Make sure the _Launch type_ is set to `FARGATE`
* Make sure _Platform version_ is set to `LATEST`
* For _Application type_ select `Service`
* For _Family_, under _Task definition_, choose your `wordpress-td` task definition from the dropdown and use the `LATEST` version
* Name your service `wordpress-service`
* Set desired tasks to `1`
* For _Networking_, select `Your Custom VPC`
* For _Subnets_, only select the ones titled `Private Subnet`
* Create a new security group called `app-sg`
* For _Inbound rules for security groups_, enter the following information:

| Type | Protocl | Port range | Source       | Values                               |
| ---- | ------- | ---------- | ------------ | ------------------------------------ |
| HTTP | TCP     | 80         | Source group | Security Group of the `ALBAllowHttp` |

* Make sure _Public IP_ is turned **off**
* For _Load balancer type_, select `Application Load Balancer`
* Use an existing load balancer
* Find the load balancer named `OurApplicationLoadBalancer`
* Set the _Health check grace period_ to `30 seconds`
* Leave the _Listener_ values as default
* For _Target group_, name it `wordpress-tg`
* For the _Health check path_, enter `/wp-admin/images/wordpress-logo.svg`
* Create

Run Progress Check12Test the Application

* Navigate to the Amazon EC2 console
* Find and select the `OurApplicationLoadBalancer` ALB
* Copy the DNS name
* Paste the DNS name into a new tab using **HTTP**
* You should be greeted by the Wordpress setup page!
