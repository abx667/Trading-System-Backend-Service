package com.cognizant.tradingcompany.controller;
 
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
import com.cognizant.tradingcompany.dto.TradingCompanyDTO;
import com.cognizant.tradingcompany.service.TradingCompanyService;
 
@RestController
@RequestMapping("/api/trading-companies")
@RequiredArgsConstructor
public class TradingCompanyController {
 
    private final TradingCompanyService service;
 
    // CREATE
    @PostMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_admin')")
    
    public ResponseEntity<TradingCompanyDTO> createCompany(@RequestBody TradingCompanyDTO dto) {
        TradingCompanyDTO savedCompany = service.saveCompany(dto);
        return ResponseEntity.ok(savedCompany);
    }
 
    // GET ALL
    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_admin','SCOPE_trader')")
    
    public ResponseEntity<List<TradingCompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(service.getAllCompanies());
    }
 
    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_admin','SCOPE_trader')")
    public ResponseEntity<TradingCompanyDTO> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCompanyById(id));
    }
 
    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_admin')")
    public ResponseEntity<TradingCompanyDTO> updateCompany(
            @PathVariable Long id,
            @RequestBody TradingCompanyDTO dto) {
        return ResponseEntity.ok(service.updateCompany(id, dto));
    }
 
    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_admin')")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        service.deleteCompanyDTO(id);
        return ResponseEntity.noContent().build();   // HTTP 204
    }
}
