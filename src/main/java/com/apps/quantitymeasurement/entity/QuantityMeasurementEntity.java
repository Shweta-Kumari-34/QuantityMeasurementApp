package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.dto.QuantityDTO;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String operationType;
    private final QuantityDTO operand1;
    private final QuantityDTO operand2;
    private final QuantityDTO result;
    private final Double scalarResult;
    private final Boolean comparisonResult;
    private final boolean error;
    private final String errorMessage;

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO result) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = null;
        this.result = result;
        this.scalarResult = null;
        this.comparisonResult = null;
        this.error = false;
        this.errorMessage = null;
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, QuantityDTO result) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.scalarResult = null;
        this.comparisonResult = null;
        this.error = false;
        this.errorMessage = null;
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, boolean comparisonResult) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = null;
        this.scalarResult = null;
        this.comparisonResult = comparisonResult;
        this.error = false;
        this.errorMessage = null;
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, Double scalarResult) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = null;
        this.scalarResult = scalarResult;
        this.comparisonResult = null;
        this.error = false;
        this.errorMessage = null;
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, QuantityDTO operand2, String errorMessage) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = null;
        this.scalarResult = null;
        this.comparisonResult = null;
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public QuantityMeasurementEntity(String operationType, QuantityDTO operand1, String errorMessage) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = null;
        this.result = null;
        this.scalarResult = null;
        this.comparisonResult = null;
        this.error = true;
        this.errorMessage = errorMessage;
    }

    public String getOperationType() {
        return operationType;
    }

    public QuantityDTO getOperand1() {
        return operand1;
    }

    public QuantityDTO getOperand2() {
        return operand2;
    }

    public QuantityDTO getResult() {
        return result;
    }

    public Double getScalarResult() {
        return scalarResult;
    }

    public Boolean getComparisonResult() {
        return comparisonResult;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        if (error) {
            return "Operation=" + operationType + ", ERROR=" + errorMessage;
        }

        if (comparisonResult != null) {
            return "Operation=" + operationType + ", operand1=" + operand1 + ", operand2=" + operand2
                    + ", comparisonResult=" + comparisonResult;
        }

        if (scalarResult != null) {
            return "Operation=" + operationType + ", operand1=" + operand1 + ", operand2=" + operand2
                    + ", scalarResult=" + scalarResult;
        }

        if (operand2 != null) {
            return "Operation=" + operationType + ", operand1=" + operand1 + ", operand2=" + operand2
                    + ", result=" + result;
        }

        return "Operation=" + operationType + ", operand1=" + operand1 + ", result=" + result;
    }
}