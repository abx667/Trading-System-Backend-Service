package com.cognizant.traderservice.entity;
 
import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDateTime;
 
@Entity
@Table(name = "trader")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trader {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "trading_company_id", nullable = false)
    private Long tradingCompanyId;
 
    @Column(name = "first_name", nullable = false)
    private String firstName;
 
    @Column(name = "last_name", nullable = false)
    private String lastName;
 
    @Column(nullable = false, unique = true)
    private String email;
 
    @Column(nullable = false)
    private String phone;
 
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
 
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}