package com.diego.crud.precios;

import org.springframework.stereotype.Component;
import com.diego.crud.modelo.Variante;

// PricingStrategyFactory.java
@Component
public class PricingStrategyFactory {
    public PricingStrategy getStrategy(Variante v) {
        if (v == null || v.getIncrementoPrecio() == null || v.getIncrementoPrecio().signum() == 0) {
            return new NormalPricingStrategy();
        }
        return new VariantPricingStrategy();
    }
}
