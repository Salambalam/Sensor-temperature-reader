package ru.chemakin.TemperatureSensor.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeasurementErrorResponse {
    private String message;
}
