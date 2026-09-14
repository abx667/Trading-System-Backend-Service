package com.cognizant.traderservice.controller;
 
import com.cognizant.traderservice.dto.*;
import com.cognizant.traderservice.service.TraderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/traders")
@RequiredArgsConstructor
public class TraderController {
 
    private final TraderService traderService;
 
    @PostMapping
    public ResponseEntity<TraderResponseDTO> createTrader(
            @Valid @RequestBody TraderRequestDTO dto) {
        return ResponseEntity.ok(traderService.saveTrader(dto));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TraderResponseDTO> getTrader(@PathVariable Long id) {
        return ResponseEntity.ok(traderService.getTraderById(id));
    }
 
    @GetMapping
    public ResponseEntity<List<TraderResponseDTO>> getAllTraders() {
        return ResponseEntity.ok(traderService.getAllTraders());
        
    }
    
    @PutMapping
    public ResponseEntity<TraderResponseDTO> updateTrader(@PathVariable Long id, @Valid @RequestBody TraderRequestDTO dto){
    	return ResponseEntity.ok(traderService.updateTrader(id,dto));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrader(@PathVariable Long id) {
        traderService.deleteTraderDTO(id);
        return ResponseEntity.noContent().build();
    }
}