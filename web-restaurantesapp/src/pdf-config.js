import React from 'react';
import { Page, Text, View, Document, StyleSheet } from '@react-pdf/renderer';



// Crear estilos
const styles = StyleSheet.create({
    page: {
      flexDirection: 'column',
      backgroundColor: '#E4E4E4',
      padding: 10,
    },
    restaurantContainer: {
      flexDirection: 'row', // Alinea los elementos en fila
      justifyContent: 'space-between', // Distribuye espacio entre los elementos
      borderBottom: '1px solid #ccc', // Añade una línea para separar entradas
      paddingBottom: 10,
      marginBottom: 10,
      
    },
    restaurantSection: {
      width: '50%', // Usa el 50% del ancho para restaurantes
      paddingRight: 5, // Espaciado para no tocar el texto de comentarios
    },
    commentSection: {
      width: '50%', // Usa el 50% del ancho para comentarios
      paddingLeft: 5, // Espaciado para no tocar el texto de restaurante
    },
    sectionTitle: {
      fontSize: 18,
      fontWeight: 'bold',
      marginBottom: 3
    },
    text: {
      fontSize: 12,
      marginBottom: 3
    }
});


// Crear el documento PDF
const MyDocument = ({ restaurantes, comentarios}) => (
    <Document>
    <Page size="A4" style={styles.page}>
      {restaurantes.map((restaurante, index) => (
        <View break={index !== 0 && index % 3 === 0} key={index} style={styles.restaurantContainer}>
          {/* Datos del restaurante en la primera columna */}
          <View style={styles.restaurantSection}>
            <Text style={styles.sectionTitle}>Datos del Restaurante</Text>
            <Text style={styles.text}>Nombre: {restaurante.nombre}</Text>
            <Text style={styles.text}>Poblacion: {restaurante.localidad}</Text>
            <Text style={styles.text}>Dirección: {restaurante.direccion}</Text>
            <Text style={styles.text}>Teléfono: {restaurante.telefono}</Text>
            <Text style={styles.text}>Estilo: {restaurante.estiloRestaurante?.tipoRestaurante}</Text>
            <Text style={styles.text}>Reserva: {restaurante.reserva?.tipoReserva }</Text>
            <Text style={styles.text}>Estado: {restaurante.estadoRestaurante?.estado}</Text>
          </View>
          {/* Comentarios en la segunda columna */}
          <View style={styles.commentSection}>
            <Text style={styles.sectionTitle}>Opinion</Text>
            <Text style={styles.text}>Puntuacion: {comentarios.find(e => e.idRestaurante === restaurante.id)?.puntuacion || 'No encontrado'}</Text>
            <Text style={styles.text}>Aparcamiento: {comentarios.find(e => e.idRestaurante === restaurante.id)?.aparcar?.tipoAparcar  || 'No encontrado'}</Text>
            <Text style={styles.text}>Localización: {comentarios.find(e => e.idRestaurante === restaurante.id)?.localizacion?.explicacion || 'No encontrado'}</Text>
            <Text style={styles.text}>Menú: {comentarios.find(e => e.idRestaurante === restaurante.id)?.menu?.tipoMenu || 'No encontrado'}</Text>
            <Text style={styles.text}>Carta: {comentarios.find(e => e.idRestaurante === restaurante.id)?.carta?.tipoCarta || 'No encontrado'}</Text>
            <Text style={styles.text}>Precio: {comentarios.find(e => e.idRestaurante === restaurante.id)?.precio || 'No encontrado'}</Text>
            <Text style={styles.text}>Servicio: {comentarios.find(e => e.idRestaurante === restaurante.id)?.servicio?.explicacion || 'No encontrado'}</Text>
            <Text style={styles.text}>Platos favoritos: {comentarios.find(e => e.idRestaurante === restaurante.id)?.platosFavoritos || 'No encontrado'}</Text>
            <Text style={styles.text}>Comentario: {comentarios.find(e => e.idRestaurante === restaurante.id)?.comentario || 'No encontrado'}</Text>
          </View>
        </View>
      ))}
    </Page>
  </Document>
);
  
  export default MyDocument;