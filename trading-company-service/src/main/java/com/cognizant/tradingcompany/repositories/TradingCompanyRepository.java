package com.cognizant.tradingcompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.tradingcompany.entity.TradingCompany;

public interface TradingCompanyRepository extends JpaRepository<TradingCompany, Long>{
	TradingCompany findByLicenseNumber(String licenseNumber);

	boolean existsByName(String name);
   
}
