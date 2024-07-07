
import React, { useState } from 'react';
import { useEffect } from 'react';
import { RestauranteService } from '../../services/RestauranteService';
import { Link } from 'react-router-dom';
import './ListaRestaurantes.css';
import { FaFilterCircleXmark } from "react-icons/fa6";
import { MdOutlineGridView } from "react-icons/md";
import { MdOutlineViewHeadline } from "react-icons/md";
import { RiFunctionAddLine } from "react-icons/ri";
import { MdOutlinePlaylistAdd } from "react-icons/md";
import { RiLogoutBoxLine } from "react-icons/ri";
import { BsTrash3 } from "react-icons/bs";
import { FaRegEdit } from "react-icons/fa";
import MyDocument from '../../pdf-config';
import { PDFDownloadLink } from '@react-pdf/renderer';
import { FaRegFilePdf } from "react-icons/fa6";
import { ComentarioService } from '../../services/ComentarioService';
import { AuthService } from '../../services/AuthService';
import { useNavigate } from 'react-router-dom';




export const ListaRestaurantes = ({ restaurantes, onEdit, obtenerRestaurantes, setModalIsOpen}) => {

    const restauranteService = new RestauranteService();
    const comentarioService = new ComentarioService();
    const authService = new AuthService();
    const navigate = useNavigate();

    const [restaurantesFiltrados, setRestaurantesFiltrados] = useState([]);
    const [comentarios, setComentarios] = useState([]);
    const [estilosRestaurante, setEstilosRestaurantes] = useState([]);
    const [estadosRestaurante, setEstadosRestaurante] = useState([]);
    
    const [tableOn, setTableOn] = useState(false);
    const [filtroEstado, setFiltroEstado] = useState('');
    const [filtroEstilo, setFiltroEstilo] = useState('');
    const [filtroNombre, setFiltroNombre] = useState('');

    useEffect(() => {
        obtenerComentarios();
        obtenerEstilosRestaurantes();
        obtenerEstadosRestaurante();
    }, []);

    const obtenerComentarios = () => {
        comentarioService.obtenerComentarios()
            .then(data => { setComentarios(data); })
            .catch(error => { console.error('Error al obtener los comentarios:', error); });
    }

    const obtenerEstilosRestaurantes = () => {
        restauranteService.obtenerTiposRestaurantes()
            .then(data => { setEstilosRestaurantes(data); })
            .catch(error => { console.error('Error al obtener los estilos restaurante:', error); });

    }

    const obtenerEstadosRestaurante = () => {
        restauranteService.obtenerEstadosRestaurante()
            .then(data => { setEstadosRestaurante(data); })
            .catch(error => { console.error('Error al obtener los estados restaurante:', error); });
    }


    const eliminarRestaurante = (id) => {
        restauranteService.borrarRestaurante(id)
            .then(() => {
                obtenerRestaurantes();
            })
            .catch(error => { console.error('Error al eliminar el restaurante:', error); });
            
    };


    const oncambiarVista = () => {
        setTableOn(!tableOn);
    }  
    

    const onChangeFiltroEstado = (e) => {
        setFiltroEstado(e.target.value);
        obtenerRestaurantes(e.target.value, filtroEstilo, filtroNombre);
    }

    const onChangeFiltroEstilo = (e) => {
        setFiltroEstilo(e.target.value);
        obtenerRestaurantes(filtroEstado, e.target.value, filtroNombre);
    }

    const onChangeFiltroNombre = (e) => {
        setFiltroNombre(e.target.value);
        obtenerRestaurantes(filtroEstado, filtroEstilo, e.target.value);
    }

    const limpiarFiltros = () => {
        setFiltroEstado('');
        setFiltroEstilo('');
        setFiltroNombre('');
        obtenerRestaurantes();
    }

    const abrirModal = () => {
        setModalIsOpen(true);
    }

    const handleLogout = async () => {
        try {
            await authService.logout();
            navigate('/');
        } catch (error) {
            console.error('Logout failed:', error);
            alert('Logout failed');
        }
    }



    return (
            
        <div>
            <div>
                <div className="button-container">
                    <button className="btn btn-primary btn-sm btn-spacing " onClick={() => oncambiarVista()}>
                        {tableOn?(
                            <MdOutlineGridView size={20}/>
                        ):(
                            <MdOutlineViewHeadline size={20}/>
                        )}
                    </button>
                    <button className="btn btn-primary btn-sm btn-spacing " onClick={handleLogout}><RiLogoutBoxLine size={20}/>Salir</button>
                    <div className="spacer"></div>
                    <div className='btn-pdf btn-primary btn-sm btn-spacing ' >
                        <PDFDownloadLink
                            document={<MyDocument 
                            restaurantes={restaurantes} 
                            comentarios={comentarios} 
                            />}
                            fileName="lista-restaurantes.pdf">
                            {({ blob, url, loading, error }) => 
                            <FaRegFilePdf size={30}/> 
                            } 
                            
                        </PDFDownloadLink>
                    </div>
                    <div>   
                        <button className="btn btn-primary btn-sm btn-spacing " onClick={() => abrirModal()}>
                            {tableOn?(
                                <MdOutlinePlaylistAdd size={30} style={{ marginRight: '10px'}} />
                            ):(
                                <RiFunctionAddLine size={30} style={{ marginRight: '10px'}}/>
                                )}  
                            Añadir nuevo  
                        </button>                                
                    </div>
                </div>
                <div className="filtro-contenedor">
                    <p className="filtro-titulo">Filtros de Búsqueda</p>
                    <div className="filtro-item">
                        <label>Filtro Estado Restaurante</label>
                        <select
                            className='form-control my-3 w-75'
                            placeholder='Estado Restaurante'
                            value={filtroEstado}
                            onChange={e => onChangeFiltroEstado(e)}>
                            <option value="" disabled>{filtroEstado ? 'Seleccione Estado Restaurante' : 'Seleccione Estado Restaurante'}</option>
                            {estadosRestaurante.map((e) => (
                                <option key={e.id} value={e.id}>
                                    {e.estado}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="filtro-item"> 
                        <label>Filtro Estilo Restaurante</label>      
                        <select
                            className='form-control my-3 w-75'
                            placeholder='Tipo Restaurante'
                            value={filtroEstilo}
                            onChange={e => onChangeFiltroEstilo(e)}>
                            <option value="" disabled>{filtroEstilo ? 'Seleccione Tipo Restaurante' : 'Seleccione Tipo Restaurante'}</option>
                            {estilosRestaurante.map((e) => (
                                <option key={e.id} value={e.id}>
                                    {e.tipoRestaurante}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="filtro-item">
                        <label>Busqueda por nombre</label>
                            <input
                                className="form-control my-3 w-75"
                                type="search"
                                placeholder="Escribe el nombre del restaurante"
                                value={filtroNombre}
                                onChange={(e) => onChangeFiltroNombre(e)}
                            />
                    </div>
                    
                    <button className="btn btn-primary btn-sm btn-spacing " onClick={() => limpiarFiltros()}><FaFilterCircleXmark size={30} /></button>                

                </div>
              

            </div>
            
            
        {tableOn ? (
             <table className="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>Nombre Restaurante</th>
                        <th>Poblacion</th>
                        <th>Calle</th>
                        <th>Telefono</th>
                        <th>Tipo Reserva</th>
                        <th>Estilo Restaurante</th>
                        <th>Estado Restaurante</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {restaurantes.map(restaurante => (
                        <tr key={restaurante.id}>
                            <td>{restaurante.nombre}</td>
                            <td>{restaurante.localidad}</td>
                            <td>{restaurante.direccion || 'Sin dirección'}</td>
                            <td>{restaurante.telefono || 'Sin teléfono'}</td>
                            <td>{restaurante.reserva?.tipoReserva}</td>
                            <td>{restaurante.estiloRestaurante?.tipoRestaurante}</td>
                            <td>{restaurante.estadoRestaurante?.estado}</td>
                            <td className="button-container">
                                <button className="btn btn-primary btn-sm btn-spacing " onClick={() => onEdit(restaurante)}>
                                    <FaRegEdit size={20} />
                                </button>
                                <button className="btn btn-primary btn-sm btn-spacing " onClick={() => eliminarRestaurante(restaurante.id)}>
                                    <BsTrash3 size={20} />
                                </button>
                                <Link to={`/opiniones/${restaurante.id}/${restaurante.nombre}`} className="btn btn-info">Opinion</Link>                           
                            </td>                            
                        </tr>
                    ))}
                    
                </tbody>
            </table> 
        ):(
            
            <div className="row">
                {restaurantes.map(restaurante => (
                <div className="rest-container col-md-4 mb-4" key={restaurante.id}>
                                             
                    <div className="card shadow-sm w-100" style={{ maxWidth: '600px' }}>
                    <Link to={`/opiniones/${restaurante.id}/${restaurante.nombre}`} className="text-decoration-none text-dark">
                    
                        <div className="card-body text-center">
                        <h2 className="card-title">{restaurante.nombre}</h2>
                        </div>
                        <div className='row'>

                            <div className="col-md-6 rest-image">
                                <img
                                src={restaurante.imagen}
                                className="card-img-top"
                                alt="Image not found"
                                />
                            </div>

                            <div className="col-md-6 d-flex flex-column justify-content-around">
                                <div>
                                    <label>Estilo restaurante</label>
                                    <div className="mb-2 p-2 bg-light rounded">
                                        {restaurante.estiloRestaurante?.tipoRestaurante}
                                    </div>
                                </div>
                                <div>
                                    <label>Reserva</label>
                                    <div className="mb-2 p-2 bg-light rounded">
                                            {restaurante.reserva?.tipoReserva}
                                    </div>
                                </div>
                                <div>
                                    <label>Estado</label>
                                    <div className="mb-2 p-2 bg-light rounded">
                                        {restaurante.estadoRestaurante?.estado}
                                    </div>
                                </div>
                            </div>

                            <div className="card-body">
                                <div>
                                        <label>Poblacion</label>
                                        <div className="mb-2 p-2 bg-light rounded">{restaurante.localidad}</div>
                                    </div>
                                    <div>
                                        <label>Direccion</label>
                                        <div className="mb-2 p-2 bg-light rounded">{restaurante.direccion || 'Sin dirección'}</div>
                                    </div>
                                    <div>
                                        <label>Telefono</label>
                                        <div className="mb-2 p-2 bg-light rounded">{restaurante.telefono || 'Sin teléfono'}</div>
                                    </div>
                            </div>

                        </div>
                        </Link>  
                            <div className="button-container" onClick={(e) => e.stopPropagation()}>
                                <button className="btn btn-primary btn-sm btn-spacing " onClick={() => onEdit(restaurante)}>
                                    <FaRegEdit size={20} />
                                </button>
                                <button className="btn btn-primary btn-sm btn-spacing " onClick={() => eliminarRestaurante(restaurante.id)}>
                                    <BsTrash3 size={20} />
                                </button>                            
                            </div> 
                        </div>
                    
                </div>
            ))}
            </div>
        )}
        </div>

    );
}

export default ListaRestaurantes;