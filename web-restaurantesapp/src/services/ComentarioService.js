import React, { Component } from 'react';
import { fetchWithAuth } from './Api';

export class ComentarioService extends Component {

    obtenerComentarios() {
        return fetchWithAuth("http://localhost:8080/api/comentarios")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    obtenerComentarioUsuarioRestaurante(idRestaurante, idUsuario) {
        console.log(idRestaurante, idUsuario)
        return fetchWithAuth(`http://localhost:8080/api/comentarios/restaurante/${idRestaurante}/usuario/${idUsuario}`)
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }


    guardarComentario(comentario) {
        return fetchWithAuth("http://localhost:8080/api/comentarios", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                idUsuario: comentario.idUsuario,
                idRestaurante: comentario.idRestaurante,
                comentario: comentario.comentario,
                puntuacion: comentario.puntuacion,
                localizacion: comentario.localizacion,
                aparcar: comentario.aparcar,
                precio: comentario.precio,
                menu: comentario.menu,
                carta: comentario.carta,
                platosFavoritos: comentario.platosFavoritos,
                servicio: comentario.servicio
            }
            )
        })
            .then(res => res.json())
            .catch(error => {
                console.error("Error saving restaurante:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    editarComentario(id, comentario) {
        return fetchWithAuth(`http://localhost:8080/api/comentarios/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                idUsuario: comentario.idUsuario,
                idRestaurante: comentario.idRestaurante,
                comentario: comentario.comentario,
                puntuacion: comentario.puntuacion,
                localizacion: comentario.localizacion,
                aparcar: comentario.aparcar,
                precio: comentario.precio,
                menu: comentario.menu,
                carta: comentario.carta,
                platosFavoritos: comentario.platosFavoritos,
                servicio: comentario.servicio
            }
            )
        })
            .then(res => res.json())
            .catch(error => {
                console.error("Error saving restaurante:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }


    obtenerTiposAparcar() {
        return fetchWithAuth("http://localhost:8080/api/tiposaparcar")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    obtenerTiposCarta() {
        return fetchWithAuth("http://localhost:8080/api/tiposcartas")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    obtenerTiposMenu() {
        return fetchWithAuth("http://localhost:8080/api/tiposmenus")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    obtenerTiposLocalizacion() {
        return fetchWithAuth("http://localhost:8080/api/tiposlocalizaciones")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    obtenerTiposServicio() {
        return fetchWithAuth("http://localhost:8080/api/tiposservicios")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }


}





export default ComentarioService;