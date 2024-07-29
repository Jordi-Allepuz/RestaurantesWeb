package com.jordi.backend.restaurantesapp_api.backend_restaurantesapp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comentarios")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "localizacion")
    private TipoLocalizacion localizacion;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aparcar")
    private TipoAparcar aparcar;

    @Nullable
    private String precio;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu")
    private TipoMenu menu;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carta")
    private TipoCarta carta;

    @Nullable
    @Column(name = "platos_favoritos")
    private String platosFavoritos ;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio")
    private TipoServicio servicio;


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
    public TipoAparcar getAparcar() {
        return aparcar;
    }

    public void setAparcar(@Nullable TipoAparcar aparcar) {
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
    public String getPlatosFavoritos() {
        return platosFavoritos;
    }

    public void setPlatosFavoritos( String platosFavoritos) {
        this.platosFavoritos = platosFavoritos;
    }

    @Nullable
    public TipoLocalizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(@Nullable TipoLocalizacion localizacion) {
        this.localizacion = localizacion;
    }

    @Nullable
    public TipoMenu getMenu() {
        return menu;
    }

    public void setMenu(@Nullable TipoMenu menu) {
        this.menu = menu;
    }

    @Nullable
    public TipoCarta getCarta() {
        return carta;
    }

    public void setCarta(@Nullable TipoCarta carta) {
        this.carta = carta;
    }

    @Nullable
    public TipoServicio getServicio() {
        return servicio;
    }

    public void setServicio(@Nullable TipoServicio servicio) {
        this.servicio = servicio;
    }
}
