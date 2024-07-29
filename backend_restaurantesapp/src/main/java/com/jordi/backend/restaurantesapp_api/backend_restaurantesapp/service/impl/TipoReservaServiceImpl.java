package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoReserva;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoReservaRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoReservaServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoReservaServiceImpl implements TipoReservaServiceIntf {

    @Autowired
    private TipoReservaRepository tipoReservaRepository;

    @Override
    public List<TipoReserva> findAll() {
        return (List<TipoReserva>) tipoReservaRepository.findAll();
    }

    @Override
    public Optional<TipoReserva> findById(Long id) {
        return tipoReservaRepository.findById(id);
    }
}
