package org.cash.surveysuam.model.logicSurvey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity @Data // Data = Getters, Setters, toString, equalsTo and hashCode
@Table(name = "Asignatura", uniqueConstraints = @UniqueConstraint(columnNames = {"idAsignatura", "idGrupo"})) //idAsignatura and idGrupo are fields from "AsignaturaId" (represented with the EmbeddedId ) both must be Unique
@Getter @Setter
public class Asignatura {

    @EmbeddedId
    private AsignaturaId idAsignatura;

    private String nombre;
    private String idCarrera;

    @ManyToOne // Relationship ManyToOne with "Facultad"
    @JoinColumn(name = "id_facultad", referencedColumnName = "idFacultad")
    private Facultad facultad;

    // Relationship ManyToOne with "Profesor"
    // FetchType.LAZY - Will be loaded only when accessed
    // JsonIgnore - Used to prevent Serialization of this field | If not will return like a thousand times the same thing
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
