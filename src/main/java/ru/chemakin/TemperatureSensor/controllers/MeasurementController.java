package ru.chemakin.TemperatureSensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.TemperatureSensor.dto.MeasurementDTO;
import ru.chemakin.TemperatureSensor.dto.SensorDTO;
import ru.chemakin.TemperatureSensor.models.Measurement;
import ru.chemakin.TemperatureSensor.util.MeasurementErrorResponse;
import ru.chemakin.TemperatureSensor.util.MeasurementNotCreatedException;
import ru.chemakin.TemperatureSensor.util.SensorErrorResponse;
import ru.chemakin.TemperatureSensor.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final ModelMapper mapper;

    @PostMapping("/add") // TODO sensor json
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult){

        Measurement measurement = convertToMeasurement(measurementDTO);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        } else {
            System.out.println(measurement);
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return mapper.map(measurementDTO, Measurement.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException exception) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
