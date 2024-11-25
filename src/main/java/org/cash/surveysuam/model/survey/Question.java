package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "survey_id")
    private Survey survey;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    private boolean required;

}
