# Route 53

Route 53 is Amazon's DNS service. It allows to register domain names, create hosted zones, manage and create DNS records.&#x20;

Route 53 is named after Route 66 (one of the original highways across the United States) but is called 53 because DNS operate on port 53.&#x20;

There are 7 Routing Polices available with Route 53:

1. Simple Routing
2. Weighted Routing
3. Latency-Based Routing
4. Failover Routing
5. Geolocation Routing&#x20;
6. Geoproximity Routing (Traffic Flow Only)
7. Multivalue Answer Routing

## What is a DNS server?

All top-level domain are in the root zone DB: www.iana.org/domains/root/db

A **domain registrar** (GoDaddy, AWS, etc.) can assing a domain name under one or more top-level domains.&#x20;

These domains are registered with InterNIC, a service of ICANN, which enforces uniqueness of domain names across the internet. All domain name are stored in the central WHOIS database.

### SOA record

A start of authority (SOA) record includes administrative information about the zone, as defined by the domain name system (DNS):

* The name of the server that supplied the data for the zone
* The administrator of the zone
* The current version of the data file
* The default number of seconds for the time-to-live file on resource records

<figure><img src="../../../.gitbook/assets/image (15).png" alt=""><figcaption></figcaption></figure>

### NS record

Name Server (NS) records are used by top-level domain servers to direct traffic to the content DNS server that contains the authoritative DNS records.



Example&#x20;

if I type "puglieseweb.com" the brower goes to the ".com" record and looks up the "puglieseweb" record. The browser will be given an NS record (e.g. ns.awsdns.com). ns.awsdns.com is where the SOA is stored.



## DNS Records

### A Record

An address (A) record is the fundamental type of DNS record. The A record is used by a computer to translate the name of the domain to an IP Address.&#x20;

### TTL

Time to live (TTL) is the length that a DNS record is cached on either the resolving server or the user's own local PC. This ifluence how fast changes to a DNS records take to propagete through the internet. For example the TTL can be lowered from 24 hours to 5 min the day before a migration of an IP address.&#x20;

### CNAME

A CNAME (canonical name) can be used to resolve one domain name to another. For example, you may have a mobile website with the domain name http://m.puglieseweb.com that is used for when users browse to your domain name on their mobile devices. CNAME allows to map for eample http://mobile.puglieseweb.com to resolve to the same IP address of http://m.puglieseweb.com

CNAMEs cannot be used for naked domain names (zone apex record). You cannot have a CNAME for http://puglieseweb.com

### Alias Records

Alias records exists within a  domain registrar space**.** Alias records are used to map resource record sets in your hosted zone to load balancers, CloudFront distributions, or S3 buckets that are configured as websites.&#x20;

Alias records work like a CNAME record in that you can map one DNS name (www.puglieseweb.com) to  another "target" DNS name (elb1234.elb.amazonaws.com).



