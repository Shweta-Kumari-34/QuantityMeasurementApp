package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable {
    FEET(12.0), 
    INCHES(1.0), 
    YARDS(36.0), 
    CENTIMETER(0.4); // 1 inch = 2.5 cm => 1 cm = 1/2.5 = 0.4 inches

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double convertToBase(double value) {
        return value * factor; // Sab inches mein convert hoga
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