package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Usuario;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories.UsuarioRepository;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.UsuarioServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlller {

    @Autowired
    UsuarioServiceIntf usuarioService;

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario.orElseThrow());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario usuario){
        Optional<Usuario> usuarioOptional = usuarioService.update(id, usuario);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioOptional.orElseThrow());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findById(id);
        if(usuario.isPresent()){
            usuarioService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario eliminado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
