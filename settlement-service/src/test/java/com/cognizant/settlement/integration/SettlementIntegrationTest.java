package com.cognizant.settlement.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.settlement.client.TradeOrderClient;
import com.cognizant.settlement.dto.SettlementDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
class SettlementIntegrationTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private TradeOrderClient tradeOrderClient;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    void fullFlowTest() throws Exception {
 
        when(tradeOrderClient.getOrderById(10L)).thenReturn(new Object());
 
        SettlementDTO dto = SettlementDTO.builder()
                .tradeOrderId(10L)
                .amount(new BigDecimal("500.00"))
                .status("COMPLETED")
                .paymentReference("PAY999")
                .settlementDate(LocalDateTime.now())
                .build();
 
        mockMvc.perform(post("/api/settlements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }
}

 