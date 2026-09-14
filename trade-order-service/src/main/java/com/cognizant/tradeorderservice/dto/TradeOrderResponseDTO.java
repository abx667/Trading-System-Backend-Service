package com.cognizant.tradeorderservice.dto;
 
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeOrderResponseDTO {
 
    private Long id;
    private Long trader_id;
    private Long asset_id;
    private String orderType;
    private BigDecimal quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
}