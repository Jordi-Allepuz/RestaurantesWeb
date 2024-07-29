package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.RestauranteRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.RestauranteServiceIntf;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.specifications.RestauranteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteServiceImpl implements RestauranteServiceIntf {

    @Autowired
    private RestauranteRepository restauranteRepository;

    // @Override
    // @Transactional(readOnly = true)
    // public List<Restaurante> findAll() {
    // return (List<Restaurante>) restauranteRepository.findAll();
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public Page<Restaurante> findAll(Pageable pageable) {
    // return restauranteRepository
    // .findAll(pageable);
    // }

    // public List<Restaurante> findAllFiltered(Long estadoRestaurante, Long
    // estiloRestaurante, String nombre) {
    // if (estadoRestaurante != null && estiloRestaurante != null && nombre != null)
    // {
    // return restauranteRepository.findAllFiltered(estadoRestaurante,
    // estiloRestaurante, nombre);
    // } else if (estadoRestaurante != null) {
    // return restauranteRepository.findAllByEstadoRestaurante(estadoRestaurante);
    // } else if (estiloRestaurante != null) {
    // return restauranteRepository.findAllByEstiloRestaurante(estiloRestaurante);
    // } else if (nombre != "" && nombre != null) {
    // return restauranteRepository.findAllByNombreContaining(nombre);
    // } else {
    // return restauranteRepository.findAll();
    // }
    // }

    @Override
    @Transactional(readOnly = true)
    public Page<Restaurante> findAllFiltered(Long estadoRestaurante, Long estiloRestaurante, String nombre,
            Pageable pageable) {
        Specification<Restaurante> spec = Specification.where(RestauranteSpecification.hasEstado(estadoRestaurante))
                .and(RestauranteSpecification.hasEstilo(estiloRestaurante))
                .and(RestauranteSpecification.hasNombre(nombre));
        return restauranteRepository.findAll(spec, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Restaurante> findById(Long id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Optional<Restaurante> update(Long id, Restaurante restaurante) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);
        Restaurante restauranteOptional = null;
        if (restauranteExistente.isPresent()) {
            Restaurante restauranteNuevo = restauranteExistente.orElseThrow();
            restauranteNuevo.setNombre(restaurante.getNombre());
            restauranteNuevo.setDireccion(restaurante.getDireccion());
            restauranteNuevo.setLocalidad(restaurante.getLocalidad());
            restauranteNuevo.setTelefono(restaurante.getTelefono());
            restauranteNuevo.setEstiloRestaurante(restaurante.getEstiloRestaurante());
            restauranteNuevo.setReserva(restaurante.getReserva());
            restauranteNuevo.setActivo(restaurante.getActivo());
            restauranteNuevo.setActivo(restaurante.getActivo());
            restauranteNuevo.setImagen(restaurante.getImagen());

            restauranteOptional = restauranteRepository.save(restauranteNuevo);
        }
        return Optional.ofNullable(restauranteOptional);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);
        if (restauranteExistente.isPresent()) {
            Restaurante restauranteDesactivar = restauranteExistente.orElseThrow();
            restauranteDesactivar.setActivo(false);

            restauranteRepository.save(restauranteDesactivar);
        }

    }

}
