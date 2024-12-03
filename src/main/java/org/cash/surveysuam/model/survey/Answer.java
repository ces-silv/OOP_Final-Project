package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "question_id")
    private Question question; // Question answered

    @ManyToOne @JoinColumn(name = "survey_id")
    private Survey survey; // survey associated

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "answer_options",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> selectedOptions = new ArrayList<>(); // Opciones seleccionadas (varias posibles para CHECKBOX)

    private String responseText;    // text

    private Long userId;

    // Getters y Setters
    public List<Long> getSelectedOptionIds() {
        return selectedOptions.stream().map(Option::getId).collect(Collectors.toList());
    }

    public void setSelectedOptionIds(List<Long> selectedOptionIds) {
        this.selectedOptions = selectedOptionIds.stream().map(id -> {
            Option option = new Option();
            option.setId(id);
            return option;
        }).collect(Collectors.toList());
    }
}