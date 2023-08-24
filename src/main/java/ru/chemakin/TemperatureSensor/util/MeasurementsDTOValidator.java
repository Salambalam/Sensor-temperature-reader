package ru.chemakin.TemperatureSensor.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chemakin.TemperatureSensor.dto.MeasurementDTO;
import ru.chemakin.TemperatureSensor.services.SensorService;

@Component
@RequiredArgsConstructor
public class MeasurementsDTOValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if (sensorService.findByName(measurementDTO.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor with specified name not found!");
        }
    }
}
