package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicsurvey.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {

    List<Profesor> findByAsignaturas_IdAsignatura_IdAsignaturaAndAsignaturas_IdAsignatura_IdGrupo(String idAsignatura, int grupo);

    List<Profesor> findByAsignaturas_IdAsignatura_IdAsignatura(String idAsignatura);
}
