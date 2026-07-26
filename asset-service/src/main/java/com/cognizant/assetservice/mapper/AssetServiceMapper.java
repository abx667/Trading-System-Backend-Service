package com.cognizant.assetservice.mapper;
 
import com.cognizant.assetservice.dto.AssetServiceDTO;

import com.cognizant.assetservice.entity.Asset;

 
public class AssetServiceMapper {
 
    public static AssetServiceDTO toDTO(Asset entity) {
        AssetServiceDTO dto = new AssetServiceDTO();
        
        dto.setName(entity.getName());
        dto.setSymbol(entity.getSymbol());
        dto.setType(entity.getType());
        dto.setCurrentPrice(entity.getCurrentPrice());
        dto.setListedSince(entity.getListedSince());
        return dto;
    }
 
    public static  Asset toEntity(AssetServiceDTO dto) {
        Asset entity = new Asset();
        entity.setName(dto.getName());
        entity.setSymbol(dto.getSymbol());
        entity.setType(dto.getType());
        entity.setCurrentPrice(dto.getCurrentPrice());
        entity.setListedSince(dto.getListedSince());
        return entity;
    }
}
