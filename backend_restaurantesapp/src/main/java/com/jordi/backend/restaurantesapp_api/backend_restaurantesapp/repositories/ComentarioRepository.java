package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Comentario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ComentarioRepository extends CrudRepository<Comentario, Long>{


    Optional<Comentario> findByIdRestauranteAndIdUsuario(Long idRestaurante, Long idUsuario);
}
