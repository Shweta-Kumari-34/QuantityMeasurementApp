package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.*;
import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    // Demonstrate equality using equals()
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println("Equal? (" + result + ")");
        return result;
    }

    // Demonstrate conversion using value parameters
    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit fromUnit,
                                                     LengthUnit toUnit) {
        Length base = new Length(value, fromUnit);
        return base.convertTo(toUnit);
    }

    // Demonstrate conversion using Length object
    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    // UC6 – implicit target (first operand unit)
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    // UC7 – explicit target unit
    public static Length demonstrateLengthAddition(Length l1,
                                                   Length l2,
                                                   LengthUnit targetUnit) {
        return l1.add(l2, targetUnit);
    }

    // Static API conversion helper
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        Length sourceLength = new Length(value, source);
        return sourceLength.convertTo(target).getValue();
    }

    public static void main(String[] args) {

        // Equality checks
        demonstrateLengthEquality(
                new Length(1, LengthUnit.FEET),
                new Length(12, LengthUnit.INCHES));

        demonstrateLengthEquality(
                new Length(1, LengthUnit.YARDS),
                new Length(36, LengthUnit.INCHES));

        // Conversion examples
        System.out.println(demonstrateLengthConversion(
                1.0, LengthUnit.FEET, LengthUnit.INCHES));

        System.out.println(demonstrateLengthConversion(
                3.0, LengthUnit.YARDS, LengthUnit.FEET));

        System.out.println(demonstrateLengthConversion(
                36.0, LengthUnit.INCHES, LengthUnit.YARDS));

        System.out.println(demonstrateLengthConversion(
                1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));

        System.out.println(demonstrateLengthConversion(
                new Length(1.0, LengthUnit.FEET),
                LengthUnit.INCHES));

        // -------------------
        // UC6 – Implicit target
        // -------------------

        System.out.println("UC6 Examples");

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES)));

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET)));

        // -------------------
        // UC7 – Explicit target
        // -------------------

        System.out.println("UC7 Examples");

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.FEET));

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES));

        System.out.println(demonstrateLengthAddition(
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS));

        System.out.println(demonstrateLengthAddition(
                new Length(2.54, LengthUnit.CENTIMETERS),
                new Length(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS));

        System.out.println(demonstrateLengthAddition(
                new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES));
    }
}