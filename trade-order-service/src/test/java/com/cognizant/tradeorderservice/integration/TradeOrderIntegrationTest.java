package com.cognizant.tradeorderservice.integration;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.tradeorderservice.client.AssetClient;
import com.cognizant.tradeorderservice.client.TraderClient;
import com.cognizant.tradeorderservice.dto.TradeOrderRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TradeOrderIntegrationTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private TraderClient traderClient;
 
    @MockBean
    private AssetClient assetClient;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    void fullFlowTest() throws Exception {
 
        when(traderClient.getTraderById(1L)).thenReturn(new Object());
        when(assetClient.getAssetById(1L)).thenReturn(new Object());
 
        TradeOrderRequestDTO dto = TradeOrderRequestDTO.builder()
                .trader_id(1L)
                .asset_id(1L)
                .orderType("BUY")
                .quantity(BigDecimal.valueOf(5))
                .price(BigDecimal.valueOf(200))
                .status("OPEN")
                .build();
 
        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}