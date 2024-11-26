# Redshift

### Amazon Redshift Overview

* A cloud-based, fully managed data warehouse solution
* Built on modified PostgreSQL architecture
* Designed for OLAP (Online Analytical Processing) workloads
* Can store up to 16 PB of data

### Key Features

1. High Performance
   * Delivers up to 10x performance compared to traditional data warehouses
   * Uses columnar storage (column-based vs row-based)
   * Enables efficient parallel query execution
2. Durability & Availability
   * Supports Multi-AZ deployment across 2 Availability Zones
   * Automated snapshots stored in S3 (S3 bucket management handled by AWS)
3. Data Processing
   * Best practice: Use large batch inserts for optimal performance
   * Redshift Spectrum allows direct querying of S3 data without loading into Redshift tables
   * Enhanced VPC Routing forces COPY and UNLOAD operations through your VPC

##

