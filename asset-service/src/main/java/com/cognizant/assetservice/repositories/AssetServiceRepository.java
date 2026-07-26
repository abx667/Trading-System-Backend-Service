package com.cognizant.assetservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.assetservice.entity.Asset;


public interface AssetServiceRepository extends JpaRepository<Asset, Long>{
	Boolean existsBySymbol(String Symbol);
   
}
