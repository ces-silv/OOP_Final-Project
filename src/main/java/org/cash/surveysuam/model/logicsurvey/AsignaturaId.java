package org.cash.surveysuam.model.logicsurvey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable @Data
public class AsignaturaId implements Serializable {
    // "Serializable" allows us to be sure that the object can
    // be serialized (stored in the database for example) or deserialized
    @Column(name = "idAsignatura")
    private String idAsignatura;
    @Column(name = "idGrupo")
    private int idGrupo;

}
