package com.cognizant.assetservice.service;
 
import java.util.List;
import java.util.Optional;

import com.cognizant.assetservice.dto.AssetServiceDTO;

 
public interface AssetService {
 
    AssetServiceDTO saveAsset(AssetServiceDTO dto);
 
    List<AssetServiceDTO> getAllAssets();
 
    AssetServiceDTO getAssetById(Long id);
    
    AssetServiceDTO updateAsset(Long id, AssetServiceDTO dto);
 
    void deleteAssetDTO(Long id);
}