package com.cognizant.settlement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.settlement.dto.SettlementDTO;
import com.cognizant.settlement.service.SettlementService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SettlementController.class)
@AutoConfigureMockMvc(addFilters = false)
class SettlementControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private SettlementService service;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    @WithMockUser(authorities = "SCOPE_admin")
    void shouldCreateSettlement() throws Exception {
 
        SettlementDTO dto = SettlementDTO.builder()
                .id(1L)
                .tradeOrderId(10L)
                .amount(new BigDecimal("1000.00"))
                .status("COMPLETED")
                .paymentReference("PAY123")
                .settlementDate(LocalDateTime.now())
                .build();
 
        when(service.createSettlement(any())).thenReturn(dto);
 
        mockMvc.perform(post("/api/settlements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}