# Redshift

Readshift is a big data relational database.

Can contaion up to 16 PB of data.

Based on PostgressSQL&#x20;

Redshift is suitable for OLAP.

High Performance. Offers up to 10x the performance of other data warehouses.

Columnar storage. column-based instead of row based, allowing for efficient parallel queries.



* Support Multi-AZ (up to 2 AZs)
* can take Snapshots copy of the database ands store on S3. We do not have controll over the S3 bucket storage.
* To optimize Reshift insert performance alway favor large batches



### The 3V of Big Data

1. Volume (terabytes, petabytes of data)
2. Variety (structured data, unstructured data, semi-structured data)
3. Velocity (data needs to be collected, stored, and analysed)

* Redshift Spectrum. Efficiently query and retrieves data from S3 without having to load data on Redshift tables
* Enhanced VPC Routing. Allow COPY and UNLOAD traffic between your cluster and your data repositories if forced through your VPC
