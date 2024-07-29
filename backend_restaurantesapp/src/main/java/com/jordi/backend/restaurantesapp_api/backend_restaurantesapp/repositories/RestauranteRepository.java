package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.repositories;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;
import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante> {

    @Query("SELECT r FROM Restaurante r WHERE r.estadoRestaurante.id = :estado AND r.estiloRestaurante.id = :estilo AND r.nombre LIKE %:nombre%")
    List<Restaurante> findAllFiltered(@Param("estado") Long estadoRestaurante, @Param("estilo") Long estiloRestaurante, @Param("nombre") String nombre);

    @Query("SELECT r FROM Restaurante r WHERE r.estadoRestaurante.id = :estado")
    List<Restaurante> findAllByEstadoRestaurante(@Param("estado") Long estadoRestaurante);

    @Query("SELECT r FROM Restaurante r WHERE r.estiloRestaurante.id = :estilo")
    List<Restaurante> findAllByEstiloRestaurante(@Param("estilo") Long estiloRestaurante);

    List<Restaurante> findAllByNombreContaining(String nombre);

    Page<Restaurante> findAll(Pageable pageable);

}
