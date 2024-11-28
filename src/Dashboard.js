import React from 'react';

const Dashboard = ({ userInfo }) => {
    // Retrieve CIF from sessionStorage
    const cif = sessionStorage.getItem('cif');

    return (
        <div>
            <h2>User Information</h2>
            <p><strong>CIF:</strong> {cif}</p>
            <ul>
                {userInfo.map((info, index) => (
                    <li key={index}>
                        <p><strong>Nombres:</strong> {info.nombres}</p>
                        <p><strong>Apellidos:</strong> {info.apellidos}</p>
                        <p><strong>Tipo:</strong> {info.tipo}</p>
                        <p><strong>Correo:</strong> {info.correo}</p>
                        <p><strong>Sexo:</strong> {info.sexo}</p>
                        <p><strong>Carrera:</strong> {info.carrera}</p>
                        <p><strong>Facultad:</strong> {info.facultad}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Dashboard;