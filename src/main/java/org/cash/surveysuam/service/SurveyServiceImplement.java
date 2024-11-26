package org.cash.surveysuam.service;

import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Survey getSurveyById(UUID id) {
        Optional<Survey> survey = surveyRepository.findById(id);
        return survey.orElseThrow(() -> new RuntimeException("Survey not found"));
    }
}
