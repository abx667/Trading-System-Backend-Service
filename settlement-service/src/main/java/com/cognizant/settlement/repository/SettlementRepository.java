package com.cognizant.settlement.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.settlement.entity.Settlement;
 
public interface SettlementRepository extends JpaRepository<Settlement, Long> {

	boolean existsByTradeOrderId(Long tradeOrderId);
	
}