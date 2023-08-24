package ru.chemakin.TemperatureSensor.util.sensorExceptionHandlers;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
