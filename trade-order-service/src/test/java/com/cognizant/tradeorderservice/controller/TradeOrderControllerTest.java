package com.cognizant.tradeorderservice.controller;
 
import com.cognizant.tradeorderservice.dto.TradeOrderRequestDTO;
import com.cognizant.tradeorderservice.dto.TradeOrderResponseDTO;
import com.cognizant.tradeorderservice.service.TradeOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
 
import java.math.BigDecimal;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
@WebMvcTest(TradeOrderServiceController.class)
@AutoConfigureMockMvc(addFilters = false)
class TradeOrderControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private TradeOrderService tradeOrderService;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    @WithMockUser(authorities = "SCOPE_admin")
    void shouldCreateOrder() throws Exception {
 
        TradeOrderRequestDTO request = TradeOrderRequestDTO.builder()
                .trader_id(1L)
                .asset_id(1L)
                .orderType("BUY")
                .quantity(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(100))
                .status("OPEN")
                .build();
 
        TradeOrderResponseDTO response = TradeOrderResponseDTO.builder()
                .id(1L)
                .trader_id(1L)
                .asset_id(1L)
                .orderType("BUY")
                .quantity(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(100))
                .status("OPEN")
                .build();
 
        Mockito.when(tradeOrderService.saveOrder(Mockito.any()))
                .thenReturn(response);
 
        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}