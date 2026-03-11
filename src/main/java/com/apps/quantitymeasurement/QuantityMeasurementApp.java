package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {
        IQuantityMeasurementRepository repository = QuantityMeasurementCacheRepository.getInstance();
        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        QuantityDTO oneFoot = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO twelveInches = new QuantityDTO(12.0, "INCHES", "LENGTH");

        System.out.println("Length Equality:");
        System.out.println(controller.performEquality(oneFoot, twelveInches));

        System.out.println("\nLength Conversion:");
        System.out.println(controller.performConversion(oneFoot, "INCHES"));

        System.out.println("\nLength Addition:");
        System.out.println(controller.performAddition(oneFoot, twelveInches));

        System.out.println("\nTemperature Addition Attempt:");
        QuantityDTO temp1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");
        System.out.println(controller.performAddition(temp1, temp2));

        System.out.println("\nCross Category Prevention:");
        QuantityDTO oneKg = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        System.out.println(controller.performEquality(oneFoot, oneKg));
    }
}