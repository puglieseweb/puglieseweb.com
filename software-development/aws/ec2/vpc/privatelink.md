# PrivateLink

* The best way to expose a service VPC to tens, hundreds, or thousands of customer VPCs&#x20;
* Doesn't require VPC peering; no route tables, NAT gateways, internet gateways, etc.
* Requires a **Network Load Balancer** on the service VPC and an **ENI** on the customer VPC.

<figure><img src="../../../../.gitbook/assets/AWS - PrivateLink.png" alt=""><figcaption></figcaption></figure>



```mermaid
flowchart TD
    subgraph consumer["Consumer VPC"]
        direction TB
        subgraph consumerPrivate["Private Subnet"]
            EC2["EC2 Instance"]
            ENI["Elastic Network\nInterface"]
            RT1["Route Table"]
        end
        CSG["Consumer\nSecurity Group"]
    end

    subgraph provider["Service Provider VPC"]
        direction TB
        subgraph providerPrivate["Private Subnet"]
            APP["Application\nServers"]
            NLB["Network Load\nBalancer"]
            RT2["Route Table"]
        end
        PSG["Provider\nSecurity Group"]
        
        subgraph endpoint["VPC Endpoint Service"]
            direction LR
            EPSVC["Endpoint Service\n(AWS PrivateLink)"]
        end
    end

    subgraph shared["AWS PrivateLink Infrastructure"]
        direction TB
        DNS["DNS Service"]
        PLNET["PrivateLink Network"]
    end

    EC2 --> ENI
    ENI -.->|"Protected by"| CSG
    ENI --> RT1
    RT1 --> PLNET

    APP --> NLB
    APP -.->|"Protected by"| PSG
    NLB --> EPSVC
    EPSVC --> PLNET

    PLNET -.->|"DNS Resolution"| DNS

    classDef awsService fill:#FF9900,stroke:#232F3E,stroke-width:2px,color:white;
    classDef networkComponent fill:#4595D4,stroke:#232F3E,stroke-width:2px,color:white;
    classDef vpc fill:#E7F4FF,stroke:#232F3E,stroke-width:2px;
    classDef security fill:#DD3522,stroke:#232F3E,stroke-width:2px,color:white;
    classDef shared fill:#95DAB6,stroke:#232F3E,stroke-width:2px,color:black;

    class APP,NLB awsService;
    class EC2,ENI,EPSVC,PLNET networkComponent;
    class consumer,provider,consumerPrivate,providerPrivate vpc;
    class CSG,PSG security;
    class DNS,shared shared;

    %% Add notes
    subgraph Notes
        direction LR
        note1["1. Secure private connectivity\nbetween VPCs"]
        note2["2. No Internet Gateway or\nVPN required"]
        note3["3. Traffic stays on AWS\nnetwork"]
        note4["4. Endpoint services support\nNetwork Load Balancer"]
    end
```
