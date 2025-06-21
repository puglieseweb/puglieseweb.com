# VPC endpoints

There are 3 types of VPC endpoints in AWS:

### Interface Endpoints (Powered by AWS PrivateLink):

* Creates an ENI (Elastic Network Interface) in your VPC
* Used for accessing services using private IP addresses
* Requires security groups
* Commonly used for: SaaS services, AWS services like CloudWatch, SNS, SQS, etc.
* Incurs hourly charges and data processing fees

## Gateway Endpoints:

* A gateway that you specify as a target for a route in your route table
* Only supports two AWS services:
  * Amazon S3
  * DynamoDB
* Free to use
* Region-specific
* Does not require security groups

## Gateway Load Balancer Endpoints:

* Used for deploying, scaling, and managing third-party network virtual appliances
* Examples include: firewalls, intrusion detection systems, deep packet inspection systems
* Uses the GENEVE protocol on port 6081
* Requires security groups
* Commonly used for security appliances and network analysis tools



```mermaid
graph TD
    subgraph "VPC Endpoint Types"
        A[VPC Endpoints]
        
        A --> B[Interface Endpoints<br/>AWS PrivateLink]
        A --> C[Gateway Endpoints]
        A --> D[Gateway Load Balancer<br/>Endpoints]
        
        subgraph "Interface Endpoint Details"
            B --> B1[Uses ENI]
            B --> B2[Requires Security Groups]
            B --> B3[AWS Services<br/>SaaS Applications]
            B --> B4[Charged per hour<br/>& data processed]
        end
        
        subgraph "Gateway Endpoint Details"
            C --> C1[Route Table Entry]
            C --> C2[S3 and DynamoDB only]
            C --> C3[Free to use]
            C --> C4[Region specific]
        end
        
        subgraph "GWLB Endpoint Details"
            D --> D1[Network Appliances]
            D --> D2[Security & Analysis]
            D --> D3[GENEVE Protocol]
            D --> D4[Requires Security Groups]
        end
    end
    
    %% Styling
    classDef default fill:#f9f9f9,stroke:#333,stroke-width:1px
    classDef type fill:#e6f3ff,stroke:#333,stroke-width:2px
    classDef detail fill:#fff2cc,stroke:#333,stroke-width:1px
    
    class A default
    class B,C,D type
    class B1,B2,B3,B4,C1,C2,C3,C4,D1,D2,D3,D4 detail
```

Key differences in use cases:

* Interface Endpoints: When you need private access to AWS or SaaS services
* Gateway Endpoints: When you need free, highly available access to S3 or DynamoDB
* Gateway Load Balancer Endpoints: When you need to insert security or network analysis appliances into your traffic flow





### VPC Gateway Endpoing VS Instance Endpoint

#### VPC Gateway Endpoint

**Route-based service**: It's implemented as special routing entries in your VPC's route tables that redirect traffic destined for S3 (or DynamoDB) through AWS's internal network instead of the internet.

**How it works**:

* You create the Gateway Endpoint in your VPC
* It automatically adds routes to your specified route tables
* These routes point S3 traffic (prefix lists like `com.amazonaws.region.s3`) to the Gateway Endpoint
* No changes needed on individual instances



#### **VPC Interface Endpoints (PrivateLink)**&#x20;

**VPC Interface Endpoints (PrivateLink)** do create:

* Elastic Network Interfaces (ENIs) in your subnets
* These ENIs have private IP addresses
* They act more like "virtual network cards" in your VPC

### Key differences:

**Gateway Endpoint**:

* Route table entries only
* No ENIs or IP addresses
* Traffic routing happens at the VPC level
* Free of charge

**Interface Endpoint**:

* Creates actual ENIs with IP addresses
* More like virtual network interfaces
* Charges apply

#### From the instance perspective:

Your EC2 instances don't see any difference - they make normal S3 API calls using the same AWS SDKs and CLI commands. The Gateway Endpoint transparently routes this traffic through AWS's private network instead of going out to the internet and back.
