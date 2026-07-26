package com.cognizant.tradeorderservice.dto;
 
import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.*;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradeOrderRequestDTO {
 
    @NotNull
    private Long trader_id;
    
    @NotNull
    private Long asset_id;
    
    @NotBlank
    private String orderType;
 
    @NotNull
    private BigDecimal quantity;
 
    
    @NotNull
    private BigDecimal price;
 
    @NotBlank
    private String status;
}