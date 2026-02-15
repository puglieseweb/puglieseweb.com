# LAB: Loading Data into a Redshift Cluster

### Introduction

In this lab, you work as a Database Administrator and manage your company's Redshift cluster. The Development team has requested a way to import data into the Redshift cluster from either an S3 bucket or DynamoDB table. They have provided you with a small sample of the data to be imported from the S3 bucket or DynamoDB table into Redshift.

### Solution

Log in to the live AWS environment using the credentials provided. Use an incognito or private browser window to ensure you're using the lab account rather than your own.

Make sure you're in the N. Virginia (`us-east-1`) region throughout the lab.

Open a terminal session and log in to the provided EC2 instance via SSH using the credentials listed on the lab page:

```
ssh cloud_user@<PUBLIC_IP>
```

#### Configure AWS CLI

1. In the AWS console, click the **cloud\_user** username in the upper right corner.
2. Select **My Security Credentials**.
3. Under _Access keys for CLI, SDK, & API access_, click **Create access key**.
4. Save _Access Key_ and _Secret Access Key_ to a scratch pad or download the CSV file.
5.  In the terminal, configure the AWS CLI client:

    ```
    aws configure
    ```
6. Paste the **Access Key** into the first prompt.
7. In the second prompt, paste the **Secret Access Key**.
8. For _Default region name_, enter `us-east-1`.
9. For _Default output format_, press **Enter** to leave it as `None`.

#### Prepare the Source Data

1.  Download CSV-formatted data:

    ```
    curl -O -l https://raw.githubusercontent.com/linuxacademy/content-aws-database-specialty/master/S06_Additional%20Database%20Services/redshift-data.csv
    ```
2.  Download JSON-formatted data:

    ```
    curl -O -l https://raw.githubusercontent.com/linuxacademy/content-aws-database-specialty/master/S06_Additional%20Database%20Services/redshift-data.json
    ```
3.  Create the S3 bucket and give it a globally unique name (e.g., having today's date and the current time at the end):

    ```
    aws s3 mb s3://redshift-import-<DATE_AND_TIME>
    ```
4.  Load the data into the bucket, replacing `<BUCKET_NAME>` with your bucket name:

    ```
    aws s3api put-object --bucket <BUCKET_NAME> --key redshift-data.csv --body redshift-data.csv
    ```
5.  Confirm `redshift-data.csv` file is listed:

    ```
    aws s3 ls s3://<BUCKET_NAME>
    ```
6.  Create the DynamoDB table:

    ```
    aws dynamodb create-table --table-name redshift-import --attribute-definitions AttributeName=ID,AttributeType=N --key-schema AttributeName=ID,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
    ```
7.  Confirm DynamoDB table was created:

    ```
    aws dynamodb list-tables
    ```
8.  Import the JSON data into the DynamoDB table _(NOTE:`"UnprocessedItems": {}` will appear if successful.)_:

    ```
    aws dynamodb batch-write-item --request-items file://redshift-data.json
    ```
9.  Confirm it was imported:

    ```
    aws dynamodb scan --table-name redshift-import
    ```

#### Create IAM Role

1. In the AWS Management Console, navigate to **IAM**.
2. In the side menu, click **Roles**.
3. Click **Create role**.
4. Scroll down and select **EC2** as the service.
5. Click the first **EC2** use case.
6. Click **Next: Permissions**.
7. On the _Attach permissions policies_ page, in the _Filter policies_ box, search for and select each of the following managed policies:
   * **AmazonS3ReadOnlyAccess**
   * **AmazonDynamoDBReadOnlyAccess**
8. Click **Next**.
9. For _Role name_, enter "redshift-import".
10. Click **Create role**.
11. On the _IAM > Roles_ page, select the newly created **redshift-import** role.
12. On the _redshift-import Summary_ page, click the **Trust relationships** tab.
13. Click **Edit trust relationship**.
14. Change the `"Service"` line to:

    ```
    "Service": "redshift.amazonaws.com"
    ```
15. Click **Update Policy**.

#### Load the Data into the Redshift Cluster

1. Navigate to Redshift and access the dashboard.
2. Select our listed cluster.
3. Click **Actions** > **Manage IAM roles**.
4. In _Available IAM roles_, open the dropdown menu and select **redshift-import**.
5. Click **Associate IAM role**.
6. Click **Save changes**.
7.  In the terminal, list the Redshift clusters:

    ```
    aws redshift describe-clusters | head -25
    ```
8. In the `"Endpoint"` section, copy the listed `Address` within the quotation marks.
9.  Paste the endpoint name in the `PGHOST` environment variable:

    ```
    export PGHOST=<Redshift_Endpoint>
    ```
10. List the IAM roles:

    ```
    aws iam list-roles
    ```
11. Copy the value of the `"Arn"` for the `redshift-import` role, and paste it into a text file.
12. List the S3 buckets:

    ```
    aws s3 ls
    ```
13. Copy the bucket name and paste it into a text file, if you haven't already.
14. Echo `$PGHOST`:

    ```
    echo $PGHOST
    ```
15. Connect to the cluster:

    ```
    psql -U masteruser -p 5439 import-test
    ```
16. At the prompt, enter the password:

    ```
    MasterPasswd2020!
    ```
17. Create the table:

    ```
    create table users_import (ID int, Name varchar, Department varchar, ExpenseCode int);   
    ```
18. Navigate to **Amazon Redshift**, select your cluster, go under **Actions** and choose **Manage IAM Roles**. Select the **redshift-import** IAM role, click **Associate IAM role**, and then click **Save changes**.
19. Import data from the S3 bucket, replacing `<BUCKET_NAME>` with your bucket name and `<IAM_ROLE_ARN>` with the ARN you copied:

    ```
    copy users_import from 's3://<BUCKET_NAME>/redshift-data.csv' iam_role '<IAM_ROLE_ARN>' delimiter ',';
    ```
20. Query the table to verify rows were inserted:

    ```
    select * from users_import ;
    ```
21. Clear the `users_import` table:

    ```
    truncate users_import ;
    ```
22. Confirm table status:

    ```
    select * from users_import ;
    ```
23. Import the data from DynamoDB, replacing `<IAM_ROLE_ARN>` with the ARN you copied:

    ```
    copy users_import from 'dynamodb://redshift-import' iam_role '<IAM_ROLE_ARN>' readratio 50;
    ```
24. Query the table to ensure rows were inserted:

    ```
    select * from users_import ;
    ```
