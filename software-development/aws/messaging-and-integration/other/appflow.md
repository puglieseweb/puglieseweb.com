# AppFlow

Amazon AppFlow service ingests data from third-party SaaS vendors and stores it in Amazon S3.

### Key Features

* Fully managed integration service for exchanging data between SaaS applications and AWS services
* Bi-directional data transfer capabilities

### Core Concepts

#### Flow

* Manages data transfer between source and destination

#### Data Mapping

* Determines how source data is stored within the destination

#### Filters

* Controls which data is transferred

#### Triggers

* Determines how the flow is initiated
* Supported trigger types:
  * Run on demand
  * Run on event
  * Run on schedule

### Common Use Cases

1. Transfer Salesforce records to Amazon Redshift
2. Ingest and analyze Slack conversations in S3
3. Migrate Zendesk and other help desk support tickets to Snowflake
4. Transfer aggregate data to S3 on a scheduled basis (up to 100 GB per flow)
