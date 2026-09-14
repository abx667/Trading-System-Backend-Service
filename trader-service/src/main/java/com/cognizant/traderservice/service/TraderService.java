package com.cognizant.traderservice.service;
 
import java.util.List;
import java.util.Optional;

import com.cognizant.traderservice.dto.TraderRequestDTO;
import com.cognizant.traderservice.dto.TraderResponseDTO;

public interface TraderService {
 
    TraderResponseDTO saveTrader(TraderRequestDTO dto);
 
    List<TraderResponseDTO> getAllTraders();
 
    TraderResponseDTO getTraderById(Long id);
    
    TraderResponseDTO updateTrader(Long id, TraderRequestDTO dto);
 
    void deleteTraderDTO(Long id);
}