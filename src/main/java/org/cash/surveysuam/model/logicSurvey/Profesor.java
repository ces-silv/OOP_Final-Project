package org.cash.surveysuam.model.logicSurvey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Profesor")
@Getter @Setter
public class Profesor {

    @Id
    private String IdProfesor;
    private String NombreProfesor;

    @OneToMany(mappedBy = "profesor")
    @JsonIgnore  // JsonIgnore - Used to prevent Serialization of this field | If not will return like a thousand times the same thing
    private List<Asignatura> asignaturas;

}
