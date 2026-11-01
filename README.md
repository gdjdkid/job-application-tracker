# Job Application Tracker

[![Backend CI](https://github.com/gdjdkid/job-application-tracker/actions/workflows/backend-ci.yml/badge.svg)](https://github.com/gdjdkid/job-application-tracker/actions/workflows/backend-ci.yml)

A RESTful backend application for managing and tracking job applications throughout the recruitment process.

This project is being developed as a hands-on Java backend engineering portfolio project, with a focus on clean code, layered architecture, database persistence, and automated testing.

## Tech Stack

| Category | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4 |
| Database | PostgreSQL 17 |
| Persistence | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| Testing | JUnit 5, Mockito |
| Infrastructure | Docker Compose |
| API Testing | Postman |

## Features

**Currently implemented:**

- Create a job application
- Retrieve job applications
- Update an existing application
- Delete an application
- Track application status
- Validate requests and handle API errors
- Persist application data in PostgreSQL
- Unit-test service-layer business logic with JUnit and Mockito

## Project Structure

```text
job-application-tracker/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/royli/jobtracker/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── exception/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw
├── docker-compose.yml
├── .env.example
└── README.md
```

## Getting Started

### Prerequisites

- Java 21
- Docker Desktop with Docker Compose
- Git

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/job-application-tracker.git
cd job-application-tracker
```

Replace `YOUR_USERNAME` with your GitHub username.

### 2. Configure environment variables

Create a `.env` file from the example:

```bash
cp .env.example .env
```

Set your own PostgreSQL development password:

```dotenv
DB_PASSWORD=your_local_database_password
```

The `.env` file is excluded from Git to avoid committing local credentials.

### 3. Start PostgreSQL

```bash
docker compose up -d
```

PostgreSQL is exposed on `127.0.0.1:5432`.

### 4. Run the Spring Boot application

From the project root:

```bash
cd backend
export DB_PASSWORD=your_local_database_password
./mvnw spring-boot:run
```

The API runs locally at:

```text
http://localhost:8080
```

The `export` command is intended for Git Bash or a similar Unix-style shell.

## Running Tests

Run the automated tests from the `backend` directory:

```bash
./mvnw test
```

For tests that start the Spring application context, make sure the database is available and the `DB_PASSWORD` environment variable is configured.

The project includes unit tests for application deletion behavior, including successful deletion and handling of non-existing records.

## Architecture

The application follows a layered backend architecture:

- **Controller:** Exposes REST endpoints and handles HTTP requests.
- **DTO:** Defines request data structures.
- **Service:** Implements application business logic.
- **Repository:** Provides database access through Spring Data JPA.
- **Entity:** Maps application data to persistent database records.
- **Exception Handler:** Centralizes API error handling.

## Development Roadmap

Potential future improvements:

- Pagination, filtering, and sorting
- Expanded unit and integration test coverage
- Database migrations with Flyway
- OpenAPI / Swagger documentation
- CI pipeline using GitHub Actions
- Frontend dashboard for tracking applications

## Project Status

**Active development — backend MVP**

This project is being developed incrementally to demonstrate practical Java backend development skills and software engineering practices.