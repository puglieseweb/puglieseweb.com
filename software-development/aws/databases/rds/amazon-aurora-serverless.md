# Amazon Aurora Serverless



&#x20;Aurora Serveless provides a relatively simple, c**ost-effective option for infrequent, intermittent, or unpredictable workloads.**

* An on-demand, auto-scaling configuration for MySQL and Postgres compatible edition
* Aurora Serverless DB cluster automatically stands up, shout down, and scales capacity up or down based on your application's needs.



Aurora Serverless is:

* On Demand and Auto Scaling service&#x20;
* Automation of monitoring workloads and adjusting capacity for databases.&#x20;
* Based on Demand: capacity adjusted based on application demands.&#x20;
* Billing: Charged only for resources consumed by DB clusters; per-second billing.
* Budged Friendly: helps customers to stay well within budgets with the Auto Scaling and per-second billing features.

### Aurora Serveless Concepts:

1. Aurora Capacity Units (ACUs): Measuraments on how your clusters scale
2. Set a minimum and maximum of ACUs for scaling requirements - can be zero.
3. Allocated (quickly) by AWS-managed warm pools
4. combination of about 2GiB of memory, matching CPU, and networking capability
5. Same data resiliancy as Aurora provisioned: six copies of data across three AZs
6. Multi-AZ deployments for establishing highly available clusters

### Use Cases&#x20;

* Variable Workloads
* Multi-Tenants Apps: Let the service mange database capacity for each individual app.
* New Apps: unsure what database istance needs are required.
* Dev and Test new features.
* Mixed-Use Apps. Application that serve more then one purpose with different traffic spikes.&#x20;
* Capacity Planning. Easily swap from provisioned to serverless or vice versa.

