package com.example.rentalservice.controllers;

import com.example.rentalservice.clients.CarClient;
import com.example.rentalservice.entities.Car;
import com.example.rentalservice.entities.Rental;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RentalControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CarClient carClient;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddRental() throws Exception {
        when(carClient.getCar("ABC123")).thenReturn(new Car("ABC123", "Toyota", 150.0));

        Rental rental = new Rental("B001", "ABC123", "Alice", 3, 0.0);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rental)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPrice").value(450.0));
    }

    @Test
    public void testGetRentals() throws Exception {
        mockMvc.perform(get("/rentals"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRentalById() throws Exception {
        when(carClient.getCar("ABC123")).thenReturn(new Car("ABC123", "Toyota", 150.0));

        Rental rental = new Rental("B001", "ABC123", "Alice", 3, 0.0);
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rental)));

        mockMvc.perform(get("/rentals/B001"))
                .andExpect(status().isOk());
    }
}
