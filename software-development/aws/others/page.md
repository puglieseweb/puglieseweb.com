# Page

A company hosts its application using Amazon Elastic Container Service (Amazon ECS) and wants to ensure high availability. The company wants to be able to deploy updates to its application even if nodes in one Availability Zone are not accessible. The expected request volume for the application is 100 requests per second, and each container task is able to serve at least 60 requests per second. The company set up Amazon ECS with a rolling update deployment type with the minimum healthy percent parameter set to 50% and the maximum percent set to 100%. Which configuration of tasks and Availability Zones meets these requirements?



**A.** Deploy the application across two Availability Zones, with one task in each Availability Zone. **B.** Deploy the application across two Availability Zones, with two tasks in each Availability Zone. **C.** Deploy the application across three Availability Zones, with one task in each Availability Zone. **D.** Deploy the application across three Availability Zones, with two tasks in each Availability Zone





Let's analyze this in detail:

1. Requirements Analysis:
   * Need high availability across AZs
   * Must support rolling updates
   * Must handle 100 requests/second
   * Each container can handle 60 requests/second
   * Rolling update config: 50% minimum healthy, 100% maximum
2. Task Capacity Requirements:
   * Total needed capacity: 100 requests/second
   * Each task handles: 60 requests/second
   * Minimum tasks needed: ⌈100/60⌉ = 2 tasks
   * During updates: Must maintain 50% capacity minimum
3. High Availability and Rolling Updates:
   * Need tasks spread across multiple AZs for HA
   * During rolling updates, with 50% minimum healthy:
     * Need total tasks = 3 (so 50% = 1.5 rounded up to 2)
     * This ensures 2 tasks remain running during updates
     * Maintains capacity: 2 × 60 = 120 requests/second
4.  Analyzing Each Option: A. Two AZs, one task each:

    * Total capacity: 2 × 60 = 120 requests/second
    * Problem: During updates, only 1 task running (50%)
    * Only 60 requests/second during updates
    * INSUFFICIENT during updates

    B. Two AZs, two tasks each:

    * Total capacity: 4 × 60 = 240 requests/second
    * OVERPROVISIONED
    * Higher cost than necessary

    C. Three AZs, one task each:

    * Total capacity: 3 × 60 = 180 requests/second
    * During updates: 2 tasks remain = 120 requests/second
    * Provides AZ redundancy
    * Meets capacity requirements
    * Cost-effective
    * OPTIMAL SOLUTION

    D. Three AZs, two tasks each:

    * Total capacity: 6 × 60 = 360 requests/second
    * Significantly OVERPROVISIONED
    * Unnecessary cost
5. Why C is Best:
   * Provides optimal task distribution
   * Meets capacity requirements (180 req/sec)
   * Maintains sufficient capacity during updates (120 req/sec)
   * Offers AZ redundancy
   * Cost-effective solution
   * Can lose an entire AZ and still maintain service
   * Supports rolling updates while maintaining required capacity

This solution offers the best balance of:

* High availability
* Cost efficiency
* Capacity requirements
* Update strategy support
* Fault tolerance
