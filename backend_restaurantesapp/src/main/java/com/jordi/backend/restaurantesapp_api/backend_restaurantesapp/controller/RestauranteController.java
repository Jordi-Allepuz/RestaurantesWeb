package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.dto.UserDto;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.RestauranteServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController implements WebMvcConfigurer {

    @Autowired
    RestauranteServiceIntf restauranteService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }


//    @GetMapping
//    public List<Restaurante> findAll() {
//        return restauranteService.findAll();
//    }

    // @GetMapping("/page/{page}")
    // public Page<Restaurante> list(@PathVariable Integer page) {
    //     Pageable pageable = PageRequest.of(page, 6);
    //     return restauranteService.findAll(pageable);
    // }

    // @GetMapping
    // public ResponseEntity<List<Restaurante>> findAll(
    //         @RequestParam(required = false) Long estadoRestaurante,
    //         @RequestParam(required = false) Long estiloRestaurante,
    //         @RequestParam(required = false) String nombre) {
    //     List<Restaurante> restaurantes = restauranteService.findAllFiltered(estadoRestaurante, estiloRestaurante, nombre);
    //     return ResponseEntity.ok(restaurantes);
    // }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<Restaurante>> findAll(
            @RequestParam(required = false) Long estadoRestaurante,
            @RequestParam(required = false) Long estiloRestaurante,
            @RequestParam(required = false) String nombre,
            @PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Restaurante> restaurantes = restauranteService.findAllFiltered(estadoRestaurante, estiloRestaurante, nombre, pageable);
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.findById(id);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> save( @RequestBody Restaurante restaurante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.save(restaurante));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Optional<Restaurante> restauranteOptional = restauranteService.update(id, restaurante);
        if (restauranteOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteOptional.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante no encontrado");
        }
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Restaurante> restauranteOptional = restauranteService.findById(id);
        if (restauranteOptional.isPresent()) {
            restauranteService.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteOptional.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante no encontrado");
        }
    }

}
