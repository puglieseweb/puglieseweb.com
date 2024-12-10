# ElastiChache

ElastiCache is a managed version of 2 open-source technologies: Memcached and Redis.

ElastiCache avoid most of the common issues you may encounter using these open source projects.

Memcached is good when you need:

1. Simple caching model
2. Large nodes with multiple cores/threads
3. Ability to scale out/in
4. Multithreaded architecture
5. Basic data types only

Redis is better when you need:

1. Complex data types (lists, sets, sorted sets)
2. Data persistence
3. Automatic failover (high availability)
4. Pub/Sub capabilities
5. Backup and restore
6. Data replication
7. Geospatial indexing

| Mencached                        | Redis                             |
| -------------------------------- | --------------------------------- |
| Simple database caching solution | Supported as a caching solution   |
| Not a database                   | Funtions as a standalone database |
| No failover or Multi-AZ          | Failover and Multi-AZ support     |
| No backups                       | Supports backups                  |
|                                  |                                   |

## Session Managing

ElastiCache provides a robust, scalable solution for distributed session management that works well with frequently scaling EC2 instances across multiple AZs. The application can be modified to store and retrieve session data from ElastiCache, ensuring session persistence regardless of which instance handles the request.

For session management specifically:

* Both can handle session data
* Redis offers more features like persistence and replication
* Memcached is simpler but might be sufficient if you just need basic session storage
* Redis is generally recommended for session management due to its richer feature set

If your session data is simple and you don't need persistence, Memcached could work. However, Redis is typically the safer choice for session management because it provides better data protection and more flexibility as your needs grow.
