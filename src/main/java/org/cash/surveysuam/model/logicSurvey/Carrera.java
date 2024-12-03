package org.cash.surveysuam.model.logicSurvey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Carrera")
@Getter @Setter
public class Carrera {

    @Id
    private int IdCarrera;
    private String NombreCarrera;
    private int idFacultad;

}
