package com.diego.crud.controler;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.diego.crud.service.CotizacionService;
import com.diego.crud.modelo.Cotizacion;
import com.diego.crud.service.CotizacionService.ItemRequest;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {
    private final CotizacionService service;

    public CotizacionController(CotizacionService service) {
        this.service = service;
    }

    @PostMapping
    public Cotizacion crear(@RequestBody List<ItemRequest> items) {
        return service.crearDraft(items);
    }

    @PostMapping("/{id}/confirmar")
    public Cotizacion confirmar(@PathVariable Long id) {
        return service.confirmarVenta(id);
    }
}