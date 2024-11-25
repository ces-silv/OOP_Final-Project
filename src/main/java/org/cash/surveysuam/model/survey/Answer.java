package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne @JoinColumn(name = "option_id", nullable = true)
    private Option selectedOption;

    private String responseText;

    private Long userId;

}
