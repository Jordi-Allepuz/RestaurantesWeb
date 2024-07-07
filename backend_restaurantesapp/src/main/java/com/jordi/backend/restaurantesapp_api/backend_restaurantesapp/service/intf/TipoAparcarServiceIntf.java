package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoAparcar;

import java.util.List;
import java.util.Optional;

public interface TipoAparcarServiceIntf {

    List<TipoAparcar> findAll();

    Optional<TipoAparcar> findById(Long id);
}
