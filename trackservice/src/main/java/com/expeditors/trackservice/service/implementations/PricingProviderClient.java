package com.expeditors.trackservice.service.implementations;

import com.expeditors.trackservice.service.PricingProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Objects;

public class PricingProviderClient implements PricingProvider {

    private static final String PRICING_URL = "http://localhost:10002/pricing";
    private final RestClient restClient;

    public PricingProviderClient() {
        this.restClient = createClientForAddress();
    }

    @Override
    public double getPrice() {

        ResponseEntity<Double> priceResponse = restClient.get()
                .retrieve()
                .toEntity(Double.class);

        var price = priceResponse.getBody();

        if(Objects.isNull(price)) {
            throw new RuntimeException("No price Retrieved");
        }

        return price;
    }

    private RestClient createClientForAddress(){

        return RestClient.builder()
                .baseUrl(PricingProviderClient.PRICING_URL)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
