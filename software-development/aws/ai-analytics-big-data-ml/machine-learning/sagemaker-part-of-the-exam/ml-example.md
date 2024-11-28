# ML Example

Real-world Example: Let's say you're building a document classification system:

1. Glue processes raw documents, extracting text and metadata
2. Ground Truth workers label these processed documents
3. The combined processed and labeled data becomes your training dataset

The key is that Glue handles data preparation/transformation, while Ground Truth handles the labeling aspect.



SageMaker Ground Truth:

* Used for data labeling/annotation
* Creates training datasets
* Handles both automated and human labeling
* Focuses on preparing data for ML training

AWS Glue:

* Handles ETL (Extract, Transform, Load) operations
* Data cataloging and discovery
* Schema management
* Data preprocessing and transformation

Integration Pattern Example:

1. Raw data stored in S3
2. Glue catalogs and processes the raw data
3. Ground Truth used to label the processed data
4. Labeled data stored back in S3
5. SageMaker uses final dataset for training

