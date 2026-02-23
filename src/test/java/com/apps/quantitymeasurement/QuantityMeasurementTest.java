package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementTest {
    private static final double EPSILON = 0.02;

    // --- UC4: Equality (Including Centimeter) ---
    @Test
    void givenTwoInchesAndFiveCentimeters_WhenCompared_ShouldReturnEqual() {
        // 2 inches = 5 cm (as per 1 inch = 2.5 cm)
        Quantity<LengthUnit> twoInches = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> fiveCm = new Quantity<>(5.0, LengthUnit.CENTIMETER);
        assertEquals(twoInches, fiveCm); // UC4 logic
    }

    // --- UC10: Addition (With Centimeter) ---
    @Test
    void givenOneInchAndOneCm_WhenAdded_ShouldReturnCorrectValueInCm() {
        Quantity<LengthUnit> oneInch = new Quantity<>(1.0, LengthUnit.INCHES);
        Quantity<LengthUnit> oneCm = new Quantity<>(1.0, LengthUnit.CENTIMETER);
        // Result: 2.5cm + 1cm = 3.5cm
        Quantity<LengthUnit> result = oneInch.add(oneCm, LengthUnit.CENTIMETER); 
        assertEquals(3.5, result.getValue(), EPSILON);
    }

    // --- UC12: Subtraction ---
    @Test
    void givenTenFeetAndSixInches_WhenSubtracted_ShouldReturnCorrectValueInFeet() {
        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> sixInches = new Quantity<>(6.0, LengthUnit.INCHES);
        // 10ft - 0.5ft = 9.5ft
        Quantity<LengthUnit> result = tenFeet.subtract(sixInches); 
        assertEquals(9.5, result.getValue(), EPSILON);
    }

    // --- UC12: Division ---
    @Test
    void givenTwentyFourInchesAndTwoFeet_WhenDivided_ShouldReturnRatioOne() {
        Quantity<LengthUnit> inches = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);
        // Result is a scalar ratio (double)
        double ratio = inches.divide(feet); 
        assertEquals(1.0, ratio, EPSILON);
    }
   
   
    // --- Error Handling ---
    @Test
    void givenLengthAndWeight_WhenSubtracted_ShouldThrowException() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        // Bypassing compile-time check to verify runtime validation
        assertThrows(IllegalArgumentException.class, () -> ((Quantity)feet).subtract(weight));
    }
}