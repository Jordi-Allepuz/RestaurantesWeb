package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tipos_localizacion")
public class TipoLocalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "tipo_localizacion")
    private String tipoLocalizacion;

    @Nullable
    @Column(name = "explicacion")
    private String explicacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getTipoReserva() {
        return tipoLocalizacion;
    }

    public void setTipoReserva(@Nullable String tipoLocalizacion) {
        this.tipoLocalizacion = tipoLocalizacion;
    }

    @Nullable
    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(@Nullable String explicacion) {
        this.explicacion = explicacion;
    }
}
