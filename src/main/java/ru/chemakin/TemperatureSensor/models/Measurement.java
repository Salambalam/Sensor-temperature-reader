package ru.chemakin.TemperatureSensor.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Getter
@Setter
@NoArgsConstructor
public class Measurement {

    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    @JsonBackReference
    @NotNull
    private Sensor sensor;

    @Column(name = "value")
    @NotNull
    private double value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @Column(name = "received_at")
    @NotNull
    private LocalDateTime receivedAt;
}
