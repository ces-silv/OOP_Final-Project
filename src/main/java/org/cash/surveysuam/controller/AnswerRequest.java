package org.cash.surveysuam.controller;

import lombok.Getter;
import lombok.Setter;
import org.cash.surveysuam.model.survey.Answer;
import org.cash.surveysuam.model.survey.Survey;

import java.util.List;

@Getter @Setter
public class AnswerRequest {
    private List<Answer> answers;
    private String participationToken;
    private Survey survey;

    private String cif;
    private int facultyId;
    private int carreraId;
    private int grupo;
    private String claseId;
    private int profesorId;

}