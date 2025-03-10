package com.FT_locations.DTO;

import java.time.LocalDate;

public record SoftwareUpdate(
        String os,
        String firmwareVersion,
        LocalDate firmwareUpdatedAt
) {
}
