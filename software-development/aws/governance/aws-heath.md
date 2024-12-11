# AWS Health

Gain visibility into resource performance and availability across AWS services and accounts.

### Key Features

* Monitor how health events affect your services, resources, and accounts.
* Maintain timely and relevant information about events.
* View and prepare for upcoming maintenance tasks.
* Receive near-instant notifications and alerts for faster troubleshooting.
* Automate actions based on incoming events using Amazon EventBridge.

### Event Types

1. Account-specific events
   * Impacts your specific AWS resources/account.
   * Example: EC2 instance scheduled maintenance.
2. Public events
   * Affects AWS services broadly.
   * Not specific to individual accounts.
   * Example: Regional service disruptions.

### Event Components

* Event type code: Identifies affected service and event type.
* Event status: Open, closed, or upcoming.
* Affected entities: Resources impacted by the event.
* Category: Service, maintenance, or issue.
* Severity: Indicates impact level.

All this information helps you proactively manage your AWS infrastructure and respond quickly to issues.
