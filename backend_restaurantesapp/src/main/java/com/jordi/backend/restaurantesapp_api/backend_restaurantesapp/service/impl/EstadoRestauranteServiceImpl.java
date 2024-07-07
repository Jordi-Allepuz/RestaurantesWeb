package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.EstadoRestaurante;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.EstadoRestauranteRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.EstadoRestauranteServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoRestauranteServiceImpl implements EstadoRestauranteServiceIntf {

    @Autowired
    private EstadoRestauranteRepository estadoRestauranteRepository;

    @Override
    public List<EstadoRestaurante> findAll() {
        return (List<EstadoRestaurante>) estadoRestauranteRepository.findAll();
    }

    @Override
    public Optional<EstadoRestaurante> findById(Long id) {
        return estadoRestauranteRepository.findById(id);
    }



}
