package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoUsuario;

import java.util.List;
import java.util.Optional;

public interface TipoUsuarioServiceIntf {

    List<TipoUsuario> findAll();

    Optional<TipoUsuario> findById(Long id);
}
