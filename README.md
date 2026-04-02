# QuantityMeasurementApp
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

=======

## Main Branch – Consolidated (UC1 → UC14)

---

## Overview

The Quantity Measurement App is a progressively evolved system for handling physical measurements across multiple domains:

Length  
Weight  
Volume  
Temperature  

The project starts with simple value equality and gradually evolves into a generic, extensible, type-safe, arithmetic-aware measurement framework.

By UC14, the system supports:

- Multiple measurement categories  
- Cross-unit equality  
- Explicit unit conversion  
- Arithmetic operations (where mathematically valid)  
- Capability-aware arithmetic enforcement  
- Generic architecture using Java Generics  

This branch represents the final stable version of the system.

---

## Core Design Principles Followed

- Value Object Pattern – Immutable, value-based comparison  
- DRY (Don’t Repeat Yourself) – No duplication across categories  
- Single Responsibility Principle (SRP)  
- Open–Closed Principle (OCP)  
- Interface Segregation Principle (ISP)  
- Type Safety using Generics  
- Base Unit Normalization  
- Backward Compatibility maintained at every step  

---

## Evolution Summary (UC1 → UC14)

### UC1 – Feet Equality

Defined a single unit (Feet).  
Overrode equals() method properly.  
Handled reference check, null check, and type safety.  
Established immutability and baseline correctness.

---

### UC2 – Feet & Inches Equality

Added Inches unit.  
Removed duplicated equality logic.  
Enforced same-unit comparison.  
First improvement toward DRY.

---

### UC3 – Generic Length Model

Introduced `QuantityLength` class.  
Created `LengthUnit` enum.  
Enabled cross-unit equality (1 ft == 12 in).  
Base unit normalization introduced.  
Major architectural shift.

---

### UC4 – Extended Length Units

Added Yards and Centimeters.  
No change in business logic.  
Only enum extended.  
Open–Closed Principle validated.

---

### UC5 – Explicit Conversion Support

Added public conversion method.  
Two-step conversion model implemented:

Source → Base  
Base → Target  

Precision handling introduced.

---

### UC6 – Addition (Implicit Target Unit)

Added arithmetic addition.  
Cross-unit addition supported.  
Result returned in first operand’s unit.  
System moved from comparison to computation.

---

### UC7 – Addition (Explicit Target Unit)

Caller can specify result unit.  
Improved API flexibility.  
Arithmetic logic reused.

---

### UC8 – SRP Refactor

Moved conversion logic to unit enum.  
Quantity became pure value object.  
No behavior change.  
Architecture improved without breaking tests.

---

### UC9 – Weight Support

New category introduced: Weight.  

Units added:
- Kilogram (base)  
- Gram  
- Pound  

Length and Weight strictly separated.  
System became multi-category.

---

### UC10 – Generic Quantity Architecture

Introduced:
- `Quantity<U extends IMeasurable>`  
- `IMeasurable` interface  

Removed category-specific quantity classes.  
Compile-time category safety achieved.  
System became fully generic.

---

### UC11 – Volume Support

Added new category: Volume.  

Units:
- Litre (base)  
- Millilitre  
- Gallon  

Only new enum added.  
No refactor required.

---

### UC12 – Subtraction & Division

Added subtraction.  
Added division (returns scalar).  
Validation and conversion logic internally duplicated.  
Functionally correct but not DRY.

---

### UC13 – Centralized Arithmetic Logic

Centralized validation, conversion, and execution.  
Introduced internal arithmetic dispatcher.  
Removed duplication completely.  
Public APIs unchanged.  
Professional refactor applied.

---

### UC14 – Temperature Support

New category: Temperature.  

Units:
- Celsius  
- Fahrenheit  
- Kelvin  

Equality and conversion supported.  
Arithmetic explicitly disallowed.  
Capability-aware design introduced.  
Interface Segregation Principle enforced.

---

## Supported Categories

- Length  
- Weight  
- Volume  
- Temperature  

---

## Supported Operations

- Equality  
- Conversion  
- Addition  
- Subtraction  
- Division  

(Temperature supports only Equality and Conversion.)

---

## Testing Strategy

Test cases written for each UC.  
Existing tests were never modified.  
Refactors validated through regression testing.  
All tests passing on main branch.

---

## Final Summary

From UC1 to UC14, the project demonstrates:

- Clean object-oriented design  
- Step-by-step refactoring discipline  
- Scalable generic architecture  
- Mathematical correctness  
- Production-level structural maturity  

Main branch represents the final, stable, production-ready system.

