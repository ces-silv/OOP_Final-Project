package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findBySurvey(Survey survey);
}
