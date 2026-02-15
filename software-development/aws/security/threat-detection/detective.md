# Detective (Root Cause Analysis)

Amazon Detective helps you analyze, investigate, and rapidly identify the root cause of security issues or suspicious activities within your AWS environment.

The service automatically collects and processes data from your AWS resources, leveraging advanced machine learning, statistical analysis, and graph theory to create an interactive data model. This model connects and visualizes relationships between your resources, IP addresses, AWS accounts, and user activities, enabling swift and effective security investigation.

Data Sources:

* Amazon VPC Flow Logs
* AWS CloudTrail logs
* Amazon Elastic Kubernetes Service (EKS) audit logs
* Amazon GuardDuty findings

Key Use Cases:

1. Triage Security Findings
   * Quickly determine the severity and scope of security issues
   * Validate alerts from security tools and identify false positives
   * Analyze resource and entity behaviors over time
   * Trace the sequence of events leading to security alerts
2. Threat Hunting
   * Identify unusual patterns of behavior across your AWS resources
   * Investigate potentially compromised resources or user credentials
   * Track suspicious IP addresses and their interactions with your environment
   * Detect and investigate potential lateral movement within your infrastructure
   * Analyze historical activity patterns to uncover potential security gaps

Detective's visualization capabilities and automated analysis help security teams reduce investigation time from hours to minutes, enabling faster incident response and remediation.
