package com.cognizant.assetservice.dto;
 
import jakarta.validation.constraints.*;
import lombok.*;
 
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetServiceDTO {
 
    @NotBlank(message = "Name is required")
    private String name;
 
    @NotBlank(message = "Symbol is required")
    @Size(max = 10, message = "Symbol cannot exceed 10 characters")
    private String symbol;
 
    @NotBlank(message = "Type is required")
    private String type;
 
    @NotNull(message = "Current price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal currentPrice;
 
    @NotNull(message = "Listed since date is required")
    @PastOrPresent(message = "Listed date cannot be in future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate listedSince;
}