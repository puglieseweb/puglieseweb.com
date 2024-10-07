# Transcribe, Polly, Lex

Transcribe is used to convert videos and images to text.&#x20;

Lex: is the chatbots.

Polly: turns text into lifelike speech using a variety of languages and accents.

## Alexa example

Person -> Amazon Translate -> Lex -> Polly -> Perosn&#x20;





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
