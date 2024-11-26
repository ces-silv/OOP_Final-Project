package org.cash.surveysuam.service.implement;

import org.cash.surveysuam.model.survey.Question;
import org.cash.surveysuam.repository.QuestionRepository;
import org.cash.surveysuam.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImplement implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
    }

}
