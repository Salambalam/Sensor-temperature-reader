package ru.chemakin.TemperatureSensor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.chemakin.TemperatureSensor.models.Sensor;

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
