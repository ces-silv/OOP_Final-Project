package org.cash.surveysuam.controller;

import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.service.interfaces.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/submit")
    public ResponseEntity<Void> submitAnswers(@RequestBody AnswerRequest request) {
        List<Answer> answers = request.getAnswers();
        String participationToken = request.getParticipationToken();
        Survey survey = request.getSurvey();

        String cif = request.getCif();
        int facultadId = request.getFacultyId();
        int carreraId = request.getCarreraId();
        int grupo = request.getGrupo();
        String claseId = request.getClaseId();
        int profesorId = request.getProfesorId();

        answerService.saveAnswer(answers, participationToken, survey, cif, facultadId, carreraId, grupo, claseId, profesorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<List<Answer>> getAnswersForSurvey(@PathVariable UUID surveyId) {
        List<Answer> answers = answerService.getAnswersForSurvey(surveyId);
        return ResponseEntity.ok(answers);
    }
}