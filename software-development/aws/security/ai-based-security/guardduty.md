# GuardDuty

A threat detection service that uses machine learning to continuously monitor for malicious behaviour.&#x20;

It looks of

* unusual API calls, calls from a know malicious IP
* Attempts to disable CloudTrail logging
* Unauthorised deployments&#x20;
* Compromised instances
* Reconnaissance by would-be attackers&#x20;
* Port scanning, failed logins

Features:

* Alerts appear in the GuardDuty console and CloudWatch Events.&#x20;
* Receives feeds from third parties like Proofpoint and CrowdStrike, as well as AWS Security, about known malicius domains and IP addresses, etc.&#x20;
* Monitors CloudTrail logs, VPC Flow Logs, and DNS logs.
* Automated responses using CloudWatch Events and Lambda
* Machine learning and anomaly detections.

Threat Detection with AI:

* 7-14 days to set a baseline - what is normal behaviour on your accout?
* findings can be found on GuardDuty console and CloudWatch Events.
