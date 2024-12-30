# Lab: Creating a Multi-Region Network with VPC Peering Using SGs, IGW, and RTs

### Introduction

It can get cumbersome trying to track all the different routing components of a network, especially in the fast-moving, dynamic IT operations world today. By maintaining your AWS resources such as VPC, SGs, and IGWs using Terraform, you can track all of the changes as code.

In this lab, students will go through creating a network setup complete with VPCs, subnets, security groups, internet gateways, and VPC peering in AWS using Terraform. Students are expected to have working knowledge of VPC resources and basic network components within AWS to be able to take full advantage of this lab.

### Solution

#### Log in to the Terraform Controller Node EC2 Instance

1.  Find the details for logging in to the Terraform Controller node provided by the hands-on lab interface and log in to the node using SSH:

    ```
    ssh cloud_user@<IP-OF-TERRAFORM-CONTROLLER>
    ```

    > **Note**: This instance already has an EC2 instance profile (role) attached to it and has all necessary AWS API permissions required for this lab. It also has the AWS CLI set up and configured with the AWS account attached to this lab, for which the console login credentials are also provided in the lab interface page once the lab spins up.
2.  After logging in, verify the version of Terraform installed (should be 12.29). Execute the following command to check:

    ```
    terraform version
    ```

#### Clone the GitHub Repo for Terraform Code

Use the `git` command to clone the GitHub repo which has the Terraform code for deploying the solution of this lab. [GitHub repo URL](https://github.com/linuxacademy/content-deploying-to-aws-ansible-terraform.git).

1.  Execute the following command:

    ```
    git clone https://github.com/linuxacademy/content-deploying-to-aws-ansible-terraform.git
    ```
2.  Change to the directory for lab Terraform code:

    ```
    cd content-deploying-to-aws-ansible-terraform/lab_network_vpc_peering
    ```
3.  Examine the contents of the directory you're in:

    ```
    ls
    ```

#### Deploy the Terraform Code

1.  Initialize the Terraform directory you changed into to download the required provider

    ```
    terraform init
    ```
2.  Ensure Terraform code is formatted properly:

    ```
    terraform fmt
    ```
3.  Ensure code has proper syntax and no errors:

    ```
    terraform validate
    ```
4.  See the execution plan and note the number of resources that will be created:

    ```
    terraform plan
    ```

    Enter `yes` when prompted.
5.  Deploy resources:

    ```
    terraform apply
    ```

    Enter `yes` when prompted.

    After `terraform apply` has run successfully, you can either use the AWS CLI on the Controller node to list and describe created resources or you can log in to the AWS Console to verify and investigate created resources.
6.  Finally, on the Terraform Controller node CLI, delete all resources which were created and ensure that it runs through successfully.

    ```
    terraform destroy
    ```
