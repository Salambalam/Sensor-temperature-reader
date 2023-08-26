package ru.chemakin.TemperatureSensor.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chemakin.TemperatureSensor.dto.MeasurementDTO;
import ru.chemakin.TemperatureSensor.models.Measurement;
import ru.chemakin.TemperatureSensor.services.MeasurementService;
import ru.chemakin.TemperatureSensor.util.ErrorHandlingUtils;
import ru.chemakin.TemperatureSensor.util.MeasurementsDTOValidator;
import ru.chemakin.TemperatureSensor.util.measurementsExceptionHandlers.MeasurementErrorResponse;
import ru.chemakin.TemperatureSensor.util.measurementsExceptionHandlers.MeasurementNotCreatedException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final ModelMapper mapper;
    private final MeasurementService measurementService;
    private final MeasurementsDTOValidator measurementsDTOValidator;

    @GetMapping
    public List<MeasurementDTO> getMeasurement() {
        return measurementService.findAll().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int rainyDaysCount() {
        return measurementService.countRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {

        measurementsDTOValidator.validate(measurementDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorHandlingUtils.formatErrorMessageFromFieldErrors(bindingResult, MeasurementNotCreatedException.class);
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return mapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        measurement.getSensor().setMeasurements(null);
        return mapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException exception) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                exception.getMessage()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
