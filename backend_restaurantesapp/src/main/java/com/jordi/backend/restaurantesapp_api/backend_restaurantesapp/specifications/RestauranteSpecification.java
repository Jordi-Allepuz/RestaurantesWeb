package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.specifications;

import com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities.Restaurante;
import org.springframework.data.jpa.domain.Specification;


public class RestauranteSpecification {

    public static Specification<Restaurante> hasEstado(Long estado) {
        return (root, query, criteriaBuilder) ->
                estado == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("estadoRestaurante"), estado);
    }

    public static Specification<Restaurante> hasEstilo(Long estilo) {
        return (root, query, criteriaBuilder) ->
                estilo == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("estiloRestaurante").get("id"), estilo);
    }

    public static Specification<Restaurante> hasNombre(String nombre) {
        return (root, query, criteriaBuilder) ->
                nombre == null || nombre.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
    }
}
