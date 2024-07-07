package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Comentario;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.ComentarioRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.ComentarioServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioServiceIntf {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Comentario> findAll() {
        return (List<Comentario>) comentarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }
    @Override
    public Optional<Comentario> findByRestauranteAndUsuario(Long idRestaurante, Long idUsuario) {
        return comentarioRepository.findByIdRestauranteAndIdUsuario(idRestaurante, idUsuario);
    }


    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Optional<Comentario> update(Long id, Comentario comentario) {
        Optional<Comentario> comentarioExistente = comentarioRepository.findById(id);
        Comentario comentarioOptional = null;
        if (comentarioExistente.isPresent()) {
            Comentario comentarioNuevo = comentarioExistente.orElseThrow();
            comentarioNuevo.setIdRestaurante(comentario.getIdRestaurante());
            comentarioNuevo.setIdUsuario(comentario.getIdUsuario());
            comentarioNuevo.setComentario(comentario.getComentario());
            comentarioNuevo.setPuntuacion(comentario.getPuntuacion());
            comentarioNuevo.setAparcar(comentario.getAparcar());
            comentarioNuevo.setPrecio(comentario.getPrecio());
            comentarioNuevo.setCarta(comentario.getCarta());
            comentarioNuevo.setLocalizacion(comentario.getLocalizacion());
            comentarioNuevo.setServicio(comentario.getServicio());
            comentarioNuevo.setMenu(comentario.getMenu());
            comentarioNuevo.setPlatosFavoritos(comentario.getPlatosFavoritos());

            comentarioOptional = comentarioRepository.save(comentarioNuevo);
        }
        return Optional.ofNullable(comentarioOptional);
    }

    @Override
    public void deleteById(Long id) {
        comentarioRepository.deleteById(id);
    }
}
