# DMS

The Database Migration Service (DMS) allows to migrate relational database, data warehouses, NoSQL databases, and other data stores.

* the migration can either be in or out of AWS.
* one-time or continuously ongoing replication.&#x20;
* &#x20;SCT (schema Covertion Tool) to translate database schemas

You schedule tasks to run on the DMS server to move data.

Creates the tables and primary keys if they do not exist on the target, or you can create the target tables beforehand.&#x20;

Source and target data stores are referred to as endpoints. One endpoints must reside in AWS.

You can migrate between different database engines.&#x20;

You can even use the converted schemas with databases running on EC2 or data stored in S3.



Three different migrations types:

1. Full Load migration. All existing data is moved from sources to targets in parallel
2. Full Load and CDC to captures changes to the source tables during migration.
3. CDC only.&#x20;

**CDC guarantees transactional integrety of the target database**



## **Migrate Large Data Stores with AWS Snowball with DMF CDC**

<figure><img src="../../../../.gitbook/assets/image (1).png" alt=""><figcaption></figcaption></figure>

