package org.cash.surveysuam.controller;

import org.cash.surveysuam.dto.SurveyDTO;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surv/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDTO> createSurvey(@RequestBody SurveyDTO surveyDTO) {
        Survey survey = new Survey();
        survey.setTitle(surveyDTO.getTitle());
        survey.setDescription(surveyDTO.getDescription());
        survey.setFaculty(surveyDTO.getFaculty());

        Survey savedSurvey = surveyService.createSurvey(survey);
        surveyDTO.setId(savedSurvey.getId());
        return ResponseEntity.ok(surveyDTO);
    }

    @GetMapping
    public ResponseEntity<List<SurveyDTO>> getAllSurveys() {
        List<Survey> surveys = surveyService.getAllSurveys();
        List<SurveyDTO> surveyDTOs = surveys.stream().map(survey -> {
            SurveyDTO dto = new SurveyDTO();
            dto.setId(survey.getId());
            dto.setTitle(survey.getTitle());
            dto.setDescription(survey.getDescription());
            dto.setFaculty(survey.getFaculty());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(surveyDTOs);
    }

    @GetMapping("/faculty/{faculty}")
    public ResponseEntity<List<SurveyDTO>> getSurveysByFaculty(@PathVariable String faculty) {
        List<Survey> surveys = surveyService.getSurveysByFaculty(faculty);
        List<SurveyDTO> surveyDTOs = surveys.stream().map(survey -> {
            SurveyDTO dto = new SurveyDTO();
            dto.setId(survey.getId());
            dto.setTitle(survey.getTitle());
            dto.setDescription(survey.getDescription());
            dto.setFaculty(survey.getFaculty());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(surveyDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyDTO> getSurveyById(@PathVariable UUID id) {
        Survey survey = surveyService.getSurveyById(id);
        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setId(survey.getId());
        surveyDTO.setTitle(survey.getTitle());
        surveyDTO.setDescription(survey.getDescription());
        surveyDTO.setFaculty(survey.getFaculty());
        return ResponseEntity.ok(surveyDTO);
    }

}
