package com.FT_locations;

import com.FT_locations.DTO.LaptopDTO;
import com.FT_locations.Models.Laptop;
import com.FT_locations.Models.Status;
import com.FT_locations.Services.iServices.LaptopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LaptopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaptopService laptopService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListLaptops() throws Exception {
        when(laptopService.getLaptops(null)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/laptops")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetLaptop() throws Exception {
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setBrand("Dell");

        when(laptopService.getLaptop(1)).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.get("/laptops/1"))
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.view().name("laptop_details"))  // Expect Thymeleaf view
                .andExpect(MockMvcResultMatchers.model().attributeExists("laptop"))  // Ensure model has 'laptop' attribute
                .andExpect(MockMvcResultMatchers.model().attribute("laptop", laptop));  // Check model attribute
    }


    @Test
    void testCreateLaptop() throws Exception {
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setBrand("Dell");
        laptop.setModel("XPS 15");
        laptop.setStatus(Status.AVAILABLE);

        // Mock service to return a non-null laptop with an ID
        when(laptopService.createLaptop(any(LaptopDTO.class))).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.post("/laptops/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("brand", "Dell")
                        .param("model", "XPS 15")
                        .param("price", "1500")
                        .param("status", "AVAILABLE"))
                .andExpect(status().is3xxRedirection()) // Expect redirect
                .andExpect(MockMvcResultMatchers.redirectedUrl("/laptops/1")); // Ensure correct redirection
    }


    @Test
    void testDecommissionLaptop() throws Exception {
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setStatus(Status.DECOMMISSIONED);

        when(laptopService.decommissionLaptop(1)).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.patch("/laptops/decommission/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/laptops/1"));
    }

}
