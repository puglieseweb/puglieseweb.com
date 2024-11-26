# Spot instances VS Spot Fleet

Spot Instances are individual EC2 instances available at discounted prices compared to On-Demand rates. They can be interrupted with 2 minutes notice when AWS needs the capacity back.

Spot Fleet is a higher-level service that automatically manages a collection of Spot Instances, giving you more flexibility and fault tolerance. Here are the key differences:

1. Management and Scaling:

* Spot Instances: You manage each instance individually
* Spot Fleet: Automatically manages a pool of instances based on your specifications, maintaining target capacity and optimizing costs

2. Instance Type Flexibility:

* Spot Instances: One instance type per request
* Spot Fleet: Can use multiple instance types and purchase options (both Spot and On-Demand) in a single pool

3. Pricing Strategy:

* Spot Instances: Based on Spot pricing for specific instance type
* Spot Fleet: Can optimize for lowest price, capacity, or instance diversity across multiple instance types

4. Availability Zones:

* Spot Instances: Single AZ per instance
* Spot Fleet: Can span multiple AZs for better availability

5. Capacity Management:

* Spot Instances: Manual replacement if interrupted
* Spot Fleet: Automatic replacement of interrupted instances to maintain target capacity

6. Use Cases:

* Spot Instances: Good for simple, single-instance workloads
* Spot Fleet: Better for larger deployments, batch processing, or workloads requiring high availability
