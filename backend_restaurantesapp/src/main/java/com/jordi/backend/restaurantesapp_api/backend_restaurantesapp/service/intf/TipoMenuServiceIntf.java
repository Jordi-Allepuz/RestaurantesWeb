package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoMenu;

import java.util.List;
import java.util.Optional;

public interface TipoMenuServiceIntf {

    List<TipoMenu> findAll();

    Optional<TipoMenu> findById(Long id);
}
