# Aurora DB

### Core Characteristics

* Fully managed RDS service supporting MySQL and PostgreSQL databases
* Built-in multi-AZ availability by design
* Automatic storage scaling managed by AWS
* Easy multi-region replication capabilities

### Architecture and High Availability

<figure><img src="../../../../.gitbook/assets/image (2) (1) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

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

<figure><img src="../../../../.gitbook/assets/image (3) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

### Global Databases

<figure><img src="../../../../.gitbook/assets/image (4) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Configuration

* Single primary region with up to 5 secondary regions
* Storage-level replication for minimal latency
* Cross-region data typically available in under 1 second
* Secondary regions can be promoted during outages

### Aurora Serverless

<figure><img src="../../../../.gitbook/assets/image (5) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

#### Key Features

* Automatic instance scaling for variable workloads
* Measured in Aurora Capacity Units (ACUs)
* 1 ACU ≈ 2GB memory + proportional CPU and networking
* Scaling range: 0.5 to 128 ACUs
* Stepwise scale-down approach similar to EC2 auto-scaling

<figure><img src="../../../../.gitbook/assets/image (7) (1) (1) (1) (1).png" alt=""><figcaption></figcaption></figure>

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
