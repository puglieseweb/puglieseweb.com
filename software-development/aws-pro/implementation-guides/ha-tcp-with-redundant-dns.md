# HA TCP with Redundant DNS

### Core Requirements

* TCP protocol support on static port
* High availability across multiple Availability Zones
* Public DNS accessibility (e.g., my.service.com)
* Fixed IP addressing for third-party allow listing
* Cross-AZ redundancy within a single region

### Option 1: NLB with Elastic IP per AZ (Recommended)

#### Architecture Components

* Network Load Balancer with static Elastic IPs per AZ
* EC2 instances in Auto Scaling Group across AZs
* Route 53 Alias record pointing to NLB



```mermaid
graph TB
    subgraph Internet
        DNS[Route 53 Alias Record<br/>my.service.com]
        Client[Client Application]
    end

    subgraph "AWS Region"
        subgraph "Network Load Balancer"
            NLB[NLB]
            EIP1[Elastic IP 1]
            EIP2[Elastic IP 2]
            EIP3[Elastic IP 3]
        end

        subgraph "Availability Zone 1"
            SG1[Security Group]
            ASG1[Auto Scaling Group]
            EC2_1_1[EC2 Instance 1]
            EC2_1_2[EC2 Instance 2]
        end

        subgraph "Availability Zone 2"
            SG2[Security Group]
            ASG2[Auto Scaling Group]
            EC2_2_1[EC2 Instance 1]
            EC2_2_2[EC2 Instance 2]
        end

        subgraph "Availability Zone 3"
            SG3[Security Group]
            ASG3[Auto Scaling Group]
            EC2_3_1[EC2 Instance 1]
            EC2_3_2[EC2 Instance 2]
        end
    end

    Client --> DNS
    DNS --> NLB
    NLB --> EIP1
    NLB --> EIP2
    NLB --> EIP3
    
    EIP1 --> SG1
    EIP2 --> SG2
    EIP3 --> SG3
    
    SG1 --> ASG1
    SG2 --> ASG2
    SG3 --> ASG3
    
    ASG1 --> EC2_1_1
    ASG1 --> EC2_1_2
    ASG2 --> EC2_2_1
    ASG2 --> EC2_2_2
    ASG3 --> EC2_3_1
    ASG3 --> EC2_3_2

    classDef az fill:#e6f3ff,stroke:#333,stroke-width:2px
    classDef nlb fill:#f9f,stroke:#333,stroke-width:2px
    classDef sg fill:#ffeb99,stroke:#333,stroke-width:2px
    classDef ec2 fill:#99ff99,stroke:#333,stroke-width:2px
    classDef eip fill:#ff9999,stroke:#333,stroke-width:2px
    classDef asg fill:#ffcc99,stroke:#333,stroke-width:2px
    classDef internet fill:#f5f5f5,stroke:#333,stroke-width:2px

    class NLB nlb
    class EIP1,EIP2,EIP3 eip
    class SG1,SG2,SG3 sg
    class EC2_1_1,EC2_1_2,EC2_2_1,EC2_2_2,EC2_3_1,EC2_3_2 ec2
    class ASG1,ASG2,ASG3 asg
    class Client,DNS internet
```

#### Implementation Details

1. Network Load Balancer Configuration
   * Create NLB and enable cross-zone load balancing
   * Assign Elastic IP to each subnet in each AZ
   * Configure TCP listener on required port
   * Set up target group with health checks
2. Compute Layer
   * Launch EC2 instances in Auto Scaling Group
   * Distribute across multiple AZs
   * Configure instance health checks
   * Implement security groups for TCP port access
3. DNS Configuration
   * Create Route 53 Alias record pointing to NLB
   * Enable health checks
   * Configure failover if needed

#### Advantages

* Fixed, predictable IP addresses for allow listing
* Native TCP support
* Automatic failover
* Low latency through zonal IP addressing
* AWS-managed high availability

#### Limitations

* Cost of Elastic IPs
* Limited to 50 IPs per region (can be increased)

### Option 2: Global Accelerator with Endpoint Groups



