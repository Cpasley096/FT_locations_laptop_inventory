package com.FT_locations.Services;

import com.FT_locations.DTO.Args;
import com.FT_locations.Models.Laptop;
import com.FT_locations.DTO.LaptopDTO;

import com.FT_locations.Models.*;
import com.FT_locations.Repositories.LaptopRepo;
import com.FT_locations.Services.iServices.EmployeeService;
import com.FT_locations.Services.iServices.LaptopService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private EmployeeService employeeService;
    public List<Laptop> getLaptops(Args args) {
        Sort sort = Sort.by(Sort.Order.by(args.orderBy()));

        if ("DESC".equalsIgnoreCase(args.orderMode())) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        if (args.status() != null) return laptopRepo.findByStatus(args.status(), sort);
        return laptopRepo.findAll(sort);
    }

    public void exportLaptopsToCsv(Args args, HttpServletResponse response) throws IOException {
        List<Laptop> laptops = getLaptops(args);

        if ("csv".equalsIgnoreCase(args.format())) {
            CsvExporter.exportCsv(response, laptops);

//            response.sendRedirect("/export-success");
        } else {
            throw new IllegalArgumentException("Invalid format. Use 'csv'.");
        }
    }

    public Laptop getLaptop(int id) {
        Optional<com.FT_locations.Models.Laptop> optionalLaptop = laptopRepo.findById(id);
        return optionalLaptop.orElse(null);
    }

    public Laptop createLaptop(LaptopDTO laptopDTO) {
        Laptop laptop = new Laptop();
        laptop.setBrand(laptopDTO.brand());
        laptop.setModel(laptopDTO.model());
        laptop.setStatus(laptopDTO.status());
        laptop.setPurchasedAt(laptopDTO.purchasedAt());
        laptop.setPrice(laptopDTO.price());
        laptop.setLastRepairedAt(laptopDTO.lastRepairedAt());

        Hardware hardware = new Hardware();
        hardware.setCpu(laptopDTO.hardwareDTO().cpu());
        hardware.setGpu(laptopDTO.hardwareDTO().gpu());
        hardware.setRam(laptopDTO.hardwareDTO().ram());
        hardware.setStorageCapacity(laptopDTO.hardwareDTO().storageCapacity());
        hardware.setScreenSize(laptopDTO.hardwareDTO().screenSize());

        laptop.setHardware(hardware);

        Software software = new Software();
        software.setOs(laptopDTO.softwareDTO().os());
        software.setFirmwareVersion(laptopDTO.softwareDTO().firmwareVersion());
        software.setFirmwareUpdatedAt(laptopDTO.softwareDTO().firmwareUpdatedAt());

        laptop.setSoftware(software);

        if (laptopDTO.employeeDTO() != null && laptopDTO.employeeDTO().id() > 0) {
            Employee employee = employeeService.getEmployee(laptopDTO.employeeDTO().id());
            if (employee == null) {
                throw new RuntimeException("Employee not found");
            }
            laptop.setEmployee(employee);
        }

        return laptopRepo.save(laptop);
    }

    public Laptop updateLaptop(int id, LaptopDTO laptopDTO) {
        Laptop laptop = laptopRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));

        if (laptopDTO.status() != null) {
            laptop.setStatus(laptopDTO.status());
        }

        if (laptopDTO.lastRepairedAt() != null) {
            laptop.setLastRepairedAt(laptopDTO.lastRepairedAt());
        }

        if (laptopDTO.softwareDTO().firmwareUpdatedAt() != null) {
            laptop.getSoftware().setFirmwareUpdatedAt(laptopDTO.softwareDTO().firmwareUpdatedAt());
        }

        if (laptopDTO.employeeDTO() != null && laptopDTO.employeeDTO().id() > 0) {
            Employee employee = employeeService.getEmployee(laptopDTO.employeeDTO().id());
            if (employee == null) {
                throw new RuntimeException("Employee not found");
            }
            laptop.setEmployee(employee);
        }

        return laptopRepo.save(laptop);
    }

    public Laptop decommissionLaptop(int id) {
        Laptop laptop = laptopRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));
        laptop.setStatus(Status.DECOMMISSIONED);

        laptop.setEmployee(null);
        return laptopRepo.save(laptop);
    }
}
