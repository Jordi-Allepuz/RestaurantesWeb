package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioServiceIntf {

    List<Comentario> findAll();

    Optional<Comentario> findById(Long id);

    Comentario save(Comentario comentario);

    Optional<Comentario> update(Long id, Comentario comentario);

    void deleteById(Long id);

    Optional<Comentario> findByRestauranteAndUsuario(Long idRestaurante, Long idUsuario);
}
