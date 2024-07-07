package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.EstiloRestaurante;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.EstiloRestauranteRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.EstiloRestauranteServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstiloRestauranteImpl implements EstiloRestauranteServiceIntf {

    @Autowired
    private EstiloRestauranteRepository estiloRestauranteRepository;


    @Override
    public List<EstiloRestaurante> findAll() {
        return (List<EstiloRestaurante>) estiloRestauranteRepository.findAll();
    }

    @Override
    public Optional<EstiloRestaurante> findById(Long id) {
        return estiloRestauranteRepository.findById(id);
    }
}
