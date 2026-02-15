# Common Amazon SageMaker Endpoint usage patterns

Common Amazon SageMaker Endpoint usage patterns with real-world scenarios:

1. Real-Time Product Recommendations (E-commerce)

```python
pythonCopy# API Gateway endpoint calling Lambda function
def lambda_handler(event, context):
    runtime = boto3.client('sagemaker-runtime')
    user_data = {
        "user_id": event['user_id'],
        "recent_views": event['product_history'],
        "cart_items": event['cart']
    }
    
    response = runtime.invoke_endpoint(
        EndpointName='recommendation-endpoint',
        ContentType='application/json',
        Body=json.dumps(user_data)
    )
    
    recommendations = json.loads(response['Body'].read())
    return {
        'statusCode': 200,
        'body': recommendations
    }
```

2. Fraud Detection (Financial Services)

```python
pythonCopy# Real-time transaction screening
def process_transaction(transaction_data):
    runtime = boto3.client('sagemaker-runtime')
    
    # Batch processing for multiple transactions
    responses = []
    for transaction in transaction_data:
        response = runtime.invoke_endpoint(
            EndpointName='fraud-detection-endpoint',
            ContentType='application/json',
            Body=json.dumps(transaction)
        )
        risk_score = json.loads(response['Body'].read())
        
        if risk_score > 0.8:
            flag_for_review(transaction)
        else:
            approve_transaction(transaction)
```

3. Image Processing (Content Moderation)

```python
pythonCopydef moderate_content(image_bytes):
    runtime = boto3.client('sagemaker-runtime')
    
    response = runtime.invoke_endpoint(
        EndpointName='content-moderation-endpoint',
        ContentType='application/x-image',
        Body=image_bytes
    )
    
    results = json.loads(response['Body'].read())
    return {
        'is_safe': results['safe_score'] > 0.95,
        'categories': results['detected_categories']
    }
```

4. Natural Language Processing (Customer Service)

```python
pythonCopy# Auto-routing customer inquiries
def route_customer_ticket(ticket_text):
    runtime = boto3.client('sagemaker-runtime')
    
    response = runtime.invoke_endpoint(
        EndpointName='ticket-classification-endpoint',
        ContentType='application/json',
        Body=json.dumps({"text": ticket_text})
    )
    
    classification = json.loads(response['Body'].read())
    return assign_to_department(classification['department'])
```

Common Deployment Patterns:

1. Multi-Model Endpoints

```python
pythonCopy# Cost-effective hosting of multiple models
runtime = boto3.client('sagemaker-runtime')
response = runtime.invoke_endpoint(
    EndpointName='multi-model-endpoint',
    TargetModel='customer-churn-model-v2',
    ContentType='application/json',
    Body=json.dumps(customer_data)
)
```

2. Auto-scaling Configuration

```python
pythonCopy# Setting up auto-scaling for endpoints
client = boto3.client('application-autoscaling')

response = client.register_scalable_target(
    ServiceNamespace='sagemaker',
    ResourceId=f'endpoint/{endpoint_name}/variant/AllTraffic',
    ScalableDimension='sagemaker:variant:DesiredInstanceCount',
    MinCapacity=1,
    MaxCapacity=4
)

# Define scaling policy based on endpoint invocations
response = client.put_scaling_policy(
    PolicyName='RequestScaling',
    ServiceNamespace='sagemaker',
    ResourceId=f'endpoint/{endpoint_name}/variant/AllTraffic',
    ScalableDimension='sagemaker:variant:DesiredInstanceCount',
    PolicyType='TargetTrackingScaling',
    TargetTrackingScalingPolicyConfiguration={
        'TargetValue': 70.0,
        'PredefinedMetricSpecification': {
            'PredefinedMetricType': 'SageMakerVariantInvocationsPerInstance'
        }
    }
)
```

3. A/B Testing with Production Variants

```python
pythonCopy# Creating endpoint with multiple variants
create_endpoint_config_response = sagemaker_client.create_endpoint_config(
    EndpointConfigName=endpoint_config_name,
    ProductionVariants=[{
        'VariantName': 'ModelA',
        'ModelName': 'model-a',
        'InitialInstanceCount': 1,
        'InstanceType': 'ml.m5.xlarge',
        'InitialVariantWeight': 0.5
    }, {
        'VariantName': 'ModelB',
        'ModelName': 'model-b',
        'InitialInstanceCount': 1,
        'InstanceType': 'ml.m5.xlarge',
        'InitialVariantWeight': 0.5
    }]
)
```

Best Practices:

1. Monitoring Setup

```python
pythonCopy# CloudWatch custom metrics for model monitoring
cloudwatch = boto3.client('cloudwatch')

def log_prediction_metrics(prediction, actual):
    cloudwatch.put_metric_data(
        Namespace='ModelMetrics',
        MetricData=[{
            'MetricName': 'PredictionAccuracy',
            'Value': calculate_accuracy(prediction, actual),
            'Unit': 'Percent'
        }]
    )
```

2. Error Handling

```python
pythonCopydef invoke_endpoint_with_retry(endpoint_name, payload, max_retries=3):
    runtime = boto3.client('sagemaker-runtime')
    
    for attempt in range(max_retries):
        try:
            response = runtime.invoke_endpoint(
                EndpointName=endpoint_name,
                ContentType='application/json',
                Body=json.dumps(payload)
            )
            return json.loads(response['Body'].read())
        except Exception as e:
            if attempt == max_retries - 1:
                raise
            time.sleep(2 ** attempt)  # Exponential backoff
```

These patterns show how SageMaker endpoints are typically used in production environments. The key is to:

* Set up proper monitoring and scaling
* Implement robust error handling
* Use cost-effective deployment strategies
* Enable A/B testing when needed
* Integrate with other AWS services for complete solutions
