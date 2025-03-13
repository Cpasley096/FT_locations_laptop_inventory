package com.FT_locations.Controller;

import com.FT_locations.DTO.*;
import com.FT_locations.Models.Employee;
import com.FT_locations.Models.Laptop;
import com.FT_locations.DTO.Args;
import com.FT_locations.Models.Status;
import com.FT_locations.Services.iServices.EmployeeService;
import com.FT_locations.Services.iServices.LaptopService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/laptops")
public class LaptopController {
    @Autowired
    private LaptopService laptopService;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping({"/", ""})
    public String listLaptops(@RequestParam(name = "orderBy", required = false) String orderBy,
                             @RequestParam(name = "orderMode", required = false) String orderMode,
                             @RequestParam(name = "status", required = false) String statusStr, Model model) {

        Status status = Status.fromString(statusStr);

        Args args = new Args(orderBy, orderMode, status, null);

        List<Laptop> laptops = laptopService.getLaptops(args);
        model.addAttribute("laptops", laptops);
        return "index";
    }

    @GetMapping("/{id}")
    String getLaptop(@PathVariable("id") int id, Model model) {
        Laptop laptop = laptopService.getLaptop(id);
        model.addAttribute("laptop", laptop);
        return "laptop_details";
    }

    @GetMapping("/newLaptop")
    public String showLaptopForm(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        System.out.println(employees.toString());
        model.addAttribute("employees", employees);
        model.addAttribute("laptopForm", new LaptopFormDTO());
        return "add_laptop";
    }
    @GetMapping({"/export"})
    public String exportPage(){
        return "export_laptops";
    }

    @GetMapping({"/export/download"})
    public String exportLaptops(
            @RequestParam(name = "orderBy", required = false) String orderBy,
            @RequestParam(name = "orderMode", required = false) String orderMode,
            @RequestParam(name = "status", required = false) String statusStr,
            @RequestParam(name = "format") String format,
            HttpServletResponse response) throws IOException {

        Status status = Status.fromString(statusStr);

        Args args = new Args(orderBy, orderMode, status, format);

        laptopService.exportLaptopsToCsv(args, response);
        return "redirect:/";
    }

    @PostMapping({"/save"})
    public String createLaptop(@ModelAttribute LaptopFormDTO laptopFormDTO){
        System.out.println(laptopFormDTO.toString());

        HardwareDTO hardwareDTO = new HardwareDTO(
                laptopFormDTO.getCpu(),
                laptopFormDTO.getRam(),
                laptopFormDTO.getStorageCapacity(),
                laptopFormDTO.getGpu(),
                laptopFormDTO.getScreenSize()
        );

        SoftwareDTO softwareDTO = new SoftwareDTO(
                laptopFormDTO.getOs(),
                laptopFormDTO.getFirmwareVersion(),
                laptopFormDTO.getFirmwareUpdatedAt()
        );

        EmployeeDTO employeeDTO = null;
        if (laptopFormDTO.getEmployeeId() != 0) {
            employeeDTO = new EmployeeDTO(laptopFormDTO.getEmployeeId());
        }

        LaptopDTO laptopDTO = new LaptopDTO(
                laptopFormDTO.getBrand(),
                laptopFormDTO.getModel(),
                laptopFormDTO.getPurchasedAt(),
                laptopFormDTO.getPrice(),
                laptopFormDTO.getLastRepairedAt(),
                laptopFormDTO.getStatus(),
                hardwareDTO,
                softwareDTO,
                employeeDTO

        );

        Laptop laptop = laptopService.createLaptop(laptopDTO);
        return "redirect:/laptops/" + laptop.getId();
    }

    @PatchMapping("/decommission/{id}")
    public String decommissionLaptop(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Laptop laptop = laptopService.decommissionLaptop(id);

        redirectAttributes.addFlashAttribute("laptop", laptop);

        return "redirect:/laptops/" + id;
    }
}
