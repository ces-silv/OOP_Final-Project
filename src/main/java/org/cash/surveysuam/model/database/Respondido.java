package org.cash.surveysuam.model.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "Respondido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Respondido {
    @Id
    private String IdRespondido;
    private boolean Respondido;
    private int FacultadId;
    private int CarreraId;
    private String ClaseId;
    private int Grupo;
    private String ProfesorId;
}
