package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;


import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoMenu;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.TipoMenuRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoMenuServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMenuServiceImpl implements TipoMenuServiceIntf {


    @Autowired
    private TipoMenuRepository tipoMenuRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoMenu> findAll() {
        return (List<TipoMenu>) tipoMenuRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoMenu> findById(Long id) {
        return  tipoMenuRepository.findById(id);
    }


}
