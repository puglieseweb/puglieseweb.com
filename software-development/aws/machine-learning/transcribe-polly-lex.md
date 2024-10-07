# Transcribe, Polly, Lex

* Transcribe is used to convert audios, videos and images into text.&#x20;
* Polly:
  * Polly is a transcript editing and cleanup tool.
  * turning the text into lifelike speech using a variety of languages and accents.
  * It's designed to take raw transcripts (often from automatic speech recognition systems) and help clean them up.
  * Features may include fixing punctuation, adjusting formatting, and correcting common transcription errors.
  * The goal is to transform raw, often messy transcripts into cleaner, more readable text.
* Lex:
  * Lex is a writing and publishing platform.
  * It's used for composing, editing, and publishing various types of content, including blog posts, articles, and potentially podcast show notes.
  * Lex often incorporates AI-assisted writing features to help with composition and editing.
  * It provides a streamlined interface for writing and publishing content directly to the web.

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

