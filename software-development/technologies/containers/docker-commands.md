# Docker Commands

## Resources

* [dockerfile reference](https://docs.docker.com/engine/reference/builder/)
* [docker cheat sheet for spring devlopers](https://springframework.guru/docker-cheat-sheet-for-spring-devlopers/)
* [confluent docker guide](https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.brand_tp.prs_tgt.confluent-brand_mt.xct_rgn.emea_lng.eng_dv.all&utm_term=confluent%20kafka%20docker&creative=&device=c&placement=&gclid=Cj0KCQiA6IHwBRCJARIsALNjViU8o7OaXVmXYB6GFkGA6CVCEHFYsFikf-eGwUiW7yyg79zq3OUyxDkaArsuEALw_wcB#download-start-cp-docker)

## Basic Commands

* start a container: `docker start kafka`
* follow log of a container: `docker logs -f kafka`
* SSH into a Container: [https://phase2.github.io/devtools/common-tasks/ssh-into-a-container/](https://phase2.github.io/devtools/common-tasks/ssh-into-a-container/)
  * `docker exec -it zookeeper /bin/sh`
* `docker image inspect mongo`
* `docker run -d centos tail -f /dev/null` ==&gt; avoid the centos container to terminate
* `docker run --entrypoint "/bin/ls" debian -al /root` ==&gt; override entrypoint 
* `docker run -it --entrypoint "/bin/bash" 16ae4fa4bfe5 -i` ==&gt; override entrypoint and keep container running
* `docker run --rm -p 8080:8080 base-image` ==&gt; run and image and automatically remove remove the container when it exits

## Docker Compose Commands

Basic docker commands: [https://phoenixnap.com/kb/how-to-list-start-stop-docker-containers](https://phoenixnap.com/kb/how-to-list-start-stop-docker-containers)

* start all docker compose: `docker-compose up -d --build`
  * stop all docker compose: `docker-compose stop`
  * remove all docker compose: `docker-compose rm -f`

> After starting docker composer you should be able to access Confluent Console at [http://localhost:9021/clusters](http://localhost:9021/clusters)

* enter docker compose containers: 
  * `docker-compose exec broker bash`
  * `docker-compose exec ksql-cli ksql` [`http://ksqldb-server:8088`](http://ksqldb-server:8088^)
* 
## Ho to build a docker file

From the directory of the Dockerfile run:

`docker build [-t <tag name>] [--no-cache] .`

## Docker house keeping

### Cleaning up containers

* `docker kill $(docker ps -q)` ==&gt; Kill all running docker container
* `docker rm $(docker ps -a -q)` ==&gt; delete all stopped docker containers

  **Clean up images**

* `docker rmi <image name> .`
* `docker rmi $(docker images -q -f dangling=true) .` ==&gt; remove untagged \(dangling\) images \(`-q` is the quite option \(gives a list of container\_ids only\) and `-f` if the filter option\) 
* `docker rmi $(docker images -q) .` ==&gt; delete all images

  **Cleaning up volumes**

* `docker volumes rm $(docker volume ls -f dangling=true -q)` ==&gt; remove all the dangling volumes

## Docker push

\(resources: [https://ropenscilabs.github.io/r-docker-tutorial/04-Dockerhub.html](https://ropenscilabs.github.io/r-docker-tutorial/04-Dockerhub.html)\)

```text
docker login --username=yourhubusername
docker images
docker tag bb38976d03cf [yourhubusername]/[repo_name]:[tag]
docker push [yourhubusername]/[repo_name]
```

## Inspect

### inspect ARG values after an image is built

`docker history`

#### inspect the default ENV variable values before the container is started:

`$ docker images $ docker inspect <image-id>`

