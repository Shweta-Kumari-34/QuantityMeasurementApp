package com.apps.quantitymeasurement.unit;

/**
 * IMeasurable
 *
 * Common abstraction implemented by all measurement unit enums.
 *
 * Responsibilities:
 * - Return unit name
 * - Return measurement category
 * - Convert value into base unit
 * - Convert base unit value back into current unit
 *
 * Categories supported:
 * - Length
 * - Weight
 * - Volume
 * - Temperature
 */
public interface IMeasurable {

    /**
     * Returns the unit name.
     *
     * @return unit name
     */
    String getUnitName();

    /**
     * Returns measurement type/category.
     *
     * @return measurement type such as LENGTH, WEIGHT, VOLUME, TEMPERATURE
     */
    String getMeasurementType();

    /**
     * Converts value into base-unit equivalent.
     *
     * @param value value in current unit
     * @return base-unit value
     */
    double toBaseUnit(double value);

    /**
     * Converts base-unit value into current unit.
     *
     * @param baseValue value in base unit
     * @return converted value in current unit
     */
    double fromBaseUnit(double baseValue);
}