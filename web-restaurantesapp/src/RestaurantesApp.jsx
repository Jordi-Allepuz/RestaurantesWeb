import { ListaRestaurantes } from "./componentes/restaurantes/ListaRestaurantes";
import { NuevoRestaurante } from "./componentes/restaurantes/NuevoRestaurante";
import { useEffect, useState } from "react";
import { handlerAddRestaurante } from "./services/RestauranteService";
import { RestauranteService } from "./services/RestauranteService";
import './RestaurantesApp.css';
import ReactPaginate from 'react-paginate';
import { MdChevronLeft, MdChevronRight } from 'react-icons/md';
import { connectStorageEmulator } from "firebase/storage";



export const RestaurantesApp = () => {

    const restauranteService = new RestauranteService();
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [restaurantes, setRestaurantes] = useState([]);
    const [restauranteEditar, setRestauranteEditar] = useState(null);

    const [currentPage, setCurrentPage] = useState(0);
    const [pageCount, setPageCount] = useState(0);


    useEffect(() => {
        obtenerRestaurantes();
    }, [currentPage]);

    const handleAddRestaurante = restaurante => {
        if (restauranteEditar) {
            const updatedRestaurantes = restaurantes.map(r => {
                if (r.id === restaurante.id) {
                    return restaurante;
                }
                return r;
            });
            setRestaurantes(updatedRestaurantes);
            setRestauranteEditar(null);
        } else {
            setRestaurantes([...restaurantes, restaurante]);
        }
    };


    const onEdit = restaurante => {
        setRestauranteEditar(restaurante);
        setModalIsOpen(true);
    };

    const onDelete = id => {
        setRestauranteEditar(id)
    };


    const obtenerRestaurantes = (estado, estilo, nombre) => {
        restauranteService.obtenerRestaurantes(true , estado, estilo, nombre, currentPage)
            .then(data => {
                setRestaurantes(data.content);
                setPageCount(data.totalPages);
                
            })
            .catch(error => {
                console.error('Error al obtener los restaurantes:', error);
            });
    }


    const handlePageClick = (data) => {
        setCurrentPage(data.selected);
        obtenerRestaurantes();
    }




    return (
        <>
            {modalIsOpen && (
                <div className="overlay" onClick={() => setModalIsOpen(false)} style={{ display: 'block' }}></div>
            )}
            <div className="container my-4">
                <div className="text-center mb-4">
                    <img src={`${process.env.PUBLIC_URL}/logo.png`} alt="Logo" className="mb-3 img-logo" style={{ height: '300px' }} />
                </div>
                <div className="row">
                    <ListaRestaurantes
                        restaurantes={restaurantes}
                        onEdit={onEdit}
                        obtenerRestaurantes={obtenerRestaurantes}
                        setModalIsOpen={setModalIsOpen}
                    />
                </div>
                {modalIsOpen && (
                    <NuevoRestaurante
                        onAddRestaurante={handleAddRestaurante}
                        modalIsOpen={modalIsOpen}
                        setModalIsOpen={setModalIsOpen}
                        restauranteEditar={restauranteEditar}
                        setRestauranteEditar={setRestauranteEditar}
                    />
                )}

                <div className="pagination-container">
                    <ReactPaginate
                        previousLabel={<MdChevronLeft />}
                        nextLabel={<MdChevronRight />}
                        breakLabel={'...'}
                        pageCount={pageCount}
                        marginPagesDisplayed={2}
                        pageRangeDisplayed={5}
                        onPageChange={e => handlePageClick(e)}
                        containerClassName={'pagination'}
                        activeClassName={'active'}
                    />
                </div>
            </div>
        </>
    );
};

export default RestaurantesApp;