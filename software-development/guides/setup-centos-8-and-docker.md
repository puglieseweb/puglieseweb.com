---
description: Collection of commands required to setup CentOS development environment
---

# Setup CentOS 8 and Docker

## Installing Ntfs Support

Command to enable NTFS/FAT32 file systems support: 

```bash
sudo dnf install epel-release -y
sudo dnf install ntfs-3g -y
```

## Installing OpenJDK

```bash
sudo dnf install java-1.8.0-openjdk-devel
sudo dnf install java-11-openjdk-devel
```

## Installing Git

* `sudo dnf install git`
* [https://help.github.com/en/enterprise/2.16/user/authenticating-to-github/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent](https://help.github.com/en/enterprise/2.16/user/authenticating-to-github/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)
* `vim ~/.ssh/config`

  and paste and save the following content:

  ```text
  Host github.com
  Hostname ssh.github.com
  Port 443
  ```

* `chmod 600 ~/.ssh/config`

## Installing Docker CE

By default podman-docker is installed. and this will cause the following error:

> package docker-ce-cli-1:19.03.5-3.el7.x86\_64 conflicts with docker provided by podman-docker-1.0.5-1.gitf604175.module\_el8.0.0+194+ac560166.noarch

To avoid this issue run the following commands:

```text
sudo dnf remove -y docker docker-client docker-client-latest docker-common docker-latest docker-latest-logrotate docker-logrotate docker-selinux docker-engine-selinux docker-engine
sudo dnf -y install dnf-plugins-core
sudo dnf config-manager --add-repo=https://download.docker.com/linux/centos/docker-ce.repo
sudo dnf -y install https://download.docker.com/linux/centos/7/x86_64/stable/Packages/containerd.io-1.2.6-3.3.el7.x86_64.rpm
sudo dnf install docker-ce docker-ce-cli containerd.io
sudo dnf clean packages
sudo dnf list docker-ce
sudo systemctl start docker
sudo systemctl enable docker
```

#### Test Docker CE

```text
docker --version
docker run hello-world
sudo docker run hello-world
docker run -it ubuntu bash
sudo docker run -it ubuntu bash
```

## Installing Docker Compose

```text
sudo curl -L "https://github.com/docker/compose/releases/download/1.25.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo usermod -aG docker $USER
docker-compose --version
docker stats
docker version
```

### Troubleshooting

#### 408 Request Time-out while signing in or pulling an image

This issue was caused by the fact that the MTU on both the ethernet and wifi connection was set to AUTO. The issue was fixed changing the MTU to 900:

```bash
ip link list
sudo ip link set wlp2s0 mtu 900
sudo ip link set enp0s31f6 mtu 900
sudo systemctl restart NetworkManager
```

#### Docker networking - Connection refused

Container B has not access to Container A and log file error is: 

`NO ROUTE TO HOST network request from container to...`

This was firewall issue in the OS hosting docker. To troubleshoot the issue: 1\) Check if the port from container A is accessible from the localhost:

1. `ss | head -1 1.`ss -l \| grep LISTEN \| grep tcp
2. `telnet localhost 2181`
3. check `docker logs -f containerA` to watch the connection happening
4. If the connection can happen from the hosting operation system, and not from container B, then it try to temporary disable the `firewalld` to verify if this fixes the issue.
5. Add appropriate firewall configuration

```text
# Masquerading allows for docker ingress and egress (this is the juicy bit)
firewall-cmd --zone=public --add-masquerade --permanen
# Reload firewall to apply permanent rules
firewall-cmd --reload
```

Further information on Docker networking time issue can be found here: 

* [https://pythonspeed.com/articles/docker-connection-refused/](https://pythonspeed.com/articles/docker-connection-refused/)
* [https://www.cyberciti.biz/faq/how-can-i-setup-the-mtu-for-my-network-interface/](https://www.cyberciti.biz/faq/how-can-i-setup-the-mtu-for-my-network-interface/)

## Installing Gradle

```text
wget https://services.gradle.org/distributions/gradle-5.1-bin.zip -P /tmp
sudo unzip -d /opt/gradle /tmp/gradle-5.1-bin.zip
sudo vim /etc/profile.d/gradle.sh
```

paste the following lines:

> export GRADLE\_HOME=/opt/gradle/gradle-5.1
>
> export PATH=${GRADLE\_HOME}/bin:${PATH}

```text
sudo chmod +x /etc/profile.d/gradle.sh
source /etc/profile.d/gradle.sh
gradle -v
```

## Installing Kubernetes

[https://www.techrepublic.com/article/how-to-install-kubernetes-on-centos-8/](https://www.techrepublic.com/article/how-to-install-kubernetes-on-centos-8/) [https://www.youtube.com/watch?v=Araf8JYQn3w&list=PL34sAs7\_26wNBRWM6BDhnonoA5FMERax0&index=3&t=0s](https://www.youtube.com/watch?v=Araf8JYQn3w&list=PL34sAs7_26wNBRWM6BDhnonoA5FMERax0&index=3&t=0s)

## Installing Intellij

1. `mkdir /opt/idea`
2. Download and unzip Intellij in `/opt/idea/`
3. `sh /opt/idea/idea-IU-183.6156.11/bin/idea.sh`
4. follow the installation steps

