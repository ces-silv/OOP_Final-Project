package org.cash.surveysuam.service.implement.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Carrera;
import org.cash.surveysuam.repository.CarreraRepository;
import org.cash.surveysuam.service.interfaces.logicSurvey.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> getCarrerasByIdFacultad(int idFacultad) {
        return carreraRepository.findByIdFacultad(idFacultad);
    }
}