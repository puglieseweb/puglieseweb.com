# Assigning Static IPs to NLBs with ALB Target Groups

During this hands-on lab you will learn how to deploy an internet-facing Network Load Balancer (NLB) with a static Elastic IP Address (EIP) assigned to it. The NLB will then front a private Application Load Balancer (ALB) as a Target Group, and the ALB will be fronting an Auto Scaling Group of internal web servers. This allows you to statically reference the internet-facing NLB while still leveraging the ALB and its capabilities!



When you are using and ALB the IP address for a give DNS name are constantly revolving, and if you are using an external DNS system you cannot create alias, like you could in Route 53. To overcome this issue we assing static IPs to a network load balancer

<figure><img src="../../../.gitbook/assets/image (36).png" alt=""><figcaption></figcaption></figure>

## 1Create Target Group

* Create a new Target Group (TG) within your custom VPC that contains the `OurApplicationLoadBalancer` Internet-facing Application Load Balancer as a target.
* Name the TG `ApplicationLoadBalancer-tg`
* Ensure you are passing `TCP` traffic via port `80`
* Health checks should be `/` via `HTTP`

### 2Create Elastic IP Addresses (EIPs)

* Create `3` new Elastic IP address within the Custom VPC within the `us-east-1` region

## 3Create Network Load Balancer and Listener

* Create an Internet-facing Network Load Balancer (NLB) within the custom VPC
* Choose for the NLB to span all 3 Availability Zones
* Name the NLB `WebServer-nlb`
* Attach the following Security Group: `AllowTCPFromEverywhere`
* Ensure you assign one Elastic IP Address for each subnet (_there should be 3_) that an NLB node lives in
* Create a new listener for `TCP` traffic over `Port 80` for your NLB
* Se the default action to forward traffic to the newly created `ApplicationLoadBalancer-tg`
