package com.diego.crud.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.diego.crud.modelo.Mueble;
import com.diego.crud.modelo.Variante;
import com.diego.crud.precios.PricingStrategyFactory;

@Service
public class PrecioService {
    private final PricingStrategyFactory factory;

    public PrecioService(PricingStrategyFactory factory) {
        this.factory = factory;
    }

    public BigDecimal precioUnitario(Mueble m, Variante v) {
        return factory.getStrategy(v).calcularPrecio(m, v);
    }
}