```mermaid
graph TB
    subgraph "Global"
        Client1[Client US]
        Client2[Client EU]
        Client3[Client APAC]
        GA[Global Accelerator<br/>2 Anycast IPs]
        DNS[Route 53<br/>my.service.com]
    end

    subgraph "AWS Region - Primary"
        subgraph "Endpoint Group US"
            NLB1[Network Load Balancer]
            
            subgraph "Availability Zone 1"
                SG1[Security Group]
                ASG1[Auto Scaling Group]
                EC2_1_1[EC2 Instance 1]
                EC2_1_2[EC2 Instance 2]
            end

            subgraph "Availability Zone 2"
                SG2[Security Group]
                ASG2[Auto Scaling Group]
                EC2_2_1[EC2 Instance 1]
                EC2_2_2[EC2 Instance 2]
            end
        end
    end

    subgraph "AWS Region - Secondary"
        subgraph "Endpoint Group EU"
            NLB2[Network Load Balancer]
            
            subgraph "Availability Zone 3"
                SG3[Security Group]
                ASG3[Auto Scaling Group]
                EC2_3_1[EC2 Instance 1]
                EC2_3_2[EC2 Instance 2]
            end

            subgraph "Availability Zone 4"
                SG4[Security Group]
                ASG4[Auto Scaling Group]
                EC2_4_1[EC2 Instance 1]
                EC2_4_2[EC2 Instance 2]
            end
        end
    end

    Client1 --> DNS
    Client2 --> DNS
    Client3 --> DNS
    DNS --> GA
    
    GA --> NLB1
    GA --> NLB2
    
    NLB1 --> SG1
    NLB1 --> SG2
    NLB2 --> SG3
    NLB2 --> SG4
    
    SG1 --> ASG1
    SG2 --> ASG2
    SG3 --> ASG3
    SG4 --> ASG4
    
    ASG1 --> EC2_1_1
    ASG1 --> EC2_1_2
    ASG2 --> EC2_2_1
    ASG2 --> EC2_2_2
    ASG3 --> EC2_3_1
    ASG3 --> EC2_3_2
    ASG4 --> EC2_4_1
    ASG4 --> EC2_4_2

    classDef az fill:#e6f3ff,stroke:#333,stroke-width:2px
    classDef nlb fill:#f9f,stroke:#333,stroke-width:2px
    classDef sg fill:#ffeb99,stroke:#333,stroke-width:2px
    classDef ec2 fill:#99ff99,stroke:#333,stroke-width:2px
    classDef ga fill:#ff9999,stroke:#333,stroke-width:2px
    classDef asg fill:#ffcc99,stroke:#333,stroke-width:2px
    classDef global fill:#f5f5f5,stroke:#333,stroke-width:2px
    classDef endpoint fill:#e6ffe6,stroke:#333,stroke-width:2px

    class NLB1,NLB2 nlb
    class GA ga
    class SG1,SG2,SG3,SG4 sg
    class EC2_1_1,EC2_1_2,EC2_2_1,EC2_2_2,EC2_3_1,EC2_3_2,EC2_4_1,EC2_4_2 ec2
    class ASG1,ASG2,ASG3,ASG4 asg
    class Client1,Client2,Client3,DNS global
```

#### Architecture Components

* AWS Global Accelerator (providing fixed IPs)
* Network Load Balancer as endpoint
* EC2 instances in Auto Scaling Group
* Route 53 for DNS management

#### Implementation Details

1. Global Accelerator Setup
   * Create accelerator (provides 2 fixed anycast IPs)
   * Configure listener for TCP port
   * Create endpoint group in region
   * Add NLB as endpoint
2. Network Load Balancer Layer
   * Deploy NLB across AZs
   * Configure target groups
   * Set up health checks
3. Compute Layer
   * EC2 instances in Auto Scaling Group
   * Multi-AZ deployment
   * Security group configuration

#### Advantages

* Only 2 IPs to allow list globally
* Built-in DDoS protection
* Global availability
* Automatic failover
* Better latency for global users

#### Limitations

* Higher cost compared to NLB-only solution
* More complex setup

### Option 3: Network Firewall with NLB

#### Architecture Components

* AWS Network Firewall
* Network Load Balancer
* EC2 instances in Auto Scaling Group
* Route 53 for DNS



