package com.cognizant.tradeorderservice.controller;
 
import com.cognizant.tradeorderservice.dto.TradeOrderRequestDTO;
import com.cognizant.tradeorderservice.dto.TradeOrderResponseDTO;
import com.cognizant.tradeorderservice.service.TradeOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class TradeOrderServiceController {
 
    private final TradeOrderService traderService;
 
    @PostMapping
    public ResponseEntity<TradeOrderResponseDTO> createOrder(
            @Valid @RequestBody TradeOrderRequestDTO dto) {
    	System.out.println("Req : "+dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(traderService.saveOrder(dto));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TradeOrderResponseDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(traderService.getOrderById(id));
    }
 
    @GetMapping
    public ResponseEntity<List<TradeOrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(traderService.getAllOrders());
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        traderService.deleteOrderDTO(id);
        return ResponseEntity.noContent().build();
    }
}