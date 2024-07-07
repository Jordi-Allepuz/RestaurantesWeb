package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteServiceIntf {

    List<Restaurante> findAll();

    Optional<Restaurante> findById(Long id);

    List<Restaurante> findAllFiltered(Long estadoRestaurante, Long estiloRestaurante, String nombre);

    Restaurante save(Restaurante restaurante);

    Optional<Restaurante> update(Long id, Restaurante restaurante);

    void deleteById(Long id);
}
