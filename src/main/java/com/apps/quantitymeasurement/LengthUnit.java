package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double convertToBase(double value) {
        return value * factor;
    }

    @Override
    public double convertFromBase(double baseValue) {
        return baseValue / factor;
    }

    @Override
    public String getCategory() {
        return "LENGTH";
    }
}