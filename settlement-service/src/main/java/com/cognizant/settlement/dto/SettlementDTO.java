package com.cognizant.settlement.dto;
 
import lombok.*;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettlementDTO {
 
    private Long id;
    private Long tradeOrderId;
    private LocalDateTime settlementDate;
    private BigDecimal amount;
    private String status;
    private String paymentReference;
}