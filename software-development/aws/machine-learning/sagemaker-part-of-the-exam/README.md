# Sagemaker (part of the exam)

Sagemaker is a way to build and deploy machine learning models in AWS:

* Ground Truth: Set up and mange labeling jobs for training datasets using active learning and human labeling.
* Notebook: Access a manged Jupyter notebook environment.
* Training. Train and tune models.
* Package and deploy you machine learning models at scale.&#x20;

## Deployment Type&#x20;

&#x20;

|               | Offline Usage deployment                               | Online Usage deployment       |
| ------------- | ------------------------------------------------------ | ----------------------------- |
| Usage         | Asynchronous or batch                                  | Synchronous or real time      |
| When          | Generate predictions for an entire dataset all at once | SageMaker hosting service     |
| Method        | SageMaker batch transform                              | SageMaker hosting service     |
| Input format  | Varies depending on the algorithm                      | Varies depending on algorithm |
| Output format | Varies depending on algorithm                          | JSON String                   |

Stateges:

1. Create Model: provide the predictions for your endpoint
2. Create an endpoint configuration: whre you specify the moder to use, interface instance type, instance count, variant name, and weight&#x20;
3. Create Endpoint: the method is published and you can invoke the mehod using InvokeEndpoint()

