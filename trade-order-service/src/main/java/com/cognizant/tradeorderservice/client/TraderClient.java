package com.cognizant.tradeorderservice.client;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.cognizant.tradeorderservice.config.FeignClientInterceptor;
 
@FeignClient(name = "TRADER-SERVICE",configuration = FeignClientInterceptor.class)
public interface TraderClient {
 
    @GetMapping("/api/traders/{id}")
    Object getTraderById(@PathVariable("id") Long id);
}