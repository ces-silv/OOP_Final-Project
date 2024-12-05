package org.cash.surveysuam.model.logicSurvey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Facultad")
@Getter @Setter
public class Facultad {

    @Id
    private int IdFacultad;
    private String NombreFacultad;

 }