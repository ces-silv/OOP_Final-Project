package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Option {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "question_id")
    private Question question; // To know which Question this option belongs to

    private String text; // Text for the option, for example:  "Yes", "No", "Maybe"

}
