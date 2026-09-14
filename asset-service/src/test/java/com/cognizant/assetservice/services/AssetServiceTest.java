package com.cognizant.assetservice.services;
 
import com.cognizant.assetservice.dto.AssetServiceDTO;
import com.cognizant.assetservice.entity.Asset;

import com.cognizant.assetservice.repositories.AssetServiceRepository;
import com.cognizant.assetservice.service.AssetService;
import com.cognizant.assetservice.service.AssetServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
class AssetServiceTest {
 
    @Mock
    private AssetServiceRepository repository;
 
    @InjectMocks
    private AssetServiceImpl service;
 
    private Asset asset;
    private AssetServiceDTO dto;
 
    @BeforeEach
    void setup() {
        asset = Asset.builder()
                .id(1L)
                .name("Gold")
                .symbol("GLD")
                .type("Commodity")
                .currentPrice(new BigDecimal("50000"))
                .listedSince(LocalDate.now())
                .build();
 
        dto = AssetServiceDTO.builder()
                .name("Gold")
                .symbol("GLD")
                .type("Commodity")
                .currentPrice(new BigDecimal("50000"))
                .listedSince(LocalDate.now())
                .build();
    }
 
    
    // CREATE TEST
    
    @Test
    void shouldSaveAsset() {
 
        when(repository.save(any())).thenReturn(asset);
 
        AssetServiceDTO saved = service.saveAsset(dto);
 
        assertNotNull(saved);
        assertEquals("Gold", saved.getName());
        verify(repository, times(1)).save(any());
    }
 
    
    // GET BY ID TEST
    
    @Test
    void shouldReturnAssetById() {
 
        when(repository.findById(1L)).thenReturn(Optional.of(asset));
 
        AssetServiceDTO result = service.getAssetById(1L);
 
        assertNotNull(result);
        assertEquals("Gold", result.getName());
        verify(repository, times(1)).findById(1L);
    }
 
    
    // GET ALL TEST
    
    @Test
    void shouldReturnAllAssets() {
 
        when(repository.findAll()).thenReturn(List.of(asset));
 
        List<AssetServiceDTO> list = service.getAllAssets();
 
        assertEquals(1, list.size());
        verify(repository, times(1)).findAll();
    }
 
    
    // UPDATE TEST
    
    @Test
    void shouldUpdateAsset() {
 
        when(repository.findById(1L)).thenReturn(Optional.of(asset));
        when(repository.save(any())).thenReturn(asset);
 
        AssetServiceDTO updated = service.updateAsset(1L, dto);
 
        assertNotNull(updated);
        assertEquals("Gold", updated.getName());
        verify(repository, times(1)).save(any());
    }
 
    
    // DELETE TEST
    
    @Test
    void shouldDeleteAsset() {
 
        when(repository.existsById(1L)).thenReturn(true);
 
        service.deleteAssetDTO(1L);
 
        verify(repository, times(1)).deleteById(1L);
    }
}