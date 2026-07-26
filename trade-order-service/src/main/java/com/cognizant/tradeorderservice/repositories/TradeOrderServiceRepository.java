package com.cognizant.tradeorderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.tradeorderservice.entity.TradeOrder;


public interface TradeOrderServiceRepository extends JpaRepository<TradeOrder, Long>{
	
   
}
