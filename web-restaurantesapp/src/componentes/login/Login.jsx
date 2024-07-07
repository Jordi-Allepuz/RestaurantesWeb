import { Link } from 'react-router-dom';
import React from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import AuthService from '../../services/AuthService';

export const Login = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();



    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            await AuthService.login(username, password);
            navigate('/restaurantes');
        } catch (error) {
            console.error('Login failed:', error);
            alert('Login failed');
        }
    };



    return (
        <div className="container-login">
            <div className="login-box">
                <div className="text-center mb-4">
                    <img src={`${process.env.PUBLIC_URL}/logo.png`} alt="Logo" className="mb-3" style={{ height: '300px' }} />
                </div>
                <form className="form-login" onSubmit={handleLogin}>
                    <div className="input-group">
                        <label htmlFor="username">Username</label>
                        <input
                            id="username"
                            type="text"
                            placeholder="Enter your username"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                        />
                    </div>
                    <div className="input-group">
                        <label htmlFor="password">Password</label>
                        <input
                            id="password"
                            type="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </div>
                    <button type='submit' className="login-button">
                        Log In 
                    </button>
                </form>
                <div className="register-link">
                    Don't have an account? <Link to="/register">Register</Link>
                </div>
            </div>
        </div>
    );
}

export default Login;