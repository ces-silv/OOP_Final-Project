package org.cash.surveysuam.service.implement;

import org.cash.surveysuam.model.database.Respondido;
import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Option;
import org.cash.surveysuam.model.survey.Participation;
import org.cash.surveysuam.model.survey.Survey;
import org.cash.surveysuam.repository.*;
import org.cash.surveysuam.service.interfaces.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImplement implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RespondidoRepository respondidoRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public void saveAnswer(List<Answer> answers, String participationToken, Survey survey, String cif, int facultadId, int carreraId, int grupo, String claseId, int profesorId) {

        // First a validation, no multiple answers
        if(participationRepository.existsBySurveyAndParticipationToken(survey, participationToken)) {
            throw new RuntimeException("You already answered this survey");
        }

        // Assign selected options to each answer
        for (Answer answer : answers) {
            List<Long> selectedOptionIds = answer.getSelectedOptionIds(); // Assuming you have a method to get selected option IDs
            if (selectedOptionIds != null && !selectedOptionIds.isEmpty()) {
                List<Option> selectedOptions = optionRepository.findAllById(selectedOptionIds);
                answer.setSelectedOptions(selectedOptions);
            }
        }

        // Save Answers
        answerRepository.saveAll(answers);

        Respondido respondido = new Respondido();
        respondido.setIdRespondido(cif);
        respondido.setRespondido(true);
        respondido.setFacultadId(facultadId); //
        respondido.setCarreraId(carreraId);
        respondido.setGrupo(grupo);
        respondido.setClaseId(claseId);
        respondido.setProfesorId(profesorId);

        // Save Participation
        Participation participation = new Participation();
        participation.setSurvey(survey);
        participation.setParticipationToken(participationToken);
        participationRepository.save(participation);
    }

    @Override
    public List<Answer> getAnswersForSurvey(UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found"));
        return answerRepository.findBySurvey(survey);
    }
}