package com.cognizant.traderservice.dto;
 
import lombok.*;
 
import java.time.LocalDateTime;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraderResponseDTO {
 
    private Long id;
    private Long tradingCompanyId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}