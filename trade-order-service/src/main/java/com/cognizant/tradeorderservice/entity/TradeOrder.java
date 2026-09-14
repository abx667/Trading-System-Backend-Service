package com.cognizant.tradeorderservice.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trade_order")
public class TradeOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(nullable=false)
    private Long trader_id;
	@Column(nullable=false)
    private Long asset_id;
	@Column(name="order_type", nullable=false)
    private String orderType;
	@Column(nullable=false)
	private BigDecimal quantity;
    @Column(nullable=false)
    private BigDecimal price;
    @Column(nullable=false)
    private String status;
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
    	createdAt=LocalDateTime.now();
    	this.status="OPEN";
    }
    
    
    
    
}
