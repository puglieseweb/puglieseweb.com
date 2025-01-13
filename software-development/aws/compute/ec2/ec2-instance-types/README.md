# EC2 Instance Types

EC2 offers a wide variety of instance types optimized for different use cases. Each instance type is grouped into an instance family and provides different combinations of CPU, memory, storage, and networking capacity.

#### Instance Families

1. General Purpose (T and M series)
   * T instances (T3, T3a, T2) are burstable performance instances
   * Uses CPU credits for burst capacity
   * M instances (M6i, M5, M4) provide balanced compute, memory, and networking
   * Best for web servers, development environments, and small databases
2. Compute Optimized (C series)
   * C6i, C5, C4 instances
   * High performance processors
   * Ideal for batch processing, media transcoding, high-performance web servers
   * Best price-to-compute performance in EC2
3. Memory Optimized (R, X, z series)
   * R6i, R5, R4 instances: Standard memory optimized
   * X2, X1e, X1: For high-memory workloads
   * z1d: High compute capacity and memory
   * Perfect for in-memory databases, real-time big data analytics, large SAP workloads
4. Storage Optimized (I, D, H series)
   * I3, I3en: NVMe SSD-backed instance storage
   * D2: HDD-backed storage
   * H1: High disk throughput
   * Ideal for data warehousing, log processing, distributed file systems
5. Accelerated Computing (P, G, F series)
   * P4, P3: GPU compute instances for machine learning
   * G4: Graphics-optimized for gaming and application streaming
   * F1: Field Programmable Gate Arrays (FPGAs)

#### Key Instance Type Features

1. Enhanced Networking
   * Uses SR-IOV for better performance
   * Available on most current generation instances
   * Lower latency, higher packet per second (PPS)
2. Placement Groups
   * Cluster: Low-latency, high-throughput
   * Spread: Distinct hardware for high availability
   * Partition: Multiple instances spread across partitions
3. Instance Store vs EBS
   * Instance store: Ephemeral, high I/O
   * EBS: Persistent storage, multiple volume types
   * Some instances are EBS-only

#### Comparison Table of Common EC2 Instance Types

| Instance Type | vCPU | Memory (GiB) | Storage          | Network Performance | Use Case                                         |
| ------------- | ---- | ------------ | ---------------- | ------------------- | ------------------------------------------------ |
| t3.micro      | 2    | 1            | EBS-Only         | Up to 5 Gigabit     | Low traffic websites, small applications         |
| m5.large      | 2    | 8            | EBS-Only         | Up to 10 Gigabit    | Small to medium databases, data processing tasks |
| c5.large      | 2    | 4            | EBS-Only         | Up to 10 Gigabit    | Web servers, batch processing                    |
| r5.large      | 2    | 16           | EBS-Only         | Up to 10 Gigabit    | Memory-intensive applications                    |
| i3.large      | 2    | 15.25        | 1 x 475 NVMe SSD | Up to 10 Gigabit    | NoSQL databases, data warehousing                |
| p3.2xlarge    | 8    | 61           | EBS-Only         | Up to 10 Gigabit    | Machine learning, high-performance computing     |

```
```

#### Key Points

1. Instance type naming convention: \[Instance Family]\[Generation]\[Additional Capabilities].\[Size] For example, in "m5.large":
   * 'm' is the instance family (General Purpose)
   * '5' is the generation
   * 'large' is the size within that type
2. As you move up in size within an instance type (e.g., from t3.micro to t3.small to t3.medium), you generally get proportionally more vCPU and memory.
3. Some instance types are EBS-optimized by default, providing additional, dedicated capacity for EBS I/O.
4. Newer generations within a family typically offer better performance and newer features.
5. Some specialized instance types offer additional features:
   * GPU instances (P and G families) for graphics and general-purpose GPU compute applications
   * FPGA instances (F1) for custom hardware acceleration
6. When choosing an instance type, consider:
   * The specific requirements of your application (CPU, memory, storage, network)
   * The workload patterns (steady-state, variable, or burst)
   * Cost optimization (different instance types have different pricing)
