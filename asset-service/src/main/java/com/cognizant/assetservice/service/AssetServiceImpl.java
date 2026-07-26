package com.cognizant.assetservice.service;
 
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
 
import java.util.List;
import java.util.Optional;

import com.cognizant.assetservice.dto.AssetServiceDTO;

import com.cognizant.assetservice.entity.Asset;
import com.cognizant.assetservice.exception.DuplicateResourceException;
import com.cognizant.assetservice.exception.ResourceNotFoundException;
import com.cognizant.assetservice.mapper.AssetServiceMapper;

import com.cognizant.assetservice.repositories.AssetServiceRepository;


@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
	
	private final AssetServiceRepository repository;
	@Override
	public AssetServiceDTO saveAsset(AssetServiceDTO dto) {
		if(repository.existsBySymbol(dto.getSymbol())) {
		    throw new DuplicateResourceException(
		            "Asset already exists with symbol: " + dto.getSymbol());
		}
		Asset entity=AssetServiceMapper.toEntity(dto);
        Asset savedEntity=repository.save(entity);
        return AssetServiceMapper.toDTO(savedEntity);
	}

	@Override
	public List<AssetServiceDTO> getAllAssets() {
		return repository.findAll().stream().map(AssetServiceMapper::toDTO).toList();
	}

	@Override
	public AssetServiceDTO getAssetById(Long id) {
		Asset entity = repository.findById(id)
        .orElseThrow(() ->
                new ResourceNotFoundException("Asset not found with id: " + id));
	 
	    return AssetServiceMapper.toDTO(entity);
	}

	@Override
	public AssetServiceDTO updateAsset(Long id, AssetServiceDTO dto) {
		Asset existingAsset = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
     
        // Update fields
        existingAsset.setName(dto.getName());
        existingAsset.setSymbol(dto.getSymbol());
        existingAsset.setType(dto.getType());
        existingAsset.setCurrentPrice(dto.getCurrentPrice());
     
        Asset updatedAsset = repository.save(existingAsset);
     
        return AssetServiceMapper.toDTO(updatedAsset);
	}

	@Override
	public void deleteAssetDTO(Long id) {
		if(!repository.existsById(id)) {
		    throw new ResourceNotFoundException("Asset not found with id: " + id);
		}
		repository.deleteById(id);
		
	}
 
    
}

	

	

	
