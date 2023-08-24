package ru.chemakin.TemperatureSensor.util.measurementsExceptionHandlers;

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException(String msg) {
        super(msg);
    }
}
