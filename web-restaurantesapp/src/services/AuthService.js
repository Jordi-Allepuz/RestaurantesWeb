
import React, { Component } from 'react';


export class AuthService extends Component {


    async login(username, password) {
        const response = await fetch("http://localhost:8080/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });
        if (!response.ok) {
            const errorBody = await response.json();
            throw new Error(errorBody.message || 'Login failed');
        }
        const data = await response.json();
        localStorage.setItem('user', JSON.stringify(data));
        return data;
    }

    async logout() {
        localStorage.removeItem('user');
    }


}

export default new AuthService();
