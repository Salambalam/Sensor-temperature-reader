package ru.chemakin.TemperatureSensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chemakin.TemperatureSensor.models.Sensor;
import ru.chemakin.TemperatureSensor.repositories.SensorRepository;

@Service
@RequiredArgsConstructor
public class SensorService {
    public final SensorRepository sensorRepository;
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
