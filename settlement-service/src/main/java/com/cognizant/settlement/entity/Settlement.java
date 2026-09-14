package com.cognizant.settlement.entity;
 
import jakarta.persistence.*;
import lombok.*;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
@Entity
@Table(name = "settlement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Settlement {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "trade_order_id", nullable = false)
    private Long tradeOrderId;
 
    @Column(name = "settlement_date", nullable = false)
    private LocalDateTime settlementDate;
 
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
 
    @Column(nullable = false, length = 50)
    private String status;
 
    @Column(name = "payment_reference", nullable = false, length = 100)
    private String paymentReference;
}
 