package com.cognizant.traderservice.service;
 
import com.cognizant.traderservice.client.TradingCompanyClient;
import com.cognizant.traderservice.dto.*;
import com.cognizant.traderservice.entity.Trader;
import com.cognizant.traderservice.exception.DuplicateResourceException;
import com.cognizant.traderservice.exception.ResourceNotFoundException;
import com.cognizant.traderservice.mapper.TraderMapper;
import com.cognizant.traderservice.repositories.TraderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
@RequiredArgsConstructor
public class TraderServiceImpl implements TraderService {
 
    private final TraderRepository traderRepository;
    private final TraderMapper traderMapper;
    private final TradingCompanyClient tradingCompanyClient;
 
    @Override
    public TraderResponseDTO saveTrader(TraderRequestDTO dto) {
 
    	if(traderRepository.existsByEmail(dto.getEmail())) {
    	    throw new DuplicateResourceException(
    	            "Trader already exists with email: " + dto.getEmail());
    	}
        tradingCompanyClient.getCompanyById(dto.getTradingCompanyId());
 
        Trader trader = traderMapper.toEntity(dto);
        Trader saved = traderRepository.save(trader);
 
        return traderMapper.toResponse(saved);
    }
 
    @Override
    public TraderResponseDTO getTraderById(Long id) {
        Trader trader = traderRepository.findById(id)
        .orElseThrow(() ->
                new ResourceNotFoundException(
                        "Trader not found with id: " + id));
 
        return traderMapper.toResponse(trader);
    }
 
    @Override
    public List<TraderResponseDTO> getAllTraders() {
        return traderRepository.findAll()
                .stream()
                .map(traderMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public void deleteTraderDTO(Long id) {
    	if(!traderRepository.existsById(id)) {
    	    throw new ResourceNotFoundException(
    	            "Trading company not found with id: " + id);
    	}
        traderRepository.deleteById(id);
    }

	@Override
	public TraderResponseDTO updateTrader(Long id, TraderRequestDTO dto) {
		 Trader trader = traderRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException(
		                    "Trader not found with id: " + id
		            ));
	 
	    
	    tradingCompanyClient.getCompanyById(dto.getTradingCompanyId());
	 
	    trader.setTradingCompanyId(dto.getTradingCompanyId());
	    trader.setFirstName(dto.getFirstName());
	    trader.setLastName(dto.getLastName());
	    trader.setEmail(dto.getEmail());
	    trader.setPhone(dto.getPhone());
	 
	    Trader updated = traderRepository.save(trader);
	 
	    return traderMapper.toResponse(updated);
	}
}
 