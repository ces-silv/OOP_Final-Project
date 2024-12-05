package org.cash.surveysuam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AnswerDTO {
    private Long questionId;
    private List<Long> selectedOptionIds;
    private String responseText;
}