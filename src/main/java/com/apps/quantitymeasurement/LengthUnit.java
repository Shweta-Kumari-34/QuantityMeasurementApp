package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactorToInches;

    LengthUnit(double conversionFactorToInches) {
        this.conversionFactorToInches = conversionFactorToInches;
    }

    @Override
    public double toBase(double value) {
        return value * conversionFactorToInches;
    }

    @Override
    public double fromBase(double baseValue) {
        return baseValue / conversionFactorToInches;
    }

    @Override
    public String getMeasurementType() {
        return "LENGTH";
    }

    @Override
    public String getUnitName() {
        return name();
    }

	
}