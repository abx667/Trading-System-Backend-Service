package com.cognizant.tradeorderservice.mapper;
 
import com.cognizant.tradeorderservice.dto.*;
import com.cognizant.tradeorderservice.entity.TradeOrder;
import org.springframework.stereotype.Component;
 
@Component
public class TradeOrderMapper {
 
    public TradeOrder toEntity(TradeOrderRequestDTO dto) {
        return TradeOrder.builder()
                .trader_id(dto.getTrader_id())
                .asset_id(dto.getAsset_id())
                .orderType(dto.getOrderType())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
 
    public TradeOrderResponseDTO toResponseDTO(TradeOrder entity) {
        return TradeOrderResponseDTO.builder()
                .id(entity.getId())
                .trader_id(entity.getTrader_id())
                .asset_id(entity.getAsset_id())
                .orderType(entity.getOrderType())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}