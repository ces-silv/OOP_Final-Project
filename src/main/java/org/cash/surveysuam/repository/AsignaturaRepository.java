package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicSurvey.Asignatura;
import org.cash.surveysuam.model.logicSurvey.AsignaturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, AsignaturaId> {

    @Query("SELECT a FROM Asignatura a WHERE a.idAsignatura.idAsignatura = :idAsignatura")
    List<Asignatura> findByIdAsignatura(@Param("idAsignatura") String idAsignatura);
}