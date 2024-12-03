package org.cash.surveysuam.model.logicSurvey;

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
    private List<Asignatura> asignaturas;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "id_asignatura", referencedColumnName = "idAsignatura"),
//            @JoinColumn(name =  "grupo", referencedColumnName = "grupo")
//    })
//    private Asignatura asignatura;




}
