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
    private Question question; // Question Answered

    @ManyToOne @JoinColumn(name = "survey_id")
    private Survey survey; // Survey Associated

    // @JoinTable we define the table (that will handle the ManyToMany Relationship) and the
    // columns that connects the entities Answer and Option.
    // CascadeType.PERSIST and CascadeType.MERGE - When Answer Execute a Persist or Merge, the list of Option will also do it
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "answer_options",
            joinColumns = @JoinColumn(name = "answer_id"), // Define la columna en la tabla, hace referencia a la clave primaria de la entidad propietaria de la relación (Answer).
            inverseJoinColumns = @JoinColumn(name = "option_id") // Define la columna en la tabla, hace referencia a la clave primaria de la entidad inversa de la relación (Option).
    )
    private List<Option> selectedOptions = new ArrayList<>(); // Different selected options could be posible, for example in a CHECKBOX

    private String responseText; // If the Answer is text we store it here

    @ManyToOne @JoinColumn(name = "response_context_id")
    private ResponseContext responseContext;

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