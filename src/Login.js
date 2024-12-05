import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { CSSTransition } from 'react-transition-group';
import './css/Login.css'; // Import the CSS file for styling
import universityLogo from './assets/university-logo.png';

const Login = ({ onLogin }) => {
    const [cif, setCif] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [isValid, setIsValid] = useState(null);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            // Clear localStorage before storing in sessionStorage
            localStorage.clear();

            const response = await axios.post('http://localhost:8087/student/login', {
                cif,
                password
            });
            // Store CIF in sessionStorage
            sessionStorage.setItem('cif', cif);
            onLogin(response.data);
            navigate('/home', { state: { userInfo: response.data } });
        } catch (err) {
            setError('Credenciales Inválidas');
            setIsValid(false);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        if (name === 'cif') setCif(value);
        if (name === 'password') setPassword(value);
        setIsValid(null);
        setError('');
    };

    return (
        <div className="login-container">
            <img src={universityLogo} alt="University Logo" className="university-logo"/>
            <CSSTransition
                in={true}
                appear={true}
                timeout={300}
                classNames="fade"
            >
                <div className="login-box">
                    <h1>Login</h1>
                    {error && <p className="error-message">{error}</p>}
                    <form onSubmit={handleLogin}>
                        <div
                            className={`input-group ${isValid === false ? 'invalid' : isValid === true ? 'valid' : ''}`}>
                            <label>CIF:</label>
                            <input
                                type="text"
                                name="cif"
                                value={cif}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div
                            className={`input-group ${isValid === false ? 'invalid' : isValid === true ? 'valid' : ''}`}>
                            <label>Contraseña:</label>
                            <input
                                type="password"
                                name="password"
                                value={password}
                                onChange={handleInputChange}
                            />
                        </div>
                        <button type="submit">Iniciar Sesión</button>
                    </form>
                </div>
            </CSSTransition>
        </div>
    );
};

export default Login;