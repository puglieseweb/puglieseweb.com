# Discovery Service

This services:

* Helps to plan migration to AWS via collection of usage and configuration from on-premises servers
* Integrates with AWS Migration Hub
* Helps you easily view discovered servers, group them by application, and track each application migration

Two types of discoveries:

* Agentless Collector: install an OVA file within the VMware vCenter. Identifies hosts and VMs, IP and MAC addresses, resource allocation, host names and Utilization data metrics
* Agent Based:&#x20;
  * Deploy AWS Application Discovery Agent in each VM and physical server.&#x20;
  * There is an installer for both Windows and Linux
  * Retrieve static configuration data, time-series, performance info, network connections, and OS processes&#x20;
