package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LengthTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testFeetToInches() {
        Length length = new Length(1.0, Length.LengthUnit.FEET);
        Length result = length.convertTo(Length.LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testInchesToFeet() {
        Length length = new Length(24.0, Length.LengthUnit.INCHES);
        Length result = length.convertTo(Length.LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testYardsToInches() {
        Length length = new Length(1.0, Length.LengthUnit.YARDS);
        Length result = length.convertTo(Length.LengthUnit.INCHES);
        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testCentimetersToInches() {
        Length length = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length result = length.convertTo(Length.LengthUnit.INCHES);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testNullUnitThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(5.0, null);
        });
    }

    @Test
    void testNaNThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, Length.LengthUnit.FEET);
        });
    }
}