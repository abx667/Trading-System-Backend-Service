package com.cognizant.traderservice.service;
 
import com.cognizant.traderservice.client.TradingCompanyClient;
import com.cognizant.traderservice.dto.TraderRequestDTO;
import com.cognizant.traderservice.dto.TraderResponseDTO;
import com.cognizant.traderservice.entity.Trader;
import com.cognizant.traderservice.exception.DuplicateResourceException;
import com.cognizant.traderservice.exception.ResourceNotFoundException;
import com.cognizant.traderservice.mapper.TraderMapper;
import com.cognizant.traderservice.repositories.TraderRepository;
import com.cognizant.traderservice.service.TraderServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.time.LocalDateTime;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
class TraderServiceTest {
 
    @Mock
    private TraderRepository traderRepository;
 
    @Mock
    private TraderMapper traderMapper;
 
    @Mock
    private TradingCompanyClient tradingCompanyClient;
 
    @InjectMocks
    private TraderServiceImpl traderService;
 
    private Trader trader;
    private TraderRequestDTO requestDTO;
    private TraderResponseDTO responseDTO;
 
    @BeforeEach
    void setUp() {
        requestDTO = TraderRequestDTO.builder()
                .tradingCompanyId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .phone("9999999999")
                .build();
 
        trader = Trader.builder()
                .id(1L)
                .tradingCompanyId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .phone("9999999999")
                .createdAt(LocalDateTime.now())
                .build();
 
        responseDTO = TraderResponseDTO.builder()
                .id(1L)
                .tradingCompanyId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .phone("9999999999")
                .createdAt(LocalDateTime.now())
                .build();
    }
 
    @Test
    void shouldSaveTrader() {
        when(traderRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(tradingCompanyClient.getCompanyById(1L)).thenReturn(new Object());
        when(traderMapper.toEntity(requestDTO)).thenReturn(trader);
        when(traderRepository.save(trader)).thenReturn(trader);
        when(traderMapper.toResponse(trader)).thenReturn(responseDTO);
 
        TraderResponseDTO result = traderService.saveTrader(requestDTO);
 
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(traderRepository, times(1)).save(trader);
    }
 
    @Test
    void shouldThrowDuplicateException() {
        when(traderRepository.existsByEmail(requestDTO.getEmail())).thenReturn(true);
 
        assertThrows(DuplicateResourceException.class,
                () -> traderService.saveTrader(requestDTO));
    }
 
    @Test
    void shouldReturnTraderById() {
        when(traderRepository.findById(1L)).thenReturn(Optional.of(trader));
        when(traderMapper.toResponse(trader)).thenReturn(responseDTO);
 
        TraderResponseDTO result = traderService.getTraderById(1L);
 
        assertEquals("John", result.getFirstName());
    }
 
    @Test
    void shouldThrowNotFound() {
        when(traderRepository.findById(1L)).thenReturn(Optional.empty());
 
        assertThrows(ResourceNotFoundException.class,
                () -> traderService.getTraderById(1L));
    }
 
    @Test
    void shouldDeleteTrader() {
        when(traderRepository.existsById(1L)).thenReturn(true);
 
        traderService.deleteTraderDTO(1L);
 
        verify(traderRepository).deleteById(1L);
    }
}