package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoLocalizacion;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoLocalizacionRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoLocalizacionServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoLocalizacionServiceImpl implements TipoLocalizacionServiceIntf {

    @Autowired
    private TipoLocalizacionRepository tipoLocalizacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoLocalizacion> findAll() {
        return (List<TipoLocalizacion>) tipoLocalizacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoLocalizacion> findById(Long id) {
        return  tipoLocalizacionRepository.findById(id);
    }
}