```mermaid
graph TB
    subgraph Internet
        DNS[Route 53 Alias Record<br/>my.service.com]
        Client[Client Application]
    end

    subgraph "AWS Region"
        subgraph "Firewall VPC"
            ANF[AWS Network Firewall]
            FWE1[Firewall Endpoint AZ1]
            FWE2[Firewall Endpoint AZ2]
            FWLOG[Firewall Logging]
        end

        subgraph "Application VPC"
            subgraph "Network Load Balancer"
                NLB[NLB]
                EIP1[Elastic IP 1]
                EIP2[Elastic IP 2]
            end

            subgraph "Inspection VPC Endpoints"
                VPCE1[VPC Endpoint AZ1]
                VPCE2[VPC Endpoint AZ2]
            end

            subgraph "Availability Zone 1"
                SG1[Security Group]
                ASG1[Auto Scaling Group]
                EC2_1_1[EC2 Instance 1]
                EC2_1_2[EC2 Instance 2]
            end

            subgraph "Availability Zone 2"
                SG2[Security Group]
                ASG2[Auto Scaling Group]
                EC2_2_1[EC2 Instance 1]
                EC2_2_2[EC2 Instance 2]
            end
        end

        subgraph "CloudWatch"
            CWL[CloudWatch Logs]
            CWM[CloudWatch Metrics]
        end
    end

    Client --> DNS
    DNS --> ANF
    ANF --> FWE1
    ANF --> FWE2
    FWE1 --> VPCE1
    FWE2 --> VPCE2
    VPCE1 --> NLB
    VPCE2 --> NLB
    
    NLB --> EIP1
    NLB --> EIP2
    
    EIP1 --> SG1
    EIP2 --> SG2
    
    SG1 --> ASG1
    SG2 --> ASG2
    
    ASG1 --> EC2_1_1
    ASG1 --> EC2_1_2
    ASG2 --> EC2_2_1
    ASG2 --> EC2_2_2

    FWLOG --> CWL
    ANF --> CWM

    classDef az fill:#e6f3ff,stroke:#333,stroke-width:2px
    classDef nlb fill:#f9f,stroke:#333,stroke-width:2px
    classDef sg fill:#ffeb99,stroke:#333,stroke-width:2px
    classDef ec2 fill:#99ff99,stroke:#333,stroke-width:2px
    classDef fw fill:#ff9999,stroke:#333,stroke-width:2px
    classDef asg fill:#ffcc99,stroke:#333,stroke-width:2px
    classDef internet fill:#f5f5f5,stroke:#333,stroke-width:2px
    classDef monitoring fill:#e6ffe6,stroke:#333,stroke-width:2px
    classDef vpc fill:#e6f3ff,stroke:#333,stroke-width:4px

    class NLB nlb
    class ANF,FWE1,FWE2 fw
    class SG1,SG2 sg
    class EC2_1_1,EC2_1_2,EC2_2_1,EC2_2_2 ec2
    class ASG1,ASG2 asg
    class Client,DNS internet
    class CWL,CWM monitoring
```

#### Implementation Details

1. Network Firewall Configuration
   * Deploy Network Firewall
   * Configure stateful rules for TCP
   * Set up logging and monitoring
2. Load Balancer Layer
   * NLB deployment across AZs
   * Target group configuration
   * Health check setup
3. Compute Resources
   * EC2 Auto Scaling Group
   * Multi-AZ distribution
   * Instance security configuration

#### Advantages

* Advanced traffic filtering
* Detailed network monitoring
* Centralized security management
* Fixed IP addresses

#### Limitations

* Additional cost for Network Firewall
* More complex network architecture
* Higher latency due to additional hop

### Option 4: Private Link Service

#### Architecture Components

* AWS PrivateLink service
* Network Load Balancer
* EC2 instances
* Route 53 Private Hosted Zone



