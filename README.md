<<<<<<< HEAD
# QuantityMeasurementApp
=======
## UC18 - Google Authentication and User Management for Quantity Measurement
**Date:** 20 March 2026

---

UC18 enhances the Spring Boot Quantity Measurement backend by introducing **user authentication and authorization** using **Spring Security, JWT, and OAuth2 (Google Login)**.

This transforms the application from an open API system (UC17) into a **secure, user-aware backend** with protected endpoints and user-specific operation management.

---

### Why UC18?

Before UC18:

- APIs were publicly accessible  
- No user authentication  
- No user-specific operation tracking  
- No authorization control  
- No secure token-based communication  

UC18 resolves these limitations by adding authentication and security.

---

### Key Enhancements

- Integrated Spring Security  
- Implemented JWT-based authentication  
- Added Google OAuth2 login support  
- Introduced User entity and repository  
- Secured REST endpoints  
- Enabled role-based access control  
- Implemented token-based stateless authentication  
- Added login and registration APIs  

---

### Security Architecture Flow

Client (Frontend / Swagger)  
↓  
Authentication Request (Google / Login)  
↓  
Spring Security Filter  
↓  
JWT Token Generation  
↓  
Client stores JWT Token  
↓  
Authenticated API Request  
↓  
JWT Validation Filter  
↓  
Controller Layer  
↓  
Service Layer  
↓  
Repository Layer  
↓  
Database  

---

### Authentication Mechanisms

UC18 supports two authentication methods:

- Google OAuth2 Login  
- JWT Username/Password Authentication  

Both generate a JWT token used for subsequent API requests.

---

### User Management

UC18 introduces:

User Entity

Fields:

- id  
- name  
- email  
- password  
- role  
- provider (GOOGLE / LOCAL)  

This enables user-specific tracking and access control.

---

### Secured Endpoints

Protected APIs require JWT token:

- /compare  
- /convert  
- /add  
- /subtract  
- /divide  
- /history  
- /count  

Public APIs:

- /auth/login  
- /auth/register  
- /oauth2/google  

---

### JWT Authentication Flow

1. User logs in (Google or credentials)  
2. Server generates JWT token  
3. Client stores token  
4. Client sends token in Authorization header  
5. Server validates token  
6. Request processed if valid  

Authorization Header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

### Google OAuth2 Login Flow

User clicks "Login with Google"  
↓  
Google authentication page  
↓  
User grants permission  
↓  
Spring Security OAuth2 handler  
↓  
User saved in database  
↓  
JWT generated  
↓  
User authenticated  

---

### Benefits of UC18

- Secure REST APIs  
- Google Single Sign-On support  
- JWT-based stateless authentication  
- Role-based access control  
- User-specific data management  
- Enterprise-level security architecture  

---

### Conclusion

UC18 introduces authentication and user management using Spring Security, JWT, and OAuth2, transforming the Quantity Measurement backend into a **secure, production-ready API service**.

The system now supports authenticated access, protected endpoints, and user-aware operation tracking.
>>>>>>> feature/UC18-GoogleAuthentication
