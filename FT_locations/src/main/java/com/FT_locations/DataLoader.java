package com.FT_locations;

import com.FT_locations.Models.*;
import com.FT_locations.Repositories.EmployeeRepo;
import com.FT_locations.Repositories.LaptopHardwareRepo;
import com.FT_locations.Repositories.LaptopRepo;
import com.FT_locations.Repositories.LaptopSoftwareRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    private final EmployeeRepo employeeRepo;

    private final LaptopRepo laptopRepo;
    private final LaptopHardwareRepo laptopHardwareRepo;
    private final LaptopSoftwareRepo laptopSoftwareRepo;


    public DataLoader(EmployeeRepo employeeRepo, LaptopRepo laptopRepo, LaptopHardwareRepo laptopHardwareRepo, LaptopSoftwareRepo laptopSoftwareRepo){
        this.employeeRepo = employeeRepo;
        this.laptopRepo = laptopRepo;
        this.laptopHardwareRepo = laptopHardwareRepo;
        this.laptopSoftwareRepo = laptopSoftwareRepo;
    }
    @Override
    public void run(String... args) throws Exception {
        Hardware hardware1 = new Hardware(
                "Intel i5",
                "Intel Integrated Graphics",
                8,
                512,
                15.6f
        );

        Hardware hardware2 = new Hardware(
                "Intel i7",
                "NVIDIA GTX 1650",
                16,
                1024,
                17.3f
        );

        Hardware hardware3 = new Hardware(
                "AMD Ryzen 5",
                "AMD Radeon RX 560X",
                32,
                512,
                13.3f
        );
        Software software1 = new Software(
                "Windows 10",
                "v1.0.0",
                LocalDate.of(2024, 5, 10)
        );

        Software software2 = new Software(
                "Windows 11",
                "v2.1.0",
                LocalDate.of(2024, 7, 10)

        );

        Software software3 = new Software(
                "Ubuntu 20.04",
                "v3.2.1",
                LocalDate.of(2024, 8, 10)

        );

        Employee employee1 = new Employee("John", "Doe", "john.doe@example.com", "IT");
        Employee employee2 = new Employee("Jane", "Smith", "jane.smith@example.com", "HR");
        Employee employee3 = new Employee("Jim", "Brown", "jim.brown@example.com", "Sales");

        employeeRepo.save(employee1);
        employeeRepo.save(employee2);
        employeeRepo.save(employee3);

        Laptop laptop1 = new Laptop(
                "Dell",
                "Inspiron 15",
                Status.ACTIVE,
                LocalDate.now(),
                899.99f,
               null ,
                hardware1,
                software1,
                employee1
        );

        Laptop laptop2 = new Laptop(
                "HP",
                "Pavilion 17",
                Status.ACTIVE,
                LocalDate.now(),
                1199.99f,
                null,
                hardware2,
                software2,
                employee2
        );

        Laptop laptop3 = new Laptop(
                "Apple",
                "MacBook Pro",
                Status.AVAILABLE,
                LocalDate.now(),
                2499.99f,
                null,
                hardware3,
                software3,
                null
        );

        laptopRepo.save(laptop1);
        laptopRepo.save(laptop2);
        laptopRepo.save(laptop3);
    }
}
