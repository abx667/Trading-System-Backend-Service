package com.cognizant.tradingcompany.dto;
 
import lombok.Data;
import java.time.LocalDateTime;
 
@Data
public class TradingCompanyDTO {
 
    private Long id;
    private String name;
    private String licenseNumber;
    private String contactEmail;
    private String address;
    private LocalDateTime createdAt;
}
