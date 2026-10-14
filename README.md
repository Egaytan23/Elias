# Elias

Elias is a personal assistant project designed to help people manage
appointments, tasks, and reminders through their existing calendar.

## Current Features

- Spring Boot backend built with Java 17
- GET /api/health endpoint returning JSON
- Automated application startup and endpoint tests
- GitHub Actions CI that runs tests on pushes and pull requests

## Planned Features

- Google Calendar integration
- Reminder creation and phone notifications
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