package org.cash.surveysuam.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class AnswerRequestDTO {
    private List<AnswerDTO> answers;
    private UUID surveyId;
    private String cif;
    private int idFacultad;
    private int idCarrera;
    private int grupo;
    private String idClase;
    private String idProfesor;
}
