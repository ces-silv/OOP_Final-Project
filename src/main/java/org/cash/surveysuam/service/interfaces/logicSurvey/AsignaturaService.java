package org.cash.surveysuam.service.interfaces.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Asignatura;
import org.cash.surveysuam.model.logicSurvey.Profesor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaService {
    List<Asignatura> getAsignaturasByIdAsignatura(String idAsignatura);
    List<Asignatura> findByCarreraAndFacultad(String idCarrera, int idFacultad);
    Profesor getProfesorByAsignaturaIdAndGrupo(String idAsignatura, int idGrupo);
}
