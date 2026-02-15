# AWS Integration Pattern Comparison Matrix

##

| Characteristic           | REST API                                                                                                                         | EventBridge                                                                                                   | SQS                                                                                                     | Kinesis                                                                                              |
| ------------------------ | -------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| **Maximum Throughput**   | <p>Depends on API Gateway limits:<br>- 10,000 RPS per account (default)<br>- Can request increase</p>                            | <p>- 10,000 events/second per account<br>- Can request increase</p>                                           | <p>- Standard: Unlimited<br>- FIFO: 3,000 msgs/sec with batching<br>- 300 msgs/sec without batching</p> | <p>- 1MB/sec or 1,000 records/sec per shard<br>- Unlimited shards</p>                                |
| **Maximum Payload Size** | 10MB                                                                                                                             | 256KB                                                                                                         | 256KB                                                                                                   | 1MB                                                                                                  |
| **Best For**             | <p>- Request/response patterns<br>- CRUD operations<br>- Synchronous operations<br>- Direct service-to-service communication</p> | <p>- Event routing/fan-out<br>- Service decoupling<br>- Multi-account event routing<br>- SaaS integration</p> | <p>- Decoupled microservices<br>- Job queues<br>- Workload leveling<br>- Ordered processing (FIFO)</p>  | <p>- Real-time analytics<br>- Log aggregation<br>- IoT data ingestion<br>- Large scale streaming</p> |
| **Latency**              | <p>- Milliseconds<br>- Synchronous</p>                                                                                           | <p>- Near real-time<br>- Some latency (seconds)</p>                                                           | <p>- Standard: variable<br>- FIFO: ordered<br>- Messages available immediately</p>                      | <p>- Sub-second<br>- Ordered within shard</p>                                                        |
| **Delivery Guarantee**   | <p>- No built-in retries<br>- Need to implement retry logic</p>                                                                  | <p>- At-least-once<br>- Retry policy<br>- DLQ support</p>                                                     | <p>- At-least-once<br>- Exactly-once (FIFO)<br>- DLQ support</p>                                        | <p>- At-least-once<br>- In-order per shard</p>                                                       |
| **Cost Model**           | <p>- Pay per request<br>- Pay for data transfer</p>                                                                              | <p>- Pay per event<br>- Pay for custom event bus</p>                                                          | <p>- Pay per million requests<br>- Pay for storage</p>                                                  | <p>- Pay per shard<br>- Pay for data ingestion/retrieval</p>                                         |
| **Scalability**          | <p>- Need to request quota increase<br>- Can use regional endpoints</p>                                                          | <p>- Automatic scaling<br>- Regional service</p>                                                              | <p>- Auto scaling<br>- No shard management</p>                                                          | <p>- Manual shard management<br>- Need to split/merge shards</p>                                     |
| **Use When**             | <p>- Need immediate response<br>- Simple CRUD operations<br>- API-first design</p>                                               | <p>- Complex event routing<br>- Fan-out patterns<br>- AWS service integration</p>                             | <p>- Need message queuing<br>- Workload decoupling<br>- Need DLQ</p>                                    | <p>- High-throughput streams<br>- Real-time processing<br>- Need ordering</p>                        |
| **Avoid When**           | <p>- Asynchronous operations<br>- Need retry mechanisms<br>- Large payload sizes</p>                                             | <p>- Large payloads<br>- Need strict ordering<br>- Ultra-low latency</p>                                      | <p>- Need streaming<br>- Need sub-second processing<br>- Large messages</p>                             | <p>- Low throughput<br>- Don't need ordering<br>- Cost-sensitive</p>                                 |

**Additional Considerations:**

1. **Cross-Region Support**
   * REST: Global via CloudFront
   * EventBridge: Cross-region rules
   * SQS: Cross-region queues
   * Kinesis: Cross-region replication
2. **Development Complexity**
   * REST: Simple to implement
   * EventBridge: Medium (event patterns)
   * SQS: Simple to implement
   * Kinesis: Complex (shard management)
3. **Monitoring & Debugging**
   * REST: API Gateway metrics/logs
   * EventBridge: CloudWatch metrics/logs
   * SQS: Queue metrics/DLQ
   * Kinesis: Enhanced monitoring
