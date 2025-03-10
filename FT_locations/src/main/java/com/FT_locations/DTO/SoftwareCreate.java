package com.FT_locations.DTO;

import java.time.LocalDate;

public record SoftwareCreate(
        String os,
        String firmwareVersion,
        LocalDate firmwareUpdatedAt,
        int laptopId
) {}
