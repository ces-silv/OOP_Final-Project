import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Login from './Login';
import Home from './Home';
import Profile from './Profile';
import SurveyResponse from './SurveyResponse';

const App = () => {
    const [userInfo, setUserInfo] = useState(null);

    const handleLogin = (data) => {
        setUserInfo(data);
    };

    return (
        <Router>
            <Routes>
                <Route path="/" element={userInfo ? <Navigate to="/home" /> : <Login onLogin={handleLogin} />} />
                <Route path="/home" element={userInfo ? <Home userInfo={userInfo} /> : <Navigate to="/" />} />
                <Route path="/profile" element={userInfo ? <Profile /> : <Navigate to="/" />} />
                <Route path="/survey-response" element={<SurveyResponse />} />
            </Routes>
        </Router>
    );
};

export default App;