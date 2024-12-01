# Instance Storage

Instance storage in AWS (also called ephemeral storage) is physical disk storage that's directly attached to the host EC2 instance. Here are its key characteristics:

1. It's temporary - all data is lost when the instance stops or terminates (data persists during reboots)
2. Provides very high IOPS and lowest latency since it's physically attached to the host
3. Included in the instance price with no additional cost
4. Size is fixed based on the instance type
5. Ideal for temporary storage needs like:
   * Cache data
   * Scratch space
   * Buffer/queue data
   * Temporary processing files

Not all instance types come with instance storage, and you can't add it after launching an instance. For persistent storage needs, EBS volumes are recommended instead.\


| Features        | Instance Store                         | EBS Volumes                              |
| --------------- | -------------------------------------- | ---------------------------------------- |
| Persistence     | Temporary (lost on stop/termination)   | Persistent (independent of instance)     |
| Latency         | Lowest (physically attached)           | Low (network attached)                   |
| IOPS            | Very high (depends on instance type)   | Up to 256,000 (io2 Block Express)        |
| Use Cases       | Temporary storage, cache, scratch data | Persistent data, databases, boot volumes |
| Backup          | Manual only                            | Snapshots available                      |
| Size Limits     | Fixed by instance type                 | Up to 16 TiB per volume                  |
| Availability    | Not all instance types                 | All instance types                       |
| Data Redundancy | None (single disk failure = data loss) | Replicated within AZ                     |
| Cost            | Included in instance price             | Additional cost per GB-month             |
| Performance     | Consistent high performance            | Varies by volume type                    |

