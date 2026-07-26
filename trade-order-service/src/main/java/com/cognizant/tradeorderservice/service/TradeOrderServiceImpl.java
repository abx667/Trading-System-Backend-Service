package com.cognizant.tradeorderservice.service;
 
import com.cognizant.tradeorderservice.dto.TradeOrderRequestDTO;
import com.cognizant.tradeorderservice.dto.TradeOrderResponseDTO;
import com.cognizant.tradeorderservice.entity.TradeOrder;
import com.cognizant.tradeorderservice.exception.ExternalServiceException;
import com.cognizant.tradeorderservice.exception.ResourceNotFoundException;
import com.cognizant.tradeorderservice.mapper.TradeOrderMapper;
import com.cognizant.tradeorderservice.repositories.TradeOrderServiceRepository;
import com.cognizant.tradeorderservice.client.AssetClient;
import com.cognizant.tradeorderservice.client.TraderClient;
 
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
@RequiredArgsConstructor
public class TradeOrderServiceImpl implements TradeOrderService {
 
    private final TradeOrderServiceRepository repository;
    private final TradeOrderMapper tradeOrderMapper;
    private final TraderClient traderClient;
    private final AssetClient assetClient;
 
    @Override
    public TradeOrderResponseDTO saveOrder(TradeOrderRequestDTO dto) {
 
        // Validate Trader Exists (Feign call)
        try {
            traderClient.getTraderById(dto.getTrader_id());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("Trader not found with id: " + dto.getTrader_id());
        } catch (FeignException ex) {
            throw new ExternalServiceException("Trader Service is unavailable");
        }
        
     
        //  Validate Asset Exists
        
        try {
            assetClient.getAssetById(dto.getAsset_id());
        }
        catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException(
                    "Asset not found with id: " + dto.getAsset_id());
        }
        catch (FeignException ex) {
            throw new ExternalServiceException(
                    "Asset Service is unavailable");
        }
 
        // Map DTO → Entity
        TradeOrder tradeOrder = tradeOrderMapper.toEntity(dto);
 
        //Save
        TradeOrder saved = repository.save(tradeOrder);
 
        // Return Response DTO
        return tradeOrderMapper.toResponseDTO(saved);
    }
 
    @Override
    public List<TradeOrderResponseDTO> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(tradeOrderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public TradeOrderResponseDTO getOrderById(Long id) {
        TradeOrder tradeOrder = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with id: " + id));
 
        return tradeOrderMapper.toResponseDTO(tradeOrder);
    }
 
    @Override
    public TradeOrderResponseDTO updateOrder(Long id, TradeOrderRequestDTO dto) {
 
        TradeOrder existingOrder = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with id: " + id));
 
        // Update fields
        existingOrder.setAsset_id(dto.getAsset_id());
        existingOrder.setTrader_id(dto.getTrader_id());
        existingOrder.setOrderType(dto.getOrderType());
        existingOrder.setQuantity(dto.getQuantity());
        existingOrder.setPrice(dto.getPrice());
        existingOrder.setStatus(dto.getStatus());
 
        TradeOrder updated = repository.save(existingOrder);
 
        return tradeOrderMapper.toResponseDTO(updated);
    }
 
    @Override
    public void deleteOrderDTO(Long id) {
 
        TradeOrder tradeOrder = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with id: " + id));
 
        repository.delete(tradeOrder);
    }
}
 