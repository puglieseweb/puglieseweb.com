# Direct Connect Gateway Implementation Guide

### Overview

This document outlines the step-by-step process for implementing a redundant AWS Direct Connect setup using Direct Connect Gateway to enable multi-region connectivity. The implementation will migrate from a single 1 Gbps connection to a redundant setup while maintaining service availability.

### Current Architecture

* Single 1 Gbps AWS Direct Connect connection
* Single private virtual interface
* Single VPC connectivity
* No cross-region capabilities

### Target Architecture

* Dual 1 Gbps AWS Direct Connect connections
* Direct Connect Gateway for multi-region support
* Redundant private virtual interfaces
* Capability to connect VPCs across multiple regions

### Prerequisites

1. AWS Direct Connect console access
2. Network admin access to on-premises router
3. BGP ASN for the Direct Connect Gateway
4. VLAN IDs for new virtual interfaces
5. IP address ranges for BGP peering
6. Maintenance window for migration

### Implementation Steps

#### Phase 1: Preparation

1. Document existing Direct Connect configuration:
   * Current private virtual interface settings
   * BGP configurations
   * Route tables
   * VLAN IDs
   * IP addressing
2. Order second Direct Connect connection:
   * Same speed (1 Gbps)
   * Same location as existing connection
   * Confirm LOA-CFA received
3. Configure on-premises router:
   * Additional physical port for new connection
   * BGP capability verification
   * Routing policy review

#### Phase 2: Direct Connect Gateway Setup

1.  Create Direct Connect Gateway:

    ```bash
    aws directconnect create-direct-connect-gateway \
      --direct-connect-gateway-name "Global-DXGW" \
      --amazon-side-asn "64512"
    ```
2.  Create VPC association:

    ```bash
    aws directconnect create-direct-connect-gateway-association \
      --direct-connect-gateway-id "dxgw-xxxxxx" \
      --virtual-gateway-id "vgw-xxxxxx"
    ```

#### Phase 3: Migration

1. Delete existing private virtual interface
   * Document all settings before deletion
   * Schedule during maintenance window
   * Verify backup connectivity (if available)
2. Create new private virtual interfaces:
   * Configure first VIF on existing DX
   * Configure second VIF on new DX
   * Assign unique VLAN IDs
   * Configure BGP settings
3.  Associate VIFs with Direct Connect Gateway:

    ```bash
    aws directconnect create-private-virtual-interface \
      --connection-id "dxcon-xxxxxx" \
      --direct-connect-gateway-id "dxgw-xxxxxx" \
      --virtual-interface-name "Primary-VIF" \
      --vlan 100 \
      --asn 65001
    ```

#### Phase 4: Testing and Validation

1. BGP Connection Testing:
   * Verify BGP sessions established
   * Check route propagation
   * Validate prefix advertisements
2. Connectivity Testing:
   * Test connectivity to VPC resources
   * Verify failover capabilities
   * Test latency and throughput
3. Monitoring Setup:
   * Configure CloudWatch metrics
   * Set up connection alerts
   * Enable VIF monitoring

#### Phase 5: Multi-Region Extension

1.  Associate additional VPCs:

    ```bash
    aws directconnect create-direct-connect-gateway-association \
      --direct-connect-gateway-id "dxgw-xxxxxx" \
      --virtual-gateway-id "vgw-yyyyyy" \
      --gateway-region "eu-west-1"
    ```
2. Configure route tables in each region
3. Test cross-region connectivity

### Network Architecture Diagram



```mermaid
graph TB
    subgraph "On-Premises Network"
        CR[Customer Router]
        DC[Data Center]
        DC --> CR
    end

    subgraph "AWS Direct Connect Location"
        DX1[Direct Connect 1<br/>1 Gbps]
        DX2[Direct Connect 2<br/>1 Gbps]
        
        subgraph "Private Virtual Interfaces"
            VIF1[Private VIF 1<br/>VLAN 100]
            VIF2[Private VIF 2<br/>VLAN 200]
        end
        
        DXGW[Direct Connect Gateway<br/>ASN 64512]
    end
    
    subgraph "Region: us-east-1"
        VPC1[Primary VPC<br/>10.0.0.0/16]
        VGW1[Virtual Private Gateway]
    end
    
    subgraph "Region: eu-west-1"
        VPC2[Secondary VPC<br/>172.16.0.0/16]
        VGW2[Virtual Private Gateway]
    end

    CR --"Primary Connection"--> DX1
    CR --"Secondary Connection"--> DX2
    DX1 --> VIF1
    DX2 --> VIF2
    VIF1 & VIF2 --"BGP Session"--> DXGW
    DXGW --> VGW1 & VGW2
    VGW1 --> VPC1
    VGW2 --> VPC2

    classDef aws fill:#FF9900,stroke:#232F3E,stroke-width:2px,color:white;
    classDef corporate fill:#00A4EF,stroke:#232F3E,stroke-width:2px,color:white;
    classDef network fill:#1EC754,stroke:#232F3E,stroke-width:2px,color:white;
    
    class VPC1,VPC2,DXGW,DX1,DX2,VGW1,VGW2,VIF1,VIF2 aws;
    class DC,CR corporate;

```
