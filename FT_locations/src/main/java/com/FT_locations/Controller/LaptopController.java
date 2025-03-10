package com.FT_locations.Controller;

import com.FT_locations.DTO.LaptopDTO;
import com.FT_locations.Models.Laptop;
import com.FT_locations.Models.Args;
import com.FT_locations.Models.Status;
import com.FT_locations.Services.LaptopService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/laptops")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;
    @GetMapping({"/", ""})
    List<Laptop> listLaptops(@RequestParam(name = "orderBy", required = false) String orderBy,
                                                     @RequestParam(name = "orderMode", required = false) String orderMode,
                             @RequestParam(name = "status", required = false) String statusStr) {

        Status status = Status.fromString(statusStr);

        Args args = new Args(orderBy, orderMode, status, null);

        return laptopService.getLaptops(args);
    }
    @GetMapping({"/export"})
    public void exportLaptops(
            @RequestParam(name = "orderBy", required = false) String orderBy,
            @RequestParam(name = "orderMode", required = false) String orderMode,
            @RequestParam(name = "status", required = false) String statusStr,
            @RequestParam(name = "format") String format,
            HttpServletResponse response) throws IOException {

        Status status = Status.fromString(statusStr);

        Args args = new Args(orderBy, orderMode, status, format);

        laptopService.exportLaptopsToCsv(args, response);
    }

    @GetMapping("/{id}")
    Laptop getLaptop(@PathVariable("id") int id) {
        return laptopService.getLaptop(id);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Laptop> createLaptop(@RequestBody LaptopDTO laptopDTO){
        Laptop laptop = laptopService.createLaptop(laptopDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(laptop);
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<Laptop> updateLaptop(@PathVariable("id") int id, @RequestBody LaptopDTO laptopDTO) {
        Laptop laptop = laptopService.updateLaptop(id, laptopDTO);
        return ResponseEntity.status(HttpStatus.OK).body(laptop);
    }

    @PatchMapping({"/decommission/{id}"})
    public ResponseEntity<Laptop> decommissionLaptop(@PathVariable("id") int id) {
        Laptop laptop = laptopService.decommissionLaptop(id);
        return ResponseEntity.status(HttpStatus.OK).body(laptop);
    }
}
