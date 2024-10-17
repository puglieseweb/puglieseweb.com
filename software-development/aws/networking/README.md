# Networking

There are different networking devices:

* ENI
* EFA
* Enhanced Networking

| Feature                  | Elastic Network Interface (ENI)                     | Elastic Fabric Adapter (EFA)                      | Enhanced Networking                                    |
| ------------------------ | --------------------------------------------------- | ------------------------------------------------- | ------------------------------------------------------ |
| Primary Purpose          | Network connectivity for EC2 instances              | High Performance Computing (HPC) and ML workloads | Improved network performance                           |
| Performance              | Standard EC2 networking performance                 | Ultra-high performance, low latency               | High performance, low latency                          |
| Max Bandwidth            | Varies by instance type                             | Up to 100 Gbps                                    | Up to 100 Gbps (with ENA)                              |
| Latency                  | Standard                                            | Ultra-low                                         | Low                                                    |
| OS-bypass                | No                                                  | Yes                                               | No                                                     |
| Multiple IP Addresses    | Yes                                                 | No (uses single ENI)                              | No (feature of underlying ENI)                         |
| Attached to Instance     | Can be attached/detached                            | Permanent for instance lifetime                   | Built into instance networking                         |
| MPI Support              | No                                                  | Yes                                               | No                                                     |
| RDMA Support             | No                                                  | Yes                                               | No                                                     |
| Use Cases                | Network segmentation, multiple IPs, security groups | HPC, large-scale parallel processing, ML          | General high-performance networking needs              |
| Instance Type Support    | All EC2 instance types                              | Select HPC-optimized instances                    | Most current generation instances                      |
| Implementation           | Virtual network interface                           | Custom network interface                          | SR-IOV (ENA or Intel 82599 VF)                         |
| Additional Configuration | Manual attachment and IP configuration              | Requires specific enablement                      | Often enabled by default, may need driver installation |

AWS Network Adapters:

| Feature                  | Elastic Network Adapter (ENA)               | Intel 82599 VF                                 | Elastic Fabric Adapter (EFA)                         |
| ------------------------ | ------------------------------------------- | ---------------------------------------------- | ---------------------------------------------------- |
| Primary Use              | Enhanced Networking                         | Enhanced Networking                            | High Performance Computing (HPC)                     |
| Max Bandwidth            | Up to 100 Gbps                              | Up to 10 Gbps                                  | Up to 100 Gbps                                       |
| Latency                  | Low                                         | Low                                            | Ultra-low                                            |
| OS-bypass                | No                                          | No                                             | Yes                                                  |
| Supported Instance Types | Most current generation                     | Older generation (C3, C4, R3, I2, M4)          | Select HPC-optimized instances                       |
| MPI Support              | No                                          | No                                             | Yes                                                  |
| RDMA Support             | No                                          | No                                             | Yes                                                  |
| Libfabric Support        | No                                          | No                                             | Yes                                                  |
| Best For                 | General purpose high performance networking | Legacy instances requiring enhanced networking | HPC and ML applications requiring the lowest latency |
| Implementation           | SR-IOV                                      | SR-IOV                                         | Custom                                               |
| Default on New Instances | Yes (for supported types)                   | No                                             | No (requires specific enablement)                    |
