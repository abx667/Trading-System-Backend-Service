package com.cognizant.traderservice.mapper;
 
import com.cognizant.traderservice.dto.*;
import com.cognizant.traderservice.entity.Trader;
import org.springframework.stereotype.Component;
 
@Component
public class TraderMapper {
 
    public Trader toEntity(TraderRequestDTO dto) {
        return Trader.builder()
                .tradingCompanyId(dto.getTradingCompanyId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }
 
    public TraderResponseDTO toResponse(Trader trader) {
        return TraderResponseDTO.builder()
                .id(trader.getId())
                .tradingCompanyId(trader.getTradingCompanyId())
                .firstName(trader.getFirstName())
                .lastName(trader.getLastName())
                .email(trader.getEmail())
                .phone(trader.getPhone())
                .createdAt(trader.getCreatedAt())
                .build();
    }
}