package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.EstadoRestaurante;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.EstadoRestauranteServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estadosrestaurantes")
public class EstadoRestauranteController implements WebMvcConfigurer {

    @Autowired
    EstadoRestauranteServiceIntf estadoRestauranteService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @GetMapping
    public List<EstadoRestaurante> findAll() {
        return estadoRestauranteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  findById(Long id) {
        Optional<EstadoRestaurante> estadoRestaurante = estadoRestauranteService.findById(id);
        if (estadoRestaurante.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(estadoRestaurante.orElseThrow());
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado de restaurante no encontrado");
        }
    }
}
