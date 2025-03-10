package com.FT_locations.Services;

import com.FT_locations.Models.Laptop;
import com.FT_locations.DTO.LaptopDTO;

import com.FT_locations.Models.*;
import com.FT_locations.Repositories.EmployeeRepo;
import com.FT_locations.Repositories.LaptopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepo laptopRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    public List<Laptop> getLaptops(Args args) {
        Sort sort = Sort.by(Sort.Order.by(args.orderBy()));

        if ("DESC".equalsIgnoreCase(args.orderMode())) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        return laptopRepo.findAll(sort);
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
        hardware.setCpu(laptopDTO.hardwareCreate().cpu());
        hardware.setGpu(laptopDTO.hardwareCreate().gpu());
        hardware.setRam(laptopDTO.hardwareCreate().ram());
        hardware.setStorageCapacity(laptopDTO.hardwareCreate().storageCapacity());
        hardware.setScreenSize(laptopDTO.hardwareCreate().screenSize());

        laptop.setHardware(hardware);

        Software software = new Software();
        software.setOs(laptopDTO.softwareCreate().os());
        software.setFirmwareVersion(laptopDTO.softwareCreate().firmwareVersion());
        software.setFirmwareUpdatedAt(laptopDTO.softwareCreate().firmwareUpdatedAt());

        laptop.setSoftware(software);

        Employee employee = employeeRepo.findById(laptopDTO.employee().id())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        laptop.setEmployee(employee);

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

        if (laptopDTO.softwareCreate().os() != null) {
            laptop.getSoftware().setOs(laptopDTO.softwareCreate().os());
        }

        if (laptopDTO.softwareCreate().firmwareVersion() != null) {
            laptop.getSoftware().setFirmwareVersion(laptopDTO.softwareCreate().firmwareVersion());
        }

        if (laptopDTO.softwareCreate().firmwareUpdatedAt() != null) {
            laptop.getSoftware().setFirmwareUpdatedAt(laptopDTO.softwareCreate().firmwareUpdatedAt());
        }

        if (laptopDTO.employee() != null && laptopDTO.employee().id() != 0) {
            Employee employee = employeeRepo.findById(laptopDTO.employee().id())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            laptop.setEmployee(employee);
        }

        return laptopRepo.save(laptop);
    }

    public Laptop decommissionLaptop(int id) {
        Laptop laptop = laptopRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found"));
        laptop.setStatus(Status.Decommissioned);

        laptop.setEmployee(null);
        return laptopRepo.save(laptop);
    }
}
