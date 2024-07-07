import React, { useState } from 'react';
import { useEffect } from 'react';
import { ComentarioService } from '../../services/ComentarioService';
import { FaStar, FaRegStar } from 'react-icons/fa';
import './NuevaOpinion.css';





export const NuevaOpinion = ({
    opinionEditar, setOpinionEditar, onAddOpinion, modalIsOpen, setModalIsOpen, obtenerComentario }) => {

    const comentarioService = new ComentarioService();

    const [opinion, setOpinion] = useState({});
    const [opiniones, setOpiniones] = useState([]);
    const [idUsuario, setIdUsuario] = useState('');
    const [idRestaurante, setIdRestaurante] = useState('');
    const [comentario, setComentario] = useState('');
    const [puntuacion, setPuntuacion] = useState('');
    const [localizacion, setLocalizacion] = useState(null);
    const [aparcar, setAparcar] = useState(null);
    const [precio, setPrecio] = useState('');
    const [precioMin, setPrecioMin] = useState('');
    const [precioMax, setPrecioMax] = useState('');
    const [menu, setMenu] = useState(null);
    const [carta, setCarta] = useState(null);
    const [platosFavoritos, setPlatosFavoritos] = useState('');
    const [servicio, setServicio] = useState(null);
    const [activo, setActivo] = useState('');

    const [tiposAparcar, setTiposAparcar] = useState([]);
    const [tiposCarta, setTiposCarta] = useState([]);
    const [tiposMenu, setTiposMenu] = useState([]);
    const [tiposLocalizacion, setTiposLocalizacion] = useState([]);
    const [tiposServicio, setTiposServicio] = useState([]);

    useEffect(() => {
        obtenerTiposAparcar();
        obtenerTiposCarta();
        obtenerTiposMenu();
        obtenerTiposLocalizacion();
        obtenerTiposServicio();

        if (opinionEditar) {
            setIdUsuario(opinionEditar.idUsuario || '');
            setIdRestaurante(opinionEditar.idRestaurante || '');
            setComentario(opinionEditar.comentario || '');
            setPuntuacion(opinionEditar.puntuacion || '');
            setLocalizacion(opinionEditar.localizacion || null);
            setAparcar(opinionEditar.aparcar || null);
            setPrecio(opinionEditar.precio || '');
            setPrecioMin(opinionEditar.precio.split(' - ')[0] || '');
            setPrecioMax(opinionEditar.precio.split(' - ')[1] || '');
            setMenu(opinionEditar.menu || null);
            setCarta(opinionEditar.carta || null);
            setPlatosFavoritos(opinionEditar.platosFavoritos || '');
            setServicio(opinionEditar.servicio || null);
            setActivo(opinionEditar.activo);
        } else {
            setIdUsuario('');
            setIdRestaurante('');
            setComentario('');
            setPuntuacion('');
            setLocalizacion(null);
            setAparcar(null);
            setPrecio('');
            setMenu(null);
            setCarta(null);
            setPlatosFavoritos('');
            setServicio(null);
            setActivo(true);

        }
    }, [opinionEditar]);

    
    const obtenerTiposAparcar = () => {
        comentarioService.obtenerTiposAparcar()
            .then(data => { setTiposAparcar(data); })
            .catch(error => { console.error('Error al obtener los tipos de aparcar:', error); });
    }

    const obtenerTiposCarta = () => {
        comentarioService.obtenerTiposCarta()
            .then(data => { setTiposCarta(data); })
            .catch(error => { console.error('Error al obtener los tipos de carta:', error); });
    }

    const obtenerTiposMenu = () => {
        comentarioService.obtenerTiposMenu()
            .then(data => { setTiposMenu(data); })
            .catch(error => { console.error('Error al obtener los tipos de menu:', error); });
    }

    const obtenerTiposLocalizacion = () => {
        comentarioService.obtenerTiposLocalizacion()
            .then(data => { setTiposLocalizacion(data); })
            .catch(error => { console.error('Error al obtener los tipos de localizacion:', error); });
    }

    const obtenerTiposServicio = () => {
        comentarioService.obtenerTiposServicio()
            .then(data => { setTiposServicio(data); })
            .catch(error => { console.error('Error al obtener los tipos de servicio:', error); });
    }


    const onChangeIdUsuario = (e) => {
        setIdUsuario(e.target.value);
    }

    const onChangeIdRestaurante = (e) => {
        setIdRestaurante(e.target.value);
    }

    const onChangeComentario = (e) => {
        setComentario(e.target.value);
    }

    const onChangePuntuacion = (e) => {
        setPuntuacion(e);
    }

    const onChangeLocalizacion = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedLocalizacion = tiposLocalizacion.find(tipoLocalizacion => tipoLocalizacion.id === selectedId);
        setLocalizacion(selectedLocalizacion);
    }

    const onChangeAparcar = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedAparcar = tiposAparcar.find(tipoAparcar => tipoAparcar.id === selectedId);
        setAparcar(selectedAparcar);
    }

    const onChangePrecioMin = (e) => {
        setPrecioMin(e.target.value);
        if (precioMax) {
            setPrecio(`${e.target.value} - ${precioMax}`);
        }
    }

    const onChangePrecioMax = (e) => {
        setPrecioMax(e.target.value);
        if (precioMin) {
            setPrecio(`${precioMin} - ${e.target.value}`);
        }
    }


    const onChangeMenu = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedMenu = tiposMenu.find(tipoMenu => tipoMenu.id === selectedId);
        setMenu(selectedMenu);
    }

    const onChangeCarta = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedCarta = tiposCarta.find(tipoCarta => tipoCarta.id === selectedId);
        setCarta(selectedCarta);
    }

    const onChangePlatosFavoritos = (e) => {
        setPlatosFavoritos(e.target.value);
    }

    const onChangeServicio = (e) => {
        const selectedId = parseInt(e.target.value, 10);
        const selectedServicio = tiposServicio.find(tipoServicio => tipoServicio.id === selectedId);
        setServicio(selectedServicio);
    }

    const guardarOpinion = () => {
        if (!precio && precioMin && precioMax) {
            setPrecio(`${precioMin} - ${precioMax}`);
        }
        const opinion = {
            idUsuario: idUsuario,
            idRestaurante: idRestaurante,
            comentario: comentario,
            puntuacion: puntuacion,
            localizacion: localizacion,
            aparcar: aparcar,
            precio: precio,
            menu: menu,
            carta: carta,
            platosFavoritos: platosFavoritos,
            servicio: servicio,
            activo: activo
        };

        if (opinionEditar) {
            comentarioService.editarComentario(opinionEditar.id, opinion)
                .then((data) => {
                    onAddOpinion(data);
                    setOpinion(data);
                    cerrarModal();
                    obtenerComentario();
                })
                .catch(error => { console.error('Error al editar la opinion:', error); });
            console.log(opinion);
        } else {
            comentarioService.guardarComentario(opinion)
                .then((data) => {
                    onAddOpinion(data);
                    setOpinion(data);
                    cerrarModal();
                    obtenerComentario();
                })
                .catch(error => { console.error('Error al guardar la opinion:', error); });
        }

    }


    const resetForm = () => {
        setIdUsuario('');
        setIdRestaurante('');
        setComentario('');
        setPuntuacion('');
        setLocalizacion(null);
        setAparcar(null);
        setPrecio('');
        setMenu(null);
        setCarta(null);
        setPlatosFavoritos('');
        setServicio(null);
        setActivo(true);
    };


    const cerrarModal = () => {
        setModalIsOpen(false);
        resetForm();
        if (opinionEditar) setOpinionEditar(null);
    };


    const StarSelector = ({ puntuacion, setPuntuacion }) => {
        const changeRating = (newRating) => {
            setPuntuacion(newRating);
        };

        return (
            <div>
                {[1, 2, 3, 4, 5].map((star, index) => (
                    <span key={index} onClick={() => changeRating(star)}>
                        {puntuacion >= star ? <FaStar color="#ffc107" /> : <FaRegStar color="#e4e5e9" />}
                    </span>
                ))}
            </div>
        );
    };

    const listaPrecios = [
        { value: 1, label: '0€' },
        { value: 2, label: '10€' },
        { value: 3, label: '15€' },
        { value: 4, label: '20€' },
        { value: 5, label: '25€' },
        { value: 6, label: '30€' },
        { value: 7, label: '35€' },
        { value: 8, label: '40€' },
        { value: 9, label: '45€' },
        { value: 10, label: '50€' }
    ];




    return (
        <div style={{ display: modalIsOpen ? 'block' : 'none' }} className="modal">

            <form onSubmit={(e) => {
                e.preventDefault();
                guardarOpinion();
            }}>
                <h2>{opinionEditar ? 'Editar Opinion' : 'Nueva Opinion'}</h2>
                <div className="opinion-container">

                    <div className="column">

                        <div className="form-group">
                            <strong>Puntuación:</strong>
                            <StarSelector puntuacion={puntuacion} setPuntuacion={onChangePuntuacion} />
                        </div>

                        <div>
                            <strong>Localización</strong>
                            <select
                                className='form-control w-50 mb-2 p-2 bg-light rounded'
                                placeholder='Localizacion'
                                value={localizacion? localizacion.id : ''}
                                onChange={onChangeLocalizacion}>
                                <option value="" disabled>{localizacion ? 'Seleccione Tipo Localizacion' : 'Seleccione Tipo Localizacion'}</option>
                                {tiposLocalizacion.map((e) => (
                                    <option key={e.id} value={e.id}>
                                        {e.tipoLocalizacion} ({e.explicacion})
                                    </option>
                                ))}
                            </select>
                        </div>


                        <div>
                            <strong>Aparcamiento</strong>
                            <select
                                className="form-control w-50 mb-2 p-2 bg-light rounded"
                                placeholder='Aparcamiento'
                                value={aparcar? aparcar.id : ''}
                                onChange={onChangeAparcar}>
                                <option value="" disabled>{aparcar ? 'Seleccione Tipo Aparcar' : 'Seleccione Tipo Aparcar'}</option>
                                {tiposAparcar.map((e) => (
                                    <option key={e.id} value={e.id}>
                                        {e.tipoAparcar}
                                    </option>
                                ))}
                            </select>
                        </div>


                        <div>
                            <strong>Precio</strong>
                            <a>Min:</a>
                            <select
                                className='form-control w-25 mb-2 p-2 bg-light rounded'
                                placeholder='PrecioMin'
                                value={precioMin}
                                onChange={onChangePrecioMin}>
                                <option value="">Seleccione Precio Mínimo</option>
                                {listaPrecios.map(opcion => (
                                    <option key={opcion.value} value={opcion.label}>
                                        {opcion.label}
                                    </option>
                                ))}
                            </select>
                        </div>


                        <div>
                            <a>Max:</a>
                            <select
                                className='form-control w-25 mb-2 p-2 bg-light rounded'
                                placeholder='PrecioMax'
                                value={precioMax}
                                onChange={onChangePrecioMax}>
                                <option value="">Seleccione Precio Máximo</option>
                                {listaPrecios.map(opcion => (
                                    <option key={opcion.value} value={opcion.label}>
                                        {opcion.label}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div>
                            <strong>Servicio</strong>
                            <select
                                className='form-control  w-80 mb-2 p-2 bg-light rounded'
                                placeholder='Servicio'
                                value={servicio? servicio.id : ''}
                                onChange={onChangeServicio}>
                                <option value="" disabled>{servicio ? 'Seleccione Tipo Servicio' : 'Seleccione Tipo Servicio'}</option>
                                {tiposServicio.map((e) => (
                                    <option key={e.id} value={e.id}>
                                        {e.tipoServicio} ({e.explicacion})
                                    </option>
                                ))}
                            </select>
                        </div>

                    </div>

                    <div className="column">

                        <div>
                            <strong>Menú</strong>
                            <select
                                className='form-control w-75 mb-2 p-2 bg-light rounded'
                                placeholder='Menu'
                                value={menu? menu.id : ''}
                                onChange={onChangeMenu}>
                                <option value="" disabled>{menu ? 'Seleccione Tipo Menu' : 'Seleccione Tipo Menu'}</option>
                                {tiposMenu.map((e) => (
                                    <option key={e.id} value={e.id}>
                                        {e.tipoMenu}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div>
                            <strong>Carta</strong>
                            <select
                                className='form-control w-75 mb-2 p-2 bg-light rounded'
                                placeholder='Carta'
                                value={carta? carta.id : ''}
                                onChange={onChangeCarta}>
                                <option value="" disabled>{carta ? 'Seleccione Tipo Carta' : 'Seleccione Tipo Carta'}</option>
                                {tiposCarta.map((e) => (
                                    <option key={e.id} value={e.id}>
                                        {e.tipoCarta}
                                    </option>
                                ))}
                            </select>
                        </div>

                        <div>
                            <strong>Platos Favoritos</strong>
                            <input
                                className="form-control w-75 mb-2 p-2 bg-light rounded"
                                placeholder="Platos Favoritos"
                                type="text"
                                name="platosFavoritos"
                                value={platosFavoritos}
                                onChange={onChangePlatosFavoritos}
                            />
                        </div>



                        <div>
                            <strong>Comentario</strong>
                            <input
                                className="form-control w-75 mb-2 p-2 bg-light rounded"
                                placeholder="Comentario"
                                type="text"
                                name="comentario"
                                value={comentario}
                                onChange={onChangeComentario}
                            />
                        </div>

                    </div>
                </div>

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

export default NuevaOpinion;