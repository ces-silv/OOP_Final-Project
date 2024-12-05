package org.cash.surveysuam.controller;

import org.cash.surveysuam.dto.OptionDTO;
import org.cash.surveysuam.dto.QuestionDTO;
import org.cash.surveysuam.dto.SurveyDTO;
import org.cash.surveysuam.model.survey.Option;
import org.cash.surveysuam.model.survey.Question;
import org.cash.surveysuam.model.survey.QuestionType;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        // Mapear las preguntas del DTO a la entidad Survey
        if (surveyDTO.getQuestions() != null) {
            List<Question> questions = surveyDTO.getQuestions().stream().map(questionDTO -> {
                Question question = new Question();
                question.setText(questionDTO.getText());
                question.setType(QuestionType.valueOf(questionDTO.getType()));
                question.setRequired(questionDTO.isRequired());
                question.setSurvey(survey); // Establecer la relación inversa

                // Mapear las opciones
                if (questionDTO.getOptions() != null) {
                    List<Option> options = questionDTO.getOptions().stream().map(optionDTO -> {
                        Option option = new Option();
                        option.setText(optionDTO.getText());
                        option.setQuestion(question); // Establecer la relación inversa
                        return option;
                    }).collect(Collectors.toList());
                    question.setOptions(options);
                }
                return question;
            }).collect(Collectors.toList());

            survey.setQuestions(questions);
        }

        // Guardar la encuesta con las preguntas y opciones
        Survey savedSurvey = surveyService.createSurvey(survey);

        // Mapear los IDs generados de vuelta al DTO (opcional)
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

            // Mapear preguntas
            if (survey.getQuestions() != null) {
                List<QuestionDTO> questionDTOs = survey.getQuestions().stream().map(question -> {
                    QuestionDTO questionDTO = new QuestionDTO();
                    questionDTO.setId(question.getId());
                    questionDTO.setText(question.getText());
                    questionDTO.setType(question.getType().name());
                    questionDTO.setRequired(question.isRequired());

                    // Mapear opciones
                    if (question.getOptions() != null) {
                        List<OptionDTO> optionDTOs = question.getOptions().stream().map(option -> {
                            OptionDTO optionDTO = new OptionDTO();
                            optionDTO.setId(option.getId());
                            optionDTO.setText(option.getText());
                            return optionDTO;
                        }).collect(Collectors.toList());
                        questionDTO.setOptions(optionDTOs);
                    }

                    return questionDTO;
                }).collect(Collectors.toList());
                dto.setQuestions(questionDTOs);
            }

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
        // Obtener la encuesta por su id
        Survey survey = surveyService.getSurveyById(id);

        // Si no se encuentra la encuesta, devolver un NotFound
        if (survey == null) {
            return ResponseEntity.notFound().build();
        }

        // Mapear la encuesta a SurveyDTO
        SurveyDTO dto = new SurveyDTO();
        dto.setId(survey.getId());
        dto.setTitle(survey.getTitle());
        dto.setDescription(survey.getDescription());
        dto.setFaculty(survey.getFaculty());

        // Mapear preguntas
        if (survey.getQuestions() != null) {
            List<QuestionDTO> questionDTOs = survey.getQuestions().stream().map(question -> {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setId(question.getId());
                questionDTO.setText(question.getText());
                questionDTO.setType(question.getType().name());
                questionDTO.setRequired(question.isRequired());

                // Mapear opciones
                if (question.getOptions() != null) {
                    List<OptionDTO> optionDTOs = question.getOptions().stream().map(option -> {
                        OptionDTO optionDTO = new OptionDTO();
                        optionDTO.setId(option.getId());
                        optionDTO.setText(option.getText());
                        return optionDTO;
                    }).collect(Collectors.toList());
                    questionDTO.setOptions(optionDTOs);
                }

                return questionDTO;
            }).collect(Collectors.toList());
            dto.setQuestions(questionDTOs);
        }

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SurveyDTO> updateSurvey(@PathVariable UUID id, @RequestBody SurveyDTO surveyDTO) {
        Survey survey = new Survey();
        survey.setId(id);
        survey.setTitle(surveyDTO.getTitle());
        survey.setDescription(surveyDTO.getDescription());
        survey.setFaculty(surveyDTO.getFaculty());

        // Mapear las preguntas del DTO a la entidad Survey
        if (surveyDTO.getQuestions() != null) {
            List<Question> questions = surveyDTO.getQuestions().stream().map(questionDTO -> {
                Question question = new Question();
                question.setId(questionDTO.getId());
                question.setText(questionDTO.getText());
                question.setType(QuestionType.valueOf(questionDTO.getType()));
                question.setRequired(questionDTO.isRequired());

                // Mapear las opciones
                if (questionDTO.getOptions() != null) {
                    List<Option> options = questionDTO.getOptions().stream().map(optionDTO -> {
                        Option option = new Option();
                        option.setId(optionDTO.getId());
                        option.setText(optionDTO.getText());
                        return option;
                    }).collect(Collectors.toList());
                    question.setOptions(options);
                }
                return question;
            }).collect(Collectors.toList());

            survey.setQuestions(questions);
        }

        // Actualizar la encuesta con las preguntas y opciones
        Survey updatedSurvey = surveyService.updateSurvey(id, survey);

        // Mapear los IDs generados de vuelta al DTO (opcional)
        surveyDTO.setId(updatedSurvey.getId());

        return ResponseEntity.ok(surveyDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable UUID id) {
        surveyService.deleteSurvey(id);
        return ResponseEntity.noContent().build();
    }


}
