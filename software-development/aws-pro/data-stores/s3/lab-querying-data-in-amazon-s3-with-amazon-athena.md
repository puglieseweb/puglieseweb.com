# Lab: Querying Data in Amazon S3 with Amazon Athena

### Introduction

Welcome to this hands-on AWS lab, where we'll query data stored in Amazon S3 with SQL queries in Amazon Athena. Let's get started!

### Solution

Log in to the AWS Management Console using the credentials provided for the lab. Make sure you're in the `us-east-1` (N. Virginia) Region.

#### Create a Table from S3 Bucket Metadata

**Review the S3 Bucket Resources**

1.  Navigate to S3 using the **Services** menu or the unified search bar.

    You should see the S3 bucket that was provided for the lab. This bucket has been partitioned by year, month, and day.
2. Select the bucket name.
3. Open the `year=2018/` folder, then open the `month=11/` folder.
4.  Open one of the `day=X/` folders.

    You should see a `.parquet` data file representing your column-oriented data structure.

**Configure Settings to Send Query Results to the S3 Bucket**

1. Navigate to Amazon Athena using the **Services** menu or the unified search bar.
2. Click **Launch query editor**.
3. From the menu on the left of the screen, select **Workgroups**.
4. Select the **primary** workgroup.
5. Click on **Edit**.
6. Scroll down and expand _Query result configuration (optional)_.
7. Click on **Browse S3**.
8. Check the radio button next to your S3 bucket name.
9. Click on **Choose**.
10. Scroll down to the bottom of the page and click on the **Save changes** button.
11. From the menu on the left, select **Query editor**.
12. Select the **Settings** tab, then click **Manage**.
13. On the right, click **Browse S3**.

    You should see the S3 bucket that was provided for the lab.
14. Select the S3 bucket's radio button, then click **Choose**.

    The S3 bucket is auto-populated in the **Location of query result** setting.
15. Enable the **Assign bucket owner full control over query results** option and click **Save**.
16. Select the **Editor** tab.
17. In the sidebar menu, select **Data sources**.

    You should see the AWS Glue Data Catalog provided for the lab.
18. Click **Create data source**.
19. Ensure **S3 - AWS Glue Data Catalog** is selected as the data source, then click **Next**.
20. Fill in the **AWS Glue Data Catalog** section:
    * **Choose an AWS Glue Data Catalog**: AWS Glue Data Catalog in this account
    * **Choose a way to create a table**: Create a table manually
21. Click **Create table**.

**Create a Table from the S3 Bucket Data**

1. In the **Table name** field, enter **cf\_access\_optimized**.
2. Fill in the **Database configuration** section:
   * **Choose an existing database or create a new database**: Create a database
   * **Database name**: aws\_service\_logs
3. Fill in the **Dataset** section:
   * Click **Browse S3**.
   * Select the S3 bucket's radio button, then click **Choose**.
4. In the **Data format** section, under _Table type_ make sure **Apache Hive** is selected.
5. For _File format_ click the dropdown and select **Apache Parquet**.
6. Fill in the **Column details** section:
   * Click **Bulk add columns**.
   *   In the **Bulk add columns** pop-up, add the following data:

       ```
       time timestamp, location string, bytes bigint, requestip string, method string, host string, uri string, status int, referrer string, useragent string, querystring string, cookie string, resulttype string, requestid string, hostheader string, requestprotocol string, requestbytes bigint, timetaken double, xforwardedfor string, sslprotocol string, sslcipher string, responseresulttype string, httpversion string
       ```
   *   Click **Add**.

       The column names and types are auto-populated.
7. Fill in the **Partition details** section:
   * Click **Add column** twice so you can create three partitions.
   * Create a **year** partition:
     * **Column name**: year
     * **Column type**: string
   * Create a **month** partition:
     * **Column name**: month
     * **Column type**: string
   * Create a **day** partition:
     * **Column name**: day
     * **Column type**: string
8. In the **Preview table query** section, review your query.
9. Click **Create table**.

#### Add Partition Metadata

1. From the Athena query editor, click the plus icon on the right to open a new query tab.
2.  In the editor, load the partition data with the following query:

    ```
    MSCK REPAIR TABLE aws_service_logs.cf_access_optimized
    ```
3. Click **Run**.
4. Click the plus icon on the right to open another new query tab.
5.  In the editor, verify the number of rows created from your data with the following query:

    ```
    SELECT count(*) AS rowcount FROM aws_service_logs.cf_access_optimized
    ```
6.  Click **Run**.

    Note that the `rowcount` equals `207535`, which is the expected number of rows in your table.
7. Click the plus icon on the right to open another new query tab.
8.  In the editor, verify the partitions were created with the following query:

    ```
    SELECT * FROM aws_service_logs.cf_access_optimized order by time desc LIMIT 10
    ```
9. Click **Run**.

#### Query the Total Bytes Served in a Date Range

1. From the Athena query editor, click the plus icon on the right to open a new query tab.
2.  In the editor, view the data timestamped between 11-02-2018 and 11-03-2018 with the following query:

    ```
    SELECT * FROM aws_service_logs.cf_access_optimized WHERE time BETWEEN TIMESTAMP '2018-11-02' AND TIMESTAMP '2018-11-03'
    ```
3.  Click **Run**.

    Note the `bytes` column. This information is useful, but doesn't give you the total bytes served.
4. Above the query results, click **Clear** to clear the current query.
5.  In the editor, view the total bytes served with the following query:

    ```
    SELECT SUM(bytes) AS total_bytes
    FROM aws_service_logs.cf_access_optimized
    WHERE time BETWEEN TIMESTAMP '2018-11-02' AND TIMESTAMP '2018-11-03'
    ```
6.  Click **Run**.

    Note that the `total_bytes` equals `87310409`. You can use this information to figure out which parts of your website can be optimized to lower your CloudFront bill.
