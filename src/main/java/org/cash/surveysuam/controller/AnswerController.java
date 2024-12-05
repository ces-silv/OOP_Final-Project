package org.cash.surveysuam.controller;

import org.cash.surveysuam.dto.AnswerRequestDTO;
import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Question;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.SurveyRepository;
import org.cash.surveysuam.service.interfaces.AnswerService;
import org.cash.surveysuam.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SurveyRepository surveyRepository;

    @PostMapping("/submit")
    public ResponseEntity<Void> saveAnswer(@RequestBody AnswerRequestDTO answerRequestDTO) {
        Survey survey = surveyRepository.findById(answerRequestDTO.getSurveyId())
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        List<Answer> answers = answerRequestDTO.getAnswers().stream()
                .map(answerDTO -> {
                    Answer answer = new Answer();

                    // Crear una nueva instancia de Question y establecer su ID
                    Question question = new Question();
                    question.setId(answerDTO.getQuestionId());
                    answer.setQuestion(question);

                    // Inicializar selectedOptionIds como una lista vacía si es null
                    if (answerDTO.getSelectedOptionIds() == null) {
                        answer.setSelectedOptionIds(new ArrayList<>());
                    } else {
                        answer.setSelectedOptionIds(answerDTO.getSelectedOptionIds());
                    }

                    answer.setResponseText(answerDTO.getResponseText());
                    return answer;
                })
                .collect(Collectors.toList());

        answerService.saveAnswerWithContext(answers, survey, answerRequestDTO.getCif(),
                answerRequestDTO.getIdFacultad(), answerRequestDTO.getIdCarrera(),
                answerRequestDTO.getGrupo(), answerRequestDTO.getIdClase(), answerRequestDTO.getIdProfesor());

        return ResponseEntity.ok().build();
    }



    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<List<Answer>> getAnswersForSurvey(@PathVariable UUID surveyId) {
        List<Answer> answers = answerService.getAnswersForSurvey(surveyId);
        return ResponseEntity.ok(answers);
    }
}