package com.FT_locations.DTO;

public record HardwareCreate(
    String cpu,
    int ram,
    int storageCapacity,
    String gpu,
    float screenSize,
    int laptopId
) {}
