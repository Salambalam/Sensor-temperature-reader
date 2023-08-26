package ru.chemakin.TemperatureSensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.chemakin.TemperatureSensor.models.Sensor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDTO {

    @Min(value = -100, message = "The range of values must be between -100 and 100 degrees!")
    @Max(value = 100, message = "The range of values must be between -100 and 100 degrees!")
    @NotNull
    private double value;

    @NotNull
    private boolean raining;

    @NotNull
    private Sensor sensor;

}
