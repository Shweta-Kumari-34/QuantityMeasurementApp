package com.apps.quantitymeasurement;

import java.util.Scanner;

public class QuantityMeasurementApp {

    // --------Feet Equality --------
    public static boolean compareFeet(double value1, double value2) {
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        return feet1.equals(feet2);
    }

    // -------- Inches Equality --------
    public static boolean compareInches(double value1, double value2) {
        Inches inch1 = new Inches(value1);
        Inches inch2 = new Inches(value2);
        return inch1.equals(inch2);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {
            // -------- FEET INPUT --------
            System.out.print("Enter first value in feet: ");
            double feetFirst = Double.parseDouble(input.nextLine());

            System.out.print("Enter second value in feet: ");
            double feetSecond = Double.parseDouble(input.nextLine());

            boolean feetResult = compareFeet(feetFirst, feetSecond);
            System.out.println("Feet Equal (" + feetResult + ")");

            System.out.println("----------------------------------");

            // -------- INCH INPUT --------
            System.out.print("Enter first value in inches: ");
            double inchFirst = Double.parseDouble(input.nextLine());

            System.out.print("Enter second value in inches: ");
            double inchSecond = Double.parseDouble(input.nextLine());

            boolean inchResult = compareInches(inchFirst, inchSecond);
            System.out.println("Inches Equal (" + inchResult + ")");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        }

        input.close();
    }
}