# CloudWatch

* System Metrics: This are metrics that you get out of the box. The more managed the service is, the more you get.
* Application Metrics: By installing the **CloudWatch agent** you get information from inside your EC2 instances
* Alarms: alerts when something goes wrong.

Default metrics are:

* CPU Utilisation&#x20;
* Network Throughput

Custom metrics (requiring CloudWatch agent) are:

* EC2 Memory Utilization&#x20;
* ESB Storage Capacity



### CloudWatch default metrics

EC2 (Think "CPU-DNS"):

* CPU: CPUUtilization (%)
* Disk: DiskRead/Write (Ops and Bytes)
* Network: NetworkIn/Out
* Status: StatusCheckFailed

EBS (Think "VRW"):

* Volume Read/Write (Bytes & Ops)
* VolumeQueueLength

RDS (Think "CPU-FD"):

* CPU Usage
* Free Memory
* DB Connections
* Free Storage

Load Balancer (Think "RHE"):

* Request Count
* Healthy/Unhealthy Hosts
* Error codes (4XX/5XX)

Lambda (Think "TED"):

* Throttles
* Errors
* Duration

Remember: Default monitoring is 5 min, detailed is 1 min (costs extra).



