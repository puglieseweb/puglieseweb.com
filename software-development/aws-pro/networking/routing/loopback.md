# Loopback

The **loopback CIDR** is **127.0.0.0/8**.

### **Loopback Network Details**

**CIDR Range**: 127.0.0.0/8\
**IP Range**: 127.0.0.0 to 127.255.255.255\
**Subnet Mask**: 255.0.0.0\
**Total Addresses**: 16,777,216 addresses

### **Most Common Loopback Address**

**127.0.0.1** - The standard "localhost" address

### **Key Characteristics**

**Purpose**:

* Internal host communication
* Testing and diagnostics
* Applications talking to themselves

**Behavior**:

* Traffic **never leaves** the local machine
* No physical network interface required
* Always available (even without network connectivity)

### **Examples of Loopback Usage**

```bash
bashping 127.0.0.1          # Test local TCP/IP stackcurl http://127.0.0.1   # Connect to local web serverssh 127.0.0.1           # SSH to local machine
```

### **AWS Context**

In AWS, you'll encounter loopback when:

* **EC2 instances** use 127.0.0.1 for local services
* **Container networking** (Docker uses 127.0.0.1 inside containers)
* **Application configuration** (databases, web servers binding to localhost)

### **RFC Standard**

**RFC 5735** officially defines 127.0.0.0/8 as:

* "Loopback addresses"
* Reserved for internal host loopback communication
* Must not appear on any network

**Bottom line**: 127.0.0.0/8 is the complete loopback CIDR range, with 127.0.0.1 being the most commonly used address within that range.
