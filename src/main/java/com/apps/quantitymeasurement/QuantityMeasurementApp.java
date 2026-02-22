package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    // Generic method to demonstrate Length equality check
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // Demonstrate Feet equality using Length class
    public static void demonstrateFeetEquality() {

        Length feet1 = new Length(5.0, LengthUnit.FEET);
        Length feet2 = new Length(5.0, LengthUnit.FEET);

        System.out.println("Feet Equality : " + feet1.equals(feet2));
    }

    // Demonstrate Inches equality using Length class
    public static void demonstrateInchesEquality() {

        Length inch1 = new Length(1.0, LengthUnit.INCHES);
        Length inch2 = new Length(1.0, LengthUnit.INCHES);

        System.out.println("Inch Equality : " + inch1.equals(inch2));
    }

    // Demonstrate Feet and Inches comparison
    public static void demonstrateFeetInchesComparison() {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Feet-Inches Equality -> " + l1.equals(l2));
    }

    public static void main(String[] args) {

        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}