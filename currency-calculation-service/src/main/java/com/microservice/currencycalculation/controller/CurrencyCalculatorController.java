package com.microservice.currencycalculation.controller;

import com.microservice.currencycalculation.model.CalculateAmount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyCalculatorController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CalculateAmount calculateAmount(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CalculateAmount> responseEntity = new RestTemplate().getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}", CalculateAmount.class, uriVariables);
        CalculateAmount calculateAmount = responseEntity.getBody();

        return new CalculateAmount(calculateAmount.getId(), calculateAmount.getFrom(),
                calculateAmount.getTo(), calculateAmount.getConversionMultiple(),
                quantity, quantity.multiply(calculateAmount.getConversionMultiple()),
                calculateAmount.getPort());
    }
}
