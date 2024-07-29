package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoServicio;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoServicioRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoServicioServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoServicioServiceImpl implements TipoServicioServiceIntf {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> findAll() {
        return (List<TipoServicio>) tipoServicioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoServicio> findById(Long id) {
        return  tipoServicioRepository.findById(id);
    }


}
