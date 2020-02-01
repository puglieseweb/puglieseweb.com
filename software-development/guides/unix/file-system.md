# File system



The UNIX File System naming standards can be located [here](https://en.wikipedia.org/wiki/Filesystem_Hierarchy_Standard). Relevant folders for a software engineer are:

* **/opt/** contains _optional application software_ \(end user programs, such as Chrome, Gradle, etc.\):

  ```text
  /opt/
  ├── containerd
  ├── google
  └── gradle
  ```

* **/etc/** contains host-specific system-wide configuration files.
* **/etc/opt/** contains configuration files for add-on packages that are stored in `/opt`.
* **/var/** contains variable files whose content is expected to continually change during normal operation of the system—such as logs, spool files, and temporary e-mail files.
* **/var/opt/** contains variable data from add-on packages that are stored in `/opt`.
* **/var/log/** contains various logs.
* **/usr/bin/** \(secondary hierarchy\) contains the majority of read-only multi-user non-essential \(to single-user mode\) command [binaries](https://en.wikipedia.org/wiki/Executable):

```text
/usr/bin/
├── ps
├── ls
├── cat
├── bash
├── sh
```

* **/usr/local/bin/** contains scripts used for opening files and project from the command line:

```bash
/usr/local/bin/
├── docker-compose
└── idea
```

* **/proc/** contains virtual filesystem providing process and kernel information as files.
* **/dev/** contains _device files_, e.g., `/dev/null`, `/dev/disk0`, `/dev/sda1`, `/dev/tty`, `/dev/random`.

