package org.cash.surveysuam.service.implement;

import jakarta.transaction.Transactional;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.SurveyRepository;
import org.cash.surveysuam.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SurveyServiceImplement implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public List<Survey> getSurveysByFaculty(String faculty) {
        return surveyRepository.findByFaculty(faculty);
    }

    @Override
    @Transactional
    public Survey getSurveyById(UUID id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        // Forzar la carga de preguntas y opciones
        survey.getQuestions().size();
        survey.getQuestions().forEach(q -> q.getOptions().size());

        return survey;
    }
}
