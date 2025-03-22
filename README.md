# Genetic Variant

## Description

This project is a genetic variant service that allows you to search for genetic variants in the ClinVar database.

You can use your data or download it from the FTP server. If data is not available, the service will download it from the FTP server.
## Run

### 1. Create `.env`.

Copy `.env.example` file to `.env` and set the variables.

### 2. Run the service:

For Linux:

```bash
docker compose up -d --build
```

For Windows:

```bash
docker-compose up -d --build
```

## Test

You can use the Postman collection `doc/ktor.postman_collection.json` to test the service.
