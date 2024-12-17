# Load Balancer

There are two types of load balancers: hardware and software.

There are different Load balancing strategies

|                        |                                                                                                                                                                                                                                                                                                                                                                                            |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Round Robin            | each server get one request in turn. This guaranty ad equal load distribution across all the server. In this strategy the actual server load is not taken in account.                                                                                                                                                                                                                      |
| Weighted Road Robing   | each server is given a weigh used to load balance the traffic. This is useful in case we have asymmetry in our server, or if we want to release a newer version of our application software more gradually by upgrading only one of our server to the new version and reduce the impact if something goes wrong. In this strategy the actual server load is not taken in account.          |
| Source IP Hash         | This is to allow the same user to continue communication with the same server. This is useful to keep Open Session (e.g. maintain the online shopping cart state between connection loses or browser refresh). See [Sticky sessions](https://www.imperva.com/learn/availability/sticky-session-persistence-and-cookies/). In this strategy the actual server load is not taken in account. |
| Least Connections      | Servers that already have many open connection are assumed to be more busy and receive less additional requests than server than have fewer open connections                                                                                                                                                                                                                               |
| Weighted Response Time | the load balancer send health check request to each server and measures the time each server takes to respond. If the server are buys handling real request it will take more time to respond to the heath check and the server is given a lower weight                                                                                                                                    |
| Agent Based Policy     | Installs Agents that check: CPU Utilization, Inbound or outbound Network Traffic (bytes), disk operation (reads/writes), memory utilization, etc.                                                                                                                                                                                                                                          |

![](<../../.gitbook/assets/image (11) (1) (1) (1).png>)

The Load Balanncer can operated at:

* Layer 4 (Transport Layer)
  * the Load Balancer performs simple TCP packets forwarding between the client and the backend servers.
  * It does not inspect the content of the TCP stream beyond the first packets - Low overhead

![](<../../.gitbook/assets/image (5) (1) (1).png>)

* Layer 7 (Application Layer):
  * can make smarter routing decision based on the HTTP header
  * Load balancer inspects TCP packets and HTTP header
  * Can route requests to different clusters of servers based on:
    * Request URL
    * Type of requested data
    * HTTP method
    * Browser cookies
