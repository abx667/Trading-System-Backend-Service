package com.cognizant.tradingcompany.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="trading_company")
public class TradingCompany {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(nullable=false)
    private String name;
	@Column(name="license_number", nullable=false, unique=true)
    private String licenseNumber;
	
	@Column(name="contact_email", nullable=false)
    private String contactEmail;
	@Column(nullable=false)
	private String address;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
    	createdAt=LocalDateTime.now();
    }
    
    
    
    
}
