package com.diego.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;
import com.diego.crud.interfaces.*;
import com.diego.crud.modelo.*;

@Service
public class CotizacionService {
    private final ICotizacion cotRepo;
    private final ICotizacionItem itemRepo;
    private final IMueble muebleRepo;
    private final IVariante varianteRepo;
    private final PrecioService precioService;

    public CotizacionService(ICotizacion cotRepo, ICotizacionItem itemRepo, IMueble muebleRepo,
                             IVariante varianteRepo, PrecioService precioService) {
        this.cotRepo = cotRepo;
        this.itemRepo = itemRepo;
        this.muebleRepo = muebleRepo;
        this.varianteRepo = varianteRepo;
        this.precioService = precioService;
    }

    @Transactional
    public Cotizacion crearDraft(List<ItemRequest> items) {
        Cotizacion c = new Cotizacion();
        cotRepo.save(c);

        BigDecimal total = BigDecimal.ZERO;
        for (ItemRequest it : items) {
            Mueble m = muebleRepo.findById(it.muebleId()).orElseThrow();
            Variante v = null;
        if (it.varianteId() != null) {
        v = varianteRepo.findById(it.varianteId()).orElseThrow();
        }

        BigDecimal unit = precioService.precioUnitario(m, v); // v puede ser null
        BigDecimal subtotal = unit.multiply(BigDecimal.valueOf(it.cantidad()));

            CotizacionItem item = new CotizacionItem();
            item.setCotizacion(c);
            item.setMueble(m);
            item.setVariante(v);
            item.setCantidad(it.cantidad());
            item.setSubtotal(subtotal);
            itemRepo.save(item);

            total = total.add(subtotal);
        }
        c.setTotal(total);
        return cotRepo.save(c);
    }

    @Transactional
    public Cotizacion confirmarVenta(Long cotizacionId) {
        Cotizacion c = cotRepo.findById(cotizacionId).orElseThrow();
        if (c.getEstado() == EstadoCotizacion.CONFIRMADA) return c;

        for (CotizacionItem it : c.getItems()) {
            Mueble m = it.getMueble();
            if (m.getStock() < it.getCantidad()) {
                throw new IllegalStateException("stock insuficiente");
            }
        }
        for (CotizacionItem it : c.getItems()) {
            Mueble m = it.getMueble();
            m.setStock(m.getStock() - it.getCantidad());
            muebleRepo.save(m);
        }
        c.setEstado(EstadoCotizacion.CONFIRMADA);
        return cotRepo.save(c);
    }

    // DTO de entrada
    // CotizacionService.java
public record ItemRequest(Long muebleId, Long varianteId, int cantidad) {}
// ^^^ Long (puede ser null)
}