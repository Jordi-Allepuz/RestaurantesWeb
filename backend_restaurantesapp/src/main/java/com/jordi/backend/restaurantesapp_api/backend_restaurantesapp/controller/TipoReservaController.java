package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoReserva;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoReservaServiceIntf;
import org.apache.coyote.Response;
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
@RequestMapping("/api/tiposreservas")
public class TipoReservaController implements WebMvcConfigurer {

    @Autowired
    TipoReservaServiceIntf tipoReservaService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @GetMapping
    public List<TipoReserva> findAll() {
        return tipoReservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<TipoReserva> tipoReserva = tipoReservaService.findById(id);
        if (tipoReserva.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoReserva.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de reserva no encontrado");
        }
    }


}
