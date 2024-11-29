# Networking

There are two types of VPC Endpoints:

* Interface Endpoint (ENI)&#x20;
* Gateway Endpoing (used for S3 and Dyname DB)&#x20;

<figure><img src="../../../.gitbook/assets/image (35).png" alt=""><figcaption></figcaption></figure>



There are three types of Network Interface Types in AWS:

1. Elastic Network Interface (**ENI**):

* Basic networking interface that serves as a virtual network card
* Real-world example: A financial services company running both customer-facing applications and internal audit systems on the same EC2 instance
  * Primary ENI handles customer transactions on the production network
  * Secondary ENI connects to a separate, isolated network for audit logging and compliance monitoring
  * This separation ensures audit logs cannot be tampered with via the production network
* Another example: Multi-homed web application
  * Primary ENI for public internet traffic
  * Secondary ENI connected to internal services like databases
  * Enables security through network segregation without need for additional instances

2. Elastic Fabric Adapter (**EFA**):

* Specialized network interface for high-performance computing (HPC) workloads
* Real-world example: Pharmaceutical company running molecular dynamics simulations
  * Uses EFA-enabled instances in a cluster to simulate protein folding
  * Achieves near-bare-metal performance with OS-bypass capability
  * Reduces simulation time from days to hours
* Another example: Financial risk analysis
  * Investment bank running Monte Carlo simulations across a cluster
  * EFA enables low-latency communication between nodes
  * Processes millions of scenarios in real-time for risk calculations

3. **Enhanced Networking**:

* High-performance networking using SR-IOV (Single Root I/O Virtualization)
* Real-world example: Video streaming service
  * Uses enhanced networking with throughput between 10 Gbps and 100 Gbps
  * Handles thousands of concurrent video streams
  * Provides consistent low-latency delivery to end users
* Another example: Real-time gaming platform
  * Leverages enhanced networking for reliable, high-throughput game server instances
  * Supports thousands of concurrent players with minimal latency
  * Uses either Intel 82599 VF or Elastic Network Adapter (ENA) depending on instance type



```mermaid
graph TB
    subgraph VPC["VPC - 10.0.0.0/16"]
        subgraph PublicSubnet["Public Subnet - 10.0.1.0/24"]
            subgraph web["Web Server EC2 - r5.xlarge"]
                sriov1["SR-IOV NIC<br/>Enhanced Networking"]
                vf1["Virtual Function 1"]
                vf2["Virtual Function 2"]
                sriov1 --- vf1
                sriov1 --- vf2
            end
            public_rt["Public Route Table<br/>0.0.0.0/0 -> IGW<br/>10.0.0.0/16 -> local"]
            public_nacl["Public NACL<br/>Inbound: Allow 80,443<br/>Allow 1024-65535 from 10.0.2.0/24<br/>Allow 1024-65535 from 10.0.3.0/24"]
            web_eni1["Primary ENI<br/>Public IP"]
            web_eni2["Secondary ENI<br/>10.0.1.11"]
            vf1 --> web_eni1
            vf2 --> web_eni2
        end
        
        subgraph PrivateSubnet["Private Subnet - 10.0.2.0/24"]
            subgraph app["App Server EC2 - c5n.4xlarge"]
                sriov2["SR-IOV NIC<br/>Enhanced Networking"]
                vf3["Virtual Function"]
                sriov2 --- vf3
            end
            private_rt["Private Route Table<br/>10.0.0.0/16 -> local"]
            private_nacl["Private NACL<br/>Inbound: Allow from 10.0.1.0/24<br/>Allow from 10.0.3.0/24<br/>Outbound: Allow to VPC"]
            app_eni["ENI<br/>10.0.2.10"]
            vf3 --> app_eni
        end

        subgraph HPCSubnet["HPC Subnet - 10.0.3.0/24"]
            hpc_rt["HPC Route Table<br/>10.0.0.0/16 -> local"]
            hpc_nacl["HPC NACL<br/>Inbound: Allow from 10.0.2.0/24<br/>Allow EFA traffic<br/>Outbound: Allow to VPC"]
            hpc1["HPC Node 1<br/>10.0.3.10"]
            hpc2["HPC Node 2<br/>10.0.3.11"]
            efa1["EFA-enabled ENI<br/>10.0.3.10"]
            efa2["EFA-enabled ENI<br/>10.0.3.11"]
            fabric["AWS EFA Networking Fabric"]
        end

        %% Internet Gateway
        igw["Internet Gateway"]
        
        %% Network Connections
        igw --> public_rt
        public_rt --> public_nacl
        public_nacl --> web_eni1
        public_nacl --> web_eni2
        
        %% Public to Private connectivity
        web_eni2 -- "Request traffic<br/>Src: 10.0.1.11<br/>Dst: 10.0.2.10" --> private_nacl
        private_nacl --> app_eni
        
        %% Private to Public return traffic
        app_eni -- "Response traffic<br/>Src: 10.0.2.10<br/>Dst: 10.0.1.11" --> private_nacl
        private_nacl --> public_nacl
        
        %% Private to HPC connectivity
        app_eni -- "HPC Job Request<br/>Src: 10.0.2.10" --> hpc_nacl
        hpc_nacl --> efa1
        hpc_nacl --> efa2
        efa1 --> hpc1
        efa2 --> hpc2
        
        %% EFA connectivity
        efa1 --> fabric
        efa2 --> fabric
        
        classDef subnet fill:#e6f3ff,stroke:#333,stroke-width:2px
        classDef instance fill:#f9f9f9,stroke:#333,stroke-width:2px
        classDef network fill:#d8e8f3,stroke:#333,stroke-width:2px
        classDef gateway fill:#f5d6c6,stroke:#333,stroke-width:2px
        classDef fabric fill:#e1f3d8,stroke:#333,stroke-width:2px
        classDef routing fill:#ffe4b5,stroke:#333,stroke-width:2px
        classDef nacl fill:#ffd7e9,stroke:#333,stroke-width:2px
        classDef sriov fill:#d4f1d4,stroke:#333,stroke-width:2px
        classDef vf fill:#e8f8e8,stroke:#333,stroke-width:2px
        
        class PublicSubnet,PrivateSubnet,HPCSubnet subnet
        class web,app,hpc1,hpc2 instance
        class web_eni1,web_eni2,app_eni,efa1,efa2 network
        class igw gateway
        class fabric fabric
        class public_rt,private_rt,hpc_rt routing
        class public_nacl,private_nacl,hpc_nacl nacl
        class sriov1,sriov2 sriov
        class vf1,vf2,vf3 vf
    end
```

Key Differences and When to Use Each:

* Use ENI when you need network segregation or multiple networks at minimal cost
* Choose EFA when you need HPC-level performance and OS-bypass capabilities
* Implement Enhanced Networking when you need reliable high bandwidth but don't require the specialized capabilities of EFA

Note that Enhanced Networking is automatically enabled on most modern EC2 instance types, while ENI and EFA require specific configuration and are used for specialized use cases.

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
