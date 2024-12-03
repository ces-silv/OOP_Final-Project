package org.cash.surveysuam.service.implement.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Facultad;
import org.cash.surveysuam.repository.FacultadRepository;
import org.cash.surveysuam.service.interfaces.logicSurvey.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultadServiceImpl implements FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    @Override
    public List<Facultad> getFacultades() {
        return facultadRepository.findAll();
    }
}
