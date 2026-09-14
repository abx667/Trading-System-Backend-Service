package com.cognizant.traderservice.repositories;
 
import com.cognizant.traderservice.entity.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface TraderRepository extends JpaRepository<Trader, Long> {

	boolean existsByEmail(String email);
	
}