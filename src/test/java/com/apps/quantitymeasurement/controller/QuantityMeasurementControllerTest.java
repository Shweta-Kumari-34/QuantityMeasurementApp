package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementControllerTest {

    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {
        repository = QuantityMeasurementCacheRepository.getInstance();
        repository.clear();
        service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    @Test
    void testController_DemonstrateEquality_Success() {
        String result = controller.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

    @Test
    void testController_DemonstrateConversion_Success() {
        String result = controller.performConversion(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                "INCHES"
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

    @Test
    void testController_DemonstrateAddition_Success() {
        String result = controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

    @Test
    void testController_DemonstrateAddition_Error() {
        String result = controller.performAddition(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE")
        );

        assertTrue(result.startsWith("ERROR"));
    }

    @Test
    void testController_DisplayResult_Success() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "COMPARE",
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH"),
                true
        );

        String result = controller.displayResult(entity);

        assertTrue(result.startsWith("SUCCESS"));
    }

    @Test
    void testController_DisplayResult_Error() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "ADD",
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                "Unsupported operation"
        );

        String result = controller.displayResult(entity);

        assertTrue(result.startsWith("ERROR"));
    }

    @Test
    void testController_AllOperations() {
        assertNotNull(controller.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        ));

        assertNotNull(controller.performConversion(
                new QuantityDTO(1.0, "FEET", "LENGTH"), "INCHES"
        ));

        assertNotNull(controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        ));

        assertNotNull(controller.performSubtraction(
                new QuantityDTO(10.0, "FEET", "LENGTH"),
                new QuantityDTO(6.0, "INCHES", "LENGTH")
        ));

        assertNotNull(controller.performDivision(
                new QuantityDTO(10.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(5.0, "KILOGRAM", "WEIGHT")
        ));
    }

    @Test
    void testController_ConsoleOutput_Format() {
        String result = controller.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

    @Test
    void testDataFlow_ControllerToService() {
        controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertFalse(repository.getAllMeasurements().isEmpty());
    }

    @Test
    void testDataFlow_ServiceToController() {
        String result = controller.performDivision(
                new QuantityDTO(10.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(5.0, "KILOGRAM", "WEIGHT")
        );

        assertTrue(result.contains("2.0"));
    }

    @Test
    void testController_NullService_Prevention() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementController(null));
    }

    @Test
    void testLayerSeparation_ControllerIndependence() {
        IQuantityMeasurementService fakeService = new IQuantityMeasurementService() {
            @Override
            public QuantityMeasurementEntity compare(QuantityDTO operand1, QuantityDTO operand2) {
                return new QuantityMeasurementEntity("COMPARE", operand1, operand2, true);
            }

            @Override
            public QuantityMeasurementEntity convert(QuantityDTO operand1, String targetUnit) {
                return new QuantityMeasurementEntity(
                        "CONVERT",
                        operand1,
                        new QuantityDTO(1.0, targetUnit, operand1.getMeasurementType())
                );
            }

            @Override
            public QuantityMeasurementEntity add(QuantityDTO operand1, QuantityDTO operand2) {
                return new QuantityMeasurementEntity(
                        "ADD",
                        operand1,
                        operand2,
                        new QuantityDTO(2.0, operand1.getUnit(), operand1.getMeasurementType())
                );
            }

            @Override
            public QuantityMeasurementEntity add(QuantityDTO operand1, QuantityDTO operand2, String targetUnit) {
                return add(operand1, operand2);
            }

            @Override
            public QuantityMeasurementEntity subtract(QuantityDTO operand1, QuantityDTO operand2) {
                return add(operand1, operand2);
            }

            @Override
            public QuantityMeasurementEntity subtract(QuantityDTO operand1, QuantityDTO operand2, String targetUnit) {
                return add(operand1, operand2);
            }

            @Override
            public QuantityMeasurementEntity divide(QuantityDTO operand1, QuantityDTO operand2) {
                return new QuantityMeasurementEntity("DIVIDE", operand1, operand2, 1.0);
            }
        };

        QuantityMeasurementController fakeController = new QuantityMeasurementController(fakeService);
        assertNotNull(fakeController);
    }

    @Test
    void testLayerDecoupling_ServiceChange() {
        IQuantityMeasurementService anotherService = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController anotherController = new QuantityMeasurementController(anotherService);

        String result = anotherController.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertNotNull(result);
    }
}