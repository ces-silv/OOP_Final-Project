package org.cash.surveysuam.service.implement.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Asignatura;
import org.cash.surveysuam.repository.AsignaturaRepository;
import org.cash.surveysuam.service.interfaces.logicSurvey.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Override
    public List<Asignatura> getAsignaturasByIdCarrera(int idCarrera) {
        return asignaturaRepository.findByIdCarrera(idCarrera);
    }
}