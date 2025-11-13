package com.diego.crud.service;

import org.springframework.stereotype.Service;
import java.util.*;
import com.diego.crud.interfaces.IMueble;
import com.diego.crud.modelo.*;

@Service
public class MuebleService {
    private final IMueble repo;

    public MuebleService(IMueble repo) {
        this.repo = repo;
    }

    public List<Mueble> listar(){ return (List<Mueble>) repo.findAll(); }
    public Optional<Mueble> porId(Long id){ return repo.findById(id); }
    public Mueble guardar(Mueble m){ return repo.save(m); }
    public void desactivar(Long id){
        repo.findById(id).ifPresent(m -> { m.setEstado(EstadoMueble.INACTIVO); repo.save(m); });
    }
    public void eliminar(Long id){ repo.deleteById(id); }
}