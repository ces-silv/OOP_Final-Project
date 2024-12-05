package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Option;
import org.cash.surveysuam.model.survey.Question;
import org.cash.surveysuam.model.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findBySurvey(Survey survey);

    @Modifying // Update or Delete must have @Modifying
    @Query("DELETE FROM Answer a WHERE :option MEMBER OF a.selectedOptions")
    void deleteAllBySelectedOptionsContaining(@Param("option") Option option);

    @Modifying
    @Query("DELETE FROM Answer a WHERE a.question = :question")
    void deleteAllByQuestion(@Param("question") Question question);
}
