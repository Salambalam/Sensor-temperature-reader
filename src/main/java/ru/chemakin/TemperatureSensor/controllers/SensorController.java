package ru.chemakin.TemperatureSensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.TemperatureSensor.dto.SensorDTO;
import ru.chemakin.TemperatureSensor.models.Sensor;
import ru.chemakin.TemperatureSensor.services.SensorService;
import ru.chemakin.TemperatureSensor.util.SensorErrorResponse;
import ru.chemakin.TemperatureSensor.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final ModelMapper mapper;
    private final SensorService sensorService;
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors){
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }else{
            sensorService.save(convertToSensor(sensorDTO));
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException exception){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO dto){
        return mapper.map(dto, Sensor.class);
    }
}
