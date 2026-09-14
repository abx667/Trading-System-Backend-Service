package com.cognizant.traderservice.integration;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.traderservice.client.TradingCompanyClient;
import com.cognizant.traderservice.dto.TraderRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TraderIntegrationTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private TradingCompanyClient tradingCompanyClient;
 
    @Test
    void fullFlowTest() throws Exception {
    	
    	when(tradingCompanyClient.getCompanyById(anyLong()))
        .thenReturn(new Object());
 
        TraderRequestDTO dto = TraderRequestDTO.builder()
                .tradingCompanyId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john" + System.currentTimeMillis() + "@test.com")
                .phone("9999999999")
                .build();
 
        mockMvc.perform(post("/api/traders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
 
        mockMvc.perform(get("/api/traders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
 