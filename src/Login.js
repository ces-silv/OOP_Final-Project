import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = ({ onLogin }) => {
    const [cif, setCif] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
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
            setError('Invalid credentials');
        }
    };

    return (
        <div>
            <h2>Login</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleLogin}>
                <div>
                    <label>CIF:</label>
                    <input type="text" value={cif} onChange={(e) => setCif(e.target.value)} />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;