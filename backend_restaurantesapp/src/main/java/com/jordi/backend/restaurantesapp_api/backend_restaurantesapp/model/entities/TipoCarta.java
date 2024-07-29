package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tipos_carta")
public class TipoCarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "tipo_carta")
    private String tipoCarta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(@Nullable String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }
}
