package com.diego.crud.precios;

import java.math.BigDecimal;
import com.diego.crud.modelo.Mueble;
import com.diego.crud.modelo.Variante;

public interface PricingStrategy {
    BigDecimal calcularPrecio(Mueble mueble, Variante variante);
}