package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

	// Demonstrate equality
	public static boolean demonstrateLengthEquality(Length l1, Length l2) {
		boolean result = l1.equals(l2);
		System.out.println("Equal? -> " + result);
		return result;
	}

	// Demonstrate comparison (if you implemented compare)
	public static boolean demonstrateLengthComparison(Length l1, Length l2) {
		boolean result = l1.equals(l2);
		System.out.println("Compare Result -> " + result);
		return result;
	}

	// Demonstrate conversion using raw values
	public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
		Length length = new Length(value, fromUnit);
		return length.convertTo(toUnit);
	}

	// Demonstrate conversion using object
	public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
		return length.convertTo(toUnit);
	}

	// UC6 – Addition
	public static Length demonstrateLengthAddition(Length l1, Length l2) {
		return l1.add(l2);
	}

	public static void main(String[] args) {

		System.out.println("========== LENGTH CONVERSION ==========");

		System.out
				.println("1 FEET to INCHES -> " + demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES));

		System.out.println("3 YARDS to FEET -> " + demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET));

		System.out.println(
				"36 INCHES to YARDS -> " + demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS));

		System.out.println("1 CENTIMETER to INCHES -> "
				+ demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));

		System.out.println("\n========== UC6 ADDITION ==========");

		System.out.println("Input: add(1.0 FEET, 2.0 FEET) -> "
				+ demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(2.0, LengthUnit.FEET)));

		System.out.println("Input: add(1.0 FEET, 12.0 INCHES) -> "
				+ demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES)));

		System.out.println("Input: add(12.0 INCHES, 1.0 FEET) -> "
				+ demonstrateLengthAddition(new Length(12.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.FEET)));

		System.out.println("Input: add(1.0 YARDS, 3.0 FEET) -> "
				+ demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET)));

		System.out.println("Input: add(36.0 INCHES, 1.0 YARDS) -> "
				+ demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS)));

		System.out.println("Input: add(2.54 CENTIMETERS, 1.0 INCHES) -> " + demonstrateLengthAddition(
				new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES)));

		System.out.println("Input: add(5.0 FEET, 0.0 INCHES) -> "
				+ demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES)));

		System.out.println("Input: add(5.0 FEET, -2.0 FEET) -> "
				+ demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET)));
	}
}