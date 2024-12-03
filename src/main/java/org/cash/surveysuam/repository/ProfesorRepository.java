package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicSurvey.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, String> {

    //List<Profesor> findByAsignaturas_IdAsignatura_IdAsignaturaAndAsignaturas_Grupo(String idAsignatura, int grupo);

    //List<Profesor> findByAsignaturas_IdAsignatura_IdAsignatura(String idAsignatura);
    // Buscar por idAsignatura y grupo
    // Buscar por idAsignatura y grupo
    List<Profesor> findByAsignaturas_IdAsignatura_IdAsignaturaAndAsignaturas_IdAsignatura_IdGrupo(String idAsignatura, int grupo);

    // Buscar solo por idAsignatura
    List<Profesor> findByAsignaturas_IdAsignatura_IdAsignatura(String idAsignatura);
}
