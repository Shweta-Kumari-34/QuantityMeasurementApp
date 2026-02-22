package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.00001;

    // Enum with conversion factors (base unit = INCHES)
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor with validation
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

    // Convert to another unit (instance method)
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.toBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        return new Length(convertedValue, targetUnit);
    }

    // Equality check (with tolerance)
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }

	
}