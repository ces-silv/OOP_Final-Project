package org.cash.surveysuam.service.interfaces.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Asignatura;

import java.util.List;

public interface AsignaturaService {
    List<Asignatura> getAsignaturasByIdCarrera(int carreraId);
}
