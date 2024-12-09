# Snow Family

The Snow Family is a set of secure appliances that provide data collection and built-in computing capabilities. These devices are designed for use at the edge, in remote locations with limited or no data center access and unreliable network connectivity.

According to AWS best practices, **if transferring more than 100 TB of data, using AWS Snow Family devices is generally more cost-effective** and faster than transfer over a 10 Gbps Direct Connect.

Calculation:

* 10 Gbps ≈ 1.25 GB/second (theoretical max)
* Practical transfer speed is typically 70-80% of theoretical
* 100 TB would take approximately 100-120 hours over Direct Connect
* Additional factors: network consistency, bandwidth sharing, and data transfer costs

Consider Snow devices if:

1. Data volume > 100 TB
2. Transfer time sensitivity is critical
3. Network bandwidth is shared/inconsistent
4. Data transfer costs are a concern



**Device Types**

1. **Snowcone**
   * Small, robust edge computing device
   * Storage capacity: Up to 8TB
2. **Snowball**
   * Provides storage, compute, and GPU capabilities
   * Storage capacity: Up to 81TB
3. **Snowmobile**
   * Extreme-scale data transfer solution
   * Storage capacity: Up to 100PB
   * Delivered in a secure shipping container on a truck

**Data Transfer**

* Bidirectional data transfer capabilities (into and out of AWS)
* Secure method for large-scale data migration

**Use Cases**

1. Hybrid cloud deployments requiring on-premises storage
2. Data center migration when on-premises storage is at capacity
3. Storage Gateway deployment as a virtual machine (VM) on-premises
4. Edge computing in locations with limited connectivity
5. Large-scale data migration projects

