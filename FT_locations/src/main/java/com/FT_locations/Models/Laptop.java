package com.FT_locations.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private Status status;
    private LocalDate purchasedAt;
    private float price;
    private LocalDate lastRepairedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hardware_id")
    private Hardware hardware;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "software_id")
    private Software software;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Laptop(String brand, String model, Status status, LocalDate purchasedAt, float price, LocalDate lastRepairedAt, Hardware hardware, Software software, Employee employee) {
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.purchasedAt = purchasedAt;
        this.price = price;
        this.lastRepairedAt = lastRepairedAt;
        this.hardware = hardware;
        this.software = software;
        this.employee = employee;
    }
}
