package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementEntityTest {

    @Test
    void testQuantityEntity_SingleOperandConstruction() {
        QuantityDTO input = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO result = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", input, result);

        assertEquals("CONVERT", entity.getOperationType());
        assertEquals(input, entity.getOperand1());
        assertNull(entity.getOperand2());
        assertEquals(result, entity.getResult());
        assertFalse(entity.hasError());
    }

    @Test
    void testQuantityEntity_BinaryOperandConstruction() {
        QuantityDTO operand1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO operand2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        QuantityDTO result = new QuantityDTO(2.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", operand1, operand2, result);

        assertEquals("ADD", entity.getOperationType());
        assertEquals(operand1, entity.getOperand1());
        assertEquals(operand2, entity.getOperand2());
        assertEquals(result, entity.getResult());
        assertFalse(entity.hasError());
    }

    @Test
    void testQuantityEntity_ErrorConstruction() {
        QuantityDTO operand1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO operand2 = new QuantityDTO(10.0, "CELSIUS", "TEMPERATURE");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", operand1, operand2, "Unsupported operation");

        assertTrue(entity.hasError());
        assertEquals("Unsupported operation", entity.getErrorMessage());
        assertEquals("ADD", entity.getOperationType());
    }

    @Test
    void testQuantityEntity_ToString_Success() {
        QuantityDTO input = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO result = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", input, result);

        String output = entity.toString();

        assertTrue(output.contains("Operation=CONVERT"));
        assertTrue(output.contains("operand1"));
        assertTrue(output.contains("result"));
    }

    @Test
    void testQuantityEntity_ToString_Error() {
        QuantityDTO input = new QuantityDTO(1.0, "CELSIUS", "TEMPERATURE");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", input, "Temperature addition not supported");

        String output = entity.toString();

        assertTrue(output.contains("ERROR"));
        assertTrue(output.contains("Temperature addition not supported"));
    }

    @Test
    void testEntity_OperationType_Tracking() {
        QuantityDTO input = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO result = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", input, result);

        assertEquals("CONVERT", entity.getOperationType());
    }

    @Test
    void testEntity_Immutability() {
        long setterCount = java.util.Arrays.stream(QuantityMeasurementEntity.class.getDeclaredMethods())
                .filter(method -> method.getName().startsWith("set"))
                .count();

        assertEquals(0, setterCount);
    }
}