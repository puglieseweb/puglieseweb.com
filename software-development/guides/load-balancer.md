# Load Balancer

There are two types of load balancers: hardware and software.

There are different Load balancing strategies

|  |  |
| :--- | :--- |
| Round Robin | each server get one request in turn. This guaranty ad equal load distribution across all the server |
| Weighted Road Robing | each server is given a weigh used to load balance the traffic. This is useful in case we have asymmetry in our server, or if we want to release a newer version of our application software more gradually by upgrading only one of our server to the new version and reduce the impact if something goes wrong |
| Source IP Hash  | This is to allow the same user to continue communication with the same server. This is useful to keep Open Session \(e.g. maintain the online shopping cart state between connection loses or browser refresh\). See [Sticky sessions](https://www.imperva.com/learn/availability/sticky-session-persistence-and-cookies/). |



