package ru.chemakin.TemperatureSensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasurementDTO {

    @Min(-100)
    @Max(100)
    @NotEmpty
    private double value;

    @NotNull
    private boolean raining;

    @NotNull
    private SensorDTO sensor;
}
