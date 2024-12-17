# Route 53

Route 53 is Amazon's DNS service. It allows to register domain names, create hosted zones, manage and create DNS records.&#x20;

Route 53 is named after Route 66 (one of the original highways across the United States) but is called 53 because DNS operate on port 53.&#x20;

There are 7 Routing Policies available with Route 53:

1. Simple Routing
2. Weighted Routing
3. Latency-Based Routing
4. Failover Routing
5. Geolocation Routing&#x20;
6. Geoproximity Routing (Traffic Flow Only)
7. Multivalue Answer Routing

| Routing Policy            | Description                                                                                      | Use Case                                                                                    | Multiple Values Support     | Health Check Support | Advantages                                                                                                      | Limitations                                                                                                         |
| ------------------------- | ------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------- | --------------------------- | -------------------- | --------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| Simple Routing            | Routes traffic to a single resource. If multiple values are specified, R53 returns one randomly. | Basic routing to a single resource or random selection from multiple values.                | Yes (returns random)        | No                   | <p>• Easy to configure<br>• Low maintenance<br>• Cost-effective</p>                                             | <p>• No intelligent routing<br>• No failover capability<br>• No traffic distribution control</p>                    |
| Weighted Routing          | Distributes traffic based on assigned weights (0-255).                                           | <p>• A/B testing<br>• Gradual migration<br>• Load distribution</p>                          | Yes                         | Yes                  | <p>• Precise traffic control<br>• Supports gradual migration<br>• Good for testing</p>                          | <p>• Requires manual weight management<br>• Doesn't consider endpoint health by default</p>                         |
| Latency-Based Routing     | Routes based on lowest network latency for end user.                                             | Global applications requiring fastest response time.                                        | Yes                         | Yes                  | <p>• Improved user experience<br>• Automatic routing to fastest region<br>• Global performance optimization</p> | <p>• Requires resources in multiple regions<br>• Higher cost<br>• Latency data must be gathered first</p>           |
| Failover Routing          | Routes traffic to primary resource, fails over to secondary if primary is unhealthy.             | Critical applications requiring high availability.                                          | No (Primary/Secondary only) | Yes (Required)       | <p>• High availability<br>• Automatic failover<br>• Disaster recovery support</p>                               | <p>• Limited to primary/secondary setup<br>• Requires health checks (additional cost)<br>• Binary failover only</p> |
| Geolocation Routing       | Routes based on geographic location of users (continent, country, or state).                     | <p>• Content localization<br>• Restriction compliance<br>• Regional distribution</p>        | Yes                         | Yes                  | <p>• Geographic control<br>• Regulatory compliance<br>• Content customization</p>                               | <p>• Requires default location<br>• Location accuracy depends on IP<br>• Complex setup for many regions</p>         |
| Geoproximity Routing      | Routes based on geographic location of resources and optionally biases traffic flow.             | <p>• Global load balancing<br>• Regional expansion<br>• Traffic shifting across regions</p> | Yes                         | Yes                  | <p>• Fine-grained traffic control<br>• Support for bias values<br>• Dynamic regional distribution</p>           | <p>• Requires Route 53 Traffic Flow<br>• More expensive<br>• Complex configuration</p>                              |
| Multivalue Answer Routing | Returns multiple healthy records selected at random.                                             | Improving availability and load balancing for non-AWS resources.                            | Yes (up to 8 records)       | Yes                  | <p>• Basic load balancing<br>• Health check support<br>• Improved availability</p>                              | <p>• Limited to 8 healthy records<br>• Random selection only<br>• No weighted distribution</p>                      |

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

<figure><img src="../../../.gitbook/assets/image (19) (1).png" alt=""><figcaption></figcaption></figure>

### NS record

Name Server (NS) records are used by top-level domain servers to direct traffic to the content DNS server that contains the authoritative DNS records.



Example&#x20;

if I type "puglieseweb.com" the brower goes to the ".com" record and looks up the "puglieseweb" record. The browser will be given an NS record (e.g. ns.awsdns.com). ns.awsdns.com is where the SOA is stored.

&#x20;ns.awsdns.com's A record is an IP Adrress&#x20;

<figure><img src="../../../.gitbook/assets/image (34).png" alt=""><figcaption></figcaption></figure>



## DNS Records

### A Record

An address (A) record is the fundamental type of DNS record. The A record is used by a computer to translate the name of the domain to an IP Address.&#x20;

### TTL

Time to live (TTL) is the length that a DNS record is cached on either the resolving server or the user's own local PC. This ifluence how fast changes to a DNS records take to propagete through the internet. For example the TTL can be lowered from 24 hours to 5 min the day before a migration of an IP address.&#x20;

### CNAME

A CNAME (canonical name) can be used to resolve one domain name to another. For example, you may have a mobile website with the domain name http://m.puglieseweb.com that is used for when users browse to your domain name on their mobile devices. CNAME allows to map for example http://mobile.puglieseweb.com to resolve to the same IP address of http://m.puglieseweb.com

CNAMEs cannot be used for naked domain names (zone apex record). You cannot have a CNAME for http://puglieseweb.com

### Alias Records

Alias records exists within a  domain registrar spac&#x65;**.** Alias records are used to map resource record sets in your hosted zone to load balancers, CloudFront distributions, or S3 buckets that are configured as websites.&#x20;

Alias records work like a CNAME record in that you can map one DNS name (www.puglieseweb.com) to  another "target" DNS name (elb1234.elb.amazonaws.com).



