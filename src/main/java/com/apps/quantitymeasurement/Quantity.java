package com.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.02;

    // UC13: Step 1 - Define Arithmetic Operations using Lambda
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> a / b);

        private final DoubleBinaryOperator operator;
        ArithmeticOperation(DoubleBinaryOperator operator) { this.operator = operator; }
        public double compute(double v1, double v2) { return operator.applyAsDouble(v1, v2); }
    }

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    // UC13: Step 2 & 3 - Centralized Private Helper Method (DRY)
    private double performArithmetic(Quantity<U> other, ArithmeticOperation op) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!this.unit.getCategory().equals(other.unit.getCategory())) 
            throw new IllegalArgumentException("Cross-category operations not allowed");
        
        double base1 = this.unit.convertToBase(this.value);
        double base2 = other.unit.convertToBase(other.value);
        
        if (op == ArithmeticOperation.DIVIDE && base2 == 0) 
            throw new ArithmeticException("Division by zero");
            
        return op.compute(base1, base2);
    }

    // UC13: Step 4 - Refactored Public API (Addition)
    public Quantity<U> add(Quantity<U> other) { return add(other, this.unit); }
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double resultBase = performArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(targetUnit.convertFromBase(resultBase)), targetUnit);
    }

    // UC13: Refactored Subtraction
    public Quantity<U> subtract(Quantity<U> other) { return subtract(other, this.unit); }
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        double resultBase = performArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(targetUnit.convertFromBase(resultBase)), targetUnit);
    }

    // UC13: Refactored Division
    public double divide(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // UC5: Conversion
    public Quantity<U> convertTo(U targetUnit) {
        double base = this.unit.convertToBase(this.value);
        return new Quantity<>(round(targetUnit.convertFromBase(base)), targetUnit);
    }

    private double round(double v) { return Math.round(v * 100.0) / 100.0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity<?> that)) return false;
        if (!this.unit.getCategory().equals(that.unit.getCategory())) return false;
        return Math.abs(this.unit.convertToBase(this.value) - that.unit.convertToBase(that.value)) < EPSILON;
    }
}