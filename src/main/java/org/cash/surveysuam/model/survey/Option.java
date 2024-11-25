package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;

@Entity
public class Option {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "question_id")
    private Question question;

    private String text;

}
