package ru.chemakin.TemperatureSensor.util;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String msg){
        super(msg);
    }
}