```mermaid
graph TB
    subgraph "Service Provider Account"
        subgraph "Provider VPC"
            subgraph "VPC Endpoint Service"
                VPCES[VPC Endpoint Service]
            end

            subgraph "Internal Network Load Balancer"
                NLB[Internal NLB<br/>Private IP from VPC]
            end

            subgraph "Availability Zone 1"
                SG1[Security Group]
                ASG1[Auto Scaling Group]
                EC2_1_1[EC2 Instance 1]
                EC2_1_2[EC2 Instance 2]
            end

            subgraph "Availability Zone 2"
                SG2[Security Group]
                ASG2[Auto Scaling Group]
                EC2_2_1[EC2 Instance 1]
                EC2_2_2[EC2 Instance 2]
            end
        end
    end

    subgraph "Consumer Account 1"
        subgraph "Consumer VPC 1"
            R53_PHZ1[Route 53 Private<br/>Hosted Zone]
            VPCE1[VPC Endpoint]
            APP1[Consumer Application]
        end
    end

    subgraph "Consumer Account 2"
        subgraph "Consumer VPC 2"
            R53_PHZ2[Route 53 Private<br/>Hosted Zone]
            VPCE2[VPC Endpoint]
            APP2[Consumer Application]
        end
    end

    subgraph "Consumer Account 3"
        subgraph "Consumer VPC 3"
            R53_PHZ3[Route 53 Private<br/>Hosted Zone]
            VPCE3[VPC Endpoint]
            APP3[Consumer Application]
        end
    end

    VPCE1 --> VPCES
    VPCE2 --> VPCES
    VPCE3 --> VPCES
    
    VPCES --> NLB
    
    NLB --> |AZ1|SG1
    NLB --> |AZ2|SG2
    
    SG1 --> ASG1
    SG2 --> ASG2
    
    ASG1 --> EC2_1_1
    ASG1 --> EC2_1_2
    ASG2 --> EC2_2_1
    ASG2 --> EC2_2_2

    APP1 --> VPCE1
    APP2 --> VPCE2
    APP3 --> VPCE3

    R53_PHZ1 --> VPCE1
    R53_PHZ2 --> VPCE2
    R53_PHZ3 --> VPCE3

    classDef az fill:#e6f3ff,stroke:#333,stroke-width:2px
    classDef nlb fill:#f9f,stroke:#333,stroke-width:2px
    classDef sg fill:#ffeb99,stroke:#333,stroke-width:2px
    classDef ec2 fill:#99ff99,stroke:#333,stroke-width:2px
    classDef vpc fill:#e6f3ff,stroke:#333,stroke-width:4px
    classDef vpcE fill:#ff9999,stroke:#333,stroke-width:2px
    classDef asg fill:#ffcc99,stroke:#333,stroke-width:2px
    classDef consumer fill:#f5f5f5,stroke:#333,stroke-width:2px
    classDef dns fill:#e6ffe6,stroke:#333,stroke-width:2px

    class NLB nlb
    class VPCES,VPCE1,VPCE2,VPCE3 vpcE
    class SG1,SG2 sg
    class EC2_1_1,EC2_1_2,EC2_2_1,EC2_2_2 ec2
    class ASG1,ASG2 asg
    class APP1,APP2,APP3 consumer
    class R53_PHZ3,R53_PHZ1,R53_PHZ2 dns
```

#### Implementation Details

1. PrivateLink Configuration
   * Create VPC endpoint service
   * Associate NLB
   * Configure allowed principals
2. Service Provider Setup
   * NLB deployment
   * Target group configuration
   * Security group setup
3. Consumer Setup
   * VPC endpoint creation
   * DNS configuration
   * Network ACL configuration

#### Advantages

* Private connectivity
* No public IP exposure
* Highly secure
* Service provider control

#### Limitations

* More complex setup
* Both parties need to be on AWS
* Regional service
* Higher cost

### Option 5: ECS with Network Load Balancer and Static IPs

#### Architecture Components

* Amazon ECS Cluster
* Network Load Balancer with Elastic IPs
* ECS Services and Tasks
* Route 53 for DNS management



