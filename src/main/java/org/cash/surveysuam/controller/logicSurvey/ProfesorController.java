package org.cash.surveysuam.controller.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Profesor;
import org.cash.surveysuam.service.interfaces.logicSurvey.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/asignatura/{idAsignatura}/grupo/{grupo}")
    public List<Profesor> getProfesoresByAsignaturaAndGrupo(@PathVariable String idAsignatura, @PathVariable int grupo) {
        return profesorService.getProfesoresByAsignaturaIdAndGrupo(idAsignatura, grupo);
    }

    @GetMapping("/asignatura/{idAsignatura}")
    public List<Profesor> getProfesores(@PathVariable String idAsignatura) {
        return profesorService.getProfesoresByAsignaturaId(idAsignatura);
    }
}