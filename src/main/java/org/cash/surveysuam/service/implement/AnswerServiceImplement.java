package org.cash.surveysuam.service.implement;

import org.cash.surveysuam.model.database.Respondido;
import org.cash.surveysuam.model.database.RespondidoId;
import org.cash.surveysuam.model.survey.*;
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
    private ResponseContextRepository responseContextRepository;

    @Autowired
    private RespondidoRepository respondidoRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public void saveAnswer(List<Answer> answers, Survey survey, String cif, int facultadId, int carreraId, int grupo, String claseId, String profesorId) {

        RespondidoId respondidoId = new RespondidoId(cif, claseId, grupo);

        // Verificar si ya existe una respuesta para esa persona
        boolean exists = respondidoRepository.existsById(respondidoId);
        if (exists) {
            // Lanzar una excepción si ya existe una respuesta para esa persona
            throw new IllegalStateException("No se permite más de una respuesta para la misma persona en esta encuesta.");
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

        // Crear la llave compuesta RespondidoId

        // Crear la entidad Respondido y asignar la llave compuesta
        Respondido respondido = new Respondido();
        respondido.setIdRespondido(respondidoId); // Asignar la llave compuesta al objeto respondido
        respondido.setRespondido(true);
        respondido.setFacultadId(facultadId);
        respondido.setCarreraId(carreraId);
        respondido.setProfesorId(profesorId);

        // Save Respondido
        respondidoRepository.save(respondido);
    }

    @Override
    public List<Answer> getAnswersForSurvey(UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new RuntimeException("Survey not found"));
        return answerRepository.findBySurvey(survey);
    }

    public void saveAnswerWithContext(List<Answer> answers, Survey survey, String cif, int idFacultad, int idCarrera, int grupo, String idClase, String idProfesor) {
        // Crear la llave compuesta RespondidoId
        RespondidoId respondidoId = new RespondidoId(cif, idClase, grupo);

        // Verificar si ya existe una respuesta para esa persona
        boolean exists = respondidoRepository.existsById(respondidoId);
        if (exists) {
            // Lanzar una excepción si ya existe una respuesta para esa persona
            throw new IllegalStateException("No se permite más de una respuesta para la misma persona en esta encuesta.");
        }

        // Assign selected options to each answer
        for (Answer answer : answers) {
            List<Long> selectedOptionIds = answer.getSelectedOptionIds(); // Assuming you have a method to get selected option IDs
            if (selectedOptionIds != null && !selectedOptionIds.isEmpty()) {
                List<Option> selectedOptions = optionRepository.findAllById(selectedOptionIds);
                answer.setSelectedOptions(selectedOptions);
            }
        }

        // Save ResponseContext
        ResponseContext responseContext = new ResponseContext();
        responseContext.setIdFacultad(idFacultad);
        responseContext.setIdCarrera(idCarrera);
        responseContext.setGrupo(grupo);
        responseContext.setIdAsignatura(idClase);
        responseContext.setIdProfesor(idProfesor);
        responseContext.setAnswers(answers);

        responseContextRepository.save(responseContext);

        // Save Answers
        for (Answer answer : answers) {
            answer.setResponseContext(responseContext);
        }
        answerRepository.saveAll(answers);


        // Crear la entidad Respondido y asignar la llave compuesta
        Respondido respondido = new Respondido();
        respondido.setIdRespondido(respondidoId); // Asignar la llave compuesta al objeto respondido
        respondido.setRespondido(true);
        respondido.setFacultadId(idFacultad);
        respondido.setCarreraId(idCarrera);
        respondido.setProfesorId(idProfesor);

        // Save Respondido
        respondidoRepository.save(respondido);
    }
}