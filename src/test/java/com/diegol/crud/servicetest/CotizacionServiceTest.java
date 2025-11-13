package com.diegol.crud.servicetest;

import com.diego.crud.interfaces.*;
import com.diego.crud.modelo.*;
import com.diego.crud.service.CotizacionService;
import com.diego.crud.service.PrecioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CotizacionServiceTest {

    @Mock private ICotizacion cotRepo;
    @Mock private ICotizacionItem itemRepo;
    @Mock private IMueble muebleRepo;
    @Mock private IVariante varianteRepo;
    @Mock private PrecioService precioService;

    @InjectMocks private CotizacionService service;

    private Cotizacion cotizacion;
    private Mueble mueble;
    private CotizacionItem item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear mueble sin stock
        mueble = new Mueble();
        mueble.setId(1L);
        mueble.setNombre("Mesa de Roble");
        mueble.setStock(0); // SIN STOCK

        // Crear cotización con 1 item
        item = new CotizacionItem();
        item.setMueble(mueble);
        item.setCantidad(2); // requiere 2 unidades
        item.setSubtotal(BigDecimal.TEN);

        cotizacion = new Cotizacion();
        cotizacion.setId(10L);
        cotizacion.setItems(List.of(item));
        cotizacion.setEstado(EstadoCotizacion.DRAFT);
    }

    @Test
    void testConfirmarVentaSinStockDebeLanzarExcepcion() {
        // Configurar mocks
        when(cotRepo.findById(10L)).thenReturn(Optional.of(cotizacion));

        // Ejecutar y verificar que lanza excepción
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            service.confirmarVenta(10L);
        });

        assertEquals("stock insuficiente", ex.getMessage());

        // Verificar que NO se haya intentado guardar el mueble
        verify(muebleRepo, never()).save(any());
        verify(cotRepo, never()).save(any());
    }
}
