package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoReserva;

import java.util.List;
import java.util.Optional;

public interface TipoReservaServiceIntf {

    List<TipoReserva> findAll();

    Optional<TipoReserva> findById(Long id);
}
