# CloudEvents

[CloudEvents](https://eda-visuals.boyney.io/visuals/cloudevents) is a vendor-neutral specification for defining the format of event data. You can find the spec here:[ ](https://github.com/cloudevents/spec/blob/v1.0.2/cloudevents/spec.md)[https://github.com/cloudevents/spec/blob/v1.0.2/cloudevents/spec.md](https://github.com/cloudevents/spec/blob/v1.0.2/cloudevents/spec.md)



CloudEvents Governace: [https://eda-visuals.boyney.io/visuals/importance-of-governance-in-event-driven-architecture](https://eda-visuals.boyney.io/visuals/importance-of-governance-in-event-driven-architecture)



CloudEvents can be transported in two ways:

1. Structured mode: The CloudEvents data is encoded (typically as JSON) in the message value, with content-type set appropriately.
2. Binary mode: The CloudEvent attributes are mapped to message headers, while the data is carried in the message value.

### Binary Mode

Binary mode works well in a multi-protocol environment with Kafka, RabbitMQ, and EventBridge! The CloudEvents specification is deliberately protocol-agnostic and provides protocol bindings for each of these systems.

Here's how binary mode maps across these systems:

1. Kafka

* CloudEvent attributes become message headers with "ce\_" prefix
* Data goes into message value

```
Copyrecord.headers().add(new RecordHeader("ce_id", ...))
record.headers().add(new RecordHeader("ce_source", ...))
// message value contains the data
```

2. RabbitMQ

* CloudEvent attributes become AMQP message application properties with "ce\_" prefix
* Data goes into message body

```java
javaCopyproperties.getHeaders().put("ce_id", ...)
properties.getHeaders().put("ce_source", ...)
// message body contains the data
```

3. AWS EventBridge

* CloudEvent attributes map to EventBridge message attributes
* There's an official AWS Event Format that's compatible with CloudEvents

```json
jsonCopy{
  "detail-type": "ce_type value",
  "source": "ce_source value",
  "detail": { /* your data */ }
}
```

Key Benefits of Using Binary Mode Across These Systems:

1. Consistent header-based metadata access
2. Easier routing/filtering based on CloudEvent attributes
3. No need to parse the payload to access metadata
4. Better performance compared to structured mode

However, be aware of these considerations:

1. Each system has different header size limits
2. Header value encoding might need to be handled differently
3. You'll need to implement protocol-specific serialization/deserialization
