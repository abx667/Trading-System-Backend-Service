package com.cognizant.tradingcompany.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.tradingcompany.dto.TradingCompanyDTO;
import com.cognizant.tradingcompany.entity.TradingCompany;
import com.cognizant.tradingcompany.exception.DuplicateResourceException;
import com.cognizant.tradingcompany.exception.ResourceNotFoundException;
import com.cognizant.tradingcompany.repositories.TradingCompanyRepository;

@ExtendWith(MockitoExtension.class)
class TradingCompanyServiceTest {
 
    @Mock
    private TradingCompanyRepository repository;
 
    @InjectMocks
    private TradingCompanyServiceImpl service;
 
    private TradingCompany company;
    private TradingCompanyDTO dto;
 
    @BeforeEach
    void setup() {
        company = new TradingCompany();
        company.setId(1L);
        company.setName("ABC Trading");
        company.setLicenseNumber("LIC123");
        company.setContactEmail("abc@test.com");
        company.setAddress("Mumbai");
 
        dto = new TradingCompanyDTO();
        dto.setId(1L);
        dto.setName("ABC Trading");
        dto.setLicenseNumber("LIC123");
        dto.setContactEmail("abc@test.com");
        dto.setAddress("Mumbai");
    }
 
    @Test
    void shouldSaveCompany() {
        when(repository.existsByName(dto.getName())).thenReturn(false);
        when(repository.save(any())).thenReturn(company);
 
        TradingCompanyDTO result = service.saveCompany(dto);
 
        assertNotNull(result);
        assertEquals("ABC Trading", result.getName());
        verify(repository, times(1)).save(any());
    }
 
    @Test
    void shouldThrowDuplicateException() {
        when(repository.existsByName(dto.getName())).thenReturn(true);
 
        assertThrows(DuplicateResourceException.class,
                () -> service.saveCompany(dto));
    }
 
    @Test
    void shouldReturnCompanyById() {
        when(repository.findById(1L)).thenReturn(Optional.of(company));
 
        TradingCompanyDTO result = service.getCompanyById(1L);
 
        assertEquals("ABC Trading", result.getName());
    }
 
    @Test
    void shouldThrowNotFoundException() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
 
        assertThrows(ResourceNotFoundException.class,
                () -> service.getCompanyById(1L));
    }
 
    @Test
    void shouldDeleteCompany() {
        when(repository.existsById(1L)).thenReturn(true);
 
        service.deleteCompanyDTO(1L);
 
        verify(repository, times(1)).deleteById(1L);
    }
}
