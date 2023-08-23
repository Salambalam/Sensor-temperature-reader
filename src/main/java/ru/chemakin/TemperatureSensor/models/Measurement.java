package ru.chemakin.TemperatureSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Data
public class Measurement {

    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "value")
    @NotEmpty(message = "Field \"value\" not be empty!")
    @Size(min = -100, max = 100, message = "The range of values must be between -100 and 100 degrees!")
    private double value;

    @Column(name = "raining")
    @NotEmpty(message = "Field \"raining\" not be empty!")
    private boolean raining;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;
}
