package com.cognizant.tradeorderservice.client;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
 
@FeignClient(name = "ASSET-SERVICE")
public interface AssetClient {
 
    @GetMapping("/api/assets/{id}")
    Object getAssetById(@PathVariable("id") Long id);
}