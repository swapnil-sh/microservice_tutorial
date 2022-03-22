package com.microservice.currencycalculation.facade;

import com.microservice.currencycalculation.model.CalculateAmount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service", url="http://localhost:8001/")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CalculateAmount retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
