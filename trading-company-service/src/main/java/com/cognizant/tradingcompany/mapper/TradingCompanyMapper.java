package com.cognizant.tradingcompany.mapper;
 
import com.cognizant.tradingcompany.entity.TradingCompany;
import com.cognizant.tradingcompany.dto.TradingCompanyDTO;
 
public class TradingCompanyMapper {
 
    public static TradingCompanyDTO toDTO(TradingCompany entity) {
        TradingCompanyDTO dto = new TradingCompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLicenseNumber(entity.getLicenseNumber());
        dto.setContactEmail(entity.getContactEmail());
        dto.setAddress(entity.getAddress());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
 
    public static TradingCompany toEntity(TradingCompanyDTO dto) {
        TradingCompany entity = new TradingCompany();
        entity.setName(dto.getName());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setContactEmail(dto.getContactEmail());
        entity.setAddress(dto.getAddress());
        return entity;
    }
}
