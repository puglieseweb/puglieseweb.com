# AppFlow

* Thsi service is used to Ingest data from third-party SaaS vendors and stores them in Amazon S3
* Integration. Fully managed integration service for exchanging data between SaaS apps and AWS services
* Bi-Directional data transfer

### Concepts

* Flow. Transfer data between source and destination
* Data Mapping. Determinate how source data is stored within the destination
* Filters. Controls which data is transferred
* Triggers. How the flow is started. Supported types are:
  * Run on demand
  * Run on event
  * Run on schedule&#x20;

Example of use cases

1. Transfer Salesforse records to Amazon Redshift&#x20;
2. Ingesting and analyzing Slack conversations in S3
3. Migrating Zendesk and other help desk support tickets to Snowflake
4. Transferring aggregate data on a scheduled basis to S3 (Up to 100 GB per flow)
