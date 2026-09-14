package com.cognizant.tradingcompany.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.tradingcompany.dto.TradingCompanyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TradingCompanyIntegrationTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    @WithMockUser(authorities = "SCOPE_admin")
    void fullFlowTest() throws Exception {
 
        TradingCompanyDTO dto = new TradingCompanyDTO();
        dto.setName("XYZ Trading");
        dto.setLicenseNumber("LIC12388");
        dto.setContactEmail("xyz@test.com");
        dto.setAddress("Kolkata");
 
        // CREATE
        mockMvc.perform(post("/api/trading-companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
 
        // GET ALL
        mockMvc.perform(get("/api/trading-companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
 
