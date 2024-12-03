package org.cash.surveysuam.model.logicSurvey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable @Data
public class AsignaturaId implements Serializable {
    @Column(name = "idAsignatura")
    private String idAsignatura;
    @Column(name = "idGrupo")
    private int idGrupo;

}
