# TravelAI

TravelAI is a Spring Boot application that provides travel planning and chat-based recommendations using the Spring AI OpenAI-compatible integration. The service can chat with a user and provide destination recommendations based on destination, budget, duration, and preferences.

## Project Overview

- Java 21, Spring Boot 4.1.0
- Spring Web REST API
- Spring AI OpenAI model integration
- Provides chat and travel recommendation endpoints
- Uses a prompt template to request specific destination suggestions and travel tips

## Features

- `GET /chat` for conversational bot interaction
- `POST /travel` for a single travel recommendation
- `POST /travel/list` for multiple travel recommendation objects
- Request payloads include destination, budget, duration, additional preferences, and conversation ID

## Installation

1. Clone this project repository.
2. Open the `travelai` folder.
3. Ensure Java 21 is installed.
4. Use Maven to build the application.

```bash
cd travel-agent-ai
./mvnw clean package
```

## Configuration

The service is configured in `src/main/resources/application.properties`:

```properties
spring.ai.openai.api-key=${GROQ_API_KEY}
spring.ai.openai.base-url=https://api.groq.com/openai/v1
spring.ai.openai.chat.model=llama-3.3-70b-versatile
```

Set the environment variable `GROQ_API_KEY` before running the application.

On Windows PowerShell:

```powershell
$env:GROQ_API_KEY="YOUR_API_KEY"
```

On Linux/macOS:

```bash
export GROQ_API_KEY="YOUR_API_KEY"
```

## Running the Service

Start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

The application runs by default on `http://localhost:8080`.

## API Endpoints

### 1. Chat

- URL: `GET /chat`
- Query parameters:
  - `message` (optional, default: `Hello, Gemini. Briefly introduce yourself.`)
  - `conversationId` (required)

Example:

```bash
curl "http://localhost:8080/chat?conversationId=12345&message=Hello"
```

Response:

```json
{
  "prompt": "Hello",
  "reply": "..."
}
```

### 2. Travel Recommendation

- URL: `POST /travel`
- Body: JSON object with request fields

Request example:

```bash
curl -X POST http://localhost:8080/travel \
  -H "Content-Type: application/json" \
  -d '{
    "conversationId": "12345",
    "destination": "Bali",
    "budget": "5000000",
    "duration": "5",
    "additionalPreferences": "beach and culture"
  }'
```

Response shape:

```json
{
  "destination": "...",
  "estimatedCost": "...",
  "description": "...",
  "travelTips": "...",
  "recommendedActivities": [
    {
      "name": "...",
      "description": "...",
      "category": "..."
    }
  ]
}
```

### 3. Travel Recommendation List

- URL: `POST /travel/list`
- Body: same JSON request as `/travel`

Example:

```bash
curl -X POST http://localhost:8080/travel/list \
  -H "Content-Type: application/json" \
  -d '{
    "conversationId": "12345",
    "destination": "Bali",
    "budget": "5000000",
    "duration": "5",
    "additionalPreferences": "beach and culture"
  }'
```

Response: list of travel recommendation objects.

## Request Fields

- `conversationId`: unique identifier to maintain conversational memory across requests
- `destination`: destination province or region name
- `budget`: budget amount in Indonesian Rupiah (Rp)
- `duration`: number of travel days
- `additionalPreferences`: optional preferences such as beach, culture, food, adventure

## Notes

- The travel recommendation prompt is defined in `src/main/java/com/travelai/travelai/util/PromptTemplateUtil.java`.
- The service expects the AI model to return destination-specific recommendations and detailed activities.
- Use a valid API key for the configured OpenAI-compatible provider.

## Testing

Run the Spring Boot tests with Maven:

```bash
./mvnw test
```

## Troubleshooting

- If the app fails to connect to the AI provider, verify `GROQ_API_KEY` is set and valid.
- Confirm `spring.ai.openai.base-url` points to the correct OpenAI-compatible endpoint.

---

Built with Spring Boot and Spring AI for travel recommendation automation.
