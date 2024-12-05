package org.cash.surveysuam.service.implement;

import jakarta.transaction.Transactional;
import org.cash.surveysuam.model.survey.Option;
import org.cash.surveysuam.model.survey.Question;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.AnswerRepository;
import org.cash.surveysuam.repository.SurveyRepository;
import org.cash.surveysuam.service.interfaces.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SurveyServiceImplement implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public List<Survey> getSurveysByFaculty(String faculty) {
        return surveyRepository.findByFaculty(faculty);
    }

    @Override
    @Transactional
    public Survey getSurveyById(UUID id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        // Forzar la carga de preguntas y opciones
        survey.getQuestions().size();
        survey.getQuestions().forEach(q -> q.getOptions().size());

        return survey;
    }

    @Override
    @Transactional
    public Survey updateSurvey(UUID id, Survey updatedSurvey) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        // Eliminar las respuestas que referencian las preguntas de la encuesta
        existingSurvey.getQuestions().forEach(question -> {
            answerRepository.deleteAllByQuestion(question);
        });

        // Eliminar las respuestas que referencian las opciones de la encuesta
        existingSurvey.getQuestions().forEach(question -> {
            question.getOptions().forEach(option -> {
                answerRepository.deleteAllBySelectedOptionsContaining(option);
            });
        });

        // Actualizar los campos de la encuesta existente
        existingSurvey.setTitle(updatedSurvey.getTitle());
        existingSurvey.setDescription(updatedSurvey.getDescription());
        existingSurvey.setFaculty(updatedSurvey.getFaculty());

        // Actualizar las preguntas y opciones
        updateQuestions(existingSurvey, updatedSurvey.getQuestions());

        return surveyRepository.save(existingSurvey);
    }

    private void updateQuestions(Survey existingSurvey, List<Question> updatedQuestions) {
        List<Question> existingQuestions = new ArrayList<>(existingSurvey.getQuestions());
        existingSurvey.getQuestions().clear();

        for (Question updatedQuestion : updatedQuestions) {
            Question existingQuestion = existingQuestions.stream()
                    .filter(q -> q.getId() != null && q.getId().equals(updatedQuestion.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingQuestion != null) {
                // Actualizar la pregunta existente
                existingQuestion.setText(updatedQuestion.getText());
                existingQuestion.setType(updatedQuestion.getType());
                existingQuestion.setRequired(updatedQuestion.isRequired());
                updateOptions(existingQuestion, updatedQuestion.getOptions());
                existingSurvey.getQuestions().add(existingQuestion);
            } else {
                // Crear una nueva pregunta
                Question newQuestion = new Question();
                newQuestion.setText(updatedQuestion.getText());
                newQuestion.setType(updatedQuestion.getType());
                newQuestion.setRequired(updatedQuestion.isRequired());
                newQuestion.setSurvey(existingSurvey);
                updateOptions(newQuestion, updatedQuestion.getOptions());
                existingSurvey.getQuestions().add(newQuestion);
            }
        }
    }

    private void updateOptions(Question existingQuestion, List<Option> updatedOptions) {
        List<Option> existingOptions = new ArrayList<>(existingQuestion.getOptions());
        existingQuestion.getOptions().clear();

        for (Option updatedOption : updatedOptions) {
            Option existingOption = existingOptions.stream()
                    .filter(o -> o.getId() != null && o.getId().equals(updatedOption.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingOption != null) {
                // Actualizar la opción existente
                existingOption.setText(updatedOption.getText());
                existingQuestion.getOptions().add(existingOption);
            } else {
                // Crear una nueva opción
                Option newOption = new Option();
                newOption.setText(updatedOption.getText());
                newOption.setQuestion(existingQuestion);
                existingQuestion.getOptions().add(newOption);
            }
        }
    }

    @Override
    @Transactional
    public void deleteSurvey(UUID id) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        // Eliminar las respuestas que referencian las preguntas de la encuesta
        existingSurvey.getQuestions().forEach(question -> {
            answerRepository.deleteAllByQuestion(question);
        });

        // Eliminar las respuestas que referencian las opciones de la encuesta
        existingSurvey.getQuestions().forEach(question -> {
            question.getOptions().forEach(option -> {
                answerRepository.deleteAllBySelectedOptionsContaining(option);
            });
        });

        surveyRepository.delete(existingSurvey);
    }

}
