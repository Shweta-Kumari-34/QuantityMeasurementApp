# UC14 – Temperature Measurement with Selective Arithmetic Support & IMeasurable Refactoring
**Date:** 23 Feb 2026  
---

##  Description

UC14 extends the Quantity Measurement Application to support **Temperature** alongside:

- Length
- Weight
- Volume

Unlike these categories, temperature has **fundamentally different arithmetic rules**:

| Operation | Temperature Support |
|-----------|--------------------|
| Equality | ✅ Supported |
| Conversion | ✅ Supported |
| Addition | ❌ Not meaningful (absolute temperatures) |
| Subtraction | ❌ Not supported (in this design) |
| Division | ❌ Meaningless |

Example:

```
100°C + 50°C ≠ 150°C (not physically meaningful)
100°C ÷ 50°C → meaningless
```

This exposes a limitation in the previous design:

> The system assumed all measurement categories support the same arithmetic operations.

UC14 refactors the design to make arithmetic support **optional and capability-based**.

---

#  Design Limitations Identified

##  IMeasurable Assumed Uniform Behavior

- No way to express unsupported operations
- Forced temperature to implement meaningless arithmetic
- Violates Interface Segregation Principle (ISP)

---

##  Quantity Assumed All Units Support Arithmetic

```
new Quantity<>(50, CELSIUS).divide(...)
```

Compiles successfully  
Fails only at runtime  

No compile-time communication of constraints.

---

##  No Operation Capability Query

- Cannot ask: "Does this unit support division?"
- Must rely on documentation
- Unsafe API design

---

# Objective of UC14

- Add Temperature category
- Support conversion & equality only
- Prevent unsupported arithmetic
- Maintain backward compatibility (UC1–UC13)
- Refactor `IMeasurable` safely
- Preserve generics and type safety
- Follow SOLID principles

---

# Refactoring Strategy

---

# Step 1 – Refactor `IMeasurable` Interface

Introduce **optional arithmetic support** via default methods.

---

##  Add Functional Interface

```java
@FunctionalInterface
public interface SupportsArithmetic {
    boolean isSupported();
}
```

---

##  Default Lambda (Backward Compatibility)

```java
SupportsArithmetic supportsArithmetic = () -> true;
```

All existing units inherit `true`.

---

##  Default Methods Added

```java
default boolean supportsArithmetic() {
    return supportsArithmetic.isSupported();
}

default void validateOperationSupport(String operation) {
    // default: allow all operations
}
```

Existing enums remain unchanged.  
No breaking changes introduced.

---

# Step 2 – Create `TemperatureUnit` Enum

Supports:

- CELSIUS
- FAHRENHEIT
- KELVIN

Celsius treated as base unit internally.

---

##  Non-Linear Conversion

Temperature conversion is NOT:

```
value × factor
```

Instead:

```
°F = (°C × 9/5) + 32
°C = (°F - 32) × 5/9
K = °C + 273.15
```

This requires special conversion handling.

---

##  Disable Arithmetic Support

```java
SupportsArithmetic supportsArithmetic = () -> false;
```

Override validation:

```java
@Override
public void validateOperationSupport(String operation) {
    throw new UnsupportedOperationException(
        "Temperature does not support " + operation + " operation."
    );
}
```

---

# Step 3 – Update `Quantity<U>`

Before any arithmetic:

```java
this.unit.validateOperationSupport(operation.name());
```

If unsupported → fail fast with meaningful exception.

No structural changes to generics.

---

#  Example Behavior

---

##  Temperature Equality

```
new Quantity<>(0.0, CELSIUS)
.equals(new Quantity<>(32.0, FAHRENHEIT))
→ true
```

```
new Quantity<>(273.15, KELVIN)
.equals(new Quantity<>(0.0, CELSIUS))
→ true
```

---

##  Temperature Conversion

```
100°C → 212°F
32°F → 0°C
0°C → 273.15K
-40°C → -40°F
```

---

##  Unsupported Operations

```
new Quantity<>(100.0, CELSIUS)
.add(new Quantity<>(50.0, CELSIUS))
```

Throws:

```
UnsupportedOperationException:
Temperature does not support ADD operation.
```

Same for subtract & divide.

---

#  Cross-Category Safety

```
new Quantity<>(100, CELSIUS)
.equals(new Quantity<>(100, FEET))
→ false
```

Runtime check:
```
unit.getClass() comparison
```

Compile-time safety:
```
Quantity<TemperatureUnit> ≠ Quantity<LengthUnit>
```

Layered type protection.

---

#  Concepts Reinforced

---

##  Interface Segregation Principle (ISP)

Temperature should not be forced to implement unsupported arithmetic.

---

##  Default Methods in Interfaces

- Evolve interface without breaking clients
- Maintain backward compatibility
- Powerful Java 8 feature

---

##  Capability-Based Design

Instead of assuming operations:

```
Ask unit if operation is supported.
```

Safer & extensible.

---

##  Non-Linear Conversions

Unlike length/weight:

Temperature requires formula-based conversion.

---

##  Absolute vs Relative Temperature

Absolute temperature:
```
100°C → specific point
```

Relative temperature:
```
Increase by 10°C → meaningful
```

This design prevents invalid arithmetic on absolute values.

---

##  Polymorphic Error Handling

Temperature provides specific error message.

Better than generic IllegalArgumentException.

---

##  Backward Compatibility

Length, Weight, Volume:
- No changes required
- Default arithmetic support = true
- All UC1–UC13 tests pass

---

#  Key Test Coverage

### Equality
- Celsius–Celsius
- Fahrenheit–Fahrenheit
- Kelvin–Kelvin
- Cross-unit equality
- Reflexive, symmetric, transitive

### Conversion
- All pair combinations
- Round-trip conversion
- Absolute zero edge case
- -40°C = -40°F special case
- Precision with epsilon

### Unsupported Operations
- add() throws
- subtract() throws
- divide() throws
- Error message clarity

### Cross Category
- Temperature vs Length → false
- Temperature vs Weight → false
- Temperature vs Volume → false

### Default Behavior
- LengthUnit supports arithmetic
- WeightUnit supports arithmetic
- VolumeUnit supports arithmetic

### Backward Compatibility
- All UC1–UC13 tests pass unchanged

---

#  Architectural Evolution

| UC | Focus |
|----|-------|
| UC1–5 | Equality & conversion |
| UC6–9 | Addition |
| UC10 | Generic design |
| UC11 | Volume scalability proof |
| UC12 | Arithmetic operations |
| UC13 | DRY refactoring |
| UC14 | Capability-based design & ISP compliance |

UC14 makes the system **intellectually complete**.

---

#  Final Outcome

After UC14, the system is:

- Generic  
- Scalable  
- DRY-compliant  
- ISP-compliant  
- Backward compatible  
- Capability-aware  
- Architecturally robust  

The design now supports categories with **different operational constraints** without hacks, duplication, or breaking changes.

---

### 📌 Repository Updates

- TemperatureUnit implemented
- IMeasurable refactored safely
- Arithmetic capability optional
- All previous tests passing
- Temperature tests passing
- System evolution validated
- Committed and pushed the UC2 implementation to the repository.
- Code Link:
-[Reference Code](https://github.com/Shweta-Kumari-34/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement/src/main/java/com/apps/quantitymeasurement)
