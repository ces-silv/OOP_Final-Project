package org.cash.surveysuam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @EqualsAndHashCode
@Getter @Setter
@AllArgsConstructor
public class StudentData {

    @JsonProperty("cif")
    private int CIF;

    @JsonProperty("nombres")
    private String firstName;

    @JsonProperty("apellidos")
    private String lastName;

    @JsonProperty("tipo")
    private String type;

    @JsonProperty("correo")
    private String email;

    @JsonProperty("sexo")
    private String gender;

    //@JsonProperty("telefono")
    //private String phone;

    @JsonProperty("carrera")
    private String major;

    @JsonProperty("facultad")
    private String faculty;
}
