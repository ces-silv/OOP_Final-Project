package org.cash.surveysuam.service.implement;

import org.cash.surveysuam.model.survey.Participation;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.ParticipationRepository;
import org.cash.surveysuam.repository.SurveyRepository;
import org.cash.surveysuam.service.interfaces.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParticipationServiceImplement implements ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public boolean hasParticipatedOrNot(UUID surveyId, String participationToken) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found"));
        return  participationRepository.existsBySurveyAndParticipationToken(survey, participationToken);
    }

    @Override
    public void registerParticipation(UUID surveyId, String participationToken) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found"));
        Participation participation = new Participation();
        participation.setSurvey(survey);
        participation.setParticipationToken(participationToken);
        participationRepository.save(participation);
    }
}
