# VPG

A **VPG (Virtual Private Gateway) is associated with a specific AWS account** and exists within that account's resources.

### **VPG Account Association**

#### **Account-Specific Resource**

```
AWS Account: 123456789012
├── VPC: vpc-12345
├── VPG: vgw-67890  ← Belongs to this account
└── Subnets, Route Tables, etc.
```

#### **VPG Characteristics**

* **Created within** a specific AWS account
* **Billed to** that AWS account
* **Managed by** that account's IAM users/roles
* **Visible only** to that account (unless shared)

### **Cross-Account Scenarios**

#### **1. VPG Cannot Be Directly Shared**

**Unlike some AWS resources, VPGs cannot be shared across accounts:**

```
Account A: VPG-A (cannot be used by Account B)
Account B: VPG-B (cannot be used by Account A)
```

#### **2. Each Account Needs Its Own VPG**

**For cross-account connectivity:**

```
Account A:
├── VPC-A (10.0.0.0/16)
├── VPG-A (vgw-aaaa)
└── Direct Connect VIF → VPG-A

Account B:
├── VPC-B (10.1.0.0/16)  
├── VPG-B (vgw-bbbb)
└── Direct Connect VIF → VPG-B
```
