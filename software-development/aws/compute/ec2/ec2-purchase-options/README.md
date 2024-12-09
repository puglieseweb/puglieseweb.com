# EC2 Purchase Options

Here are all the main options:

### **On-Demand Instances**

* Pay by the hour/second with no commitment
* Most flexible but most expensive
* Best for short-term, unpredictable workloads
* No upfront costs or contracts

### **Reserved Instances (RI)**

* Commit to 1 or 3 years for significant discounts (up to 72%)
* Three payment options:
  * All upfront (highest discount)
  * Partial upfront
  * No upfront (lowest discount)
* Best for steady, predictable workloads

### **Savings Plans**

* Newer, more flexible version of RIs
* Commit to hourly spend for 1 or 3 years
* More flexible than RIs as they work across:
  * Different instance types
  * AWS regions
  * EC2, Lambda, and Fargate

### **Spot Instances/Fleet (as discussed earlier)**

* Up to 90% discount
* Can be interrupted
* Best for flexible, fault-tolerant workloads

### **Dedicated Hosts**

* Physical servers dedicated to your use
* Most expensive option
* Used for:
  * Compliance requirements
  * License management (like Oracle licenses)
  * Legacy server-bound software licenses

### **Capacity Reservations**

* Reserve capacity in specific AZ
* Pay On-Demand rates
* No discount but guarantees availability
* Good for short-term, capacity-critical workloads
