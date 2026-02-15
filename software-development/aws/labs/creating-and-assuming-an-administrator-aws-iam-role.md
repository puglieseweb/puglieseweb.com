# Creating and Assuming an Administrator AWS IAM Role

In this Hands-on Lab we are going to work through creating a brand-new IAM Role within your AWS Sandbox account. This IAM Role will be granted Administrator Access permissions within the same account.

## Create IAM Role

* Create a brand-new IAM role named `CSAA_AdministratorTest` and attach the AWS-managed policy titled **AdministratorAccess**.
* Copy the [IAM Trust Policy](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/01-week-1/1.1%20-%20Creating%20and%20Assuming%20an%20Administrator%20AWS%20IAM%20Role/1-iam-role-trust-policy.json) from the lab GitHub repo and update `%REPLACE_WITH_ACCOUNT_ID%` with your account ID. This policy will only allow an IAM identity with your `cloud_user` ARN to assume the role

> The naming convention for this step is critical to avoid conflicts in future steps!

## Assume the IAM Role

After creation, test assumption of the IAM role using the Switch Role console option.

## Create & Deploy CloudFormation Template of IAM Role

* After the role is verified to be working, create a new CloudFormation template that mimics the newly created IAM role. This allows for easy future deployment when required.
* To do this, copy and run the template code for which ever language you choose in Application Composer.

[YAML Template Code](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/01-week-1/1.1%20-%20Creating%20and%20Assuming%20an%20Administrator%20AWS%20IAM%20Role/3a-cloudformation-template.yml)

[JSON Template Code](https://github.com/pluralsight-cloud/aws-certified-solutions-architect-associate/blob/main/bootcamp-hands-on-labs/01-week-1/1.1%20-%20Creating%20and%20Assuming%20an%20Administrator%20AWS%20IAM%20Role/3b-cloudformation-template.json)

* When prompted, you must name the stack `AdministratorAccessRole`
