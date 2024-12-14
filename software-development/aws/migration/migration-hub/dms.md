# DMS

The Database Migration Service (DMS) allows to migrate relational database, data warehouses, NoSQL databases, and other data stores.

*   ## AWS Database Migration Service (DMS)

    ### Overview

    AWS DMS enables migration of various database types including:

    * Relational databases
    * Data warehouses
    * NoSQL databases
    * Other data stores

    ### Key Features

    * Supports migrations both into and out of AWS
    * Offers one-time or continuous replication
    * Uses Schema Conversion Tool (SCT) for database schema translation
    * Allows migration between different database engines
    * Can work with databases on EC2 or data stored in S3

    ### How It Works

    * Migration tasks are scheduled to run on the DMS server
    * Source and target data stores are called endpoints
      * At least one endpoint must be in AWS
    * DMS can automatically create target tables and primary keys if needed
      * Alternatively, you can create target tables in advance

    ### Migration Types

    1. **Full Load**
       * Moves all existing data from source to target in parallel
    2. **Full Load + CDC (Change Data Capture)**
       * Performs full load migration
       * Captures changes to source tables during migration
    3. **CDC Only**
       * Only captures and applies ongoing changes
       * Ensures transactional integrity of the target database

    ### Large-Scale Migration

    For large data stores, AWS DMS can be used in conjunction with AWS Snowball and CDC for efficient migration.

<figure><img src="../../../../.gitbook/assets/image (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

