package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
        Optional<Role> findByName(String name);
}