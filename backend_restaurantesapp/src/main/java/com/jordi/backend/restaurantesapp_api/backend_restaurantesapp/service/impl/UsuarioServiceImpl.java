package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.impl;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Usuario;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.UsuarioRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.UsuarioServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioServiceIntf {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        Usuario usuarioOptional = null;
        if (usuarioExistente.isPresent()) {
            Usuario usuarioNuevo = usuarioExistente.orElseThrow();
            usuarioNuevo.setNombre(usuario.getNombre());
            usuarioNuevo.setApellido(usuario.getApellido());
            usuarioNuevo.setEmail(usuario.getEmail());
            usuarioNuevo.setPassword(usuario.getPassword());
            usuarioNuevo.setTipoUsuario(usuario.getTipoUsuario());
            usuarioNuevo.setActivo(usuario.getActivo());

            usuarioOptional = usuarioRepository.save(usuarioNuevo);
        }
        return Optional.ofNullable(usuarioOptional);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
