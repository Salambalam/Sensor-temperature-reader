package ru.chemakin.TemperatureSensor.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chemakin.TemperatureSensor.dto.SensorDTO;
import ru.chemakin.TemperatureSensor.services.SensorService;

@Component
@RequiredArgsConstructor
public class SensorDTOValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorService.findByName(sensorDTO.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name already taken!");
        }
    }
}
