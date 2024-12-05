package org.cash.surveysuam.service.implement.logicSurvey;

import jakarta.transaction.Transactional;
import org.cash.surveysuam.model.logicSurvey.Asignatura;
import org.cash.surveysuam.model.logicSurvey.Profesor;
import org.cash.surveysuam.repository.AsignaturaRepository;
import org.cash.surveysuam.service.interfaces.logicSurvey.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public List<Asignatura> getAsignaturasByIdAsignatura(String idAsignatura) {
        return asignaturaRepository.findByIdAsignatura(idAsignatura);
    }

    @Override
    public List<Asignatura> findByCarreraAndFacultad(String idCarrera, int idFacultad) {
        return asignaturaRepository.findByCarreraAndFacultad(idCarrera, idFacultad);
    }

    @Override @Transactional
    public Profesor getProfesorByAsignaturaIdAndGrupo(String idAsignatura, int idGrupo) {
        return asignaturaRepository.findProfesorByAsignaturaIdAndGrupo(idAsignatura, idGrupo);
    }


}