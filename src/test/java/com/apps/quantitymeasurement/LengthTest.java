package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LengthTest {

    // ---------- YARD TESTS ----------

    @Test
    public void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(1.0, Length.LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalent() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    // ---------- CENTIMETER TESTS ----------

    @Test
    public void testEquality_CentimeterToCentimeter_SameValue() {
        Length l1 = new Length(5.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(5.0, Length.LengthUnit.CENTIMETERS);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_CentimeterToInches_EquivalentValue() {
        Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(0.393701, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_CentimeterToFeet_NonEquivalent() {
        Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    // ---------- TRANSITIVE PROPERTY ----------

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    // ---------- COMPLEX SCENARIO ----------

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        Length yard = new Length(2.0, Length.LengthUnit.YARDS);
        Length feet = new Length(6.0, Length.LengthUnit.FEET);
        Length inches = new Length(72.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    // ---------- NULL & EDGE CASES ----------

    @Test
    public void testEquality_SameReference() {
        Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
        assertTrue(l1.equals(l1));
    }

    @Test
    public void testEquality_NullComparison() {
        Length l1 = new Length(2.0, Length.LengthUnit.YARDS);
        assertFalse(l1.equals(null));
    }

    @Test
    public void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }
}