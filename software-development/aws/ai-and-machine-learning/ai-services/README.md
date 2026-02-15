# AI

Here's a comprehensive breakdown of AWS AI services:

1. Core AI/ML Services

* Amazon SageMaker
  * Full ML platform
  * Model development, training, deployment
  * Built-in algorithms
  * Notebooks and development environments
  * MLOps capabilities

2. AI Applications

* Vision:
  * Rekognition (image/video analysis)
  * Lookout for Vision (visual inspection)
* Language/Text:
  * Comprehend (NLP)
  * Translate
  * Textract (document processing)
  * Transcribe (speech-to-text)
  * Kendra (intelligent search)
* Speech:
  * Polly (text-to-speech)
  * Lex (chatbots)

3. Generative AI

* Bedrock
  * Access to foundation models (Claude, Llama, Stable Diffusion)
  * API integration
  * Model customization
  * Fine-tuning capabilities
* Amazon CodeWhisperer
  * AI-powered coding assistant
  * Code suggestions
  * Security scans

4. Business Solutions

* Forecast (time-series forecasting)
* Fraud Detector
* Personalize (recommendation systems)
* Contact Lens (contact center analytics)

5. AI Infrastructure

```
Development → Training → Deployment → Monitoring
(SageMaker Studio) (EC2, ECS) (Endpoints) (CloudWatch)
```

6. Latest Additions:

* Amazon Q (enterprise AI assistant)
* HealthScribe (medical transcription)
* AWS Clean Rooms ML (collaborative ML)
* Titan (AWS foundation models)

Common Integration Pattern:

```
Data Sources → Data Prep → AI Processing → Business Apps
(S3, RDS)    (Glue)     (SageMaker)    (Applications)
         ↳ Bedrock API calls for GenAI ↗
```
