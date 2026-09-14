package com.cognizant.settlement.service;
 
import com.cognizant.settlement.dto.SettlementDTO;
 
import java.util.List;
 
public interface SettlementService {
 
    SettlementDTO createSettlement(SettlementDTO dto);
 
    List<SettlementDTO> getAllSettlements();
 
    SettlementDTO getSettlementById(Long id);
 
    SettlementDTO updateSettlement(Long id, SettlementDTO dto);
 
    void deleteSettlement(Long id);
}