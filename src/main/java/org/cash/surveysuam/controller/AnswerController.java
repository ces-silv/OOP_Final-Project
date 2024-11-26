package org.cash.surveysuam.controller;

import org.cash.surveysuam.dto.AnswerDTO;
import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Option;
import org.cash.surveysuam.model.survey.Question;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.service.interfaces.AnswerService;
import org.cash.surveysuam.service.interfaces.OptionService;
import org.cash.surveysuam.service.interfaces.QuestionService;
import org.cash.surveysuam.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController @RequestMapping("/surv/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @PostMapping("/{surveyId}")
    public ResponseEntity<Void> submitAnswers(
            @PathVariable UUID surveyId,
            @RequestHeader("Participation-Token") String participationToken,
            @RequestBody List<AnswerDTO> answersDTO
    ) {
        Survey survey = surveyService.getSurveyById(surveyId);

        List<Answer> answers = answersDTO.stream().map(dto -> {
            Answer answer = new Answer();
            answer.setSurvey(survey);

            // Obtener la pregunta
            Question question = questionService.getQuestionById(dto.getQuestionId());
            answer.setQuestion(question);

            // Asignar opciones seleccionadas
            if (dto.getSelectedAnswerIds() != null && !dto.getSelectedAnswerIds().isEmpty()) {
                List<Option> selectedOptions = optionService.getOptionsById(dto.getSelectedAnswerIds());
                answer.setSelectedOptions(selectedOptions);
            }

            // Asignar texto de respuesta
            answer.setResponseText(dto.getResponse());

            return answer;
        }).collect(Collectors.toList());

        // Guardar las respuestas
        answerService.saveAnswer(answers, participationToken, survey);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/{surveyId}")
    public ResponseEntity<List<AnswerDTO>> getAnswers(@PathVariable UUID surveyId) {
        List<Answer> answers = answerService.getAnswersForSurvey(surveyId);
        List<AnswerDTO> answerDTOs = answers.stream().map(answer -> {
            AnswerDTO dto = new AnswerDTO();
            dto.setResponse(answer.getResponseText());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(answerDTOs);
    }

}
