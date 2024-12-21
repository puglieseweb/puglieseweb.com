# Bucket Policies

1. Through AWS Console:

```
Copy1. Go to S3 console
2. Select your bucket
3. Click "Permissions" tab
4. Under "Bucket Policy", click "Edit"
5. Enter your policy in JSON format
6. Click "Save changes"
```

2. Basic Bucket Policy Structure:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "Statement1",
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:iam::ACCOUNT-ID:root"
            },
            "Action": [
                "s3:GetObject",
                "s3:PutObject"
            ],
            "Resource": [
                "arn:aws:s3:::BUCKET-NAME/*"
            ]
        }
    ]
}
```

Common Policy Examples:

1. Allow public read access to a specific folder:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicRead",
            "Effect": "Allow",
            "Principal": "*",
            "Action": ["s3:GetObject"],
            "Resource": ["arn:aws:s3:::BUCKET-NAME/PUBLIC-FOLDER/*"]
        }
    ]
}
```

2. Allow specific IAM user access:

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "AllowIAMUser",
            "Effect": "Allow",
            "Principal": {
                "AWS": "arn:aws:iam::ACCOUNT-ID:user/USERNAME"
            },
            "Action": [
                "s3:GetObject",
                "s3:PutObject",
                "s3:DeleteObject"
            ],
            "Resource": ["arn:aws:s3:::BUCKET-NAME/*"]
        }
    ]
}
```

Important Notes:

* Bucket policies apply to the entire bucket and all objects within
* Object ACLs are more granular and apply to specific objects
* Bucket policies can be up to 20KB in size
* When using both, the most restrictive policy takes precedence
* Always follow the principle of least privilege when granting permissions
* Regularly audit permissions to ensure security
