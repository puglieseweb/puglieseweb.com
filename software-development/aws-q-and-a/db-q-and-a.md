# DB Q\&A

## Can I use Amazon Aurora Global Database for bidirectional data replication across regions

Yes, you can use Amazon Aurora Global Database for bidirectional data replication across regions. However, it's important to note some key points about how this works:

1. Primary-Secondary Model: Aurora Global Database traditionally uses a primary-secondary model. One region hosts the primary cluster that handles write operations, while other regions contain read-only secondary clusters.
2. Write Forwarding: Aurora introduced a feature called write forwarding. This allows secondary regions to accept write queries, which are then automatically forwarded to the primary region for processing.
3. Limitations:
   * Write forwarding adds some latency, as writes must travel to the primary region.
   * It's not true bidirectional replication in the sense that all regions are equal.
   * There can be conflicts if the same data is modified in multiple regions simultaneously.
4. Use Cases: This setup is beneficial for:
   * Disaster recovery
   * Reducing read latency for global applications
   * Enabling some write capabilities in secondary regions
5. Alternatives: For true bidirectional replication, you might need to consider other solutions like:
   * Custom application-level replication
   * Third-party database replication tools

## Can I store images and movies in DynamoDB?

While DynamoDB can technically store binary data like movies or pictures, it's generally not recommended for several reasons:

1. Size limitations:
   * As we discussed earlier, DynamoDB has a maximum item size of 400 KB.
   * Most movies and high-quality images easily exceed this limit.
2. Cost and performance:
   * DynamoDB charges based on the amount of data stored and read/write capacity units used.
   * Storing large binary files would be expensive and inefficient.
   * Querying and retrieving large binary objects would be slow and consume a lot of read capacity.
3. Lack of streaming support:
   * DynamoDB doesn't support efficient streaming of binary data, which is crucial for video playback.

Instead, here's the recommended approach for storing movies or pictures in conjunction with DynamoDB:

1. Use Amazon S3 (Simple Storage Service):
   * S3 is designed for storing and retrieving large objects like movies and images.
   * It's much more cost-effective for this purpose.
   * S3 supports efficient streaming for video content.
2. Store metadata in DynamoDB:
   * Use DynamoDB to store metadata about your movies or pictures.
   * This could include filenames, upload dates, user IDs, tags, etc.
   * Also store the S3 object key (essentially the file path in S3) in DynamoDB.

Here's a simple example of how you might structure this:

```json
{
  "TableName": "UserMedia",
  "KeySchema": [
    { "AttributeName": "UserId", "KeyType": "HASH" },
    { "AttributeName": "MediaId", "KeyType": "RANGE" }
  ],
  "AttributeDefinitions": [
    { "AttributeName": "UserId", "AttributeType": "S" },
    { "AttributeName": "MediaId", "AttributeType": "S" }
  ],
  "Items": [
    {
      "UserId": "user123",
      "MediaId": "photo1",
      "Type": "image",
      "S3Key": "users/user123/photos/photo1.jpg",
      "UploadDate": "2024-10-12T14:30:00Z",
      "FileSize": 2500000,
      "ContentType": "image/jpeg",
      "Tags": ["vacation", "beach"]
    },
    {
      "UserId": "user123",
      "MediaId": "video1",
      "Type": "video",
      "S3Key": "users/user123/videos/video1.mp4",
      "UploadDate": "2024-10-12T15:45:00Z",
      "FileSize": 150000000,
      "ContentType": "video/mp4",
      "Duration": 180,
      "Tags": ["family", "birthday"]
    }
  ]
}

```

In this structure:

1. The `UserMedia` table uses a composite key of `UserId` and `MediaId`.
2. Each item contains metadata about the media file, including its location in S3 (`S3Key`).
3. The actual media files (images, videos) are stored in S3, not in DynamoDB.

This approach offers several benefits:

* Efficient querying: You can quickly find all media for a user or specific pieces of media.
* Scalability: S3 handles the large files, while DynamoDB manages the lightweight metadata.
* Cost-effective: You're not paying DynamoDB prices for storing large binary data.
* Performance: Retrieving metadata is fast, and S3 is optimized for serving large files.

To use this setup in an application:

1. To upload: Save the file to S3, then create a metadata entry in DynamoDB.
2. To retrieve: Query DynamoDB for the metadata, then use the S3 key to fetch the file from S3.

This pattern is commonly used in applications dealing with user-generated content, media libraries, and similar scenarios where you need to manage large files along with searchable metadata.
