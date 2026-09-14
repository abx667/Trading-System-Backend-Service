package com.cognizant.tradeorderservice.service;
 
import com.cognizant.tradeorderservice.client.AssetClient;
import com.cognizant.tradeorderservice.client.TraderClient;
import com.cognizant.tradeorderservice.dto.TradeOrderRequestDTO;
import com.cognizant.tradeorderservice.dto.TradeOrderResponseDTO;
import com.cognizant.tradeorderservice.entity.TradeOrder;
import com.cognizant.tradeorderservice.mapper.TradeOrderMapper;
import com.cognizant.tradeorderservice.repositories.TradeOrderServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 
import java.math.BigDecimal;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(SpringExtension.class)
class TradeOrderServiceTest {
 
    @InjectMocks
    private TradeOrderServiceImpl service;
 
    @Mock
    private TradeOrderServiceRepository repository;
 
    @Mock
    private TradeOrderMapper mapper;
 
    @Mock
    private TraderClient traderClient;
 
    @Mock
    private AssetClient assetClient;
 
    private TradeOrderRequestDTO requestDTO;
    private TradeOrder order;
    private TradeOrderResponseDTO responseDTO;
 
    @BeforeEach
    void setUp() {
        requestDTO = TradeOrderRequestDTO.builder()
                .trader_id(1L)
                .asset_id(1L)
                .orderType("BUY")
                .quantity(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(100))
                .status("OPEN")
                .build();
 
        order = TradeOrder.builder()
                .id(1L)
                .trader_id(1L)
                .asset_id(1L)
                .orderType("BUY")
                .quantity(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(100))
                .status("OPEN")
                .build();
 
        responseDTO = TradeOrderResponseDTO.builder()
                .id(1L)
                .trader_id(1L)
                .asset_id(1L)
                .orderType("BUY")
                .quantity(BigDecimal.valueOf(10))
                .price(BigDecimal.valueOf(100))
                .status("OPEN")
                .build();
    }
 
    @Test
    void shouldSaveOrderSuccessfully() {
 
        when(traderClient.getTraderById(1L)).thenReturn(new Object());
        when(assetClient.getAssetById(1L)).thenReturn(new Object());
        when(mapper.toEntity(requestDTO)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(mapper.toResponseDTO(order)).thenReturn(responseDTO);
 
        TradeOrderResponseDTO result = service.saveOrder(requestDTO);
 
        assertNotNull(result);
        assertEquals("BUY", result.getOrderType());
        verify(repository, times(1)).save(order);
    }
}
 