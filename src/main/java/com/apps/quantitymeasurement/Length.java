package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

    public enum LengthUnit {
        INCHES(1.0),           // Base Unit
        FEET(12.0),
        YARDS(36.0),
        CENTIMETERS(0.393700787); // 1 cm = 0.393700787 inches

        private final double toInchesFactor;

        LengthUnit(double factor) {
            this.toInchesFactor = factor;
        }

        public double toBase(double value) {
            return value * toInchesFactor;
        }

        public double fromBase(double baseValue) {
            return baseValue / toInchesFactor;
        }
    }

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.0001;

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Convert to another unit
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = unit.toBase(this.value);
        double converted = targetUnit.fromBase(baseValue);
        return new Length(converted, targetUnit);
    }

    // UC6: Add two lengths
    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null");
        }

        // Convert both to base (inches)
        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        // Add in base
        double sumBase = base1 + base2;

        // Convert back to first operand's unit
        double result = this.unit.fromBase(sumBase);

        return new Length(result, this.unit);
    }

    // Compare for equality with epsilon
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;

        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.toBase(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}