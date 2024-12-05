package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicsurvey.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    List<Carrera> findByIdFacultad(int idFacultad);
}