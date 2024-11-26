package org.cash.surveysuam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AnswerDTO {
    private Long questionId;
    private List<Long> selectedAnswerIds; // List of selected options
    private String response; // text if its needed
}