```mermaid
graph TB
    subgraph Internet
        DNS[Route 53 Alias Record<br/>my.service.com]
        Client[Client Application]
    end

    subgraph "AWS Region"
        subgraph "Network Load Balancer"
            NLB[Network Load Balancer]
            EIP1[Elastic IP AZ1]
            EIP2[Elastic IP AZ2]
            EIP3[Elastic IP AZ3]
        end

        subgraph "ECS Cluster"
            CP[Capacity Provider]
            SD[Service Discovery]
            
            subgraph "Availability Zone 1"
                ASG1[Auto Scaling Group]
                subgraph "ECS Service AZ1"
                    EC1_1[ECS Container 1]
                    EC1_2[ECS Container 2]
                end
                NG1[NAT Gateway]
            end

            subgraph "Availability Zone 2"
                ASG2[Auto Scaling Group]
                subgraph "ECS Service AZ2"
                    EC2_1[ECS Container 1]
                    EC2_2[ECS Container 2]
                end
                NG2[NAT Gateway]
            end

            subgraph "Availability Zone 3"
                ASG3[Auto Scaling Group]
                subgraph "ECS Service AZ3"
                    EC3_1[ECS Container 1]
                    EC3_2[ECS Container 2]
                end
                NG3[NAT Gateway]
            end
        end
    end

    Client --> DNS
    DNS --> NLB
    NLB --> EIP1
    NLB --> EIP2
    NLB --> EIP3
    
    EIP1 --> EC1_1
    EIP1 --> EC1_2
    EIP2 --> EC2_1
    EIP2 --> EC2_2
    EIP3 --> EC3_1
    EIP3 --> EC3_2
    
    CP --> ASG1
    CP --> ASG2
    CP --> ASG3
    
    ASG1 --> EC1_1
    ASG1 --> EC1_2
    ASG2 --> EC2_1
    ASG2 --> EC2_2
    ASG3 --> EC3_1
    ASG3 --> EC3_2

    EC1_1 --> NG1
    EC1_2 --> NG1
    EC2_1 --> NG2
    EC2_2 --> NG2
    EC3_1 --> NG3
    EC3_2 --> NG3

    classDef az fill:#e6f3ff,stroke:#333,stroke-width:2px
    classDef nlb fill:#f9f,stroke:#333,stroke-width:2px
    classDef eip fill:#ff9999,stroke:#333,stroke-width:2px
    classDef ecs fill:#99ff99,stroke:#333,stroke-width:2px
    classDef asg fill:#ffcc99,stroke:#333,stroke-width:2px
    classDef nat fill:#ffeb99,stroke:#333,stroke-width:2px
    classDef internet fill:#f5f5f5,stroke:#333,stroke-width:2px
    classDef service fill:#e6ffe6,stroke:#333,stroke-width:2px

    class NLB nlb
    class EIP1,EIP2,EIP3 eip
    class EC1_1,EC1_2,EC2_1,EC2_2,EC3_1,EC3_2 ecs
    class ASG1,ASG2,ASG3 asg
    class NG1,NG2,NG3 nat
    class Client,DNS internet
    class CP,SD service
```

#### Implementation Details

1. Network Configuration
   * Create VPC with public and private subnets
   * Assign Elastic IPs to NLB per AZ
   * Configure NAT Gateways for container internet access
2. ECS Cluster Setup
   * Deploy ECS cluster across multiple AZs
   * Use EC2 launch type for predictable networking
   * Configure capacity providers
   * Set up Auto Scaling groups for ECS hosts
3. Container Configuration
   * Define task definitions with TCP port mapping
   * Configure service discovery
   * Set up ECS services with desired count
   * Enable container health checks
4. Load Balancer Setup
   * Deploy Network Load Balancer
   * Assign Elastic IPs per AZ
   * Configure TCP listener
   * Create target groups for ECS services
5. DNS Configuration
   * Create Route 53 Alias record
   * Point to NLB
   * Configure health checks

#### Advantages

* Container orchestration benefits
* Fixed IP addresses for allow listing
* Auto scaling capabilities
* Easy service updates
* Built-in health checks and recovery

#### Limitations

* More complex than EC2-only solution
* Requires ECS-specific knowledge
* Container networking considerations
* Higher operational overhead

### Best Practices Across All Options

#### High Availability

* Deploy across minimum three AZs
* Implement proper health checks
* Configure automatic failover
* Use Auto Scaling Groups

#### Security

* Implement least privilege access
* Enable VPC Flow Logs
* Regular security audits
* DDoS protection configuration

#### Monitoring

* CloudWatch metrics
* Health check alerts
* Latency monitoring
* Connection tracking

#### Cost Optimization

* Right-size instances
* Monitor traffic patterns
* Optimize AZ usage
* Regular cost analysis

### Implementation Decision Matrix

| Requirement       | NLB w/EIP | Global Accelerator | Network Firewall | PrivateLink |
| ----------------- | --------- | ------------------ | ---------------- | ----------- |
| Fixed IPs         | Yes       | Yes                | Yes              | N/A         |
| Global Reach      | No        | Yes                | No               | No          |
| Setup Complexity  | Low       | Medium             | High             | High        |
| Cost              | Low       | Medium             | High             | Medium      |
| Security Features | Basic     | Advanced           | Advanced         | Advanced    |
| Latency           | Low       | Low                | Medium           | Low         |

### Conclusion

For most standard TCP service deployments, the NLB with Elastic IP per AZ option provides the best balance of functionality, cost, and complexity. However, specific requirements around global availability (Global Accelerator), advanced security (Network Firewall), or private connectivity (PrivateLink) might make other options more suitable.

The choice between these options should be based on:

* Geographic distribution requirements
* Security and compliance needs
* Budget constraints
* Operational complexity tolerance
* Performance requirements
* Integration with existing infrastructure
