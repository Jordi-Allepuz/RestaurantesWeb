package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>  { }
