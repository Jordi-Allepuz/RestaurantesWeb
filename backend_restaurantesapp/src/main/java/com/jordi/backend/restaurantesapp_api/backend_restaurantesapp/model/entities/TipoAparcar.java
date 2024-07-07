package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tipos_aparcar")
public class TipoAparcar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "tipo_aparcar")
    private String tipoAparcar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getTipoAparcar() {
        return tipoAparcar;
    }

    public void setTipoAparcar( String tipoAparcar) {
        this.tipoAparcar = tipoAparcar;
    }
}
