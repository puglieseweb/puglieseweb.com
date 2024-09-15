# Data Pipeline

AWS Data Pipeline is a managed ETL service for automating movement and transformation of data.



Web service that allows:

* Define data-driven workflows
* Define your parameters for data transformations
* HA and fault tolerant
* Automatically retries failed activities.&#x20;
* Configure notification via SNS
* Integrate with Amazon DynamoDB, RDS, Redshift, S3.
* Works with Amazon EC2 and Amazon EMR for compute needs.

### Components

* Pipeline Definition: Specify the business logic of your data management needs.
* Managed Compute: Service will create EC2 instances to perform your activities - or leverage existing EC2.&#x20;
* Task Runners. Task runners (EC2) poll for different tasks and perofrm them when found&#x20;
* Data Nodes. Define the locations and types of data that will be input and output.
