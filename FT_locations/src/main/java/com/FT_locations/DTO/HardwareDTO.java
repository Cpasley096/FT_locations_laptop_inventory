package com.FT_locations.DTO;

public record HardwareDTO(
    String cpu,
    int ram,
    int storageCapacity,
    String gpu,
    float screenSize
) {}
