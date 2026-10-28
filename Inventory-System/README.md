# Inventory System

A Spring Boot REST API for managing products and their stock levels.

## Tech Stack

- Java 17
- Spring Boot 3 (Web, Data JPA, Validation, Actuator)
- H2 in-memory database
- Maven

## Project Structure

```
Inventory-System/
├── .github/workflows/ci.yml        # GitHub Actions build & test
├── src/
│   ├── main/
│   │   ├── java/com/inventory/
│   │   │   ├── InventorySystemApplication.java
│   │   │   ├── controller/         # REST endpoints
│   │   │   ├── dto/                # Request payloads
│   │   │   ├── exception/          # Custom exceptions & global handler
│   │   │   ├── model/              # JPA entities
│   │   │   ├── repository/         # Spring Data repositories
│   │   │   └── service/            # Business logic
│   │   └── resources/
│   │       └── application.yml
│   └── test/java/com/inventory/    # Unit & integration tests
├── .editorconfig
├── .gitattributes
├── .gitignore
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.9+

### Build & Run

```bash
mvn clean verify
mvn spring-boot:run
```

The API starts on `http://localhost:8080`. The H2 console is at `http://localhost:8080/h2-console`
(JDBC URL `jdbc:h2:mem:inventorydb`, user `sa`, empty password).

## API Endpoints

| Method | Path                               | Description                         |
|--------|------------------------------------|-------------------------------------|
| GET    | `/api/products`                    | List all products                   |
| GET    | `/api/products/{id}`               | Get a product by id                 |
| GET    | `/api/products/low-stock?threshold=5` | Products at or below a threshold |
| POST   | `/api/products`                    | Create a product                    |
| PUT    | `/api/products/{id}`               | Update a product                    |
| PATCH  | `/api/products/{id}/stock`         | Adjust stock by `delta`             |
| DELETE | `/api/products/{id}`               | Delete a product                    |
| GET    | `/actuator/health`                 | Health check                        |

### Example

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"sku":"SKU-001","name":"Widget","quantity":10,"price":9.99}'

curl -X PATCH http://localhost:8080/api/products/1/stock \
  -H "Content-Type: application/json" \
  -d '{"delta":-3}'
```

## Pushing to GitHub

```bash
git init
git add .
git commit -m "Initial commit: Inventory System"
git branch -M main
git remote add origin https://github.com/<your-username>/Inventory-System.git
git push -u origin main
```
