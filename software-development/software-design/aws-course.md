# AWS course

### How computer communicate

* Linux SSH uses port 22
* Windows RDP uses port 3389
* HTTP Web browsing uses port 89
* HTTPS Web browsing uses port 443&#x20;

### Security groups&#x20;

Security groups are virtual firewall for EC2 instances. By default everything is blocked.

To let everything in: 0.0.0.0/0



Tip1: Changes to security groups takes effect immediatily&#x20;

Tip 2: You can have any number of EC2 instances withing a security group&#x20;

Tip 3: YOu can have multiple security groups attached to EC2 instances.

Tip 4: All inbound traffic is blocked by default

Tip 5: All outbound traffic is allowed.

### Bootstrap Scripts

A script that runs when the instance first runs. It has root level permissions

NOTE: Adding tasks at boot time adds to the amount of time it takes to boot the instances. However, it allows you to automate the installation of applications.



