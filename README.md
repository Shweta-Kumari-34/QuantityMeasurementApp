## UC17 - Spring Boot REST API with JPA Persistence for Quantity Measurement
**Date:** 12 March 2026

---

UC17 transforms the Quantity Measurement Application into a **Spring Boot RESTful service** with **JPA-based database persistence**, enabling API-driven interaction and automatic database operations.

The system now exposes quantity measurement operations over HTTP, allowing integration with **Swagger UI, Postman, and frontend applications**.

---

### Why UC17?

UC16 used JDBC with manual SQL handling, which had limitations:

- Manual query writing increased boilerplate code  
- No REST API exposure  
- No automatic API documentation  
- No standardized HTTP responses  
- Tight coupling between layers  
- No JSON-based communication support  

UC17 resolves these by introducing Spring Boot and JPA.

---

### Key Enhancements

- Introduced Spring Boot REST APIs  
- Replaced JDBC with Spring Data JPA  
- Added REST Controller layer  
- Implemented DTO-based request/response  
- Enabled automatic CRUD operations  
- Added global exception handling  
- Integrated Swagger (OpenAPI) documentation  
- Enabled JSON request and response handling  

---

### Architecture Flow

Client (Swagger / Postman / Frontend)  
↓  
Controller Layer (REST APIs)  
↓  
DTO Layer (Request / Response)  
↓  
Service Layer (Business Logic)  
↓  
Domain Logic (Quantity Engine)  
↓  
Entity Layer (JPA Mapping)  
↓  
Repository Layer (Spring Data JPA)  
↓  
Database  

This ensures clean separation of concerns and scalable architecture.

---

### REST Controller

UC17 introduces:

QuantityMeasurementController

Responsibilities:

- Handle HTTP requests (GET, POST)  
- Validate input using annotations  
- Delegate to Service layer  
- Return JSON responses  

Available APIs:

- POST /compare  
- POST /convert  
- POST /add  
- POST /subtract  
- POST /divide  
- GET /history/type/{type}  
- GET /count/{operation}  

---

### Service Layer

QuantityMeasurementServiceImpl

Responsibilities:

- Convert DTO to domain objects  
- Perform quantity operations  
- Handle validation  
- Save results to database  
- Return response DTO  

---

### Repository Layer

Spring Data JPA Repository:

IQuantityMeasurementRepository

Responsibilities:

- Automatic CRUD operations  
- Query generation  
- Data persistence  

---

### Entity Layer

QuantityMeasurementEntity

Database Table:

quantity_measurements

Fields:

- id  
- operand1  
- operand2  
- operation  
- result  
- error  

---

### DTO Layer

UC17 introduces:

QuantityInputDTO → Request object  
QuantityMeasurementDTO → Response object  
QuantityDTO → Internal service object  

This ensures loose coupling between layers.

---

### Swagger Integration

Swagger UI provides interactive API documentation.

Access URLs:

Swagger UI → http://localhost:8080/swagger-ui.html  
OpenAPI Docs → http://localhost:8080/v3/api-docs  

---

### Example Flow

Example:

1 ft + 12 in

Execution flow:

Client (Swagger/Postman)  
→ Controller  
→ Service  
→ Quantity Engine  
→ JPA Repository  
→ Database  
→ Response DTO  
→ Client  

Response:

{
  "result": 2,
  "unit": "FEET",
  "operation": "ADD",
  "message": "Addition successful"
}

---

### Benefits of UC17

- REST API based architecture  
- Automatic database persistence  
- Swagger documentation  
- JSON-based communication  
- Reduced boilerplate code  
- Clean layered architecture  
- Enterprise-ready backend  

---

### Conclusion

UC17 converts the Quantity Measurement Application into a **modern Spring Boot REST API** with JPA persistence, structured layering, and interactive API documentation.

The system is now scalable, maintainable, and ready for frontend or microservice integration.
