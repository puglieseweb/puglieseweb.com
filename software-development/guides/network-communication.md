---
description: Network Communication For Distributed Systems
---

# TCP/IP Network Model

In contest of Distributed Systems, the TCP/IP Network Model defines a four layer abstraction model.

![](<../../.gitbook/assets/image (12) (1) (1).png>)

| 192.158. 1.38                             | :8081                                       | /path                                          |
| ----------------------------------------- | ------------------------------------------- | ---------------------------------------------- |
| <p>Internet Layer</p><p>(IP protocol)</p> | <p>Transport Layer</p><p>(TCP protocol)</p> | <p>Application Layer</p><p>(HTTP protocol)</p> |

## Layer 1 - Data Link

The Data Link layer uses the **Ethernet protocol** to wrap data packets into frames and move them form one device MAC address to another device MAC address (Ethernet deals with physical hardware)

## Layer 2 - Internet

The Internet layer uses the **IP protocol** to route the data packets from one from the destination IP address (public address identifying a computer on the Internet) to the source IP address

## Layer 3 - Network

The Network layer we can use two protocols: TCP and UDP.

UDP protocol is used for real-time video streaming, gaming, broadcasting and other use case. in UDP protocol data frames can be lost but there is less latency compared to TCP.

TCP protocol uses Ports to identify the process where the data needs to be delivered. TCP is:

* Reliable - Guarantees data delivery as sent, without any losses
* It is based on a connection between 2 points that need to be created before data is sent
* Sends a stream of bytes (and not UDP data frames).

In TCP if two sources are connection to the same destination then two different sockets are open to access the process on the same Port.

## Layer 4 - Application

The Application Layer can use different _Protocols to frame the data that then is steamed using the TCP protocol by the network Layer._

Common protocols are:

| Protocol                             | Purpose                                  |
| ------------------------------------ | ---------------------------------------- |
| FTP (File Transfer Protocol)         | Transfering files through the web        |
| SMTP (Simple Mail Transfer Protocol) | Sending and receiving emails             |
| DNS (Domain Name System)             | Translating host names into IP addresses |
| HTTP (Hypertext Transfer Protocol)   | Transmitting Hypermedia documents        |
