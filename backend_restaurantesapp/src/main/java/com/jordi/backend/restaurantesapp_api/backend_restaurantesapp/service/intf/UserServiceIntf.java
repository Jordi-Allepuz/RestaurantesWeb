package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.UserDto;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.request.UserRequest;


import java.util.List;
import java.util.Optional;

public interface UserServiceIntf {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    UserDto save(User usuario);

    Optional<UserDto> update(Long id, UserRequest usuarioRequest);

    void deleteById(Long id);


}
