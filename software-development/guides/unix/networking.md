---
description: Basic networking concepts and related firewall rules
---

# Networking

## Network Interface Cards  \(NICs\)

`ifconfig` can be used to list all available Network Interface Cards \(NICs\).

## Understanding Network Interface Naming convention in CentOS

In Centos7 new predictable network interface name conventions have been introduced. For example, the naming convention used to identify an ethernet card are: **type**, **bus**, and **slot:**

> _enp3sp indicate_ an Ethernet device, bus 3, slot 0.

The types of network interfaces are:

| NIC name | Description |
| :--- | :--- |
| en | ethernet |
| sl | Serial line IP \(slip\) |
| wl | wlan |
| ww | swa |
| lo | Loopback interface |
|  |  |

### Deprecated naming conventions

| NIC name | Description |
| :--- | :--- |
| eth0, eth1 | Internet-connected interface  |
| wlan0, wlan1 | wlan |

