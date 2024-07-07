package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.EstiloRestaurante;

import java.util.List;
import java.util.Optional;

public interface EstiloRestauranteServiceIntf {

    List<EstiloRestaurante> findAll();

    Optional<EstiloRestaurante> findById(Long id);

}
