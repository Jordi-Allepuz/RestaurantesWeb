package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("SELECT r FROM Restaurante r WHERE r.estadoRestaurante = :estado AND r.estiloRestaurante = :estilo AND r.nombre LIKE %:nombre%")
    List<Restaurante> findAllFiltered(@Param("estado") Long estadoRestaurante, @Param("estilo") Long estiloRestaurante, @Param("nombre") String nombre);

    List<Restaurante> findAllByEstadoRestaurante(Long estadoRestaurante);

    List<Restaurante> findAllByEstiloRestaurante(Long estiloRestaurante);

    List<Restaurante> findAllByNombreContaining(String nombre);

}
