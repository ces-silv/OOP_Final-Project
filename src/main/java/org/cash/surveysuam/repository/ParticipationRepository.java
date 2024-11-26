package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.survey.Participation;
import org.cash.surveysuam.model.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
    boolean existsBySurveyAndParticipationToken(Survey survey, String participationToken);
}
