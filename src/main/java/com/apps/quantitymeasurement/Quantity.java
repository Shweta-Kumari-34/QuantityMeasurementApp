package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Conversion
    public Quantity<U> convertTo(U targetUnit) {

        if (!unit.getCategory().equals(targetUnit.getCategory()))
            throw new IllegalArgumentException("Different measurement categories");

        double base = unit.convertToBase(value);
        double converted = targetUnit.convertFromBase(base);

        return new Quantity<>(converted, targetUnit);
    }

    // UC10 Addition
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (!unit.getCategory().equals(other.unit.getCategory()))
            throw new IllegalArgumentException("Cannot add different categories");

        double base1 = unit.convertToBase(this.value);
        double base2 = other.unit.convertToBase(other.value);

        double sumBase = base1 + base2;
        double finalValue = targetUnit.convertFromBase(sumBase);

        return new Quantity<>(finalValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (!this.unit.getCategory().equals(other.unit.getCategory()))
            return false;

        double base1 = this.unit.convertToBase(this.value);
        double base2 = ((IMeasurable) other.unit)
                .convertToBase((Double) other.value);

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBase(value), unit.getCategory());
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}