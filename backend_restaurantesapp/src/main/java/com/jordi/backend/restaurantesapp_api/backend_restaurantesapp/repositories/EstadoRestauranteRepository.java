package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.EstadoRestaurante;
import org.springframework.data.repository.CrudRepository;

public interface EstadoRestauranteRepository  extends CrudRepository<EstadoRestaurante, Long> {
}
