# Banking REST API

A RESTful banking backend built with Spring Boot. Supports user registration, JWT-based authentication, fund transfers, transaction history, and admin controls.

---

## Tech Stack

- Java, Spring Boot
- Spring Data JPA
- JSON Web Tokens (JWT)
- MySQL
- Docker

---

## Features

- User registration and login with JWT authentication
- Check account balance
- Transfer funds between accounts
- View full transaction history
- Admin endpoints: view all users, freeze accounts

---

## Getting Started

### Prerequisites
- Java 17+
- MySQL
- Docker (optional)

### Setup

1. Clone the repository
```bash
git clone https://github.com/your-username/banking-api.git
cd banking-api
```

2. Create a MySQL database
```sql
CREATE DATABASE bankdb;
```

3. Update `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

4. Run the application
```bash
./gradlew bootRun
```

The server starts at `http://localhost:8080`

---

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/register` | Register a new user |
| POST | `/api/login` | Login and receive a JWT token |

### User
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/balance?email=` | Get account balance |
| POST | `/api/transfer` | Transfer funds to another user |
| GET | `/api/transactions?email=` | Get transaction history |

### Admin
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/users` | Get all users |
| POST | `/api/admin/freeze` | Freeze a user account |

---

## Example Requests

### Register
```json
POST /api/register
{
  "name": "test",
  "email": "test@gmail.com",
  "password": "123456",
  "balance": 1000
}
```

### Login
```json
POST /api/login
{
  "email": "test@gmail.com",
  "password": "123456"
}
```

### Transfer
```json
POST /api/transfer
{
  "senderEmail": "test@gmail.com",
  "receiverEmail": "test2@gmail.com",
  "amount": 100
}
```

---

## Project Structure

```
src/main/java/com/example/demo/
├── controller/
│   ├── UserController.java
│   └── AdminController.java
├── service/
│   ├── UserService.java
│   └── AdminService.java
├── repository/
│   ├── UserRepository.java
│   └── TransactionRepository.java
├── entity/
│   ├── User.java
│   └── Transaction.java
├── dto/
│   ├── LoginRequest.java
│   └── TransferRequest.java
└── util/
    └── JwtUtil.java
```
