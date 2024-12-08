# CloudEvents Kafka

CloudEvents can be transported over Kafka in two ways:

1. Structured mode: The CloudEvents data is encoded (typically as JSON) in the Kafka message value, with content-type set appropriately.
2. Binary mode: The CloudEvent attributes are mapped to Kafka message headers, while the data is carried in the message value.

Let me show you an example of implementing both approaches:

```java
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CloudEventsKafkaExample {
    // CloudEvent data class
    public static class CloudEvent<T> {
        String id;
        String source;
        String specversion;
        String type;
        OffsetDateTime time;
        T data;
        
        public CloudEvent(String source, String type, T data) {
            this.id = UUID.randomUUID().toString();
            this.source = source;
            this.specversion = "1.0";
            this.type = type;
            this.time = OffsetDateTime.now();
            this.data = data;
        }
    }

    // Example event data
    public static class OrderCreated {
        String orderId;
        double amount;
        
        public OrderCreated(String orderId, double amount) {
            this.orderId = orderId;
            this.amount = amount;
        }
    }

    // Structured mode - entire CloudEvent as JSON in message value
    public static ProducerRecord<String, String> createStructuredCloudEvent(
            String topic, OrderCreated orderData) {
        
        CloudEvent<OrderCreated> event = new CloudEvent<>(
            "/orders",
            "com.example.order.created",
            orderData
        );
        
        String jsonValue = new Gson().toJson(event);
        
        return new ProducerRecord<>(
            topic,
            null,  // key
            jsonValue
        );
    }

    // Binary mode - CloudEvent attributes in headers, data as value
    public static ProducerRecord<String, String> createBinaryCloudEvent(
            String topic, OrderCreated orderData) {
            
        CloudEvent<OrderCreated> event = new CloudEvent<>(
            "/orders",
            "com.example.order.created",
            orderData
        );
        
        String jsonData = new Gson().toJson(orderData);
        
        ProducerRecord<String, String> record = new ProducerRecord<>(
            topic,
            null,  // key
            jsonData
        );
        
        // Add CloudEvents attributes as headers
        record.headers().add(
            new RecordHeader("ce_id", event.id.getBytes(StandardCharsets.UTF_8)));
        record.headers().add(
            new RecordHeader("ce_source", event.source.getBytes(StandardCharsets.UTF_8)));
        record.headers().add(
            new RecordHeader("ce_specversion", event.specversion.getBytes(StandardCharsets.UTF_8)));
        record.headers().add(
            new RecordHeader("ce_type", event.type.getBytes(StandardCharsets.UTF_8)));
        record.headers().add(
            new RecordHeader("ce_time", event.time.toString().getBytes(StandardCharsets.UTF_8)));
        
        return record;
    }

    // Example usage
    public static void main(String[] args) {
        OrderCreated orderData = new OrderCreated("12345", 99.99);
        
        // Create events in both modes
        ProducerRecord<String, String> structuredRecord = 
            createStructuredCloudEvent("orders", orderData);
        
        ProducerRecord<String, String> binaryRecord = 
            createBinaryCloudEvent("orders", orderData);
        
        // Use these records with your Kafka producer...
    }
}

```

Key points about using CloudEvents with Kafka:

1. In structured mode, you just need to ensure your CloudEvent JSON is valid and set the content-type appropriately (typically `application/cloudevents+json`)
2. In binary mode, CloudEvent attributes are prefixed with "ce\_" in the Kafka headers, making them easy to identify and process
3. The binary mode is often more efficient as it avoids double-encoding of the data payload
