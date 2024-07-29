package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "tipos_servicio ")
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "tipo_servicio")
    private String tipoServicio;

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
    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(@Nullable String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    @Nullable
    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(@Nullable String explicacion) {
        this.explicacion = explicacion;
    }
}
