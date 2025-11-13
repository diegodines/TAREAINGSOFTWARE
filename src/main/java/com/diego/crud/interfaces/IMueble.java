package com.diego.crud.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.diego.crud.modelo.Mueble;

public interface IMueble extends CrudRepository<Mueble, Long> {}