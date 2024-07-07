package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.EstadoRestaurante;

import java.util.List;
import java.util.Optional;

public interface EstadoRestauranteServiceIntf {

    List<EstadoRestaurante> findAll();

    Optional<EstadoRestaurante> findById(Long id);
}
