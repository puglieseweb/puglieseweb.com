# Dockerfile

## Difference between RUN, CMD, and ENTRYPOINT

\(resource: [https://goinbigdata.com/docker-run-vs-cmd-vs-entrypoint/](https://goinbigdata.com/docker-run-vs-cmd-vs-entrypoint/)\)

* `RUN` executes command\(s\) in a new layer and creates a new image. E.g., it is often used for installing software packages.
* `CMD` sets default command and/or parameters, which can be overwritten from command line when docker container runs. Therefore CMD is executed only when you run container without specifying a command. If Docker container runs with a command, the default command will be ignored. If Dockerfile has more than one CMD instruction, all but last CMD instructions are ignored.
* `ENTRYPOINT` configures a container that will run as an executable. It is similar to CMD but command and parameters are not ignored when Docker container runs with command line parameters.

### Shell and exec forms

All three instructions \(RUN, CMD and ENTRYPOINT\) can be specified in _shell form_ or _exec form_:

* in the _shell form_ the instruction is executed calling the shell `/bin/sh -c <command>`
* in the _exec form_ the instruction is executed in an **executable directly**, and shell processing does not happen. 
  * _exec form_ can be sued to run bash \(or any other interpreter but sh\)
  * _exec form_ is preferred for `CMD` and `ENTRYPOINT` instructions.

For example RUN has two forms:

* `RUN <command>` \(shell form\)
* `RUN ["executable", "param1", "param2"]` \(exec form\) 

## Summary

Use **RUN** instructions to build your image by adding layers on top of initial image.

Prefer **ENTRYPOINT** to **CMD** when building executable Docker image and you need a command always to be executed. Additionally use CMD if you need to provide extra default arguments that could be overwritten from command line when docker container runs.

Choose **CMD** if you need to provide a default command and/or arguments that can be overwritten from command line when docker container runs.

