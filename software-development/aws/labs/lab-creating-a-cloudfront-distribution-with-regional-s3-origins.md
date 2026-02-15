# LAB - Creating a CloudFront Distribution with Regional S3 Origins

### Introduction

In this lab, we will be setting up a S3 bucket to serve as an origin for our CloudFront distribution by creating a S3 bucket, making it public, and creating a CloudFront distribution to help keep your site safer.

### Solution

1. Log in to the AWS Management Console using the credentials provided on the lab instructions page. Make sure you're using the `us-east-1` Region.
2. Optionally, download this [JPEG image](https://github.com/ACloudGuru-Resources/Course_Certified_Solutions_Architect_Professional/blob/e5b4e0ef7652e145a6b1446b3f266455b3509f2f/lab-networking/7508839.jpg) to use as a file to be uploaded for this lab.

#### Create an S3 Bucket with Content

1. In the AWS Management Console, select **S3**.
2. Click the **Create bucket** button.
3. Under **Bucket name**, enter a name for your bucket, such as _proxy_ followed by random digits.
4. Click the checkbox to remove the check next to **Block all public access**, which unblocks public access.
5. Click the checkbox next to **I acknowledge that the current settings might result in this bucket and the objects within becoming public**.
6. Click the **Create bucket** button.
7. Once the bucket is successfully created, click the link to the bucket in the **Buckets** section.
8. Click the **Upload** button.
9. Click the **Add Files** button.
10. Choose a file to be uploaded into the S3 bucket, such as the JPEG image from above.

> **Note** For the CloudFront distribution in later steps, there can't be any spaces in your object's filename. Only alphanumeric characters, (abc123) and dashes ( - ) or underscores ( \_ ).

11. Click the **Upload** button.

#### Add a Bucket Policy and Add a Tag on JPEG

1. Once the file has been successfully uploaded, click the link to the file in the **Files and folders** section.
2. Click the **Object actions** button in the top right.
3. From the dropdown menu, select **Edit tags**.
4. Click the **Add tag** button.
5. Under **Key**, enter _public_.
6. Under **Value - optional**, enter _yes_.
7. Click the **Save changes** button.
8. Click the link to the bucket in the **Summary** section.
9. Click the **Permissions** tab.
10. To the right of **Bucket policy**, click the **Edit** button.
11. Under **Policy**, paste the following policy:

    ```
    {
      "Version": "2012-10-17",
      "Statement": [
        {
          "Effect": "Allow",
          "Principal": "*",
          "Action": "s3:GetObject",
          "Resource": "arn:aws:s3:::DOC-EXAMPLE-BUCKET/*",
          "Condition": {
            "StringEquals": {
              "s3:ExistingObjectTag/public": "yes"
            }
          }
        }
      ]
    }
    ```
12. In the policy, replace `DOC-EXAMPLE-BUCKET` with the name of your bucket.
13. Click the **Save changes** button. You should see that your bucket is now labeled as **Publicly accessible**.

#### Create a CloudFront Distribution

1. Click on the **Objects** tab.
2. Click the link for the object in your S3 bucket.
3. Copy the name of your object.
4. In the search bar on top of the console, enter _cloudfront_.
5. From the search results, select **CloudFront**.
6. Click the **Create a CloudFront distribution** button.
7. Under **Origin domain**, select your bucket listed under **Amazon S3**.
8. Under **Web Application Firewall (WAF)** select **Do not enable security protections**.
9. Scroll down to the bottom of the page and under **Default root object - optional**, paste or enter the name of your object.
10. Click the **Create distribution** button. This process can take up to 5-10 minutes to complete successfully.

#### Navigate to the Distribution

1. Once the distribution has successfully launched, copy the distribution name under **Distribution domain name**.
2. Open a new browser window or tab.
3. Paste the distribution name in the address bar. You should see the file that you uploaded to your S3 bucket as an object.

\
