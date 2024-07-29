package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tipos_menu")
public class TipoMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "tipo_menu")
    private String tipoMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(@Nullable String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }
}
