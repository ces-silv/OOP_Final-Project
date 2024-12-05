import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Home = ({ userInfo, setUserInfo }) => {
    const navigate = useNavigate();

    // Función para manejar el log out
    const handleLogOut = () => {
        // Limpiar la información del usuario
        setUserInfo(null); // Ahora puedes llamar correctamente a setUserInfo
        // Redirigir al inicio (o login)
        navigate('/');
    };

    return (
        <div style={styles.container}>
            <h2 style={styles.header}>Menú Principal</h2>

            {/* Información del usuario */}
            {userInfo && (
                <div style={styles.userInfoContainer}>
                    <h3 style={styles.userName}>{userInfo[0].nombres} {userInfo[0].apellidos}</h3>
                </div>
            )}

            {/* Botón para responder encuesta */}
            <div style={styles.buttonContainer}>
                <Link to="/survey-response" style={styles.link}>
                    <button style={styles.button}>Responder Encuesta</button>
                </Link>
            </div>

            {/* Botón para ver perfil */}
            <div style={styles.buttonContainer}>
                <Link to="/profile" style={styles.link}>
                    <button style={styles.button}>Ver Perfil</button>
                </Link>
            </div>

            {/* Botón de Log Out */}
            <div style={styles.buttonContainer}>
                <button onClick={handleLogOut} style={styles.button}>Cerrar Sesión</button>
            </div>
        </div>
    );
};

// Estilos en línea
const styles = {
    container: {
        maxWidth: '600px',
        margin: '0 auto',
        padding: '20px',
        fontFamily: 'Arial, sans-serif',
        backgroundColor: '#f9f9f9',
        borderRadius: '8px',
        boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
        textAlign: 'center',
    },
    header: {
        fontSize: '24px',
        marginBottom: '20px',
        color: '#333',
    },
    buttonContainer: {
        marginBottom: '15px',
    },
    link: {
        textDecoration: 'none', // Elimina el subrayado de los enlaces
    },
    button: {
        width: '100%',
        padding: '12px',
        fontSize: '16px',
        backgroundColor: '#1c9aa5',
        color: '#fff',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
        transition: 'background-color 0.3s ease',
    },
    buttonHover: {
        backgroundColor: '#45a049',
    },
    userInfoContainer: {
        marginTop: '20px',
        padding: '10px',
        backgroundColor: '#e9e9e9',
        borderRadius: '5px',
    },
    userName: {
        fontSize: '18px',
        fontWeight: 'bold',
        color: '#333',
    },
    userCif: {
        fontSize: '16px',
        color: '#555',
    },
};

export default Home;
