# Migration

## Migration Phases:&#x20;

1. Discover: Find Servers and databases to plan your migrations.
2. Migrate: Connect tools to Migration Hub, and begin migrating.
3. Track: Follow migration statuses and  progress.

## Costs&#x20;

When migrating data to AWS, there are several options available, each with its own pricing structure and use cases. Let's compare some of the main AWS data transfer options, assuming you're transferring large amounts of data (like your 8TB example) into AWS. Keep in mind that prices can vary by region and may have changed since my last update, so always check the official AWS pricing page for the most current information.

1. AWS Snowcone (for reference):
   * Device fee: \~$6/day
   * Data transfer in: Usually free
   * Good for: 8TB or less, edge locations, limited network connectivity
2. AWS Snowball:
   * Device fee: \~$300 for 10 days (80TB capacity)
   * Data transfer in: Usually free
   * Good for: 10TB to 80TB of data
3. AWS Snowmobile:
   * Custom pricing, typically for extremely large datasets (100TB+)
   * Good for: Petabyte-scale data transfer
4. AWS Direct Connect:
   * Port hourly fee: From $0.30/hour for 1Gbps to $20/hour for 100Gbps
   * Data transfer in: Usually free
   * Good for: Ongoing, high-bandwidth needs
5. Internet Transfer:
   * Data transfer in: Usually free
   * Only cost is your own internet bandwidth
   * Good for: Smaller datasets, when time is not a critical factor

Comparison for 8TB transfer:

1. Snowcone (14 days): \~$124 (from our previous calculation)
2. Snowball (10 days): \~$300 (flat fee for 10 days)
3. Direct Connect (assuming 1Gbps for 1 day): $0.30 \* 24 hours = $7.20 (Note: This doesn't include the cost of setting up the connection)
4. Internet Transfer: $0 (but depends on your internet speed and any business downtime)

Key Considerations:

* Speed: Snowball and Snowcone are faster for large datasets compared to internet transfer.
* Frequency: For one-time transfers, Snowball or Snowcone might be more cost-effective. For ongoing needs, Direct Connect could be better.
* Network limitations: If you have limited bandwidth, physical device options are often better.
* Security: Physical devices offer an extra layer of security for sensitive data.
* Setup time: Internet transfer requires no extra setup, while Direct Connect requires more initial setup.

For your 8TB migration:

* If it's a one-time transfer and speed is crucial, Snowcone might be the most cost-effective.
* If you have good internet speed and time isn't critical, internet transfer could be the cheapest option.
* If this is part of an ongoing migration or you have more data to transfer soon, considering a larger option like Snowball might be more cost-effective in the long run.



## DataSync VS Storage Gateway

Storage Gateway is use for a more long term hybrid architecture&#x20;

DataSync is only for one time migration.
