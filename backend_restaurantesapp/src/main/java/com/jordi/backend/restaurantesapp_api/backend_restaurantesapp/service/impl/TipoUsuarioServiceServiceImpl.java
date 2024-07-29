// package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

// import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoUsuario;
// import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoUsuarioRepository;
// import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoUsuarioServiceIntf;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class TipoUsuarioServiceServiceImpl implements TipoUsuarioServiceIntf {

//     @Autowired
//     private TipoUsuarioRepository tipoUsuarioRepository;

//     @Override
//     public List<TipoUsuario> findAll() {
//         return (List<TipoUsuario>) tipoUsuarioRepository.findAll();
//     }

//     @Override
//     public Optional<TipoUsuario> findById(Long id) {
//         return tipoUsuarioRepository.findById(id);
//     }
// }
