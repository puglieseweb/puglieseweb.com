# DDoS

A Layer 4 Distributed Denial of Service (DDoS) attack is often refered as SYN flood. It works at transport layer (TCP).

### Layer 4 DDoS attack

TCP connection requires a 3-ways handshake. The client send a SYN packet to the server, the server replies with a SYN-ACK, adn the client then responds to that with an ACK. After the 3-ways handskake done at Layer 4 the connection is established and Layer 7 can start sending application data.&#x20;

In a DDoS attack the client attacker ignore the SYN-ACKs returned by the server and send a large number or SYN packets. This causes the server to use up resources waiting for a set amount of time for the anticipated ACK that should come from a a legitimate client. This causes the server eat through the allowed number of TCP connections, preventing legitimate requests.&#x20;

### Amplification DDoS Attack

Amplification/reflection attacks can include things such as NTP, SSDP, DNS, GharGEN, SNMP attacks.  This is where an attacker may send a third-party server a request using a spoofed IP address.&#x20;

An Amplified Attack, more commonly known as an Amplification Attack or Reflection Amplification Attack, exploits certain protocols and services to generate a large amount of traffic from a relatively small initial request. The specific amplification factor you mentioned (28-54 times) is likely referring to a particular type of amplification attack, possibly the DNS amplification attack. Let me explain why this happens:

1. Protocol Exploitation: Amplification attacks take advantage of protocols that allow for responses significantly larger than the initial request. Common protocols used include DNS, NTP (Network Time Protocol), and SNMP (Simple Network Management Protocol).
2. DNS Amplification Example: In a DNS amplification attack, which often achieves the amplification factor you mentioned:
   * The attacker sends a small DNS query (typically 60-70 bytes) to an open DNS resolver.
   * The query is crafted to elicit a large response, often requesting all known information about a domain (ANY record).
   * The DNS server responds with a much larger packet (potentially 3000-4000 bytes).
3. Amplification Factor: The amplification factor is calculated by dividing the size of the response by the size of the request. In the DNS example: 3000 bytes (response) / 60 bytes (request) ≈ 50 times amplification

<figure><img src="../../../../.gitbook/assets/image (17).png" alt=""><figcaption></figcaption></figure>

### Layer 7 DDoS attack&#x20;

This involves a Web Server receiving a flood of GET or POST requests, usually from a botnet or a large number of compromised computers.

