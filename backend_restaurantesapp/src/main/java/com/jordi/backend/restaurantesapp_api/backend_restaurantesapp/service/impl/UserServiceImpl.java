package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.UserDto;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.mapper.DtoMapperUser;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Role;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.request.UserRequest;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.RoleRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.UserRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.UserServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserServiceIntf {

    @Autowired
    private UserRepository usuarioRepository;

    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = (List<User>) usuarioRepository.findAll();
        return users
                .stream()
                .map(u -> DtoMapperUser.builder().setUser(u).build())
                .collect(Collectors.toList());
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findById(Long id) {
        return usuarioRepository.findById(id).map(u -> DtoMapperUser
                .builder()
                .setUser(u)
                .build());

    }


    // @Override
    // @Transactional
    // public UserDto save(User user) {
    //     user.setPassword(passwordEncoder.encode(user.getPassword()));
    //     user.setRoles(getRoles(user));
    //     return DtoMapperUser.builder().setUser(repository.save(user)).build();
    // }

    @Override
    @Transactional
    public UserDto save(User usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Optional<Role> o = roleRepository.findByName("ROLE_USER");

        List<Role> roles = new ArrayList<>();
        if (o.isPresent()) {
            roles.add(o.orElseThrow());
        }
        usuario.setRoles(roles);

        return DtoMapperUser.builder().setUser(usuarioRepository.save(usuario)).build();
    }

    @Override
    @Transactional
    public Optional<UserDto> update(Long id, UserRequest user ) {
        Optional<User> o = usuarioRepository.findById(id);
        User userOptional = null;
        if (o.isPresent()) {
            User userDb = o.orElseThrow();  
            userDb.setUsername(user.getUsername());
            userDb.setEmail(user.getEmail());
            userOptional = usuarioRepository.save(userDb);
        }
        return Optional.ofNullable(DtoMapperUser.builder().setUser(userOptional).build());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    // private List<Role> getRoles(IUser user) {
    //     Optional<Role> ou = roleRepository.findByName("ROLE_USER");

    //     List<Role> roles = new ArrayList<>();
    //     if (ou.isPresent()) {
    //         roles.add(ou.orElseThrow());
    //     }

    //     if (user.isAdmin()) {
    //         Optional<Role> oa = roleRepository.findByName("ROLE_ADMIN");
    //         if (oa.isPresent()) {
    //             roles.add(oa.orElseThrow());
    //         }
    //     }
    //     return roles;
    // }

}
