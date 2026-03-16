package com.apps.quantitymeasurement.exception;

/**
 * QuantityMeasurementException
 *
 * Base custom exception for the Quantity Measurement Application.
 *
 * This exception is used to represent domain-level and application-level
 * issues that occur during quantity comparison, conversion, arithmetic,
 * or persistence operations.
 *
 * Architectural Role:
 * - Base exception in the application's custom exception hierarchy
 * - Allows higher layers to catch application-specific failures
 *   instead of raw Java exceptions
 */
public class QuantityMeasurementException extends RuntimeException {

    /**
     * Constructs exception with message.
     *
     * @param message error message
     */
    public QuantityMeasurementException(String message) {
        super(message);
    }

    /**
     * Constructs exception with message and cause.
     *
     * @param message error message
     * @param cause root cause
     */
    public QuantityMeasurementException(String message, Throwable cause) {
        super(message, cause);
    }
}