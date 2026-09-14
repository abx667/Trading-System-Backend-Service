package com.cognizant.settlement.service;
 
import com.cognizant.settlement.dto.SettlementDTO;
import com.cognizant.settlement.entity.Settlement;
import com.cognizant.settlement.exception.DuplicateResourceException;
import com.cognizant.settlement.exception.ExternalServiceException;
import com.cognizant.settlement.exception.ResourceNotFoundException;
import com.cognizant.settlement.mapper.SettlementMapper;
import com.cognizant.settlement.repository.SettlementRepository;
import com.cognizant.settlement.client.TradeOrderClient;
 
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {
 
    private final SettlementRepository repository;
    private final TradeOrderClient tradeOrderClient;
 
    
    // CREATE
    
    @Override
    public SettlementDTO createSettlement(SettlementDTO dto) {
 
        // Validate Trade Order Exists (Feign call)
        try {
            tradeOrderClient.getOrderById(dto.getTradeOrderId());
        }
        catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException(
                    "Trade Order not found with id: " + dto.getTradeOrderId());
        }
        catch (FeignException ex) {
            throw new ExternalServiceException(
                    "Trade Order Service is unavailable");
        }
 
        // Prevent Duplicate Settlement for same TradeOrder
        if (repository.existsByTradeOrderId(dto.getTradeOrderId())) {
            throw new DuplicateResourceException(
                    "Settlement already exists for Trade Order id: " + dto.getTradeOrderId());
        }
 
        // Map DTO → Entity
        Settlement settlement = SettlementMapper.toEntity(dto);
 
        // Save
        Settlement saved = repository.save(settlement);
 
        return SettlementMapper.toDTO(saved);
    }
 
   
    // GET ALL
   
    @Override
    public List<SettlementDTO> getAllSettlements() {
        return repository.findAll()
                .stream()
                .map(SettlementMapper::toDTO)
                .collect(Collectors.toList());
    }
 
   
    // GET BY ID
    
    @Override
    public SettlementDTO getSettlementById(Long id) {
 
        Settlement settlement = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Settlement not found with id: " + id));
 
        return SettlementMapper.toDTO(settlement);
    }
 
    
    // UPDATE
    
    @Override
    public SettlementDTO updateSettlement(Long id, SettlementDTO dto) {
 
        Settlement existingSettlement = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Settlement not found with id: " + id));
 
        // Optional: Validate trade order again
        try {
            tradeOrderClient.getOrderById(dto.getTradeOrderId());
        }
        catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException(
                    "Trade Order not found with id: " + dto.getTradeOrderId());
        }
        catch (FeignException ex) {
            throw new ExternalServiceException(
                    "Trade Order Service is unavailable");
        }
 
        // Update fields
        existingSettlement.setTradeOrderId(dto.getTradeOrderId());
        existingSettlement.setSettlementDate(dto.getSettlementDate());
        existingSettlement.setAmount(dto.getAmount());
        existingSettlement.setStatus(dto.getStatus());
        existingSettlement.setPaymentReference(dto.getPaymentReference());
 
        Settlement updated = repository.save(existingSettlement);
 
        return SettlementMapper.toDTO(updated);
    }
 
    
    // DELETE
    
    @Override
    public void deleteSettlement(Long id) {
 
        Settlement settlement = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Settlement not found with id: " + id));
 
        repository.delete(settlement);
    }
}