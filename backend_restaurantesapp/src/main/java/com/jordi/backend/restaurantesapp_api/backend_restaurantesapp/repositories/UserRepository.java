package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;

import java.util.Optional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>  { 

        Optional<User> findByUsername(String username);

        @Query("select u from User u where u.username=?1")
        Optional<User> getUserByUsername(String username);

        

}
