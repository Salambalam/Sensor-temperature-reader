package ru.chemakin.TemperatureSensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.chemakin.TemperatureSensor.models.Sensor;

@Data
public class MeasurementDTO {

    @Size(min = -100, max = 100, message = "The range of values must be between -100 and 100 degrees!")
    private double value;

    @NotEmpty(message = "Field \"raining\" not be empty!")
    private boolean raining;
//    @NotEmpty(message = "Field \"sensor\" not be empty!")
    //private Sensor sensor;
}
