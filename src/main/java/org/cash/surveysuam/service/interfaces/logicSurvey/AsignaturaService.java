package org.cash.surveysuam.service.interfaces.logicSurvey;

import org.cash.surveysuam.model.logicsurvey.Asignatura;
import org.cash.surveysuam.model.logicsurvey.Profesor;

import java.util.List;

public interface AsignaturaService {
    List<Asignatura> getAsignaturasByIdAsignatura(String idAsignatura);
    List<Asignatura> findByCarreraAndFacultad(String idCarrera, int idFacultad);
    Profesor getProfesorByAsignaturaIdAndGrupo(String idAsignatura, int idGrupo);
}
