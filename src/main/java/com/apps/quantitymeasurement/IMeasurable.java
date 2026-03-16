package com.apps.quantitymeasurement;

public interface IMeasurable {

    double toBase(double value);

    double fromBase(double baseValue);

    String getMeasurementType();

    String getUnitName();

    IMeasurable getUnitInstance(String unitName);

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
                return LengthUnit.FEET.getUnitInstance(unit);
            case "WEIGHT":
                return WeightUnit.GRAM.getUnitInstance(unit);
            case "VOLUME":
                return VolumeUnit.MILLILITRE.getUnitInstance(unit);
            case "TEMPERATURE":
                return TemperatureUnit.CELSIUS.getUnitInstance(unit);
            default:
                throw new IllegalArgumentException("Unsupported measurement type: " + measurementType);
        }
    }
}