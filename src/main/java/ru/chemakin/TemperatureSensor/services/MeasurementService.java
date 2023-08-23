package ru.chemakin.TemperatureSensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chemakin.TemperatureSensor.models.Measurement;
import ru.chemakin.TemperatureSensor.repositories.MeasurementRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public void save(Measurement measurement){
        measurement.setReceivedAt(LocalDateTime.now());

    }
}
