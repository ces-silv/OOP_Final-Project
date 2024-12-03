package org.cash.surveysuam.service.interfaces;

import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Survey;

import java.util.List;
import java.util.UUID;

public interface AnswerService {

    void saveAnswer(List<Answer> answers, String participationToken, Survey survey, String cif, int facultadId, int carreraId, int grupo, String claseId, int profesorId);

    List<Answer> getAnswersForSurvey(UUID surveyId);

}
