# E-Commerce REST API

A Spring Boot-based e-commerce REST API with JWT authentication, Swagger documentation, and PostgreSQL database.

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Security with JWT
- PostgreSQL
- Swagger/OpenAPI 3.0
- Maven
- Lombok
- JPA/Hibernate

## Features

- JWT-based Authentication
- Role-based Authorization
- API Documentation with Swagger
- Exception Handling
- Database Auditing
- RESTful API Design

## Prerequisites

- JDK 17 or later
- Maven 3.6+
- PostgreSQL 12+

## Setup & Installation

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/ecommerce-api.git
cd ecommerce-api
```

2. **Configure PostgreSQL**
   - Create a new PostgreSQL database
   - Update `src/main/resources/application.properties` with your database credentials:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Build the project**
```bash
mvn clean install
```

4. **Run the application**
```bash
mvn spring-boot:run
```

## API Documentation

Once the application is running, you can access the Swagger UI documentation at:
```

## API Endpoints

### Authentication
- POST `/api/auth/signup` - Register a new user
- POST `/api/auth/login` - Login and get JWT token

### Categories
- GET `/api/categories` - Get all categories
- GET `/api/categories/{id}` - Get category by ID
- POST `/api/categories` - Create new category (Admin only)
- PUT `/api/categories/{id}` - Update category (Admin only)
- DELETE `/api/categories/{id}` - Delete category (Admin only)

### Users
- GET `/api/users` - Get all users (Admin only)
- GET `/api/users/{id}` - Get user by ID
- PUT `/api/users/{id}` - Update user
- DELETE `/api/users/{id}` - Delete user (Admin only)

## Security

The API uses JWT (JSON Web Token) for authentication. To access protected endpoints:
1. Login using `/api/auth/login` to get the JWT token
2. Include the token in the Authorization header for subsequent requests:
   ```
   Authorization: Bearer your_jwt_token
   ```

## Error Handling

The API uses standard HTTP status codes and returns error responses in the following format:
```json
{
    "timestamp": "2024-03-10T10:00:00Z",
    "status": 404,
    "error": "Not Found",
    "message": "Resource not found",
    "path": "/api/resource/123"
}
```

