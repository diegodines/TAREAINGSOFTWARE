package com.diego.crud.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.diego.crud.modelo.Cotizacion;

public interface ICotizacion extends CrudRepository<Cotizacion, Long> {}
