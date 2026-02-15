# Cross-regions failover

Route 53 is better when:

* DNS-based failover is acceptable
* You need domain-based routing
* Cost is a primary concern
* Simple configuration is preferred
* Health checks can be slower (30+ seconds)

Global Accelerator is better when:

* You need fast failover (<30 seconds)
* Static IP addresses are required
* You want to optimize network path to applications
* TCP/UDP traffic optimization is important
* Client-IP preservation is needed
* Network latency is critical

For most regional failover scenarios, Route 53 is sufficient unless you specifically need Global Accelerator's features like fast failover or static IPs.





Key cross-regional failover mechanisms in AWS:

1. S3 Cross-Region Replication (CRR)

* Automatically replicates objects to backup region
* Asynchronous replication with versioning

2. Aurora Global Database

* Sub-second replication across regions
* Promotes secondary region in <1 minute
* Managed failover process

3. DynamoDB Global Tables

* Multi-master, multi-region replication
* Active-active configuration
* Sub-second replication

4. EC2 Auto Recovery

* Monitors instance health
* Automatically recovers to new hardware
* Preserves instance ID and metadata

5. Elastic Disaster Recovery (DRS)

* Continuous replication of servers
* Sub-second RPO
* Automated failover testing

6. RDS Cross-Region Read Replicas

* Asynchronous replication
* Manual promotion to master
* Read scaling in secondary region

7. CloudFront with Origin Failover

* Automatic failover to backup origin
* Based on origin health checks
* No DNS propagation delay
