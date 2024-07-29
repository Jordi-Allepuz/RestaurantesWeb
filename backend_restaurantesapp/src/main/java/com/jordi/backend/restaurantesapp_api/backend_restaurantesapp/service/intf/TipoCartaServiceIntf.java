package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoCarta;

import java.util.List;
import java.util.Optional;

public interface TipoCartaServiceIntf {

    List<TipoCarta> findAll();

    Optional<TipoCarta> findById(Long id);
}
