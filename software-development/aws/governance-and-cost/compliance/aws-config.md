# AWS Config

Config is an inventory management and control tool.&#x20;

It is the best way to check what standards are applied to your architecture.

You can track previously deleted AWS resources

It allows you to show the configuration history of your infrastructure over time.

Able to create rules to make sure resources confirm to your requirements.&#x20;

Can send alerts via SNS.

**Configured per Region!**&#x20;

**Results can be aggregated across Regions and AWS accounts**



### **Config rules**

* AWS-managed rules&#x20;
* Custom Config rules
* rules are evaluated on a schedules or by a trigger
* AWS Config is NOT prevention
* AWS config service  costs $0.003 per item and $0.001 per rule evaluation

### Automated Remediation

* You can enable automatic remediation via SSM Automation Documents&#x20;
* Automation Documents can be AWS-managed or custom&#x20;
* Custom Automation Documents can leverage Lambda functions for custom logic&#x20;
* If auto-remediation fails, you can enable a retry

