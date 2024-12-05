import React from 'react';
import { Link } from 'react-router-dom';

const Home = ({ userInfo }) => {
    return (
        <div>
            <h2>Menú Principal</h2>
            <div>
                <Link to="/survey-response">
                    <button>Responder Encuesta</button>
                </Link>
            </div>
            <div>
                <Link to="/profile">
                    <button>Ver Perfil</button>
                </Link>
            </div>
            {userInfo && (
                <div>
                    <h3>Bienvenido, {userInfo[0].nombres} {userInfo[0].apellidos}</h3>
                    <p>CIF: {userInfo[0].cif}</p>
                </div>
            )}
        </div>
    );
};

export default Home;