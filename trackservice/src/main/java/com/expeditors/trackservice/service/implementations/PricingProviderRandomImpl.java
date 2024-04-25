package com.expeditors.trackservice.service.implementations;

import com.expeditors.trackservice.service.PricingProvider;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;


public class PricingProviderRandomImpl
        implements PricingProvider {

    @Override
    public double getPrice() {
        return ThreadLocalRandom.current().nextDouble(1, 20);
    }
}
