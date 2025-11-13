package com.diego.crud.precios;

import java.math.BigDecimal;
import com.diego.crud.modelo.Mueble;
import com.diego.crud.modelo.Variante;

public class NormalPricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calcularPrecio(Mueble mueble, Variante variante) {
        return mueble.getPrecioBase();
    }
}