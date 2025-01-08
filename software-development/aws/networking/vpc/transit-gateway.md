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
