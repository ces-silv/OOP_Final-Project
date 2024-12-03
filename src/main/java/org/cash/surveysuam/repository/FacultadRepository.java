package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicSurvey.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {
}