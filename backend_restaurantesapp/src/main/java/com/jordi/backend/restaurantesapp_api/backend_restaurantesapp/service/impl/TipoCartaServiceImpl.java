package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoCarta;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoCartaRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoCartaServiceIntf;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCartaServiceImpl implements TipoCartaServiceIntf {

    @Autowired
    private TipoCartaRepository tipoCartaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoCarta> findAll() {
        return (List<TipoCarta>) tipoCartaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoCarta> findById(Long id) {
        return  tipoCartaRepository.findById(id);
    }


}
