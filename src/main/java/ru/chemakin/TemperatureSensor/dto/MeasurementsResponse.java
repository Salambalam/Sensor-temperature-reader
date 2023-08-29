package ru.chemakin.TemperatureSensor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;
}
