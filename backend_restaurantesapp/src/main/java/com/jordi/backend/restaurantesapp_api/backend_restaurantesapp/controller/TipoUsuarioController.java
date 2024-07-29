// package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.controller;

// import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.TipoUsuario;
// import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.service.intf.TipoUsuarioServiceIntf;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/tiposusuarios")
// public class TipoUsuarioController {

//     @Autowired
//     TipoUsuarioServiceIntf tipoUsuarioService;

//     @GetMapping
//     public List<TipoUsuario> findAll() {
//         return tipoUsuarioService.findAll();
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<?> findById(@PathVariable Long id) {
//         Optional<TipoUsuario> tipoUsuario = tipoUsuarioService.findById(id);
//         if (tipoUsuario.isPresent()) {
//             return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuario.orElseThrow());
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de usuario no encontrado");
//         }
//     }



// }
