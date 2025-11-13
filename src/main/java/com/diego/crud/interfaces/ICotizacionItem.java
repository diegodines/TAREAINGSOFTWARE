package com.diego.crud.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.diego.crud.modelo.CotizacionItem;

public interface ICotizacionItem extends CrudRepository<CotizacionItem, Long> {}
