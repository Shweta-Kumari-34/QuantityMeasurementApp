package com.apps.quantitymeasurement;

public interface IMeasurable {

    double toBase(double value);

    double fromBase(double baseValue);

    String getMeasurementType();

    String getUnitName();

    default boolean supportsAddition() {
        return true;
    }

    default boolean supportsSubtraction() {
        return true;
    }

    default boolean supportsDivision() {
        return true;
    }

    static IMeasurable resolve(String measurementType, String unitName) {
        if (measurementType == null || unitName == null) {
            throw new IllegalArgumentException("Measurement type and unit cannot be null");
        }

        String type = measurementType.trim().toUpperCase();
        String unit = unitName.trim().toUpperCase();

        switch (type) {
            case "LENGTH":
                return LengthUnit.valueOf(unit);
            case "WEIGHT":
                return WeightUnit.valueOf(unit);
            case "VOLUME":
                return VolumeUnit.valueOf(unit);
            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit);
            default:
                throw new IllegalArgumentException("Unsupported measurement type: " + measurementType);
        }
    }
}