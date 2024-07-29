package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoAparcar;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoAparcarRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoAparcarServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class TipoAparcarServiceImpl implements TipoAparcarServiceIntf {

    @Autowired
    private TipoAparcarRepository tipoAparcarRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoAparcar> findAll() {
        return (List<TipoAparcar>) tipoAparcarRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAparcar> findById(Long id) {
        return tipoAparcarRepository.findById(id);
    }
}
