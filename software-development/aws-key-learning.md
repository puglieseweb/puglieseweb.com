# AWS Key Learning



## Handling External certificates

An application needs to be encrypted at the edge with an SSL/TLS certificate that is issued by an external certificate authority (CA). The certificate must be rotated each year before the certificate expires.

Use AWS Certificate Manager (ACM) to import an SSL/TLS certificate. Apply the certificate to the ALB. Use Amazon EventBridge (Amazon CloudWatch Events) to send a notification when the certificate is nearing expiration. Rotate the certificate manually.



## Hybrid Architecture Failover

A solutions architect is designing a new hybrid architecture to extend a company's on-premises infrastructure to AWS. The company requires a highly available connection with consistent low latency to an AWS Region. The company needs to minimize costs and is willing to accept slower traffic if the primary connection fails.

This Option:

* Provides a highly available, low-latency primary connection with Direct Connect
* Offers a cost-effective backup solution with VPN
* Meets the requirement of accepting slower traffic in failover scenarios
* Provides a clear and reliable failover mechanism
* Aligns well with hybrid architecture best practices
