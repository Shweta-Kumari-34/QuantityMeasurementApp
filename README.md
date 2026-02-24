# QuantityMeasurementApp

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
