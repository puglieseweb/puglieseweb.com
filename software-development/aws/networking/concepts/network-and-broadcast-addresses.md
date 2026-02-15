# Network and Broadcast Addresses

A **Network Address** is the first address in a subnet and identifies the network itself. It has all host bits set to 0. For example, in a subnet 192.168.1.0/24:

* The network address would be 192.168.1.0
* This address cannot be assigned to any device
* It serves as the identifier for the entire network segment

A **Broadcast Address** is the last address in a subnet and is used to communicate with all devices in that network simultaneously. It has all host bits set to 1. In our 192.168.1.0/24 example:

* The broadcast address would be 192.168.1.255
* When a device sends traffic to this address, every device in that subnet receives the message
* Like the network address, it cannot be assigned to any device

To illustrate with a smaller subnet example, let's look at 192.168.1.16/28 (16 addresses):

* Network Address: 192.168.1.16
* Valid Host Addresses: 192.168.1.17 - 192.168.1.30
* Broadcast Address: 192.168.1.31
