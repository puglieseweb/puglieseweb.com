# DRs

1. Backup & Restore
   * Uses: AWS Backup, S3, AMIs, EBS snapshots
   * RPO: Hours (depends on backup frequency)
   * RTO: 24+ hours
   * Cost: $
2. Pilot Light
   * Uses: EC2 minimal standby, RDS replicas
   * RPO: Minutes to hours
   * RTO: Tens of minutes to hours
   * Cost: \$$
3. Warm Standby
   * Uses: Route 53, Auto Scaling Groups, reduced capacity standby
   * RPO: Minutes
   * RTO: Minutes
   * Cost: \$$$
4. Multi-Site Active/Active
   * Uses: Route 53 with health checks, DynamoDB global tables
   * RPO: Near zero
   * RTO: Near zero
   * Cost: \$$\$$

Reference: [AWS Disaster Recovery Documentation](https://docs.aws.amazon.com/whitepapers/latest/disaster-recovery-workloads-on-aws/disaster-recovery-options-in-the-cloud.html)
