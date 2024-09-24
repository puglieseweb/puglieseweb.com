# System Manager (mainly for inside EC2 instances)

Key concepts to learn: Automation documents, Parameter Store, and possibly Session Manager)



A suite of tools designed to let you view, control, an automate both your managed instances in AWS and on-premises.

SSM Agent (System Manager Agent) is executed on manged AWS instances or on-premises. Allows  communication between instances and compute and the System Manager.

Capabilities:

* Automation: Uses predefined or custom playbooks to enable resource management.
* Run Command: Remotly execute commands on managed compute without SSH or RDP&#x20;
* Patch Manager: Automate Patches.
* Parameter Store: to store secrets and application configuration information (it can integrate with KMS)&#x20;
* Maintenance Windows: define schedules for performing actions on your managed instances.
* Session Manager: Security connect to your managed compute without needing SSH access.

Concepts:&#x20;

* Logging: allows to log all usage during session (commands and connections)  to CloudWatch and CloudTrail&#x20;
* SSM Agent:
  * supports both Linux and Windows without the need for SSH or RDP. A**gent-based connection without opening ports!**
  * preinstalled on a majority of the official AWS AMIs. Only IAM permission are needed
  * it is possible to install on you own compute and edge devices to allow System Manager interactions.&#x20;
  * Parameter Store:
    * free (note that Secret Manager costs money but allows key rotations)
    * store config data and secret values in a hierarchical manner with parameter policies (e.g. expiration dates)
    * parameters can be of type
      * String&#x20;
      * StringList
      * SecureString (encrypted via AWS KMS)

