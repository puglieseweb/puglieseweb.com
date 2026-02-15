# For CPU Spikes in DB use RDS Proxy

what Amazon RDS Proxy does to reduce DB CPU utilization?

Edit

Amazon RDS Proxy reduces database CPU utilization through connection pooling:

1. Maintains a pool of established database connections
2. Reuses existing connections instead of creating new ones
3. Multiplexes database connections (allows multiple client sessions to share the same database connection)

This eliminates the overhead of repeatedly establishing new connections, which is CPU-intensive due to authentication and SSL handshake processes.
