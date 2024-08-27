# VPC Endpoint

VPC Endpoints are virtual devices horizontally scalable, redudant, and highly available VPC components that allow communication between instances in your VPC and services without imposing availability risks or bandwidth constraints on your network traffic.

A VPC endpoint enables to privately connect VPC to  supported AWS services and VPC endpoints services powered by PriviateLink without requiring an internet gateway, NAT device, VPN connect, or AWS Direct Connect connection.&#x20;



**Traffic between a VPC and other services do not leave the Amzaon Network**. Instances in the VPC do not require public IP addresses to communicate with resources in the service.

As opposite to a NAT Gateway that has a limit on the bandwidth. For example if we have got and EC2 instance ghat is connecting to an S3 service we want to use a VPN endpoint and not a NAT Gateway.



There are 2 types of endpoints:

1. Interface endpoints. These are **elastic network interface with a private IP address** that server as entry point for traffic haded to a supported service. They support a large number of AWS services.
2. Gateway endpoints. Similar to NAT gateway, these are **virtual device we provision**. It supports connection to S3 and DynameDB.
