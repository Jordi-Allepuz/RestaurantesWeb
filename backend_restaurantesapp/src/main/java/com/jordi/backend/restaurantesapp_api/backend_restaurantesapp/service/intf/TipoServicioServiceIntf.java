package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoServicio;

import java.util.List;
import java.util.Optional;

public interface TipoServicioServiceIntf {

    List<TipoServicio> findAll();

    Optional<TipoServicio> findById(Long id);
}
