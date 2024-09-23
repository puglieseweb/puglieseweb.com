# Exercise

User Data\


```
#!/bin/bash
yum update -y
yum install httpd -y
systemctl start httpd
systemctl enable httpd
cd /var/www/html
echo "<html><body><h1>Hi Facegov</h1></body></html>" > index.html
```
