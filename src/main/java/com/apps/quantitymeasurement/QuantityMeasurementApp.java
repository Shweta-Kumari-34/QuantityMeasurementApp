package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    public static void demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

       // double result = Length.convert(value, from, to);
    	Length length = new Length(value, from);
    	Length converted = length.convertTo(to);
    	double result = converted.getValue();

        System.out.println("Converted: " + value + " " + from +
                " → " + result + " " + to);
    }

    // Method Overloading
    public static void demonstrateLengthConversion(
            Length length,
            LengthUnit to) {

        Length converted = length.convertTo(to);

        System.out.println("Converted: " + length +
                " → " + converted);
    }

    public static void demonstrateLengthEquality(Length l1, Length l2) {
        System.out.println("Are equal? " + l1.equals(l2));
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);

        Length length = new Length(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(length, LengthUnit.INCHES);
    }
}