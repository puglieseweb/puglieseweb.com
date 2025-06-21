# Transit Gateway

AWS Transit Gateway connects VPCs and on-premises networks through a central hub. This simplifies your network and puts an end to complex peering relationships. **It acts as a cloud router** - each new connection is only made once.

<figure><img src="../../../../.gitbook/assets/AWS - Transit Gateway.png" alt=""><figcaption></figcaption></figure>



Key facts:&#x20;

* Allows to have transitive peering between thousands of VPCs and on-premises data centers.&#x20;
* Works on a hub-and-spoke model.
* Works on a regional basis, but you can have it across multiple regions.
* You can use it across multiple AWS accounts using RAM (Resource Access Manager).
* You can use route tables to limit how VPCs talk to one another.
* Works with Direct Connect as well as VPN connections.
* Support IP multicast (not supported by any other AWS service)



### IP Multicast

IP multicast support means a network device or system can handle multicast communication protocols, where data is transmitted from one sender to multiple receivers simultaneously.

Here's what this involves:

**Basic concept**: Instead of sending separate copies of data to each recipient (unicast) or broadcasting to everyone on a network (broadcast), multicast sends one copy that gets replicated only where needed along the network path.

**Key technical aspects**:

* **Multicast addressing**: Uses special IP address ranges (224.0.0.0 to 239.255.255.255 for IPv4)
* **Group management**: Devices can join or leave multicast groups using protocols like IGMP (Internet Group Management Protocol)
* **Efficient routing**: Routers create distribution trees to deliver data only to network segments with interested receivers
* **Protocol support**: Handles multicast-specific protocols and packet forwarding rules

**Common applications**:

* Video streaming and live broadcasts
* Software updates distributed to multiple computers
* Real-time financial data feeds
* Online gaming with multiple players
* Video conferencing

**Why it matters**: Multicast is much more bandwidth-efficient than sending individual streams to each recipient. For example, streaming video to 1000 users requires only one stream from the source rather than 1000 separate streams.

When a device lists "IP multicast support," it means it can participate in these multicast communications either as a sender, receiver, or intermediate router that properly forwards multicast traffic.

