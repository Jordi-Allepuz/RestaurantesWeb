import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { RestaurantesApp } from './RestaurantesApp';
import { Opiniones } from './componentes/opiniones/Opiniones';
import { Login } from './componentes/login/Login';

export const App = () => {
  return (
    <Router>
      <Routes>
        <Route path ="/" element={<Login />} />
        <Route path="/restaurantes" element={<RestaurantesApp />} />
        <Route path="/opiniones/:idRestaurante/:nombreRestaurante" element={<Opiniones />} />
      </Routes>
    </Router>
  );
};


export default App;