# Scaling Relational Databases

There are 4 types of scaling to adjust relational DB performances:

1. Aurora Serverless. We can offload scaling to AWS. Excels with unpredictable workloads.
2. Read Replicas. Create read-only copies of our data to spread out the workload. Can be created across regions. Read replicas are not meant to be used for HA solution, for that we should use multi AZ deployment so that AWS maintains a synchronously replicated standby database. Standby instance cannot be used for read and right
3. Scaling Storage. Storage can be resized, but it's only able to go up, not down. This is costly as well.
4. Vertical Scaling. Resizing the database from one size to another can create greater performance.

Another viable scaling option is refactoring to DynamoDB.



## AWS Relational Database Scaling Options Comparison

| Scaling Option          | Cost                                                    | Performance                                      | Use Case                                                        | Pros                                                                                                                    | Cons                                                                                                                                 |
| ----------------------- | ------------------------------------------------------- | ------------------------------------------------ | --------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| Read Replicas           | Medium                                                  | High for read-heavy workloads                    | Read-heavy applications with moderate write loads               | <p>- Improves read performance<br>- Offloads reads from primary instance<br>- Can be promoted to primary</p>            | <p>- Adds complexity to application logic<br>- Eventual consistency<br>- Additional cost for each replica</p>                        |
| Scaling Storage         | Low                                                     | Moderate                                         | Applications with growing data needs                            | <p>- No downtime for most RDS engines<br>- Easy to implement<br>- Cost-effective for storage-bound workloads</p>        | <p>- Doesn't improve compute performance<br>- May hit IOPS limits<br>- Cannot decrease storage (for most engines)</p>                |
| Vertical Scaling        | High                                                    | High for both read and write operations          | Applications needing more overall performance                   | <p>- Simple to implement<br>- Improves both read and write performance<br>- No application changes needed</p>           | <p>- Downtime during scaling (for non-Aurora instances)<br>- Can be costly for large instances<br>- Limited by max instance size</p> |
| Aurora Serverless       | Variable (can be cost-effective for variable workloads) | Good for variable workloads                      | Applications with unpredictable or variable workloads           | <p>- Automatic scaling<br>- Pay only for resources used<br>- Good for development and variable production loads</p>     | <p>- Higher latency on scale-up<br>- Limited configuration options<br>- Not ideal for stable, predictable workloads</p>              |
| Refactoring to DynamoDB | Variable (can be very cost-effective at scale)          | Very high for key-value and document data models | Applications requiring low-latency access to any amount of data | <p>- Unlimited scaling<br>- Consistent single-digit millisecond latency<br>- Managed service with high availability</p> | <p>- Requires application refactoring<br>- Limited query flexibility compared to SQL<br>- Learning curve for NoSQL data modeling</p> |

### Additional Considerations:

1. **Read Replicas**:
   * Cost increases linearly with each added replica
   * Excellent for geo-distribution of read traffic
2. **Scaling Storage**:
   * Most cost-effective for databases that are storage-bound rather than compute-bound
   * Autoscaling can be enabled for automatic storage increases
3. **Vertical Scaling**:
   * Simplest option but can become very expensive for large instances
   * Aurora instances can scale vertically with minimal downtime
4. **Aurora Serverless**:
   * Can scale to zero, potentially saving costs during idle periods
   * Good option for applications with intermittent usage
5. **Refactoring to DynamoDB**:
   * Requires significant upfront investment in refactoring
   * Can be extremely cost-effective and high-performing for suitable workloads
   * Offers features like global tables for multi-region deployment

Remember that the best scaling strategy often involves a combination of these options, tailored to your specific application needs, traffic patterns, and budget constraints.
