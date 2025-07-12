# IP Address allocation

Out of the 126 usable Class A networks (1-126):

* **125 are designated for public use** (networks 1-9 and 11-126)
* **1 is reserved for private use** (network 10)



**Reserved/Special Use Networks:**

* **10.0.0.0/8** - Reserved for private use (RFC 1918)
* **127.0.0.0/8** - Loopback addresses (though this is technically outside the 1-126 range)

**Early Internet Assignments:** Many Class A networks were assigned in the early days of the internet to large organizations, universities, and government entities. Some notable examples:

* MIT, Stanford, IBM, General Electric, AT\&T, and other major institutions received entire Class A blocks
* The U.S. Department of Defense received several Class A networks

**Current Reality:**

* Some originally assigned Class A networks have been **returned** to IANA for redistribution
* Others have been **subdivided** using CIDR (Classless Inter-Domain Routing) rather than being used as single massive networks
* The rigid Class A/B/C system has been largely replaced by CIDR, which allows for more flexible network sizing

**IPv4 Exhaustion:** Since IPv4 addresses are now exhausted (the last blocks were allocated in 2011), the focus has shifted to IPv6 deployment rather than trying to utilize every possible Class A network.



The RFC 1918 **private IP address ranges are:**

**Class A:**

* **10.0.0.0/8** (10.0.0.0 to 10.255.255.255) - Only one Class A private range

**Class B:**

* **172.16.0.0/12** (172.16.0.0 to 172.31.255.255) - 16 Class B private ranges

**Class C:**

* **192.168.0.0/16** (192.168.0.0 to 192.168.255.255) - 256 Class C private ranges



The **technical definition** of "10.0.0.0/8" would be:

**"Network 10.0.0.0 with an 8-bit subnet mask"** or **"Network 10.0.0.0 with a /8 prefix length"**

