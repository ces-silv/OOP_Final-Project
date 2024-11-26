package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "survey_id")
    private Survey survey; // a quien le pertenece esta pregunta, a cual survey

    private String text; // texto de la pregunta directamente

    @Enumerated(EnumType.STRING)
    private QuestionType type; //tipo de pregunta

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>(); // las distintas opciones que puede tener una pregunta

    private boolean required; // saber si es requerida la respuesta o no

}
