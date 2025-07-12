# Aurora DB

Aurora decouple compute from storage

<figure><img src="../../../../.gitbook/assets/image (140).png" alt=""><figcaption></figcaption></figure>

Aurora Storage:

* Storage layer is ALWAYS replicated across 3 AZs
* This is automatic and happens regardless of how many DB instances you have
* Data is replicated 6 ways (2 copies per AZ)

Aurora DB Instances:

* Can be deployed in 1, 2, or 3 AZs
* Minimum recommended for high availability is 2 AZs
  * 1 primary instance in one AZ
  * At least 1 replica in another AZ
* You can choose to deploy instances in 3 AZs for additional availability

### Core Characteristics

Fully managed RDS service supporting MySQL and PostgreSQL databases

* Built-in multi-AZ availability by design
* Automatic storage scaling managed by AWS
* Easy multi-region replication capabilities

### Architecture and High Availability

<figure><img src="../../../../.gitbook/assets/image (2) (1) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Instance Configuration

* Primary instance + up to 15 read replicas
* Instances handle compute and networking
* Data stores distributed across availability zones
* Independent scaling of storage from instances

#### Data Management

* Primary instance handles all write traffic
* Data replication typically available within 100ms
* Both primary and read replicas can handle read traffic
* Automatic failover handling without endpoint changes

#### Endpoints

* Cluster endpoint: Routes to primary instance for writes
* Reader endpoint: Routes through ELB to distribute read traffic
* Optional custom instance and group endpoints available

<figure><img src="../../../../.gitbook/assets/image (3) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Global Databases

<figure><img src="../../../../.gitbook/assets/image (4) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Configuration

* Single primary region with up to 5 secondary regions
* Storage-level replication for minimal latency
* Cross-region data typically available in under 1 second
* Secondary regions can be promoted during outages

### Aurora Serverless

<figure><img src="../../../../.gitbook/assets/image (5) (1) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Key Features

* Automatic instance scaling for variable workloads
* Measured in Aurora Capacity Units (ACUs)
* **1 ACU ≈ 2GB memory + proportional CPU and networking**
* Scaling range: 0.5 to 128 ACUs
* **Storage automatically scales from 10 GiB to 128 TiB**
* **Storage scaling is independent of ACU scaling**
* Stepwise scale-down approach similar to EC2 auto-scaling

<figure><img src="../../../../.gitbook/assets/image (7) (1) (1) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Use Cases

* MySQL and PostgreSQL workloads requiring high availability
* Applications needing resilient database infrastructure
* Variable/spiky workload patterns (using Serverless)
* Scenarios requiring minimal operational overhead
* Read-heavy applications benefiting from replica distribution

### Performance Characteristics

* Fast replication (sub-100ms within region)
* Sub-second replication for global databases
* Elastic scaling for both storage and compute
* Distributed read workload capabilities
