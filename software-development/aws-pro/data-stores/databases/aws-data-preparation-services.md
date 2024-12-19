# AWS Data Preparation Services

<figure><img src="../../../../.gitbook/assets/image (2) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

<figure><img src="../../../../.gitbook/assets/image (57).png" alt=""><figcaption></figcaption></figure>

### Data Ecosystem Components

* Data Sources
  * S3
  * Data lakes
  * Redshift
  * Operational databases (cloud/on-premises)
* Analysis Tools
  * QuickSight (Business Intelligence)
  * SageMaker (Machine Learning)

### ETL (Extract, Transform, Load) Process

1. Extract
   * Select relevant subset of data
   * Focus on data needed for specific insights
2. Transform
   * Clean data
   * Remove duplicates
   * Format correction
   * Combine data sources
   * Sort data
3. Load
   * Transfer to target analysis platform
   * Prepare for consumption by applications

### AWS Glue

#### Core Features

* Serverless ETL service
* Automated scaling for processing and storage
* Data quality monitoring and rules
* Scheduled or event-driven jobs

<figure><img src="../../../../.gitbook/assets/image (59).png" alt=""><figcaption></figcaption></figure>

#### Components

1. Glue Crawlers
   * Discover data across multiple sources
   * Collect metadata
   * Support for S3, Redshift, RDS, EC2 databases
2. Glue Data Catalog
   * Stores metadata about data locations
   * Integration with:
     * EMR
     * Redshift
     * Athena
3. Glue ETL Jobs
   * Transform data based on catalogs
   * Load to various destinations:
     * Lake Formation
     * Redshift
     * S3
     * CloudWatch
4. Glue Data Quality
   * Automated quality recommendations
   * Predefined rule sets
   * Quality metrics and alerts
   * Threshold monitoring

<figure><img src="../../../../.gitbook/assets/image (60).png" alt=""><figcaption></figcaption></figure>

### Amazon Athena

#### Key Characteristics

* Serverless query service
* Petabyte-scale analysis capability
* Query data where it resides
* SQL query support
* Apache Spark integration

<figure><img src="../../../../.gitbook/assets/image (61).png" alt=""><figcaption></figcaption></figure>

#### Features

* Direct S3 querying
* Standard SQL syntax
* Federated queries across 25+ data sources
* Integration with:
  * QuickSight
  * SageMaker
  * Lake Formation
  * Operational databases

### Key Exam Points

1. Most data ecosystems require ETL, though AWS is working toward ETL-free solutions
2. Glue provides end-to-end ETL capabilities in a serverless environment
3. Athena specializes in petabyte-scale extraction and SQL querying of S3 objects
4. Both services integrate with broader AWS analytics ecosystem
