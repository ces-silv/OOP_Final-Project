package org.cash.surveysuam.service.implement.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Profesor;
import org.cash.surveysuam.repository.ProfesorRepository;
import org.cash.surveysuam.service.interfaces.logicSurvey.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> getProfesoresByAsignaturaIdAndGrupo(String asignaturaId, int grupo) {
        return profesorRepository.findByAsignaturas_IdAsignatura_IdAsignaturaAndAsignaturas_IdAsignatura_IdGrupo(asignaturaId, grupo);
    }

    @Override
    public List<Profesor> getProfesoresByAsignaturaId(String asignaturaId) {
        return profesorRepository.findByAsignaturas_IdAsignatura_IdAsignatura(asignaturaId);
    }
}
