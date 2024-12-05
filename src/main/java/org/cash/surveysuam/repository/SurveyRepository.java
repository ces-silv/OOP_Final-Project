package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.survey.Survey;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    @EntityGraph(attributePaths = {"questions", "questions.options"})
    // Especifica que relaciones deben ser cargadas inmediatamente junto con la entidad Survey
    Optional<Survey> findById(UUID id);

    List<Survey> findByFaculty(String faculty);
}
