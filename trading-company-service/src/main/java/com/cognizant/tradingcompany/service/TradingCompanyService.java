package com.cognizant.tradingcompany.service;
 
import java.util.List;


import com.cognizant.tradingcompany.dto.TradingCompanyDTO;

 
public interface TradingCompanyService {
 
    TradingCompanyDTO saveCompany(TradingCompanyDTO dto);
 
    List<TradingCompanyDTO> getAllCompanies();
 
    TradingCompanyDTO getCompanyById(Long id);
    
    TradingCompanyDTO updateCompany(Long id, TradingCompanyDTO dto);
 
    void deleteCompanyDTO(Long id);
}