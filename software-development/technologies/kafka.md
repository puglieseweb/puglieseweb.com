# Kafka

## Topics

Topics should be created to be available and durable. For example, 

```text
replication.factor 3
min.insync.replicas 2
```

Once you're provides a balance between availability and durability. If enforces high replication with strict sync requirements. 

To access kafka broker from docker you can run the following command

```text
docker-compose exec broker bash
kafka-topics --bootstrap-server localhost:9092 --list
```

To execute a KSQL

```text
docker-compose exec ksql-cli ksql http://ksql-server:8088
```

