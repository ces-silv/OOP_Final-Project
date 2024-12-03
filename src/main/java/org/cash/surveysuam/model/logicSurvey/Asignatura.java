package org.cash.surveysuam.model.logicSurvey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity @Data
@Table(name = "Asignatura", uniqueConstraints = @UniqueConstraint(columnNames = {"idAsignatura", "idGrupo"}))
@Getter @Setter
public class Asignatura {

    @EmbeddedId
    private AsignaturaId idAsignatura;

    private String nombre;
    private String idCarrera;

    @ManyToOne
    @JoinColumn(name = "id_facultad", referencedColumnName = "idFacultad")
    private Facultad facultad;

    @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore
    @JoinColumn(name = "id_profesor", referencedColumnName = "IdProfesor")
    private Profesor profesor;

    public String getIdAsignatura() {
        return idAsignatura.getIdAsignatura();
    }

    public int getGrupo() {
        return idAsignatura.getIdGrupo();
    }


}
