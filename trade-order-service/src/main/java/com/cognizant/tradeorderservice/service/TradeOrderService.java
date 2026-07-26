package com.cognizant.tradeorderservice.service;
 
import java.util.List;

import com.cognizant.tradeorderservice.dto.TradeOrderRequestDTO;
import com.cognizant.tradeorderservice.dto.TradeOrderResponseDTO;


 
public interface TradeOrderService {
 
    TradeOrderResponseDTO saveOrder(TradeOrderRequestDTO dto);
 
    List<TradeOrderResponseDTO> getAllOrders();
 
    TradeOrderResponseDTO getOrderById(Long id);
    
    TradeOrderResponseDTO updateOrder(Long id, TradeOrderRequestDTO dto);
 
    void deleteOrderDTO(Long id);
}