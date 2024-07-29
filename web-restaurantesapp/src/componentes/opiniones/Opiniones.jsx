import React from 'react';
import { Link } from 'react-router-dom';
import { useEffect } from 'react';
import './Opiniones.css';
import { ComentarioService } from '../../services/ComentarioService';
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { NuevaOpinion } from './NuevaOpinion';
import { BsTrash3 } from "react-icons/bs";
import { FaRegEdit } from "react-icons/fa";
import { MdOutlineRestaurant } from "react-icons/md";
import { FaStar, FaRegStar } from 'react-icons/fa';



export const Opiniones = () => {

    const comentarioService = new ComentarioService();
    const [comentario, setComentario] = useState({});
    const [comentarios, setComentarios] = useState([]);
    const { idRestaurante } = useParams();
    const { nombreRestaurante } = useParams();
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [opinionEditar, setOpinionEditar] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        obtenerComentario();
    }, [idRestaurante]);


    const obtenerComentario = () => {
        comentarioService.obtenerComentarioUsuarioRestaurante(idRestaurante, 4)
            .then(data => { setComentario(data); })
            .catch(error => { console.error('Error al obtener los comentarios:', error); });
            console.log(comentario.tipoAparcar);
    }



    const onRestaurantesClick = () => {
        navigate('/restaurantes');
    };

    const onEditar = () => {
        setOpinionEditar(comentario);
        setModalIsOpen(true);
    };

    const handleAddOpinion = comentario => {
        if (opinionEditar) {
            const updatedComentarios = comentarios.map(c => {
                if (c.id === comentario.id) {
                    return comentario;
                }
                return c;
            });
            setComentarios(updatedComentarios);
            setOpinionEditar(null);
        } else {
            setComentarios([...comentarios, comentario]);
        }
    };

    const StarRating = ({ puntuacion }) => {
        const stars = Array.from({ length: 5 }, (_, index) => index);

        return (
            <div>
                {stars.map((index) => {
                    return index < puntuacion ? <FaStar key={index} color="#ffc107" /> : <FaRegStar key={index} color="#ffc107" />;
                })}
            </div>
        );
    };




    return (
        <>
            {modalIsOpen && (
                <div className="overlay" onClick={() => setModalIsOpen(false)} style={{ display: 'block' }}></div>
            )}
            <div className="container-opinion">
                <div className="card-opinion">
                    <h2 style={{ textAlign: 'center', padding: '10px' }}>{nombreRestaurante}</h2>
                    <div className="opinion-container">
                        <div className="column">

                            <div>
                                <strong>Puntuación</strong>
                                <div className="mb-2 p-2 bg-light rounded">
                                    <StarRating puntuacion={comentario.puntuacion} />
                                </div>
                            </div>
                            <div>
                                <strong>Localización</strong>
                                <div className="mb-2 p-2 bg-light rounded">
                                    { comentario.localizacion?.tipoLocalizacion} : {comentario.localizacion?.explicacion } 
                            </div>
                            
                            </div>
                            <div>
                                <strong>Aparcamiento</strong>
                                <div className="mb-2 p-2 bg-light rounded">{comentario.aparcar?.tipoAparcar}</div>
                            </div>
                            <div>
                                <strong>Precio</strong>
                                <div className="mb-2 p-2 bg-light rounded">entre {comentario.precio} por persona</div>
                            </div>
                            <div>
                                <strong>Menú</strong>
                                <div className="mb-2 p-2 bg-light rounded">{comentario.menu?.tipoMenu}</div>
                            </div>
                        </div>

                        <div className="column">

                            <div>
                                <strong>Carta</strong>
                                <div className="mb-2 p-2 bg-light rounded">{comentario.carta?.tipoCarta}</div>
                            </div>
                            <div>
                                <strong>Platos Favoritos</strong>
                                <div className="mb-2 p-2 bg-light rounded">{comentario.platosFavoritos}</div>
                            </div>
                            <div>
                                <strong>Servicio</strong>
                                <div className="mb-2 p-2 bg-light rounded">
                                {comentario.servicio?.tipoServicio} : {comentario.servicio?.explicacion}
                                </div>
                            </div>
                            <div>
                                <strong>Comentario</strong>
                                <div className="mb-2 p-2 bg-light rounded">{comentario.comentario}</div>
                            </div>
                        </div>

                    </div>

                    <div className="button-container">
                        <button className="btn btn-primary btn-sm btn-spacing" onClick={onEditar}>
                            <FaRegEdit size={20} />
                        </button>
                        <button className="btn btn-primary btn-sm btn-spacing">
                            <BsTrash3 size={20} />
                        </button>
                        <button className="btn btn-primary btn-sm btn-spacing" onClick={onRestaurantesClick}>
                            <MdOutlineRestaurant size={20} />
                            Lista
                        </button>
                    </div>
                    {modalIsOpen && (
                        <NuevaOpinion
                            onAddOpinion={handleAddOpinion}
                            modalIsOpen={modalIsOpen}
                            setModalIsOpen={setModalIsOpen}
                            opinionEditar={opinionEditar}
                            setOpinionEditar={setOpinionEditar}
                            obtenerComentario={obtenerComentario}
                        />
                    )}
                </div>
            </div>
        </>
    );
}

export default Opiniones;