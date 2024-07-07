package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Comentario;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.ComentarioServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    ComentarioServiceIntf comentarioService;

    @GetMapping
    public List<Comentario> findAll() {
        return comentarioService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.findById(id);
        if (comentario.isPresent()) {
            return ResponseEntity.ok(comentario.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado");
        }
    }
    @GetMapping("/restaurante/{idRestaurante}/usuario/{idUsuario}")
    public ResponseEntity<?> findByRestauranteAndUsuario(@PathVariable Long idRestaurante, @PathVariable Long idUsuario) {
        Optional<Comentario> comentario = comentarioService.findByRestauranteAndUsuario(idRestaurante, idUsuario);
        if (comentario.isPresent()) {
            return ResponseEntity.ok(comentario.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado");
        }
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Comentario comentario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.save(comentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Comentario comentario) {
        Optional<Comentario> comentarioOptional = comentarioService.update(id, comentario);
        if (comentarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(comentarioOptional.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado");
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteById(@PathVariable Long id) {
//        Optional<Comentario> comentario = comentarioService.findById(id);
//        if (comentario.isPresent()) {
//            comentarioService.deleteById(id);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comentario eliminado");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado");
//        }
//    }

}
