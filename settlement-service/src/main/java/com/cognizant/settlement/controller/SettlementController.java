package com.cognizant.settlement.controller;
 
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.cognizant.settlement.dto.SettlementDTO;
import com.cognizant.settlement.service.SettlementService;

import jakarta.validation.Valid;

import java.util.List;
 
@RestController
@RequestMapping("/api/settlements")
@RequiredArgsConstructor
public class SettlementController {
 
    private final SettlementService service;
 
    @PostMapping
    public SettlementDTO create(@RequestBody SettlementDTO dto) {
        return service.createSettlement(dto);
    }
 
    @GetMapping
    public List<SettlementDTO> getAll() {
        return service.getAllSettlements();
    }
 
    @GetMapping("/{id}")
    public SettlementDTO getById(@PathVariable Long id) {
        return service.getSettlementById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SettlementDTO> updateAsset(@Valid
            @PathVariable Long id,
            @RequestBody SettlementDTO dto) {
        return ResponseEntity.ok(service.updateSettlement(id, dto));
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSettlement(id);
    }
}