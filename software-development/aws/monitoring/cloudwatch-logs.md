# CloudWatch Logs

CoudWatch Logs is a tool that allows you to monitor, store, and access logs files from a variety of different sources. It gives you the ability to query your logs to look for potential issues or data that is relevant to you.

For custom logs, you can use the Amazon CloudWatch Agent. The agent enables users to collect and monitor system and application metrics on their AWS instances and on-premises servers.

Terms:

* Log Event: a record of what happen. It contains a timestamp and the data.
* Log Stream: a collection of Log Events for the same source (instance).&#x20;
* Log Groups: This is a collection of Log Streams (e.g. all the Apache web server across all the hosts)

Features:

* Filter Patterns: You can look at specific terms in your web server logs.
* CloudWatch Logs Insights: This allows you to query all your logs using a SQL-like interactive solution.
* Alarms: Once we have identified a trends or patterns, we can set alerts.
