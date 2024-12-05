import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // Importamos useNavigate

const SurveyResponse = () => {
    const [facultades, setFacultades] = useState([]);
    const [carreras, setCarreras] = useState([]);
    const [profesores, setProfesores] = useState([]);
    const [asignaturas, setAsignaturas] = useState([]);
    const [selectedFacultad, setSelectedFacultad] = useState('');
    const [selectedCarrera, setSelectedCarrera] = useState('');
    const [selectedProfesor, setSelectedProfesor] = useState('');
    const [selectedAsignatura, setSelectedAsignatura] = useState('');
    const [selectedGrupo, setSelectedGrupo] = useState('');

    const navigate = useNavigate(); // Instanciamos el hook useNavigate

    // Obtener todas las facultades
    useEffect(() => {
        axios.get('http://localhost:8087/facultades')
            .then(response => setFacultades(response.data))
            .catch(error => console.error('Error fetching facultades:', error));
    }, []);

    // Obtener carreras por facultad seleccionada
    useEffect(() => {
        if (selectedFacultad) {
            axios.get(`http://localhost:8087/carreras?idFacultad=${selectedFacultad}`)
                .then(response => setCarreras(response.data))
                .catch(error => console.error('Error fetching carreras:', error));
        } else {
            setCarreras([]);
            setAsignaturas([]);
            setProfesores([]);
            setSelectedCarrera('');
            setSelectedAsignatura('');
            setSelectedGrupo('');
            setSelectedProfesor('');
        }
    }, [selectedFacultad]);

    // Obtener asignaturas por carrera y facultad seleccionadas
    useEffect(() => {
        if (selectedCarrera && selectedFacultad) {
            axios.get(`http://localhost:8087/asignaturas/search?idCarrera=${selectedCarrera}&idFacultad=${selectedFacultad}`)
                .then(response => setAsignaturas(response.data))
                .catch(error => console.error('Error fetching asignaturas:', error));
        } else {
            setAsignaturas([]);
            setProfesores([]);
            setSelectedAsignatura('');
            setSelectedGrupo('');
            setSelectedProfesor('');
        }
    }, [selectedCarrera, selectedFacultad]);

    // Obtener profesor por asignatura y grupo seleccionados
    useEffect(() => {
        if (selectedAsignatura && selectedGrupo) {
            axios.get(`http://localhost:8087/asignaturas/profesor?idAsignatura=${selectedAsignatura}&grupo=${selectedGrupo}`)
                .then(response => {
                    setSelectedProfesor(response.data.idProfesor);
                    setProfesores([response.data]);
                })
                .catch(error => console.error('Error fetching profesor:', error));
        } else {
            setProfesores([]);
            setSelectedProfesor('');
        }
    }, [selectedAsignatura, selectedGrupo]);

    const handleAsignaturaChange = (e) => {
        const selectedAsignaturaId = e.target.value;
        const selectedAsignaturaObj = asignaturas.find(a => a.idAsignatura === selectedAsignaturaId);

        // Si la asignatura seleccionada tiene un grupo, actualizamos el grupo
        if (selectedAsignaturaObj) {
            setSelectedAsignatura(selectedAsignaturaId);
            setSelectedGrupo(selectedAsignaturaObj.grupo);
        } else {
            setSelectedAsignatura('');
            setSelectedGrupo('');
        }
    };

    const handleSubmit = () => {
        console.log('Facultad:', selectedFacultad);
        console.log('Carrera:', selectedCarrera);
        console.log('Asignatura:', selectedAsignatura);
        console.log('Profesor:', selectedProfesor);
    };

    // Validación de si todos los campos están seleccionados
    const isFormValid = selectedFacultad && selectedCarrera && selectedAsignatura && selectedProfesor;

    // Función para redirigir al home
    const goToHome = () => {
        navigate('/'); // Esto redirige a la página principal
    };

    return (
        <div>
            <h2>Responder Encuesta</h2>

            {/* Facultad */}
            <div>
                <label>Facultad:</label>
                <select value={selectedFacultad} onChange={(e) => setSelectedFacultad(e.target.value)}>
                    <option value="">Seleccionar Facultad</option>
                    {facultades.map(facultad => (
                        <option key={facultad.idFacultad} value={facultad.idFacultad}>{facultad.nombreFacultad}</option>
                    ))}
                </select>
            </div>

            {/* Carrera */}
            <div>
                <label>Carrera:</label>
                <select value={selectedCarrera} onChange={(e) => setSelectedCarrera(e.target.value)}>
                    <option value="">Seleccionar Carrera</option>
                    {carreras.map(carrera => (
                        <option key={carrera.idCarrera} value={carrera.idCarrera}>{carrera.nombreCarrera}</option>
                    ))}
                </select>
            </div>

            {/* Asignatura */}
            <div>
                <label>Asignatura:</label>
                <select value={selectedAsignatura} onChange={handleAsignaturaChange}>
                    <option value="">Seleccionar Asignatura</option>
                    {asignaturas.map(asignatura => (
                        <option key={asignatura.idAsignatura} value={asignatura.idAsignatura}>
                            {asignatura.nombre} - Grupo {asignatura.grupo}
                        </option>
                    ))}
                </select>
            </div>

            {/* Profesor */}
            <div>
                <label>Profesor:</label>
                <p>{profesores.length > 0 ? profesores[0].nombreProfesor : "Seleccionar profesor"}</p>
            </div>

            {/* Botón de búsqueda con validación */}
            <button onClick={handleSubmit} disabled={!isFormValid}>Buscar Encuestas</button>

            {/* Botón Principal para regresar al home */}
            <button onClick={goToHome}>Botón Principal</button>
        </div>
    );
};

export default SurveyResponse;
