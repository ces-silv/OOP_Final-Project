package org.cash.surveysuam.controller.logicSurvey;

import org.cash.surveysuam.model.logicsurvey.Asignatura;
import org.cash.surveysuam.model.logicsurvey.Profesor;
import org.cash.surveysuam.service.interfaces.logicSurvey.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<List<Asignatura>> getAsignaturasByIdCarrera(@RequestParam String idAsignatura) {
        List<Asignatura> asignaturas = asignaturaService.getAsignaturasByIdAsignatura(idAsignatura);
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Asignatura>> getAsignaturasByCarreraAndFacultad(
            @RequestParam String idCarrera,
            @RequestParam int idFacultad) {
        List<Asignatura> asignaturas = asignaturaService.findByCarreraAndFacultad(idCarrera, idFacultad);
        return ResponseEntity.ok(asignaturas);
    }

    @GetMapping("/profesor")
    public ResponseEntity<Profesor> getProfesorByAsignaturaAndGrupo(
            @RequestParam String idAsignatura,
            @RequestParam int grupo) {
        Profesor profesor = asignaturaService.getProfesorByAsignaturaIdAndGrupo(idAsignatura, grupo);
        return ResponseEntity.ok(profesor);
    }

}