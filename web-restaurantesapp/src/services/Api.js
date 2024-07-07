export const fetchWithAuth = async (url, options = {}) => {
   const user = JSON.parse(localStorage.getItem('user'));
    if (!user || !user.token) {
        throw new Error("No session found");
    }

    const headers = {
        'Authorization': `Bearer ${user.token}`,
        'Content-Type': 'application/json',
        ...options.headers
    };

    const response = await fetch(url, { ...options, headers });

    if (!response.ok) {
        try {
            const errorResponse = await response.json(); // Intenta parsear la respuesta como JSON
            throw new Error(errorResponse.message || "Network response was not ok.");
        } catch (error) {
            // Si hay un error al parsear JSON, usa el statusText
            throw new Error(response.statusText || "Network response was not ok.");
        }
    }

    return response; // Retorna aquí asegurándote que sólo se llama si la respuesta es 'ok'
};