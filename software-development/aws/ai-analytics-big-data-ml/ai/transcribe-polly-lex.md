# Transcribe, Polly, Lex

## AWS Audio & Conversational AI Services

### Amazon Transcribe

Converts audio and video into text using automatic speech recognition (ASR).

### Amazon Polly

[Polly](https://aws.amazon.com/polly/) (Generate speech):

* Text-to-speech service that creates lifelike speech
* Supports multiple languages and accents
* Natural-sounding voices for various applications
* Used for:
  * Audio content creation
  * Voice response systems
  * E-learning materials
  * Accessibility solutions

### Amazon Lex

[Lex](https://aws.amazon.com/pm/lex/) (AI conversational application - chatbox):

* Conversational AI service for building chatbots and voice interfaces
* Powers technology behind Amazon Alexa
* Key features:
  * Natural language understanding
  * Speech recognition
  * Dialog management
  * Integration with AWS services
* Common uses:
  * Customer service bots
  * Virtual assistants
  * Automated information systems
  * Interactive voice response (IVR)





<figure><img src="../../../../.gitbook/assets/image (24).png" alt=""><figcaption></figcaption></figure>



## Alexa example



```mermaid
graph TD
    A[User Voice Input] --> B[Speech Recognition]
    B --> C[Transcribe]
    C --> D[Raw Transcript]
    D --> E[Poly]
    E --> F[Cleaned Transcript]
    F --> G[Natural Language Processing]
    G --> H{Intent Recognition}
    H --> |Query| I[Lex]
    I --> J[Content Generation]
    H --> |Command| K[Action Execution]
    J --> L[Text-to-Speech]
    K --> L
    L --> M[Voice Response to User]

    style B fill:#f9d5e5,stroke:#333,stroke-width:2px
    style C fill:#f9d5e5,stroke:#333,stroke-width:2px
    style E fill:#eeac99,stroke:#333,stroke-width:2px
    style I fill:#e06377,stroke:#333,stroke-width:2px
    style G fill:#b5e7a0,stroke:#333,stroke-width:2px
    style H fill:#b5e7a0,stroke:#333,stroke-width:2px
```

### Example 2



```mermaid
graph TD
    A[Audio Input] --> B[Transcribe]
    B --> C[Raw Transcript]
    C --> D[Pully]
    D --> E[Cleaned Transcript]
    E --> F[Lex]
    F --> G[Formatted Content]
    G --> H[Published Output]

    style B fill:#f9d5e5,stroke:#333,stroke-width:2px
    style D fill:#eeac99,stroke:#333,stroke-width:2px
    style F fill:#e06377,stroke:#333,stroke-width:2px
```

