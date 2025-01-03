# Business intelligence

Amazon QuickSight is Primary BI service in AWS:

* Key features:
  * Serverless architecture
  * Pay-per-session pricing
  * ML-powered insights (QuickSight Q for natural language queries)
  * Easy data source integration with AWS services
  * SPICE (Super-fast, Parallel, In-memory Calculation Engine)
  * Interactive dashboards and visualizations



### AWS Data Integration Tools for BI

* Amazon Redshift (Data Warehouse)
  * Columnar storage
  * Massive parallel processing
  * Integrates with most BI tools
  * Redshift Spectrum for query across data lake
* AWS Glue
  * ETL service
  * Data catalog
  * Job scheduling
  * Data preparation for BI



Additional AWS Services supporting BI:

* Amazon Athena (SQL queries on S3)
* AWS Lake Formation (data lake)
* Amazon OpenSearch (search and analytics)
* Amazon EMR (big data processing)



### Common Integration Patterns

```
Data Sources → ETL/Processing → Storage → Analysis → Visualization
(S3, RDS)    (Glue, Lambda)    (Redshift) (Athena)  (QuickSight)
```

Common AWS BI Architecture:

```
Raw Data → Glue ETL → Redshift → QuickSight
     ↳ S3 Data Lake ←→ Athena ↗
```

