---
description: Basic firewall concepts and configuration
---

# Firewall

`iptables` is a command-line firewall utility that uses policy chains to allow or block traffic.

All the packets going through the firewall will require to traverse the  input and output chain of rules. Following an example of of how to set up rules using `iptables`: 

```bash
# Remove any existing rules from all chains
iptables --flush

# Unlimited traffic on the loopback interface
iptables -A INPUT -i lo -j ACCEPT
iptables -A OUTPUT -o lo -j ACCEPT

# Set the default policy to drop
iptables --policy INPUT DROP
iptables --policy OUTPUT DROP
iptables --policy FORWARD DROP
```

To list all the ip4 rules execute:

```bash
sudo iptables -S
```

