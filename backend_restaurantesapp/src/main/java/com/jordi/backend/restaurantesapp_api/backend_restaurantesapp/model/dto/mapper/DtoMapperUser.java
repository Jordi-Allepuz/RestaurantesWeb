package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.mapper;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.UserDto;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;

public class DtoMapperUser {

    private User usuario;
    
    private DtoMapperUser() {
    }

    public static DtoMapperUser builder() {
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(User usuario) {
        this.usuario = usuario;
        return this;
    }

    public UserDto build() {
        if (usuario == null) {
            throw new RuntimeException("Debe pasar el entity user!");
        }
        return new UserDto(this.usuario.getId(), usuario.getUsername(), usuario.getEmail());
    }
    
}
