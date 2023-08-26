package ru.chemakin.TemperatureSensor.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@NoArgsConstructor
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters!")
    private String name;

    @OneToMany(mappedBy = "sensor")
    @JsonManagedReference
    private List<Measurement> measurements;

}
