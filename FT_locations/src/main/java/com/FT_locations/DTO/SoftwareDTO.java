package com.FT_locations.DTO;

import java.time.LocalDate;

public record SoftwareDTO(
        String os,
        String firmwareVersion,
        LocalDate firmwareUpdatedAt
) {}
