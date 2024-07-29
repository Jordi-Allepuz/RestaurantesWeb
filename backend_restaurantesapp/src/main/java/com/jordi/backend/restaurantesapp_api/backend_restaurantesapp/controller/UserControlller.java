package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.UserDto;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.request.UserRequest;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.UserServiceIntf;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(originPatterns = "*")
public class UserControlller {

    @Autowired
    UserServiceIntf usuarioService;

    @GetMapping
    public List<UserDto> list() {
        return usuarioService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<UserDto> user = usuarioService.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.orElseThrow());
        }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User usuario, BindingResult result) {
        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserRequest usuario, BindingResult result ) {
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<UserDto> user = usuarioService.update(id, usuario);
        
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user.orElseThrow());
        }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<UserDto> user = usuarioService.findById(id);

        if (user.isPresent()) {
            usuarioService.deleteById(id);
            // return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario eliminado");
            return ResponseEntity.noContent().build(); // 204
        }
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
