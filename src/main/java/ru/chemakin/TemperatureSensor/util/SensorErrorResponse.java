package ru.chemakin.TemperatureSensor.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorErrorResponse {
    private String message;
}
