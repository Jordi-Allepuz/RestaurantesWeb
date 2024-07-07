package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoAparcar;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoAparcarServiceIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tiposaparcar")
public class TipoAparcarController implements WebMvcConfigurer {

    @Autowired
    private TipoAparcarServiceIntf tipoAparcarService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
    }


    @GetMapping
    public List<TipoAparcar> findAll() {
        return tipoAparcarService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<TipoAparcar> tipoAparcar = tipoAparcarService.findById(id);
        if (tipoAparcar.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoAparcar.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TipoAparcar no encontrado");
        }
    }
}
