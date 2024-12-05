package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicsurvey.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {
}