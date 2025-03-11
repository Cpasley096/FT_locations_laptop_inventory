package com.FT_locations.DTO;

import com.FT_locations.Models.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LaptopFormDTO {
    private String brand;
    private String model;
    private LocalDate purchasedAt;
    private float price;
    private LocalDate lastRepairedAt;
    private Status status;
    private String cpu;
    private int ram;
    private int storageCapacity;
    private String gpu;
    private float screenSize;
    private String os;
    private String firmwareVersion;
    private LocalDate firmwareUpdatedAt;
    private int employeeId;
}