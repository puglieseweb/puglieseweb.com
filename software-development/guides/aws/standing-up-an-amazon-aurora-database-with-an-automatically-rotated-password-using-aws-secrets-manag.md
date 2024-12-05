# Standing Up an Amazon Aurora Database with an Automatically Rotated Password Using AWS Secrets Manag

**Learning Objectives**

Successfully complete this lab by achieving the following learning objectives.

## Verify MySQL Client Installation

* Connect to the `DatabaseClient` EC2 instance using Session Manager
* Verify the MySQL client is installed:

```shell
sudo yum list installed | grep mariadb
```

* If not installed, install it:

```shell
sudo yum install -y mysql
```

## Create MySQL-compatible Amazon Aurora Database

* Create a new **Amazon Aurora** MySQL-compatible database
* Use the standard create option
* Leave the engine version as the default
* Select a `Dev/Test` template
* Use `employees-database` as the DB cluster identifier
* Use `admin` as the master username
* Enable using **AWS Secrets Manager** to manage the master credentials
* Use the default KMS key for encryption of the password (_aws/secretsmanager_)
* Use an Aurora Standard configuration option
* Select a `db.t3.medium` burstable class instance type
* Do **not** create an Aurora replica
* Enable connectivity to `Connect to an EC2 compute resource`
* Select the `DatabaseClient` EC2 instance
* Create a new VPC Security Group called `database-sg`
* Create an initial database named `employees_db`

## Connect to the Aurora Database and Import Data

* Find and select your RDS credentials secret from the **AWS Secrets Manager** service (Example: _rds!cluster-a5fd421e-64e5-4580-be8e-006919539758_)
* Refresh the page to ensure you are using the latest version of the secret. RDS will run an initial rotation once the RDS instance is created!
* Retrieve the secret value from the secret and keep the tab open
* Go back to your `DatabaseClient` EC2 connection
* Create a new local file called `employees.txt` within the **/home/ssm-user/** directory that contains the soon to be imported data using this code block from the [GitHub repo](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/03-week-3/3.4%20-%20Standing%20Up%20an%20Amazon%20Aurora%20Database%20with%20Automatic%20Password%20Rotatation%20via%20AWS%20Secrets%20Manager#create-employeestxt-file)
* Change the directory to the ssm-user home and `cat` the file to ensure the data was written.
* Confirm the status of the Amazon Aurora database writer endpoint is _Available_ and copy the endpoint name.
* In Session Manager, connect to your RDS instance writer endpoint using the below command. Make sure you change the value for host to the writer endpoint name. The user and password are your Secrets Manager secret that you set in previous steps.

```shell
mysql --host=CHANGE_TO_WRITER_ENDPOINT_NAME --user=admin --password employees_db
```

* Once prompted for the password, use the value retrieved from **AWS Secrets Manager**. (If you get an error, refresh the page, copy the latest secret value, rerun the command, and enter the new value for the password prompt).
* You should now see the MySQL prompt!
* Create a new table called `Employees` for later use: [GitHub Repo - Create Table](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/03-week-3/3.4%20-%20Standing%20Up%20an%20Amazon%20Aurora%20Database%20with%20Automatic%20Password%20Rotatation%20via%20AWS%20Secrets%20Manager#creating-employees-table)
* Load the data using the following SQL query: [GitHub Repo - Load Data](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/tree/main/bootcamp-hands-on-labs/03-week-3/3.4%20-%20Standing%20Up%20an%20Amazon%20Aurora%20Database%20with%20Automatic%20Password%20Rotatation%20via%20AWS%20Secrets%20Manager#importingloading-employeestxt-file-data)
* Verify the data was imported by viewing data in the table:

```sql
SELECT *
FROM Employees;
```

## Trigger Credential Rotation

* Quit your RDS connection within the EC2 Session Manager terminal and go back to the **ssm-user** home directory
* Within the **AWS Secrets Manager** service (_console or API/CLI_), trigger an immediate rotation of the RDS credential secret
* Ensure the version status has changed before moving on

## Test Connection with New Credentials

* After the rotation is completed in **AWS Secrets Manager** navigate back to your EC2 Session Manager connection
* Attempt to connect to RDS using the same credentials from before (_This should fail now_)
* Within **AWS Secrets Manager**, retrieve the update secret value for the RDS credentials
* Re-connect to RDS using the new password value for your Master Username
