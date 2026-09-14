package com.cognizant.settlement.mapper;
 
import com.cognizant.settlement.dto.SettlementDTO;
import com.cognizant.settlement.entity.Settlement;
 
public class SettlementMapper {
 
    public static SettlementDTO toDTO(Settlement settlement) {
        return SettlementDTO.builder()
                .id(settlement.getId())
                .tradeOrderId(settlement.getTradeOrderId())
                .settlementDate(settlement.getSettlementDate())
                .amount(settlement.getAmount())
                .status(settlement.getStatus())
                .paymentReference(settlement.getPaymentReference())
                .build();
    }
 
    public static Settlement toEntity(SettlementDTO dto) {
        return Settlement.builder()
                .id(dto.getId())
                .tradeOrderId(dto.getTradeOrderId())
                .settlementDate(dto.getSettlementDate())
                .amount(dto.getAmount())
                .status(dto.getStatus())
                .paymentReference(dto.getPaymentReference())
                .build();
    }
}