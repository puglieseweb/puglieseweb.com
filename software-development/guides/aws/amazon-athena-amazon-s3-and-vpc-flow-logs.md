# Amazon Athena, Amazon S3, and VPC Flow Logs

During this hands-on project you will explore using Amazon Athena, Amazon S3, and VPC Flow Logs to deploy an easily searchable analytics platform using SQL-like queries.

Successfully complete this lab by achieving the following learning objectives.

1Create the Amazon S3 Bucket

* Create a new S3 bucket that is prefixed with `csaa-hol-`

2Create the VPC Flow Log and Generate Records

* Create a brand new VPC Flow Log for the entire VPC.
* Name the flow logs `vpc-to-s3`
* Set the filter to `All`
* Set a `1 minute` aggregation interval
* Configure the flow log to be sent to your new Amazon S3 bucket
* Use the AWS default format
* Use the `Parquet` log file format
* Enable `Hive-compatible S3 prefixes`
* Partition logs by every `1 hour`
* Browse to the DNS entry for `OurApplicationLoadBalancer` **using HTTP** and refresh a couple of times to generate traffic
* Wait a few minutes and refresh the S3 objects list until you start seeing objects generated before you move on

3Set up Amazon Athena

* Change your Athena settings to save query results to your new Amazon S3 bucket and append `/queries/` to the end of the bucket name (Example: \_s3://csaa-hol-213432inh32i4/queries/)
* Create a new database called `vpc_flow_logs_db` within the `AwsDataCatalog` data source. [SQL Query - Create Database](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/04-week-4/4.1%20-%20Sending%20VPC%20Flow%20Logs%20to%20S3%20and%20Parsing%20the%20Logs%20via%20Amazon%20Athena#create-sql-database)
* Within the new database, create a new table called `vpc_flow_logs`. [SQL Query - Create Table](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/04-week-4/4.1%20-%20Sending%20VPC%20Flow%20Logs%20to%20S3%20and%20Parsing%20the%20Logs%20via%20Amazon%20Athena#sql-create-table-command)
* After successful, update and repartition the table using MSCK. [SQL Query - Repair Table and Update Partitions](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/04-week-4/4.1%20-%20Sending%20VPC%20Flow%20Logs%20to%20S3%20and%20Parsing%20the%20Logs%20via%20Amazon%20Athena#repair-table-and-update-partitions)
* Run a SQL query of your choosing!
* If you receive a DDL error when attempting to run the **MSCK** command, execute the [Fixing Errors](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/04-week-4/4.1%20-%20Sending%20VPC%20Flow%20Logs%20to%20S3%20and%20Parsing%20the%20Logs%20via%20Amazon%20Athena#fixing-errors) steps and restart the Athena process.
