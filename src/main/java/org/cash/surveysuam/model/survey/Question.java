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
    private Survey survey; // DEPRECATED ! - Who this Survey belong to?

    private String text; // The text of the Questions (the question itself hahaha)

    @Enumerated(EnumType.STRING)
    private QuestionType type; //The Type of Question (Super Hyper Mega Important for the Front-End :D)

    // CascadeType.ALL - Operations like persist or remove executed at the Question will also have effect at the List of Questions
    // So, if we Delete the Question the List of Options will also be Deleted
    // orphanRemoval - If the Question is deleted from the list, will also be deleted from the database
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>(); // A question could have different options so he store them here

    private boolean required; // Is the Question required or not? Let the front-end developer knows :)

}
