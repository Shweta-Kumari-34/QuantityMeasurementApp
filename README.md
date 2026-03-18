
## UC15 – Layered Architecture with Caching and Exception Handling

UC15 enhances the Quantity Measurement Application by introducing a clean **layered architecture** to improve modularity, maintainability, and scalability.

The system is refactored into multiple layers:

- Controller Layer – Handles user input and delegates requests  
- Service Layer – Contains business logic and validation  
- Repository Layer – Stores operation history (in-memory cache)  
- Domain Layer – Core logic (Quantity, Units, Arithmetic)  
- DTO Layer – Transfers data between layers safely  

---

### Key Improvements

- Introduced separation of concerns using layered architecture  
- Added in-memory caching to store operation history  
- Implemented DTO (`QuantityDTO`) to avoid exposing domain objects  
- Added custom exception (`QuantityMeasurementException`) for centralized error handling  
- Improved testability and code organization  

---

### System Flow

Application  
→ Controller  
→ Service  
→ Domain Logic (Quantity Engine)  
→ Repository (Cache Storage)  
→ Result  

---

### Repository Cache

- Stores operation history in memory using a list  
- Each entry contains:
  - operand1  
  - operand2  
  - operation  
  - result  
  - error flag  
- Acts as a runtime log (cleared when application stops)  

---

### Example

Operation:

1 ft + 12 in  

Flow:

MainApp → Controller → Service → Quantity Engine → Repository → Result  

Output:

Quantity(2.0, FEET)

---

### Conclusion

UC15 restructures the application into a clean layered system with caching and exception handling.  
This prepares the project for future enhancements like database integration, REST APIs, and production-level scalability.

---

### Status

- UC15 implemented  
- Layered architecture applied  
- Cache repository added  
- DTO and exception handling integrated  
- All tests passing
- - Code Link:
-[Reference Code](https://github.com/Shweta-Kumari-34/QuantityMeasurementApp/tree/feature/UC15-N-TierArchitectureRefactoring/src) 
