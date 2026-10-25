# Elias

Elias is a personal assistant project built with Java and Spring Boot.
Its current backend supports creating and listing reminders through a REST API.
Calendar integration and notifications are planned.

## Current Features

## Current Features

- Spring Boot backend built with Java 17
- JSON health endpoint
- Create and list reminders through REST endpoints
- Validation for missing or blank titles and missing or nonfuture reminder times
- HTTP 400 responses with clear validation messages
- In-memory reminder storage with synchronized creation and retrieval
- Automated tests covering startup, health, reminder validation, POST responses,
  and concurrent reminder creation
- GitHub Actions CI that runs tests on pushes and pull requests

## API Endpoints

| Method | Endpoint | Purpose | Success Status |
|--------|----------|---------|----------------|
| GET | /api/health | Check that the backend responds | 200 |
| POST | /api/reminders | Create a reminder | 201 |
| GET | /api/reminders | List stored reminders | 200 |

## Current Limitations

- Reminders are stored in memory and disappear when the application restarts.
- IDs reset when the application restarts.
- The API does not yet support user accounts or authentication.
- Creating a reminder stores its details; it does not send a notification.
- Synchronization protects shared storage within one service instance.
  It does not coordinate multiple application instances.

## Planned Features

- PostgreSQL persistence with database migrations
- User accounts, authentication, and reminder ownership
- Update and delete reminders
- Reminder notifications
- Google Calendar integration
- Voice input with a microphone button
- iCloud Calendar support in a later version

## Run Locally

### Requirements
- Java 17
- Git

### Setup
```bash
git clone https://github.com/Egaytan23/Elias.git
cd Elias
```

### Start the application (Windows PowerShell)
```powershell
.\mvnw.cmd spring-boot:run
```

Once the application starts, visit:
http://localhost:8080/api/health

### Run tests
```powershell
.\mvnw.cmd test
```

## Why build it?

- I decided to build this project because I wanted to expand my tech stack and I needed a personal assistant to remind me of important dates and help me stay organized while I work a full time job, work on my internship, and manage my everyday responsibilities. 