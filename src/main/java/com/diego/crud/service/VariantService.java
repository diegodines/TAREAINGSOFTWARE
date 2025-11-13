    package com.diego.crud.service;

    import org.springframework.stereotype.Service;
    import java.util.*;
    import java.math.BigDecimal;

    import com.diego.crud.interfaces.IMueble;
    import com.diego.crud.interfaces.IVariante;
    import com.diego.crud.modelo.Mueble;
    import com.diego.crud.modelo.Variante;

    @Service
    public class VariantService {
        private final IVariante varianteRepo;
        private final IMueble muebleRepo;

        public VariantService(IVariante varianteRepo, IMueble muebleRepo) {
            this.varianteRepo = varianteRepo;
            this.muebleRepo = muebleRepo;
        }

        public List<Variante> listar(Optional<Long> muebleId) {
            if (muebleId.isPresent()) {
                Long id = muebleId.get();
                // filtro simple en memoria (si creces, crea query en repo)
                List<Variante> out = new ArrayList<>();
                for (Variante v : varianteRepo.findAll()) {
                    if (v.getMueble() != null && id.equals(v.getMueble().getId())) {
                        out.add(v);
                    }
                }
                return out;
            }
            return (List<Variante>) varianteRepo.findAll();
        }

        public Variante porId(Long id) {
            return varianteRepo.findById(id).orElseThrow();
        }

        public Variante crear(Long muebleId, String nombre, BigDecimal incrementoPrecio) {
            Mueble m = muebleRepo.findById(muebleId).orElseThrow();
            Variante v = new Variante();
            v.setMueble(m);
            v.setNombre(nombre);
            v.setIncrementoPrecio(incrementoPrecio);
            return varianteRepo.save(v);
        }

        public Variante actualizar(Long id, Long muebleId, String nombre, BigDecimal incrementoPrecio) {
            Variante v = varianteRepo.findById(id).orElseThrow();
            if (muebleId != null) {
                Mueble m = muebleRepo.findById(muebleId).orElseThrow();
                v.setMueble(m);
            }
            if (nombre != null) v.setNombre(nombre);
            if (incrementoPrecio != null) v.setIncrementoPrecio(incrementoPrecio);
            return varianteRepo.save(v);
        }

        public void eliminar(Long id) {
            varianteRepo.deleteById(id);
        }
    }
