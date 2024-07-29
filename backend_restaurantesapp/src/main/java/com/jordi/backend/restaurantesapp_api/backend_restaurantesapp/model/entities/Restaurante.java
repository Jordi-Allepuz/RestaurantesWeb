package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "restaurantes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estilo_restaurante")
    private EstiloRestaurante estiloRestaurante;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_reserva")
    private TipoReserva reserva;

    @NotNull
    private Boolean activo=true;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_restaurante")
    private EstadoRestaurante estadoRestaurante;

    @Nullable
    @Column(name = "imagen")
    private String imagen;

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

    public EstiloRestaurante getEstiloRestaurante() {
        return estiloRestaurante;
    }

    public void setEstiloRestaurante(EstiloRestaurante estiloRestaurante) {
        this.estiloRestaurante = estiloRestaurante;
    }

    public TipoReserva getReserva() {
        return reserva;
    }

    public void setReserva(TipoReserva reserva) {
        this.reserva = reserva;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public EstadoRestaurante getEstadoRestaurante() {
        return estadoRestaurante;
    }

    public void setEstadoRestaurante(EstadoRestaurante estadoRestaurante) {
        this.estadoRestaurante = estadoRestaurante;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


}
