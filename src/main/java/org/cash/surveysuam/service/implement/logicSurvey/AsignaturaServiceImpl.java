package org.cash.surveysuam.service.implement.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Asignatura;
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

}