package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    private String nombre;
    @Nullable
    private String direccion;
    @Nullable
    private String localidad;
    @Nullable
    private String telefono;
    @Nullable
    @Column(name = "estilo_restaurante")
    private Long estiloRestaurante;
    @Nullable
    @Column(name = "tipo_reserva")
    private Long reserva;
    @NotNull
    private Boolean activo=true;
    @Nullable
    @Column(name = "estado_restaurante")
    private Long estadoRestaurante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getEstiloRestaurante() {
        return estiloRestaurante;
    }

    public void setEstiloRestaurante(Long estiloRestaurante) {
        this.estiloRestaurante = estiloRestaurante;
    }

    public Long getReserva() {
        return reserva;
    }

    public void setReserva(Long reserva) {
        this.reserva = reserva;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getEstadoRestaurante() {
        return estadoRestaurante;
    }

    public void setEstadoRestaurante(Long estadoRestaurante) {
        this.estadoRestaurante = estadoRestaurante;
    }


}
