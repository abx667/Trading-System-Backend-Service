package com.cognizant.tradingcompany.service;
 
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
 
import java.util.List;


import com.cognizant.tradingcompany.dto.TradingCompanyDTO;
import com.cognizant.tradingcompany.entity.TradingCompany;
import com.cognizant.tradingcompany.exception.DuplicateResourceException;
import com.cognizant.tradingcompany.exception.ResourceNotFoundException;
import com.cognizant.tradingcompany.mapper.TradingCompanyMapper;
import com.cognizant.tradingcompany.repositories.TradingCompanyRepository;

 
@Service
@RequiredArgsConstructor
public class TradingCompanyServiceImpl implements TradingCompanyService {
 
    private final TradingCompanyRepository repository;
 
    @Override
    public TradingCompanyDTO saveCompany(TradingCompanyDTO dto) {
    	
    		if(repository.existsByName(dto.getName())) {
        	    throw new DuplicateResourceException(
        	            "Trading company already exists with name: " + dto.getName());
        	}
            TradingCompany entity=TradingCompanyMapper.toEntity(dto);
            TradingCompany savedEntity=repository.save(entity);
            return TradingCompanyMapper.toDTO(savedEntity);
    	
    	
    	
    }
 
    @Override
    public List<TradingCompanyDTO> getAllCompanies() {
        return repository.findAll().stream().map(TradingCompanyMapper::toDTO).toList();
    }
 
    @Override
	public TradingCompanyDTO getCompanyById(Long id) {
		TradingCompany entity = repository.findById(id)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Trading company not found with id: " + id));
	 
	    return TradingCompanyMapper.toDTO(entity);
	}
    
    @Override
	public TradingCompanyDTO updateCompany(Long id, TradingCompanyDTO dto) {
    	TradingCompany existingCompany = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trading Company not found with id: " + id));
     
        // Update fields
        existingCompany.setName(dto.getName());
        existingCompany.setLicenseNumber(dto.getLicenseNumber());
        existingCompany.setContactEmail(dto.getContactEmail());
        existingCompany.setAddress(dto.getAddress());
     
        TradingCompany updatedCompany = repository.save(existingCompany);
     
        return TradingCompanyMapper.toDTO(updatedCompany);
	}
 
    @Override
    public void deleteCompanyDTO(Long id) {
    	if(!repository.existsById(id)) {
    	    throw new ResourceNotFoundException(
    	            "Trading company not found with id: " + id);
    	}
    	repository.deleteById(id);
    }

	

	

	
}