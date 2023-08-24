package ru.chemakin.TemperatureSensor.util.sensorExceptionHandlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorErrorResponse {
    private String message;
}
