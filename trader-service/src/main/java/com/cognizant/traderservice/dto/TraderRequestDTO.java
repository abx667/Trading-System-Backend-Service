package com.cognizant.traderservice.dto;
 
import jakarta.validation.constraints.*;
import lombok.*;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraderRequestDTO {
 
    @NotNull
    private Long tradingCompanyId;
 
    @NotBlank
    private String firstName;
 
    @NotBlank
    private String lastName;
 
    @Email
    @NotBlank
    private String email;
 
    @NotBlank
    private String phone;
}