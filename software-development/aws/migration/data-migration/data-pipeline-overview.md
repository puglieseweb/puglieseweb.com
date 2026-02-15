# Data Pipeline

common data flow:

1. Operational Database (Source) ↓
2. ETL/ELT (Extract, Transform, Load) ↓
3. Data Warehouse and/or Data Lake (Storage/Processing) ↓
4. BI Tools (Analysis/Visualization)

Key points:

* ETL/ELT happens before data reaches the Data Lake
* Data Lake is typically a destination, not an intermediate step
* You generally don't do ETL after the Data Lake phase

Common patterns:

1. ETL → Data Warehouse → BI Tools
2. ELT → Data Lake → BI Tools
3. ELT → Data Lake → Data Warehouse → BI Tools

The choice between ETL and ELT depends on your needs:

* ETL: Transform before loading (traditional approach)
* ELT: Load raw data first, transform later (modern approach, common with Data Lakes)
