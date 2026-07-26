package com.cognizant.assetservice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="asset")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Asset {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(nullable=false)
    private String name;
	@Column(nullable=false, unique=true)
    private String symbol;
	@Column(nullable=false)
    private String type;
	@Column(name="current_price",nullable=false,precision=15,scale=2)
	private BigDecimal currentPrice;
    @Column(name="listed_since", nullable=false)
    private LocalDate listedSince;
    
   
    
    
}
