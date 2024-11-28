import React from 'react';
import { Link } from 'react-router-dom';

const Home = ({ userInfo }) => {
    return (
        <div>
            <h2>Página Principal</h2>
            <Link to="/profile" state={{ userInfo }}>
                <button>Perfil</button>
            </Link>
        </div>
    );
};

export default Home;