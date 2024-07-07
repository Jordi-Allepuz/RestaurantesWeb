import React, { Component } from 'react';
import { fetchWithAuth } from './Api';

export class RestauranteService extends Component {


    obtenerRestaurantes(activo, estado, estilo, nombre, page = 0) {
        const params = new URLSearchParams({
            activo: activo || '',
            estadoRestaurante: estado || '',
            estiloRestaurante: estilo || '',
            nombre: nombre || ''
        }).toString();
        if (estado !== undefined || estilo !== undefined || nombre !== undefined) {
            page = 0;
        }

        return fetchWithAuth(`http://localhost:8080/api/restaurantes/page/${page}?${params}`)
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error("Network response was not ok: " + text);
                    });
                }
                return response.json(); // Asegúrate de que esto se llama solo una vez
            })
            .then(data => {
                return data; // Pasar los datos para futuros then()
            })
            .catch(error => {
                console.error("Error fetching productos:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }


    obtenerTiposRestaurantes() {
        return fetchWithAuth("http://localhost:8080/api/estilosrestaurantes")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });

    }

    obtenerTiposReserva() {
        return fetchWithAuth("http://localhost:8080/api/tiposreservas")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    obtenerEstadosRestaurante() {
        return fetchWithAuth("http://localhost:8080/api/estadosrestaurantes")
            .then(res => res.json())
            .catch(error => {
                console.error("Error fetching restaurantes:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    guardarRestaurante(restaurante) {
        return fetchWithAuth("http://localhost:8080/api/restaurantes", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nombre: restaurante.nombre,
                direccion: restaurante.direccion,
                localidad: restaurante.localidad,
                telefono: restaurante.telefono,
                estiloRestaurante: restaurante.estilo,
                reserva: restaurante.reserva,
                activo: restaurante.activo,
                estadoRestaurante: restaurante.estado,
                imagen: restaurante.imagen
            }
            )
        })
            .then(res => res.json())
            .catch(error => {
                console.error("Error saving restaurante:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }

    editarRestaurante(id, restaurante) {
        return fetch(`http://localhost:8080/api/restaurantes/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nombre: restaurante.nombre,
                direccion: restaurante.direccion,
                localidad: restaurante.localidad,
                telefono: restaurante.telefono,
                estiloRestaurante: restaurante.estilo,
                reserva: restaurante.reserva,
                activo: restaurante.activo,
                estadoRestaurante: restaurante.estado,
                imagen: restaurante.imagen
            }
            )
        })
            .then(res => res.json())
            .catch(error => {
                console.error("Error saving restaurante:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }


    borrarRestaurante(id) {
        return fetch(`http://localhost:8080/api/restaurantes/${id}`, {
            method: 'DELETE'
        })
            .then(res => res.json())
            .catch(error => {
                console.error("Error saving restaurante:", error);
                throw error;  // Lanzar el error para manejarlo en el componente
            });
    }
}





export default RestauranteService;