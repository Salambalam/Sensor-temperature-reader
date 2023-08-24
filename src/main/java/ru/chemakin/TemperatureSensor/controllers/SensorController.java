package ru.chemakin.TemperatureSensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.TemperatureSensor.dto.SensorDTO;
import ru.chemakin.TemperatureSensor.models.Sensor;
import ru.chemakin.TemperatureSensor.services.SensorService;
import ru.chemakin.TemperatureSensor.util.ErrorHandlingUtils;
import ru.chemakin.TemperatureSensor.util.SensorDTOValidator;
import ru.chemakin.TemperatureSensor.util.sensorExceptionHandlers.SensorErrorResponse;
import ru.chemakin.TemperatureSensor.util.sensorExceptionHandlers.SensorNotCreatedException;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final ModelMapper mapper;
    private final SensorService sensorService;
    private final SensorDTOValidator sensorDTOValidator;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorHandlingUtils.formatErrorMessageFromFieldErrors(bindingResult, SensorNotCreatedException.class);
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException exception) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO dto) {
        return mapper.map(dto, Sensor.class);
    }
}
