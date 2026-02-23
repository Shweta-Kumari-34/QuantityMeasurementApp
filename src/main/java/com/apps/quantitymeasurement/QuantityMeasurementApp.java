package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ---------------- LENGTH METHODS ----------------

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        System.out.println("Equal (" + l1.compare(l2) + ")");
        return l1.equals(l2);
    }

    public static boolean demonstrateLengthComparison(Length l1, Length l2) {
        System.out.println("Compare (" + l1.compare(l2) + ")");
        return l1.compare(l2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        Length base = new Length(value, from);
        return base.convertTo(to);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit to) {
        return length.convertTo(to);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit target) {
        return l1.add(l2, target);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        Length sourceLength = new Length(value, source);
        return sourceLength.convertTo(target).getValue();
    }

    // ---------------- WEIGHT METHODS ----------------

    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit) {
        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2) {
        return w1.add(w2);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit target) {
        return w1.add(w2, target);
    }

    // ---------------- MAIN METHOD ----------------

    public static void main(String[] args) {

        System.out.println("----------- UC 9: Weight Measurement Implementation -----------");

        // Equality
        System.out.println("1 KG == 1000 GRAM ? -> "
                + demonstrateWeightEquality(
                        new Weight(1.0, WeightUnit.KILOGRAM),
                        new Weight(1000.0, WeightUnit.GRAM)));

        System.out.println("1 KG == 2.20462 POUND ? -> "
                + demonstrateWeightEquality(
                        new Weight(1.0, WeightUnit.KILOGRAM),
                        new Weight(2.20462, WeightUnit.POUND)));

        // Conversion
        System.out.println("1 KG to GRAM -> "
                + demonstrateWeightConversion(
                        new Weight(1.0, WeightUnit.KILOGRAM),
                        WeightUnit.GRAM));

        System.out.println("2 POUND to KG -> "
                + demonstrateWeightConversion(
                        new Weight(2.0, WeightUnit.POUND),
                        WeightUnit.KILOGRAM));

        // Implicit Addition
        System.out.println("1 KG + 1000 GRAM -> "
                + demonstrateWeightAddition(
                        new Weight(1.0, WeightUnit.KILOGRAM),
                        new Weight(1000.0, WeightUnit.GRAM)));

        // Explicit Addition
        System.out.println("1 KG + 1000 GRAM (in GRAM) -> "
                + demonstrateWeightAddition(
                        new Weight(1.0, WeightUnit.KILOGRAM),
                        new Weight(1000.0, WeightUnit.GRAM),
                        WeightUnit.GRAM));

        // Category Safety
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        System.out.println("Weight equals Length ? -> " + weight.equals(length));
    }
}