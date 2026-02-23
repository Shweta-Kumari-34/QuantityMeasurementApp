package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * UC10 & UC11: Generic Quantity class.
 * Works with any unit implementing IMeasurable (Length, Weight, Volume).
 */
public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 0.001;

    public Quantity(double value, U unit) {
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

    public U getUnit() {
        return unit;
    }

    /**
     * Converts current quantity to a target unit of the same category.
     */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        
        // Ensure we aren't converting across categories (e.g., Litre to Feet)
        if (!unit.getCategory().equals(targetUnit.getCategory())) {
            throw new IllegalArgumentException("Incompatible measurement categories");
        }

        double baseValue = unit.convertToBase(this.value);
        double convertedValue = targetUnit.convertFromBase(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    /**
     * Adds two quantities and returns the result in the specified target unit.
     */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        // Validate categories match
        if (!this.unit.getCategory().equals(other.unit.getCategory()) || 
            !this.unit.getCategory().equals(targetUnit.getCategory())) {
            throw new IllegalArgumentException("Cannot add different measurement categories");
        }

        double sumInBase = this.unit.convertToBase(this.value) + 
                           other.unit.convertToBase(other.value);

        double finalValue = targetUnit.convertFromBase(sumInBase);
        return new Quantity<>(finalValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        
        // Use wildcard to check if it's any type of Quantity
        if (!(obj instanceof Quantity<?> other)) return false;

        // Important: Compare categories via the interface to prevent Litre == Feet
        IMeasurable thisUnit = (IMeasurable) this.unit;
        IMeasurable otherUnit = (IMeasurable) other.getUnit();

        if (!thisUnit.getCategory().equals(otherUnit.getCategory())) {
            return false;
        }

        double thisBase = thisUnit.convertToBase(this.value);
        double otherBase = otherUnit.convertToBase(other.getValue());

        // Use Math.abs for floating point precision (UC11 Gallon conversion)
        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        // Hash based on base value and category for consistency
        return Objects.hash(unit.convertToBase(value), unit.getCategory());
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}