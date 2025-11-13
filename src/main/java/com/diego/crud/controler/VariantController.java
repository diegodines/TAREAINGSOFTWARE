package com.diego.crud.controler;

import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;

import com.diego.crud.modelo.Variante;
import com.diego.crud.service.VariantService;

@RestController
@RequestMapping("/api/variantes")
public class VariantController {

    private final VariantService service;
    public VariantController(VariantService service) { this.service = service; }

    @GetMapping
    public List<Variante> listar(@RequestParam(required = false) Long muebleId) {
        return service.listar(Optional.ofNullable(muebleId));
    }

    @GetMapping("/{id}")
    public Variante porId(@PathVariable Long id) { return service.porId(id); }

    // DTOs para claridad
    public record CreateVarianteRequest(Long muebleId, String nombre, BigDecimal incrementoPrecio) {}
    public record UpdateVarianteRequest(Long muebleId, String nombre, BigDecimal incrementoPrecio) {}

    @PostMapping
    public Variante crear(@RequestBody CreateVarianteRequest body) {
        return service.crear(body.muebleId(), body.nombre(), body.incrementoPrecio());
    }

    @PutMapping("/{id}")
    public Variante actualizar(@PathVariable Long id, @RequestBody UpdateVarianteRequest body) {
        return service.actualizar(id, body.muebleId(), body.nombre(), body.incrementoPrecio());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
