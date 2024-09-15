# Data Pipeline

AWS Data Pipeline is a managed ETL service for automating movement and transformation of data.

This service is designed for orchestrating and automating the movement and transformation of data. Let me break down its core concepts for you.

1. Pipeline Definition:
   * The core of Data Pipeline is the pipeline definition.
   * It's a JSON format file that describes the data management workflow.
   * Specifies data sources, destinations, and the processing steps in between.
2. Activities:
   * These are the actual work performed in your pipeline.
   * Examples include copying data, running EMR jobs, or executing custom scripts.
   * Can be chained together to create complex workflows.
3. Data Nodes:
   * Represent the locations where your input data comes from and where your output data goes.
   * Can be Amazon S3 locations, RDS databases, Redshift clusters, or DynamoDB tables.
4. Preconditions:
   * Checks that must pass before an activity can start.
   * Ensures dependencies are met before proceeding with tasks.
5. Schedules:
   * Define when and how often your pipeline activities should run.
   * Can be set up for one-time execution or recurring schedules.
6. Resources:
   * The computational resources used to perform activities.
   * Could be EC2 instances, EMR clusters, or your own on-premises resources.
7. Tasks:
   * The combination of an activity, schedule, and resource.
   * Represents a unit of work in your pipeline.
8. Instances:
   * When a pipeline runs, it creates task instances for each task defined.
   * These are the actual executions of your tasks.
9. Component Objects:
   * Reusable definitions that can be referenced in multiple pipelines.
   * Helps in maintaining consistency across different pipelines.
10. Pipeline Fields:
    * Special parameters that can be used in your pipeline definition.
    * Examples include @scheduledStartTime and @pipelineId.
11. Data Pipeline Console:
    * Web-based interface for creating, managing, and monitoring pipelines.
    * Provides visual tools for pipeline creation and management.
12. Data Pipeline API:
    * Allows programmatic interaction with Data Pipeline.
    * Useful for automating pipeline management or integrating with other systems.

Amazon Data Pipeline is particularly useful for scenarios like:

* Regularly moving data from on-premises systems to AWS
* Processing log files on a schedule
* Loading data into Amazon Redshift for analysis
* Orchestrating complex ETL workflows across multiple AWS services

It's worth noting that while Amazon Data Pipeline is still supported, AWS has been putting more emphasis on newer services like AWS Glue for ETL workloads. However, Data Pipeline remains useful for certain complex workflow scenarios, especially those involving on-premises data sources.

Activities are pipeline components that define the work to perform.



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

### Use Cases

1. Processing data in EMR using Hadoop streaming
2. Importing or exporting DynamoDB data
3. copying CSV files or data between S3 buckets&#x20;
4. Exporting RDS data to S3
5. Copying data to Redshift
