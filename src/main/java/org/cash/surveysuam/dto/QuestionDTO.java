package org.cash.surveysuam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class QuestionDTO {

    private Long id;
    private String text;
    private String type;
    private boolean required;
    private List<OptionDTO> options;

}
