package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoMenu;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoMenuServiceIntf;
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
@RequestMapping("/api/tiposmenus")
public class TipoMenuController implements WebMvcConfigurer {

    @Autowired
    private TipoMenuServiceIntf tipoMenuService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @GetMapping
    public List<TipoMenu> findAll() {
        return tipoMenuService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<TipoMenu> tipoMenu = tipoMenuService.findById(id);
        if (tipoMenu.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoMenu.orElseThrow());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TipoMenu no encontrado");
        }
    }
}
