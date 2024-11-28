import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const Profile = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const userInfo = location.state ? location.state.userInfo : null;

    if (!userInfo) {
        return <div>No se encontró información del usuario.</div>;
    }

    const handleGoBack = () => {
        navigate('/home');
    };

    return (
        <div>
            <h2>Perfil</h2>
            <p><strong>Nombre Completo:</strong> {userInfo[0].nombres} {userInfo[0].apellidos}</p>
            <h3>Facultad y Carrera</h3>
            <select>
                {userInfo.map((info, index) => (
                    <option key={index} value={info.carrera}>
                        {info.facultad} - {info.carrera}
                    </option>
                ))}
            </select>
            <button onClick={handleGoBack}>Volver al Menú Principal</button>
        </div>
    );
};

export default Profile;