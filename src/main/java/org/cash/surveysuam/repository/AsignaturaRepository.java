package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.logicsurvey.Asignatura;
import org.cash.surveysuam.model.logicsurvey.AsignaturaId;
import org.cash.surveysuam.model.logicsurvey.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, AsignaturaId> {

    @Query("SELECT a FROM Asignatura a WHERE a.idAsignatura.idAsignatura = :idAsignatura")
    List<Asignatura> findByIdAsignatura(@Param("idAsignatura") String idAsignatura);

    @Query("SELECT a FROM Asignatura a WHERE a.idCarrera = :idCarrera AND a.facultad.IdFacultad = :IdFacultad")
    List<Asignatura> findByCarreraAndFacultad(@Param("idCarrera") String idCarrera, @Param("IdFacultad") int idFacultad);

    @Query("SELECT a.profesor FROM Asignatura a WHERE a.idAsignatura.idAsignatura = :idAsignatura AND a.idAsignatura.idGrupo = :grupo")
    Profesor findProfesorByAsignaturaIdAndGrupo(@Param("idAsignatura") String idAsignatura, @Param("grupo") int grupo);

}