package org.cash.surveysuam.service.interfaces.logicSurvey;

import org.cash.surveysuam.model.logicsurvey.Profesor;

import java.util.List;

public interface ProfesorService {
    List<Profesor> getProfesoresByAsignaturaIdAndGrupo(String asignaturaId, int grupo);
    List<Profesor> getProfesoresByAsignaturaId(String asignaturaId);
}
