package com.cognizant.settlement.client;
 
import com.cognizant.settlement.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "trade-order-service",
        configuration = FeignConfig.class
)
public interface TradeOrderClient {

    @GetMapping("/api/orders/{id}")
    Object getOrderById(@PathVariable("id") Long id);
}