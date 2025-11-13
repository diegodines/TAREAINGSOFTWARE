package com.diego.crud.controler;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.diego.crud.modelo.Mueble;
import com.diego.crud.service.MuebleService;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {
    private final MuebleService service;

    public MuebleController(MuebleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Mueble> listar(){ return service.listar(); }

    @GetMapping("/{id}")
    public Mueble porId(@PathVariable Long id){ return service.porId(id).orElseThrow(); }

    @PostMapping
    public Mueble crear(@RequestBody Mueble m){ return service.guardar(m); }

    @PutMapping("/{id}")
    public Mueble actualizar(@PathVariable Long id, @RequestBody Mueble m){
        m.setId(id);
        return service.guardar(m);
    }

    @PostMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id){ service.desactivar(id); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){ service.eliminar(id); }
}