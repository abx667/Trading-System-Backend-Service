package com.cognizant.traderservice.client;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.traderservice.config.FeignClientConfig;
 
@FeignClient(name = "trading-company-service", configuration=FeignClientConfig.class)
public interface TradingCompanyClient {
 
    @GetMapping("/api/trading-companies/{id}")
    Object getCompanyById(@PathVariable Long id);
}