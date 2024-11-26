package org.cash.surveysuam.service;

import org.cash.surveysuam.model.survey.Survey;

import java.util.List;
import java.util.UUID;

public interface SurveyService {

    Survey createSurvey(Survey survey);

    List<Survey> getAllSurveys();

    List<Survey> getSurveysByFaculty(String faculty);

    Survey getSurveyById(UUID id);

}
