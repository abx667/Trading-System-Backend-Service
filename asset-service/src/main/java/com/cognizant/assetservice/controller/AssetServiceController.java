package com.cognizant.assetservice.controller;
 
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;

import com.cognizant.assetservice.dto.AssetServiceDTO;
import com.cognizant.assetservice.service.AssetService;

import jakarta.validation.Valid;

 
@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetServiceController {
 
    private final AssetService service;
 
    // CREATE
    @PostMapping
    public ResponseEntity<AssetServiceDTO> createAsset(@Valid @RequestBody AssetServiceDTO dto) {
        AssetServiceDTO savedAsset = service.saveAsset(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAsset);
    }
 
    // GET ALL
    @GetMapping
    public ResponseEntity<List<AssetServiceDTO>> getAllAssets() {

        return ResponseEntity.ok(service.getAllAssets());
    }
 
    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AssetServiceDTO> getAssetById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(service.getAssetById(id));
    }
 
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AssetServiceDTO> updateAsset(@Valid
            @PathVariable Long id,
            @RequestBody AssetServiceDTO dto) {
        return ResponseEntity.ok(service.updateAsset(id, dto));
    }
 
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@Valid @PathVariable Long id) {
        service.deleteAssetDTO(id);
        return ResponseEntity.noContent().build();   // HTTP 204
    }
}
