# Enhanced Networking

AWS Enhanced Networking is a feature that provides higher bandwidth, lower latency, and lower jitter network performance for EC2 instances. It's different from EFA but shares some similar goals in improving network performance. Here are the key points about AWS Enhanced Networking:

1. Implementation: It uses single root I/O virtualization (SR-IOV) to provide higher I/O performance and lower CPU utilization compared to traditional virtualized network interfaces.
2. Network adapters: Enhanced Networking is typically enabled through specialized network adapters, such as the Elastic Network Adapter (ENA) or the Intel 82599 Virtual Function (VF) interface.
3. Performance benefits:
   * Higher packets per second (PPS) performance
   * Lower inter-instance latencies
   * Less jitter
4. Instance types: It's available on most current generation EC2 instance types, but not all.
5. No additional cost: Enhanced Networking is available at no extra charge for supported instance types.
6. Use cases: It's beneficial for any workload that requires high network performance, including database servers, web servers, and applications with high network I/O.
7. Enablement: Depending on the instance type and AMI, Enhanced Networking might be enabled by default or require manual configuration.
