# CIDR ranges examples

1. What's the number of available IP addresses in:

* /24 = 256 IPs (254 usable)
* /28 = 16 IPs (14 usable)
* /27 = 32 IPs (30 usable)
* /16 = 65,536 IPs (65,534 usable)

2. Given VPC 10.0.0.0/16:

* What's the largest possible subnet? (/16)
* What's a valid subnet CIDR? (10.0.1.0/24)
* Is 11.0.0.0/24 valid? (No, outside VPC range)

3. For 192.168.1.0/24:

* First usable IP? (192.168.1.1)
* Last usable IP? (192.168.1.254)
* Broadcast address? (192.168.1.255)

4. Can these subnets overlap? 10.0.0.0/24 and 10.0.0.128/25 (Yes) 10.0.0.0/24 and 10.0.1.0/24 (No)
5. Split 10.0.0.0/24 into four equal subnets:

* 10.0.0.0/26
* 10.0.0.64/26
* 10.0.0.128/26
* 10.0.0.192/26
