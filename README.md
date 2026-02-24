#  Quantity Measurement App  

## UC12 – Subtraction and Division Operations on Quantity Measurements  
---
**Date:** 23 Feb 2026  
---

##  Description  

UC12 extends the Quantity Measurement Application by introducing two new arithmetic operations to the generic `Quantity<U>` class:

- Subtraction  
- Division  

Building on UC1–UC11 (equality, conversion, addition), this use case enables comprehensive arithmetic manipulation of measurements.

### Subtraction
Allows users to compute the difference between two quantities of the same measurement category.

Examples:
- `5 L - 2 L = 3 L`
- `10 FEET - 6 INCHES = 9.5 FEET`

### Division
Allows users to compute the ratio between two quantities, producing a **dimensionless scalar result**.

Examples:
- `10 KG ÷ 5 KG = 2.0`
- `24 INCHES ÷ 2 FEET = 1.0`

Both operations:

- Support cross-unit arithmetic (within same category)
- Support implicit and explicit target unit
- Maintain immutability
- Provide validation and error handling
- Preserve SOLID principles and architectural consistency

UC12 proves that the generic `Quantity<U>` design scales without structural changes.

---

##  Preconditions  

- `Quantity<U extends IMeasurable>` is fully operational (UC10)
- `IMeasurable` defines conversion contract
- `LengthUnit`, `WeightUnit`, `VolumeUnit` implement `IMeasurable`
- UC1–UC11 functionality remains intact
- Subtraction returns `Quantity<U>`
- Division returns `double`
- Cross-category arithmetic is prevented

---

#  Main Flow  

---

##  Subtraction Operation  

###  Method Signatures

```java
Quantity<U> subtract(Quantity<U> other)
Quantity<U> subtract(Quantity<U> other, U targetUnit)
```

###  Validation

- `other` is non-null
- Same measurement category
- Values are finite
- Target unit (if provided) is non-null

###  Conversion to Base Unit

- Convert both quantities to base unit
- Perform subtraction:
  ```
  baseResult = this.baseValue - other.baseValue
  ```

###  Convert to Target Unit

- Default: first operand's unit
- Or explicitly provided unit
- Round to two decimal places

###  Return New Object

- Return new immutable `Quantity<U>`
- Original objects remain unchanged

### Result Meaning

- Positive → first operand larger
- Negative → second operand larger
- Zero → quantities equivalent

---

## Division Operation  

###  Method Signature

```java
double divide(Quantity<U> other)
```

###  Validation

- `other` is non-null
- Same measurement category
- Finite numeric values
- Divisor not zero

###  Conversion to Base Unit

```
result = this.baseValue / other.baseValue
```

###  Return Scalar

- Return `double`
- Dimensionless result

### Result Meaning

- > 1 → first larger
- < 1 → second larger
- = 1 → equal

---

#  Postconditions  

- Subtraction returns new `Quantity<U>`
- Division returns scalar `double`
- Immutability preserved
- Cross-category operations prevented
- Works for Length, Weight, Volume
- Addition, subtraction, division coexist
- Mathematical properties respected:
  - Subtraction → non-commutative
  - Division → non-commutative & non-associative

---

# Testing Strategy  

Comprehensive unit tests cover:

## Subtraction Tests

- Same-unit subtraction
- Cross-unit subtraction
- Explicit target unit
- Negative result
- Zero result
- Identity property
- Non-commutativity
- Large and small values
- Null operand handling
- Null target handling
- Cross-category prevention
- Chained operations
- Immutability validation
- Precision & rounding

---

## Division Tests

- Same-unit division
- Cross-unit division
- Ratio > 1
- Ratio < 1
- Ratio = 1
- Non-commutativity
- Non-associativity
- Division by zero
- Large ratios
- Small ratios
- Null operand handling
- Cross-category prevention
- Immutability validation
- Precision handling

---

#  Concepts Reinforced  

##  Comprehensive Arithmetic Support  
System evolves from comparison to full arithmetic domain model.

##  Immutability  
All arithmetic returns new objects.

##  Non-Commutativity Awareness  
Subtraction and division are order dependent.

##  Division by Zero Protection  
Fail-fast validation strategy.

##  Target Unit Pattern  
Consistent implicit & explicit unit handling.

##  Cross-Category Type Safety  
Compile-time generics + runtime validation.

##  Code Reuse  
Private helper methods prevent duplication.

##  Validation Consistency  
Uniform validation across all operations.

##  Precision Handling  
Subtraction → rounded  
Division → raw double  

## Polymorphism  
Generic methods operate across all categories.

---

# SOLID & Object Calisthenics Review  

### Single Responsibility  
Quantity handles arithmetic logic only.

### Open–Closed Principle  
New operations added without breaking architecture.

### DRY Principle  
Shared validation & conversion logic reused.

### Possible Improvement  
If arithmetic logic grows further, consider:
- Strategy pattern for operations
- Extract ArithmeticService

Current design remains clean and maintainable.

---

# Example Outputs  

## Subtraction (Implicit Target)

```
10 FEET - 6 INCHES → 9.5 FEET
10 KG - 5000 G → 5 KG
5 L - 500 mL → 4.5 L
```

## Subtraction (Explicit Target)

```
10 FEET - 6 INCHES (INCHES) → 114 INCHES
5 L - 2 L (mL) → 3000 mL
```

## Division

```
10 FEET ÷ 2 FEET → 5.0
24 INCHES ÷ 2 FEET → 1.0
2000 G ÷ 1 KG → 2.0
5 L ÷ 10 L → 0.5
```

## Error Cases

```
subtract(null) → IllegalArgumentException
divide(0) → ArithmeticException
cross-category operation → IllegalArgumentException
```

---

#  Conclusion  

UC12 transforms the system into a **complete arithmetic measurement engine**.

- Supports addition, subtraction, division
- Maintains immutability
- Preserves type safety
- Scales across all measurement categories
- Requires no architectural restructuring

The Quantity Measurement App is now:

- Generic
- Extensible
- Architecturally mature
- Production-ready
- Interview-ready

---

### 📌 Repository Updates 

- UC12 implemented  
- Subtraction added  
- Division added  
- All categories supported  
- All tests passing  
- Backward compatibility preserved
- Committed and pushed the UC-12 implementation to the repository.
---
- Code Link:
-[Reference Code](https://github.com/Shweta-Kumari-34/QuantityMeasurementApp/tree/feature/UC12-QuantitySubtractionandDivision/src/main/java/com/apps/quantitymeasurement)
