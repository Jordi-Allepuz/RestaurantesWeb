package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    @Column(name = "id_restaurante")
    private Long idRestaurante;
    @Nullable
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Nullable
    private String comentario;
    @Nullable
    private int puntuacion;
    @Nullable
    private String localizacion;
    @Nullable
    @Column(name = "aparcar")
    private Long aparcar;
    @Nullable
    private String precio;
    @Nullable
    private String menu;
    @Nullable
    private String carta;
    @Nullable
    @Column(name = "platos_favoritos")
    private String platosFavoritos ;
    @Nullable
    private int servicio;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Nullable
    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion( String localizacion) {
        this.localizacion = localizacion;
    }

    public Long getAparcar() {
        return aparcar;
    }

    public void setAparcar(Long aparcar) {
        this.aparcar = aparcar;
    }

    @Nullable
    public String getPrecio() {
        return precio;
    }

    public void setPrecio( String precio) {
        this.precio = precio;
    }

    @Nullable
    public String getMenu() {
        return menu;
    }

    public void setMenu( String menu) {
        this.menu = menu;
    }

    @Nullable
    public String getCarta() {
        return carta;
    }

    public void setCarta( String carta) {
        this.carta = carta;
    }

    @Nullable
    public String getPlatosFavoritos() {
        return platosFavoritos;
    }

    public void setPlatosFavoritos( String platosFavoritos) {
        this.platosFavoritos = platosFavoritos;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }
}
