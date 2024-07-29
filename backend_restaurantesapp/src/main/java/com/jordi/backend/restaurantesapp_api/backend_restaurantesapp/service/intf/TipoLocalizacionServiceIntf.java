package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoLocalizacion;

import java.util.List;
import java.util.Optional;

public interface TipoLocalizacionServiceIntf {

    List<TipoLocalizacion> findAll();

    Optional<TipoLocalizacion> findById(Long id);
}
