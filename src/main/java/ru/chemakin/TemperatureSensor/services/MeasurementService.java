package ru.chemakin.TemperatureSensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chemakin.TemperatureSensor.models.Measurement;
import ru.chemakin.TemperatureSensor.repositories.MeasurementRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setReceivedAt(LocalDateTime.now());
        measurement.setSensor(sensorService
                .findByName(measurement.getSensor().getName())
                .orElse(null));
    }
}
