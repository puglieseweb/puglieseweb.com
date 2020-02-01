# Unix Commands

## File commands <a id="file-commands"></a>

‌

* `>>` ==&gt; append redirection symbol.
  * `cat file1 >> file2` ==&gt; append file1 to file2

‌

## Useful commands <a id="useful-commands"></a>

‌

* `cat /etc/os-release` ==&gt; check OS version

‌

## Manual commands <a id="manual-commands"></a>

‌

* full manual of a commands: `man firewall-cmd`
* short list of commands: `firewall-cmd -h | less`

‌

## Network commands <a id="network-commands"></a>

‌

* find port on Listen state: `$ netstat -tulpn | grep LISTEN`
* ping port: `telnet localhost 2128`

  > TELNET is a program that connects you to another computer so you can remotely control it via the command line. The other computer has to allow it, and you need a username and password on that computer or it won't work. NETSTAT is a way to find out basic information about a computer's networking information from the command line - for example it will tell you the computer's hostname and IP address. It's really handy for TELNET. For example, suppose I want to copy a bunch of files between two computers but I don't know anything else about them. I run NETSTAT on one to find out its IP address. Then I run TELNET on the other one and connect it to that other computer's IP address, then use the command line to copy the files.

* `nslookup -type=AAAA auth.docker.io`
* `nslookup -type=A auth.docker`
* `nmcli connection show` or `nmcli c s`==&gt; network manager cli show all the active connection in green. [video](https://www.youtube.com/watch?v=DJDL_hD3WzE)​
* `nmcli device status` shows all the devices status including the unmanaged

‌

## Create a script for opening files and project from the command line <a id="create-a-script-for-opening-files-and-project-from-the-command-line"></a>

‌

`/usr/local/bin/idea`‌

## Grep <a id="grep"></a>

‌

`grep -- -P`‌

## Parsing outputs <a id="parsing-outputs"></a>

‌

* You can access the console output via the _proc_ filesystem: `tail -f /proc/<pid>/fd/1` where `1` = stdout, `2` = stderr

‌

## Find and remove folders is: <a id="find-and-remove-folders-is"></a>

‌

`find . -name "FILE-TO-FIND" -exec rm -rf {} \;`‌

## Locate files <a id="locate-files"></a>

> Search Results Featured snippet from the web 'locate' and 'updatedb' are the front runners in searching for the existence of any file on a Linux system. In order to operate efficiently, 'locate' uses a database rather than hunting individual directory paths. For this to operate, and remain current, it means the database itself must be updated.
>
> ```text
> sudo updatedblocate [FILE_NAME]
> ```

