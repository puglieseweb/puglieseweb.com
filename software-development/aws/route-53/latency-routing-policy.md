# Latency Routing Policy

Allows to route traffic based on the lowest network latency for your end user (i.e, which region will give the fasted response time).



## Latency Resource Record Set

To use the latency-based routing, you create a **latency resource record set** for the EC2 (or ELB) resource in each region that hosts your website.

When Route 53 receives a query for your site, it selects the latency resource record set for the region that gives the user the lowest latency.
