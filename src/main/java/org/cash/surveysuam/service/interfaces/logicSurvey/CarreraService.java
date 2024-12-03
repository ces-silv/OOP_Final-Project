package org.cash.surveysuam.service.interfaces.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Carrera;

import java.util.List;

public interface CarreraService {
    List<Carrera> getCarrerasByIdFacultad(int facultadId);
}
