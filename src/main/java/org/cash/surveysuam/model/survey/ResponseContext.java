package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class ResponseContext {

    @Id
    @GeneratedValue
    private UUID id;

    private int idFacultad;
    private int idCarrera;
    private String idProfesor;
    private String idAsignatura;
    private int grupo;

//    @OneToMany(mappedBy = "responseContext", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "responseContext", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

}
