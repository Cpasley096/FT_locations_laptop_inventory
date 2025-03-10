package com.FT_locations.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String cpu;
    private String gpu;
    private int ram;
    private int storageCapacity;
    private float screenSize;

    @OneToOne(mappedBy = "hardware")
    @JsonIgnore
    private Laptop laptop;

    public Hardware(String cpu, String gpu, int ram, int storageCapacity, float screenSize) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.storageCapacity = storageCapacity;
        this.screenSize = screenSize;
    }
}
