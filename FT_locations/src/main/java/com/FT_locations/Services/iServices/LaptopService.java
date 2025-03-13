package com.FT_locations.Services.iServices;

import com.FT_locations.DTO.LaptopDTO;
import com.FT_locations.DTO.Args;
import com.FT_locations.Models.Laptop;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface LaptopService {
    List<Laptop> getLaptops(Args args);
    void exportLaptopsToCsv(Args args, HttpServletResponse response) throws IOException;
    Laptop getLaptop(int id);
    Laptop createLaptop(LaptopDTO laptopDTO);
    Laptop updateLaptop(int id, LaptopDTO laptopDTO);
    Laptop decommissionLaptop(int id);
}
