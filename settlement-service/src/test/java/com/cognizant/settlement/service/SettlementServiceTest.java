package com.cognizant.settlement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.settlement.client.TradeOrderClient;
import com.cognizant.settlement.dto.SettlementDTO;
import com.cognizant.settlement.entity.Settlement;
import com.cognizant.settlement.repository.SettlementRepository;

@ExtendWith(MockitoExtension.class)
class SettlementServiceTest {
 
    @Mock
    private SettlementRepository repository;
 
    @Mock
    private TradeOrderClient tradeOrderClient;
 
    @InjectMocks
    private SettlementServiceImpl service;
 
    private Settlement settlement;
    private SettlementDTO dto;
 
    @BeforeEach
    void setUp() {
        dto = SettlementDTO.builder()
                .id(1L)
                .tradeOrderId(10L)
                .amount(new BigDecimal("1000.00"))
                .status("COMPLETED")
                .paymentReference("PAY123")
                .settlementDate(LocalDateTime.now())
                .build();
 
        settlement = Settlement.builder()
                .id(1L)
                .tradeOrderId(10L)
                .amount(new BigDecimal("1000.00"))
                .status("COMPLETED")
                .paymentReference("PAY123")
                .settlementDate(LocalDateTime.now())
                .build();
    }
 
    @Test
    void shouldCreateSettlement() {
 
        when(tradeOrderClient.getOrderById(10L)).thenReturn(new Object());
        when(repository.existsByTradeOrderId(10L)).thenReturn(false);
        when(repository.save(any(Settlement.class))).thenReturn(settlement);
 
        SettlementDTO result = service.createSettlement(dto);
 
        assertNotNull(result);
        assertEquals("COMPLETED", result.getStatus());
    }
 
    @Test
    void shouldGetSettlementById() {
        when(repository.findById(1L)).thenReturn(Optional.of(settlement));
 
        SettlementDTO result = service.getSettlementById(1L);
 
        assertEquals(1L, result.getId());
    }
 
    @Test
    void shouldDeleteSettlement() {
        when(repository.findById(1L)).thenReturn(Optional.of(settlement));
 
        service.deleteSettlement(1L);
 
        verify(repository, times(1)).delete(settlement);
    }
}