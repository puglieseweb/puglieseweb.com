# EFS (Elastic File System)

EFS (Elastic File System): When you need a **distributed, highly resilient storage** for Linux instances and Linux-based applications. EFS is Managed NAS (Network Attached Storage) solution.

* Supports the Network File System version 4 (NFSv4) protocol&#x20;
* Only Pay for the storage you use (no pre-provisioning required)
* Can scale up to petabytes
* Can support thousands of concurrent NFS connections
* Data is stored across multiple AZs within a region
* Read-after-write consistency

## EFS throughput modes

Choose Provisioned when you need consistent performance above baseline or can't rely on burst credits. Choose Bursting for variable workloads where occasional performance spikes are acceptable.

### Bursting Throughput

* Baseline performance of 50 KB/s per GB of storage
* Accumulates burst credits when not using full baseline
* Can burst up to 100 MB/s when needed using credits
* Better for variable workloads with periodic spikes
* More cost-effective for inconsistent usage patterns

### Provisioned Throughput

* **Allows you to specify required throughput regardless of storage size**
* Charged for provisioned amount whether used or not
* Can be increased/decreased as needed
* Measured in MiB/s
* Ideal for applications requiring consistent, predictable performance
* Better for high-performance workloads that need guaranteed throughput

## Example

A 100 GB file system with bursting mode would have:

* Baseline: 5 MB/s (100 GB × 50 KB/s)
* Burst: Up to 100 MB/s using burst credits

## EFS Types

* Standard offers highest availability across multiple AZs
* One Zone reduces costs but trades off availability
* IA options best for infrequently accessed data with minimum 30-day storage
* Throughput modes (Bursting/Provisioned) available across all classes

| Feature                  | Standard Storage Class                                                        | One Zone Storage Class                                                        | Infrequent Access (IA)                                      | Standard-IA                                                      | One Zone-IA                                                        |
| ------------------------ | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------- | ---------------------------------------------------------------- | ------------------------------------------------------------------ |
| Availability             | 99.999999999% (11 9's)                                                        | 99.999999999% (11 9's)                                                        | Based on storage class                                      | 99.999999999%                                                    | 99.9%                                                              |
| Durability               | 99.999999999% (11 9's)                                                        | 99.9%                                                                         | Same as primary storage class                               | 99.999999999%                                                    | 99.9%                                                              |
| AZ Replication           | Multiple AZ                                                                   | Single AZ                                                                     | Based on storage class                                      | Multiple AZ                                                      | Single AZ                                                          |
| Performance Modes        | General Purpose and Max I/O                                                   | General Purpose only                                                          | Based on storage class                                      | General Purpose                                                  | General Purpose                                                    |
| Throughput Modes         | Bursting and Provisioned                                                      | Bursting and Provisioned                                                      | Based on storage class                                      | Bursting and Provisioned                                         | Bursting and Provisioned                                           |
| Minimum Storage Duration | None                                                                          | None                                                                          | 30 days                                                     | 30 days                                                          | 30 days                                                            |
| Cost                     | Higher                                                                        | \~47% lower than Standard                                                     | Lower for infrequent access                                 | Lower than Standard                                              | Lowest                                                             |
| Use Cases                | <p>- Production workloads<br>- Critical data<br>- High availability needs</p> | <p>- Dev/test environments<br>- Data backup<br>- Cost-sensitive workloads</p> | <p>- Infrequently accessed files<br>- Long-term storage</p> | <p>- Infrequently accessed files<br>- Need high availability</p> | <p>- Infrequently accessed files<br>- Cost-sensitive workloads</p> |
| Access Patterns          | Frequent access                                                               | Frequent access                                                               | Infrequent access                                           | Infrequent access                                                | Infrequent access                                                  |
| Retrieval Fee            | No                                                                            | No                                                                            | Yes                                                         | Yes                                                              | Yes                                                                |
| Data Transfer AZ         | Free                                                                          | Free                                                                          | Based on storage class                                      | Charged                                                          | Charged                                                            |
