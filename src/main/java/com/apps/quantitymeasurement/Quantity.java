package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.02; // CM ke liye thoda margin

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null hai");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = this.unit.convertToBase(this.value);
        double converted = targetUnit.convertFromBase(baseValue);
        return new Quantity<>(Math.round(converted * 100.0) / 100.0, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other);
        double sum = this.unit.convertToBase(this.value) + other.unit.convertToBase(other.value);
        return new Quantity<>(Math.round(targetUnit.convertFromBase(sum) * 100.0) / 100.0, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validate(other);
        double diff = this.unit.convertToBase(this.value) - other.unit.convertToBase(other.value);
        // Default subtract returns result in same unit
        return new Quantity<>(Math.round(this.unit.convertFromBase(diff) * 100.0) / 100.0, this.unit);
    }
 // UC12: Division (Returns double, not Quantity)
    public double divide(Quantity<U> other) {
        validate(other); // Category check
        if (other.value == 0) {
            throw new ArithmeticException("Zero se divide nahi kar sakte");
        }
        
        // Convert both to base and get the ratio
        double thisBase = this.unit.convertToBase(this.value);
        double otherBase = other.unit.convertToBase(other.value);
        
        return thisBase / otherBase; // Ye ek simple number (ratio) hai
    }
    private void validate(Quantity<U> other) {
        if (other == null || !this.unit.getCategory().equals(other.unit.getCategory()))
            throw new IllegalArgumentException("Category mismatch");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity<?> that = (Quantity<?>) o;
        if (!this.unit.getCategory().equals(that.unit.getCategory())) return false;
        return Math.abs(this.unit.convertToBase(this.value) - that.unit.convertToBase(that.value)) < EPSILON;
    }
}