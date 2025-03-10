package com.FT_locations.DTO;

import com.FT_locations.Models.Status;

import java.time.LocalDate;

public record LaptopDTO(
        String brand,
        String model,
        LocalDate purchasedAt,
        float price,
        LocalDate lastRepairedAt,
        Status status,
        int employeeId,
        HardwareCreate hardwareCreate,
        SoftwareCreate softwareCreate,
        Employee employee
) {}
