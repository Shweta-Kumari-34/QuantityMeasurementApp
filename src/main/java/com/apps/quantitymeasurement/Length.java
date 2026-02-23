package com.apps.quantitymeasurement;

import java.util.Objects;

public final class Length {

    private static final double EPSILON = 1e-5;

    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor; // relative to inches (base)

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

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

    // Convert to base unit (inches)
    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // Convert from base (inches) to target unit
    private static double fromBaseUnit(double baseValue, LengthUnit targetUnit) {
        return baseValue / targetUnit.getConversionFactor();
    }

    // UC6 – implicit target (first operand's unit)
    public Length add(Length that) {
        return add(that, this.unit);
    }

    // UC7 – explicit target unit
    public Length add(Length that, LengthUnit targetUnit) {

        if (that == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumInBase = this.toBaseUnit() + that.toBaseUnit();
        double resultValue = fromBaseUnit(sumInBase, targetUnit);

        return new Length(resultValue, targetUnit);
    }

    // Convert to specific unit
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base = this.toBaseUnit();
        double converted = fromBaseUnit(base, targetUnit);
        return new Length(converted, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        Length that = (Length) o;

        return Math.abs(this.toBaseUnit() - that.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(this.toBaseUnit() / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("%.5f %s", value, unit);
    }
}