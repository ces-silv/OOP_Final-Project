package org.cash.surveysuam.controller.logicSurvey;

import org.cash.surveysuam.model.logicsurvey.Carrera;
import org.cash.surveysuam.service.interfaces.logicSurvey.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<Carrera>> getCarrerasByIdFacultad(@RequestParam int idFacultad) {
        List<Carrera> carreras = carreraService.getCarrerasByIdFacultad(idFacultad);
        return ResponseEntity.ok(carreras);
    }
}