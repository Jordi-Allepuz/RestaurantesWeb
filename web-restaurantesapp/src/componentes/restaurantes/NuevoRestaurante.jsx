import React, { useState } from 'react';
import { useEffect } from 'react';
import { RestauranteService } from '../../services/RestauranteService';
import { ComentarioService } from '../../services/ComentarioService';
import './NuevoRestaurante.css';
import {storage} from '../../firebase-config';
import { initializeApp } from 'firebase/app';
import { getStorage, ref, uploadBytes, getDownloadURL, connectStorageEmulator } from 'firebase/storage';


export const NuevoRestaurante = ({ restauranteEditar, setRestauranteEditar, onAddRestaurante, modalIsOpen, setModalIsOpen }) => {

    const restauranteService = new RestauranteService();
    const opinionService = new ComentarioService();
    const [restaurante, setRestaurante] = useState({});
    const [nombre, setNombre] = useState('');
    const [direccion, setDireccion] = useState('');
    const [telefono, setTelefono] = useState('');
    const [reserva, setReserva] = useState(null);
    const [estilo, setEstilo] = useState(null);
    const [localidad, setLocalidad] = useState('');
    const [estado, setEstado] = useState(null);
    const [activo, setActivo] = useState('');
    const [imagen, setImagen] = useState(''); 

    const [tiposReserva, setTiposReserva] = useState([]);
    const [estilosRestaurante, setEstilosRestaurante] = useState([]);
    const [estadosRestaurante, setEstadosRestaurante] = useState([]);

    useEffect(() => {
        obtenerTiposReserva();
        obtenerEstilosRestaurantes();
        obtenerEstadosRestaurante();

        if (restauranteEditar) {
            setNombre(restauranteEditar.nombre || '');
            setDireccion(restauranteEditar.direccion || '');
            setTelefono(restauranteEditar.telefono || '');
            setReserva(restauranteEditar.reserva || null);
            setEstilo(restauranteEditar.estiloRestaurante || null);
            setLocalidad(restauranteEditar.localidad || '');
            setEstado(restauranteEditar.estadoRestaurante || null);
            setActivo(restauranteEditar.activo);
            setImagen(restauranteEditar.imagen || '');
        } else {
            // Limpiar formulario
            setNombre('');
            setDireccion('');
            setTelefono('');
            setLocalidad('');
            setReserva(null);
            setEstilo(null);
            setEstado(null);
            setActivo(true);
            setImagen('');
        }
    }, [restauranteEditar]);


    const obtenerTiposReserva = () => {
        restauranteService.obtenerTiposReserva()
            .then(data => { setTiposReserva(data); })
            .catch(error => { console.error('Error al obtener los tipos reserva:', error); });

    }


    const obtenerEstilosRestaurantes = () => {
        restauranteService.obtenerTiposRestaurantes()
            .then(data => { setEstilosRestaurante(data); })
            .catch(error => { console.error('Error al obtener los estilos restaurante:', error); });

    }

    const obtenerEstadosRestaurante = () => {
        restauranteService.obtenerEstadosRestaurante()
            .then(data => { setEstadosRestaurante(data); })
            .catch(error => { console.error('Error al obtener los estados restaurante:', error); });
    }

    const onChangeNombre = (e) => {
        setNombre(e.target.value);
    }

    const onChangeDireccion = (e) => {
        setDireccion(e.target.value);
    }

    const onChangeTelefono = (e) => {
        setTelefono(e.target.value);
    }

    const onChangeLocalidad = (e) => {
        setLocalidad(e.target.value);
    }

    const onChangeReserva = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedReserva = tiposReserva.find(r => r.id === selectedId);
        setReserva(selectedReserva);
    };

    const onChangeEstilo = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedEstilo = estilosRestaurante.find(estilo => estilo.id === selectedId);
        setEstilo(selectedEstilo);
    };

    const onChangeEstado = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedEstado = estadosRestaurante.find(estado => estado.id === selectedId);
        setEstado(selectedEstado);
    };
    

    const onChangeImagen = async (event, nombreRestaurante) => {
        const file = event.target.files[0];
        if (!file) return;
    
        const storageRef = ref(storage, `restaurantes/${nombreRestaurante}`);
        try {
            const snapshot = await uploadBytes(storageRef, file);
            const fileUrl = await getDownloadURL(snapshot.ref);
            setImagen(fileUrl);  // Guarda la URL en el estado
            console.log("Archivo subido y URL obtenida:", fileUrl);
        } catch (error) {
            console.error("Error al subir el archivo:", error);
        }
    };


    const guardarRestaurante = () => {
        const nuevoRestaurante = {
            nombre: nombre,
            direccion: direccion,
            localidad: localidad,
            telefono: telefono,
            reserva: reserva,
            estilo: estilo,
            activo: true,
            estado: estado,
            imagen: imagen
        };

        const crearComentarioVacio = (idRestaurante) => {
            const comentarioVacio = {
                idRestaurante: idRestaurante,
                idUsuario: 1,  // Suponiendo que el usuario por defecto tiene ID 1
                comentario: '',
                puntuacion: 0,
                localizacion: null,
                aparcar: null,
                precio: '',
                menu: null,
                carta: null,
                platosFavoritos: '',
                servicio: null,
                activo: true
            };
            opinionService.guardarComentario(comentarioVacio)
                .then(data => console.log('Comentario inicial creado', data))
                .catch(error => console.error('Error al crear comentario inicial', error));
        };

        if (restauranteEditar) {
            restauranteService.editarRestaurante(restauranteEditar.id, nuevoRestaurante )
                .then(data => {
                    console.log('Restaurante editado:', data);
                    setRestaurante(data);
                    onAddRestaurante(data);
                    setModalIsOpen(false);
                    resetForm();
                })
                .catch(error => {
                    console.error('Error al editar el restaurante:', error);
                });
        } else {
            restauranteService.guardarRestaurante(nuevoRestaurante)
                .then(data => {
                    console.log('Restaurante guardado:', data);
                    setRestaurante(data);
                    onAddRestaurante(data);
                    setModalIsOpen(false);
                    crearComentarioVacio(data.id);
                    resetForm();
                })
                .catch(error => {
                    console.error('Error al guardar el restaurante:', error);
                    console.log(nuevoRestaurante)
                });
        }
    }


    const resetForm = () => {
        setNombre('');
        setDireccion('');
        setTelefono('');
        setLocalidad('');
        setReserva(null);
        setEstilo(null);
        setEstado(null);
        setActivo(true);
        setImagen('');
    };


    const cerrarModal = () => {
        setModalIsOpen(false);
        resetForm();
        if (restauranteEditar) setRestauranteEditar(null);
    };



    return (
        <div style={{ display: modalIsOpen ? 'block' : 'none' }} className="modal">
            <p>Nuevo Restaurante</p>

            <form onSubmit={(e) => {
                e.preventDefault();
                guardarRestaurante();
            }}>
                <input
                    className="form-control my-3 w-50"
                    placeholder="Nombre Restaurante"
                    type="text"
                    name="nombre"
                    value={nombre}
                    onChange={onChangeNombre} />

                <input
                    className="form-control my-3 w-50"
                    placeholder="Direccion"
                    type="text"
                    name="direccion"
                    value={direccion}
                    onChange={onChangeDireccion}
                />

                {imagen && (
                    <div>
                        <img src={imagen} alt="Imagen Actual" style={{ width: '100px', height: 'auto' }} />
                    </div>
                )}
                
                <input
                    type="file"
                    onChange={e => onChangeImagen(e, nombre)}
                    accept="image/*"  // Asegúrate de aceptar solo imágenes
                />

                <input
                    className="form-control my-3 w-50"
                    placeholder="Telefono"
                    type="text"
                    name="telefono"
                    value={telefono}
                    onChange={onChangeTelefono}
                />

                <input
                    className="form-control my-3 w-50"
                    placeholder="Localidad"
                    type="text"
                    name="localidad"
                    value={localidad}
                    onChange={onChangeLocalidad}
                />
                <select
                    className='form-control my-3 w-50'
                    placeholder='Tipo Reserva'
                    value={reserva? reserva.id : ''}
                    onChange={onChangeReserva}>
                    <option value="" disabled>{reserva ? 'Seleccione Tipo Reserva' : 'Seleccione Tipo Reserva'}</option>
                    {tiposReserva.map((e) => (
                        <option key={e.id} value={e.id}>
                            {e.tipoReserva}
                        </option>
                    ))}
                </select>
                <select
                    className='form-control my-3 w-50'
                    placeholder='Tipo Restaurante'
                    value={estilo ? estilo.id : ''}
                    onChange={onChangeEstilo}>
                    <option value="" disabled>{estilo ? 'Seleccione Tipo Restaurante' : 'Seleccione Tipo Restaurante'}</option>
                    {estilosRestaurante.map((e) => (
                        <option key={e.id} value={e.id}>
                            {e.tipoRestaurante}
                        </option>
                    ))}
                </select>
                <select
                    className='form-control my-3 w-50'
                    placeholder='Estado Restaurante'
                    value={estado ? estado.id : ''}
                    onChange={onChangeEstado}>
                    <option value="" disabled>{estado ? 'Seleccione Estado Restaurante' : 'Seleccione Estado Restaurante'}</option>
                    {estadosRestaurante.map((e) => (
                        <option key={e.id} value={e.id}>
                            {e.estado}
                        </option>
                    ))}
                </select>

                <div className='button-container'>
                    <button
                        className="btn btn-primary"
                        type="submit">
                        {'Guardar'}
                    </button>
                    <button
                        className="btn btn-primary"
                        type="button"
                        onClick={cerrarModal}>
                        Cancelar
                    </button>
                </div>

            </form>

        </div>
    );
}

export default NuevoRestaurante;
