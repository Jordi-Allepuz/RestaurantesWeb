import { initializeApp } from 'firebase/app';
import { getStorage } from 'firebase/storage';


// Configuración de Firebase
const firebaseConfig = {
  apiKey: "AIzaSyBEBP1JUtRDGaoBq8J1C2PhLL3yJrctHas",
  authDomain: "restaurantesapp-2568c.firebaseapp.com",
  projectId: "restaurantesapp-2568c",
  storageBucket: "restaurantesapp-2568c.appspot.com",
  messagingSenderId: "1008690544041",
  appId: "1:1008690544041:web:fd35a6362b4853756b18b8"
};

// Inicializa Firebase App
const app = initializeApp(firebaseConfig);

// Obtiene una instancia de Firebase Storage
const storage = getStorage(app);

export { storage };