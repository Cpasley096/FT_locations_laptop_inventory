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

        mockMvc.perform(MockMvcRequestBuilders.get("/laptops/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Dell"));
    }

    @Test
    void testCreateLaptop() throws Exception {
        LaptopDTO laptopDTO = new LaptopDTO("Dell", "XPS 15", null, 1500,null , Status.AVAILABLE, null, null, null);
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setBrand(laptopDTO.brand());
        laptop.setModel(laptopDTO.model());

        when(laptopService.createLaptop(laptopDTO)).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.post("/laptops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptopDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("Dell"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("XPS 15"));
    }

    @Test
    void testUpdateLaptop() throws Exception {
        LaptopDTO laptopDTO = new LaptopDTO("Dell", "XPS 15", null, 1500,null , Status.REPAIRING, null, null, null);
        Laptop updatedLaptop = new Laptop();
        updatedLaptop.setId(1);
        updatedLaptop.setBrand("Dell");
        updatedLaptop.setStatus(Status.REPAIRING);

        when(laptopService.updateLaptop(1, laptopDTO)).thenReturn(updatedLaptop);

        mockMvc.perform(MockMvcRequestBuilders.patch("/laptops/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laptopDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("REPAIRING"));
    }

    @Test
    void testDecommissionLaptop() throws Exception {
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setStatus(Status.Decommissioned);

        when(laptopService.decommissionLaptop(1)).thenReturn(laptop);

        mockMvc.perform(MockMvcRequestBuilders.patch("/laptops/decommission/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("Decommissioned"));
    }
}
