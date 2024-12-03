package org.cash.surveysuam.model.logicSurvey;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "profesor") //@JsonIgnore
    @JsonIgnore  // Evita la recursión al serializar Profesor -> Asignatura
    private List<Asignatura> asignaturas;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "id_asignatura", referencedColumnName = "idAsignatura"),
//            @JoinColumn(name =  "grupo", referencedColumnName = "grupo")
//    })
//    private Asignatura asignatura;




}
