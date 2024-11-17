# DDoS

## Understanding Layer 4 DDoS Attacks

### TCP SYN Flood Attack

A Layer 4 Distributed Denial of Service (DDoS) attack often manifests as a SYN flood attack, targeting the transport layer of the OSI model where TCP operates.

#### TCP Three-Way Handshake

Normal TCP connection establishment requires a three-way handshake:

1. Client sends SYN (synchronize) packet to server
2. Server responds with SYN-ACK (synchronize-acknowledge) packet
3. Client completes handshake with ACK (acknowledge) packet

Only after this handshake is complete can Layer 7 (Application Layer) begin transmitting data.

#### SYN Flood Attack Mechanism

In a SYN flood attack:

1. Attacker sends numerous SYN packets, often with spoofed source IP addresses
2. Server responds with SYN-ACK packets to each request
3. Attacker intentionally ignores the SYN-ACK responses
4. Server maintains half-open connections, allocating resources while waiting for the final ACK
5. Server's connection pool becomes exhausted, preventing legitimate connections

### Amplification DDoS Attacks

Amplification (or reflection) attacks exploit protocols that generate responses significantly larger than the initial request.

#### Common Amplification Protocols

* DNS (Domain Name System)
* NTP (Network Time Protocol)
* SSDP (Simple Service Discovery Protocol)
* SNMP (Simple Network Management Protocol)
* CharGEN (Character Generation Protocol)

#### Anatomy of an Amplification Attack

1. **Initial Setup**:
   * Attacker spoofs victim's IP address
   * Attacker identifies vulnerable third-party servers (reflectors)
2. **Attack Execution**:
   * Attacker sends small requests to reflectors using victim's spoofed IP
   * Reflectors send amplified responses to the victim
   * Victim receives multiple large responses, overwhelming their resources

#### Example: DNS Amplification

* Request size: \~60-70 bytes
* Response size: \~3000-4000 bytes
* Amplification factor: 28-54x

**Attack Process:**

1. Attacker sends small DNS query (ANY record request) to open DNS resolver
2. Query is crafted to generate maximum response size
3. DNS resolver sends large response to victim's IP address
4. Multiple reflectors can be used simultaneously to increase attack volume

#### Amplification Factor Calculation

```
Amplification Factor = Response Size / Request Size
Example: 3000 bytes / 60 bytes = 50x amplification
```

### Prevention Measures

1. Rate limiting incoming SYN packets
2. Implementing SYN cookies
3. Configuring proper timeout values
4. Using DDoS mitigation services
5. Properly configuring DNS resolvers to prevent reflection attacks

<figure><img src="../../../../.gitbook/assets/image (20).png" alt=""><figcaption></figcaption></figure>

### Layer 7 DDoS attack&#x20;

This involves a Web Server receiving a flood of GET or POST requests, usually from a botnet or a large number of compromised computers.

