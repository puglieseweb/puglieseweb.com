# Docker Commands

## Docker Commands

`docker exec -i -t vault sh`

`docker pull kalilinux/kali-rolling` 

`docker run kalilinux/kali-rolling`

`docker container kill $(docker ps -q)` 

`docker ps` 

`docker containers ls -a`

`docker container rm e11ff9a61a73`

`docker run --name kali_linux --net="host" --privileged -e DISPLAY=$DISPLAY -it kalilinux/kali-rolling /bin/bash`

## Docker Compose Commands

`docker-compose up -d --build`

`docker-compose ps` 

`docker-compose exec -it broker bash`

`docker-compose exec broker bash` 

`docker-compose exec ksql-cli ksql` [http://ksqldb-server:8088](http://ksqldb-server:8088)

`docker-compose exec ksql-cli ksql` 

`docker-compose stop`

\`\`



