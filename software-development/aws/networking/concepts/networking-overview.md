# Networking

### **Best Practices to Avoid Clashes**

#### **For Private Networks**

* Use **RFC 1918** ranges: 10.x.x.x, 172.16-31.x.x, 192.168.x.x

**Use proper private ranges:**

```
10.0.0.0/8       ← Large networks
172.16.0.0/12    ← Medium networks  
192.168.0.0/16   ← Small networks
```

### **When Network ID Clashes Don't Matter**

#### **Isolated Private Networks**

Multiple organizations using the same private ranges:

```
Company A: 10.0.0.0/16 (internal only)
Company B: 10.0.0.0/16 (internal only)
```

**No problem**: These networks are completely isolated behind NAT.

#### **AWS VPCs**

```
Your VPC: 10.0.0.0/16
Someone else's VPC: 10.0.0.0/16
```

#### **For Public Networks**

* **Never use someone else's public IP space**
* Get your own allocated public IPs from your ISP
* Use **NAT** to translate private IPs to public
