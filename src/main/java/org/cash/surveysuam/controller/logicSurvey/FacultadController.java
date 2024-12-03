package org.cash.surveysuam.controller.logicSurvey;

import org.cash.surveysuam.model.logicSurvey.Facultad;

import org.cash.surveysuam.service.interfaces.logicSurvey.FacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facultades")
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    @GetMapping
    public ResponseEntity<List<Facultad>> getFacultades() {
        List<Facultad> facultades = facultadService.getFacultades();
        return ResponseEntity.ok(facultades);
    }
}