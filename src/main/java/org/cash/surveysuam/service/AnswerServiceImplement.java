package org.cash.surveysuam.service;

import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Participation;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.AnswerRepository;
import org.cash.surveysuam.repository.ParticipationRepository;
import org.cash.surveysuam.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerServiceImplement implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public void saveAnswer(List<Answer> answers, String participationToken, Survey survey) {

        // First a validation, no multiple answers
        if(participationRepository.existsBySurveyAndParticipationToken(survey, participationToken)) {
            throw new RuntimeException("You already answered this survey");
        }

        // Save Answers
        answerRepository.saveAll(answers);

        // Save Participation
        Participation participation = new Participation();
        participation.setSurvey(survey);
        participation.setParticipationToken(participationToken);
        participationRepository.save(participation);

    }

    @Override
    public List<Answer> getAnswersForSurvey(UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found"));
        return answerRepository.findBySurvey(survey);
    }
}
