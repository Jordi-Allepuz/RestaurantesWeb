package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceIntf {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    Optional<Usuario> update(Long id, Usuario usuario);

    void deleteById(Long id);


}
