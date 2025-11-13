package com.diegol.crud.servicetest;

import com.diego.crud.interfaces.IMueble;
import com.diego.crud.modelo.*;
import com.diego.crud.service.MuebleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MuebleServiceTest {

    @Mock
    private IMueble repo;

    @InjectMocks
    private MuebleService service;

    private Mueble muebleActivo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        muebleActivo = new Mueble();
        muebleActivo.setId(1L);
        muebleActivo.setNombre("Silla Gamer");
        muebleActivo.setEstado(EstadoMueble.ACTIVO);
    }

    @Test
    void testListar() {
        when(repo.findAll()).thenReturn(List.of(muebleActivo));

        List<Mueble> result = service.listar();

        assertEquals(1, result.size());
        assertEquals("Silla Gamer", result.get(0).getNombre());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testPorId() {
        when(repo.findById(1L)).thenReturn(Optional.of(muebleActivo));

        Optional<Mueble> result = service.porId(1L);

        assertTrue(result.isPresent());
        assertEquals(EstadoMueble.ACTIVO, result.get().getEstado());
        verify(repo).findById(1L);
    }

    @Test
    void testGuardar() {
        when(repo.save(muebleActivo)).thenReturn(muebleActivo);

        Mueble result = service.guardar(muebleActivo);

        assertNotNull(result);
        assertEquals("Silla Gamer", result.getNombre());
        verify(repo).save(muebleActivo);
    }

    @Test
    void testDesactivar() {
        when(repo.findById(1L)).thenReturn(Optional.of(muebleActivo));

        service.desactivar(1L);

        assertEquals(EstadoMueble.INACTIVO, muebleActivo.getEstado());
        verify(repo).save(muebleActivo);
    }

    @Test
    void testEliminar() {
        service.eliminar(1L);

        verify(repo).deleteById(1L);
    }
}
