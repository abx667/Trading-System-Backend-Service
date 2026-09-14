package com.cognizant.tradingcompany.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.tradingcompany.dto.TradingCompanyDTO;
import com.cognizant.tradingcompany.service.TradingCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(TradingCompanyController.class)
@AutoConfigureMockMvc(addFilters = false)
class TradingCompanyControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private TradingCompanyService service;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    void shouldCreateCompany() throws Exception {
 
        TradingCompanyDTO dto = new TradingCompanyDTO();
        dto.setId(1L);
        dto.setName("ABC Trading");
        dto.setLicenseNumber("LIC123");
        dto.setContactEmail("abc@test.com");
        dto.setAddress("Mumbai");
 
        when(service.saveCompany(any())).thenReturn(dto);
 
        mockMvc.perform(post("/api/trading-companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ABC Trading"));
    }
 
    @Test
    void shouldReturnAllCompanies() throws Exception {
 
        TradingCompanyDTO dto = new TradingCompanyDTO();
        dto.setId(1L);
        dto.setName("ABC Trading");
 
        when(service.getAllCompanies()).thenReturn(List.of(dto));
 
        mockMvc.perform(get("/api/trading-companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
 