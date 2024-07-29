package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;


import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestauranteServiceIntf {

    // List<Restaurante> findAll();

    Optional<Restaurante> findById(Long id);

    // Page<Restaurante> findAll(Pageable pageable);

    // List<Restaurante> findAllFiltered(Long estadoRestaurante, Long estiloRestaurante, String nombre);

    Page<Restaurante> findAllFiltered(Long estadoRestaurante, Long estiloRestaurante, String nombre, Pageable pageable);

    Restaurante save(Restaurante restaurante);

    Optional<Restaurante> update(Long id, Restaurante restaurante);

    void deleteById(Long id);
}
