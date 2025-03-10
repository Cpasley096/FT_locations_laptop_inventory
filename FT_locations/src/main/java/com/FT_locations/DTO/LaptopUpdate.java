package com.FT_locations.DTO;

import com.FT_locations.Models.Employee;
import com.FT_locations.Models.Hardware;
import com.FT_locations.Models.Software;
import com.FT_locations.Models.Status;

import java.time.LocalDate;

public record LaptopUpdate(
        LocalDate lastRepairedAt,
        Status status,
        Hardware hardware,
        Software software,
        Employee employee
) {

}
