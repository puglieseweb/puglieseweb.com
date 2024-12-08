# DR for RDS

Here are the main strategies to implement Disaster Recovery (DR) for RDS databases, from simplest to most complex:

1. Backup and Restore:

* Simplest DR strategy
* Uses automated backups and snapshots
* Highest RPO/RTO

```bash
# Create manual snapshot
aws rds create-db-snapshot \
    --db-instance-identifier mydb \
    --db-snapshot-identifier mydb-snapshot

# Restore from snapshot
aws rds restore-db-instance-from-db-snapshot \
    --db-instance-identifier mydb-restored \
    --db-snapshot-identifier mydb-snapshot
```

2. Read Replicas:

* Cross-region read replica
* Can be promoted to master
* Lower RPO, moderate RTO

```bash
# Create cross-region read replica
aws rds create-db-instance-read-replica \
    --db-instance-identifier mydb-replica \
    --source-db-instance-identifier arn:aws:rds:us-east-1:123456789012:db:mydb \
    --region us-west-2

# Promote to master
aws rds promote-read-replica \
    --db-instance-identifier mydb-replica
```

3. Multi-AZ Deployment:

* Synchronous replication
* Automatic failover
* Zero RPO, low RTO

```bash
# Enable Multi-AZ
aws rds modify-db-instance \
    --db-instance-identifier mydb \
    --multi-az \
    --apply-immediately
```

4. Multi-Region Active-Passive:

* Primary region active
* Secondary region with read replica
* Can be promoted during DR Steps:

1. Create cross-region read replica
2. Set up monitoring
3. Create failover procedure
4. Test regularly
5. Multi-Region Active-Active:

* Both regions active
* Uses Route 53 for routing
* Most complex but lowest downtime Components needed:
* Cross-region read replicas
* Route 53 health checks
* Application logic for write distribution

Key Considerations:

1. RPO (Recovery Point Objective):

* Backup/Restore: Hours
* Read Replica: Minutes
* Multi-AZ: Seconds
* Multi-Region: Minutes to seconds

2. RTO (Recovery Time Objective):

* Backup/Restore: Hours
* Read Replica: Minutes
* Multi-AZ: Minutes
* Multi-Region: Minutes to seconds

3. Cost Implications:

* Backup/Restore: Lowest cost
* Read Replica: Moderate cost
* Multi-AZ: Higher cost
* Multi-Region: Highest cost

4. Best Practices:

* Document DR procedures
* Regular testing
* Automated failover when possible
* Monitor replication lag
* Consider automated DR tools
* Maintain up-to-date DNS records
* Regular backup validation

5. Additional Considerations:

* VPC peering/Transit Gateway setup
* Security group configurations
* IAM roles and permissions
* SSL/TLS certificates
* Database parameter groups
* Option groups
* Monitoring and alerting setup
* Network latency between regions
