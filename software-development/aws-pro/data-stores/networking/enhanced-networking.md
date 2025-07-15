# Enhanced Networking

## AWS Enhanced Networking and Placement Groups Guide

### Enhanced Networking Overview

Enhanced Networking is a specialized networking feature designed primarily for High Performance Computing (HPC) workloads in AWS. This capability provides superior networking performance through Single Root I/O Virtualization (SR-IOV) technology.

#### SR-IOV (Single Root I/O Virtualization)

SR-IOV (Single Root I/O Virtualization) is a hardware-level virtualization technology that allows a single network interface cards (NICs) to present itself as multiple virtual devices to different virtual machines or containers.&#x20;

**Core Concept:** SR-IOV enables a physical network card, storage controller, or other PCIe device to be divided into multiple virtual functions (VFs) that can be directly assigned to different VMs, bypassing the hypervisor for I/O operations.

**AWS's Physical Infrastructure:** AWS runs on physical servers with SR-IOV-capable network interface cards (NICs). These are typically high-performance Ethernet adapters from vendors like Intel, Mellanox, or custom AWS silicon like the Nitro cards.

**How SR-IOV Works in AWS:**

* AWS's hypervisor (Nitro) uses SR-IOV to create virtual functions (VFs) on the physical NICs
* Each EC2 instance gets direct access to a VF, bypassing the hypervisor for network I/O
* This provides near-native network performance to your EC2 instances



#### Key Features

* Uses SR-IOV to deliver higher performance compared to traditional virtualized network interfaces
* Provides 10 Gbps throughput with Intel 82599 VF Interface
* Offers 25 Gbps throughput with Elastic Network Adapter
* Pre-configured in Amazon Linux HVM AMI; may require driver installation for other operating systems

#### Technical Considerations

The enhanced networking capability requires specific configuration and setup considerations:

* Driver installation may be necessary for non-Amazon Linux HVM AMI systems
* Leverages specialized hardware interfaces for optimal performance
* Designed specifically for high-throughput, low-latency workloads

### Placement Groups

Placement Groups in AWS provide different strategies for organizing EC2 instances to meet specific workload requirements. There are three distinct types of placement groups, each serving different use cases.

#### 1. Clustered Placement

**Description:**

* Instances are placed in a low-latency group within a single Availability Zone
* Optimized for applications requiring minimal network latency and maximum network throughput

**Best Used When:**

* Applications need low network latency
* Workloads require high network throughput
* HPC applications that benefit from close proximity

**Advantages:**

* Maximizes the benefits of Enhanced Networking instances
* Provides optimal network performance within the group

**Limitations:**

* Finite capacity requires upfront planning
* Recommended to launch all needed instances at once

#### 2. Spread Placement

**Description:**

* Instances are distributed across underlying hardware
* Each instance runs on distinct hardware

**Best Used When:**

* Need to minimize risk of simultaneous hardware failures
* Critical applications requiring high availability

**Key Features:**

* Can span multiple Availability Zones
* Maximum of 7 instances running per group per AZ

**Advantages:**

* Reduces risk of correlated hardware failures
* Provides better isolation for individual instances

#### 3. Partition Placement

**Description:**

* Instances are grouped into partitions and spread across racks
* Each partition has its own dedicated rack

**Best Used When:**

* Running large distributed workloads
* Deploying replicated applications
* Managing multi-instance workloads

**Key Features:**

* Better suited for large distributed/replicated workloads compared to Spread placement
* Provides logical isolation between partitions
* Not compatible with Dedicated Hosts

**Example Use Case:** Hadoop HDFS Landscape:

* Instances can be organized into partitions
* Each partition can represent a different segment of the HDFS cluster
* Provides both performance and failure isolation

### Visualization of Placement Strategies

The different placement strategies organize instances distinctly:

**Default Placement:**

* Basic instance distribution without specific placement rules
* No guaranteed instance proximity or separation

**Cluster Placement:**

* Tightly packed instances within same AZ
* Optimized for inter-instance communication

<figure><img src="../../../../.gitbook/assets/image (99).png" alt=""><figcaption></figcaption></figure>

**Spread Placement:**

* Instances distributed across hardware
* Maximizes availability and fault tolerance

**Partition Placement:**

* Instances grouped into logical partitions
* Balanced between isolation and resource utilization

<figure><img src="../../../../.gitbook/assets/image (100).png" alt=""><figcaption></figcaption></figure>
