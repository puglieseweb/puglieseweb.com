# GuardDuty

Amazon GuardDuty is a threat detection service that uses machine learning to continuously monitor for malicious behavior.

It looks for:

* Unusual API calls, including calls from known malicious IP addresses
* Attempts to disable AWS CloudTrail logging
* Unauthorized deployments
* Compromised instances
* Reconnaissance by would-be attackers
* Port scanning and failed login attempts

Features:

* Alerts appear in the GuardDuty console and Amazon EventBridge
* Receives threat intelligence feeds from third parties like Proofpoint and CrowdStrike, as well as AWS Security, about known malicious domains and IP addresses
* Monitors AWS CloudTrail logs, VPC Flow Logs, and DNS logs
* Supports automated responses using Amazon EventBridge and AWS Lambda
* Uses machine learning and anomaly detection

Threat Detection with AI:

* Takes 7-14 days to establish a baseline of normal behavior on your account
* Findings can be viewed in the GuardDuty console and Amazon EventBridge

\
